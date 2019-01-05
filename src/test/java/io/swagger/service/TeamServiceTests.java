package io.swagger.service;

import io.swagger.api.AlreadyExistsException;
import io.swagger.api.NotFoundException;
import io.swagger.model.*;
import io.swagger.repository.TeamRepositoryStub;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TeamServiceTests {
    private TeamRepositoryStub teamRepositoryStub = new TeamRepositoryStub();
    private ITeamService teamService = new TeamService(teamRepositoryStub);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUpData() {
        this.teamRepositoryStub.setMembershipsTable(this.setUpMemberships());
        this.teamRepositoryStub.setTeamRoleTable(this.setUpTeamRoles());
        this.teamRepositoryStub.setTeamsTable(this.setUpTeams());
        this.teamRepositoryStub.setUsersTable(this.setUpUsers());
    }

    @Test
    public void testGetAllTeams() {
        List<Team> obtainedResult = teamService.getAllTeams();

        assertEquals(obtainedResult.size(), 3);
    }

    @Test
    public void testGetTeamByIDSuccessfully() throws NotFoundException {
        Team expectedTeam = new Team();
        expectedTeam.setId(1);
        expectedTeam.setName("Team1");
        expectedTeam.setLead(5);

        Team obtainedTeam = teamService.getTeamByID(1);

        assertEquals(obtainedTeam, expectedTeam);
    }

    @Test
    public void testGetTeamByIDUnsuccessfully() throws NotFoundException {
        exception.expect(NotFoundException.class);
        teamService.getTeamByID(10);
    }

    @Test
    public void getTeamMembersByRoleExistingTeam() throws NotFoundException {
        List<UserRole> obtainedList = teamService.getUsersByRole(3, "Developer");

        assertEquals(obtainedList.size(), 2);
    }

    @Test
    public void getTeamMembersByRoleNotExistingTeam() throws NotFoundException {
        exception.expect(NotFoundException.class);
        teamService.getUsersByRole(10, "Developer");
    }

    @Test
    public void getTeamMembersByRoleNotExistingRole() throws NotFoundException {
        List<UserRole> obtainedList = teamService.getUsersByRole(3, "Scrum Master");

        assertEquals(obtainedList.size(), 0);
    }

    @Test
    public void getTeamRoles() throws NotFoundException {
        TeamRolesDTO obtainedResult = teamService.teamRoles(1);

        assertEquals(obtainedResult.getRoles().size(), 4);
    }

    @Test
    public void getTeamRolesTeamNotFound() throws NotFoundException {
        exception.expect(NotFoundException.class);
        teamService.teamRoles(10);
    }

    @Test
    public void addRoleToTeamSuccessfully() throws NotFoundException, AlreadyExistsException {
        TeamRolesDTO oldTeamRoles = teamService.teamRoles(1);
        int oldTeamRolesSize = oldTeamRoles.getRoles().size();

        assertEquals(oldTeamRolesSize, 4);

        TeamRolesDTO obtainedResult = teamService.addRoleToTeam(1, "Scrum Master");

        assertEquals(obtainedResult.getRoles().size(), oldTeamRolesSize + 1);
    }

    @Test
    public void addRoleToTeamTeamNotFound() throws NotFoundException, AlreadyExistsException {
        exception.expect(NotFoundException.class);
        teamService.addRoleToTeam(10, "Scrum Master");
    }

    @Test
    public void addRoleToTeamAlreadyExists() throws NotFoundException, AlreadyExistsException {
        exception.expect(AlreadyExistsException.class);
        teamService.addRoleToTeam(1, "Developer");
    }

    @Test
    public void getUserRoleSuccessfully() throws NotFoundException {
        UserRole userRole = teamService.getUserRole(1, 5);

        assertEquals(userRole.getRole(), "Lead");
    }

    @Test
    public void getUserRoleUserNotExists() throws NotFoundException {
        exception.expect(NotFoundException.class);
        teamService.getUserRole(1, 10);
    }

    @Test
    public void getUserRoleTeamNotExists() throws NotFoundException {
        exception.expect(NotFoundException.class);
        teamService.getUserRole(10, 1);
    }

    @Test
    public void getUserRoleUserNotInTeam() throws NotFoundException {
        exception.expect(NotFoundException.class);
        teamService.getUserRole(1, 3);
    }

    @Test
    public void addTeamRoleToUserSuccessfully() throws NotFoundException {
        UserRole expectedResult = new UserRole();
        expectedResult.setUserId(1);
        expectedResult.setRole("Developer");

        UserRole oldRole = teamService.getUserRole(1, 1);
        assertEquals(oldRole.getRole(), "Scrum Master");

        UserRole obtainedResult = teamService.addTeamRoleToUser(1, 1, "Developer");
        assertEquals(obtainedResult, expectedResult);

        UserRole newRole = teamService.getUserRole(1, 1);
        assertEquals(newRole.getRole(), "Developer");
    }

    @Test
    public void addNewTeamRoleToUserSuccessfully() throws NotFoundException {
        UserRole expectedResult = new UserRole();
        expectedResult.setUserId(1);
        expectedResult.setRole("Scrum Master");

        TeamRolesDTO oldRoles = teamService.teamRoles(1);
        int oldRolesSize = oldRoles.getRoles().size();
        assertEquals(oldRolesSize, 4);

        UserRole obtainedResult = teamService.addTeamRoleToUser(1, 1, "Scrum Master");
        assertEquals(obtainedResult, expectedResult);

        TeamRolesDTO newRoles = teamService.teamRoles(1);
        assertEquals(newRoles.getRoles().size(), oldRolesSize + 1);
    }

    @Test
    public void addTeamRoleToUserUserNotFound() throws NotFoundException {
        exception.expect(NotFoundException.class);
        teamService.addTeamRoleToUser(1, 10, "Scrum Master");
    }

    @Test
    public void addTeamRoleToUserTeamNotFound() throws NotFoundException {
        exception.expect(NotFoundException.class);
        teamService.addTeamRoleToUser(10, 1, "Scrum Master");
    }

    @Test
    public void addTeamRoleToUserNotInTeam() throws NotFoundException {
        exception.expect(NotFoundException.class);
        teamService.addTeamRoleToUser(1, 3, "Scrum Master");
    }

    public List<User> setUpUsers() {
        List<User> usersTable = new ArrayList<User>();

        User u1 = new User();
        u1.setId(1);
        u1.setName("User One");
        u1.setUsername("user1");
        usersTable.add(u1);

        User u2 = new User();
        u2.setId(2);
        u2.setName("User Two");
        u2.setUsername("user2");
        usersTable.add(u2);

        User u3 = new User();
        u3.setId(3);
        u3.setName("User Three");
        u3.setUsername("user3");
        usersTable.add(u3);

        User u4 = new User();
        u4.setId(4);
        u4.setName("User Four");
        u4.setUsername("user4");
        usersTable.add(u4);

        User u5 = new User();
        u5.setId(5);
        u5.setName("User Five");
        u5.setUsername("user5");
        usersTable.add(u5);

        return usersTable;
    }

    public List<Team> setUpTeams() {
        List<Team> teamsTable = new ArrayList<Team>();

        Team t1 = new Team();
        t1.setId(1);
        t1.setName("Team1");
        t1.setLead(5);
        teamsTable.add(t1);

        Team t2 = new Team();
        t2.setId(2);
        t2.setName("Team2");
        t2.setLead(3);
        teamsTable.add(t1);

        Team t3 = new Team();
        t3.setId(3);
        t3.setName("Team3");
        t3.setLead(1);
        teamsTable.add(t3);

        return teamsTable;
    }

    public List<Membership> setUpMemberships() {
        List<Membership> membershipsTable = new ArrayList<Membership>();

        Membership m1 = new Membership();
        m1.setMemberId(1);
        m1.setTeamId(3);
        m1.setRole("Lead");
        membershipsTable.add(m1);

        Membership m2 = new Membership();
        m2.setMemberId(2);
        m2.setTeamId(3);
        m2.setRole("Tester");
        membershipsTable.add(m2);

        Membership m3 = new Membership();
        m3.setMemberId(3);
        m3.setTeamId(3);
        m3.setRole("Developer");
        membershipsTable.add(m3);

        Membership m4 = new Membership();
        m4.setMemberId(3);
        m4.setTeamId(2);
        m4.setRole("Lead");
        membershipsTable.add(m4);

        Membership m5 = new Membership();
        m5.setMemberId(5);
        m5.setTeamId(2);
        m5.setRole("Developer");
        membershipsTable.add(m5);

        Membership m6 = new Membership();
        m6.setMemberId(4);
        m6.setTeamId(2);
        m6.setRole("Product Owner");
        membershipsTable.add(m6);

        Membership m7 = new Membership();
        m7.setMemberId(2);
        m7.setTeamId(2);
        m7.setRole("Developer");
        membershipsTable.add(m7);

        Membership m8 = new Membership();
        m8.setMemberId(5);
        m8.setTeamId(1);
        m8.setRole("Lead");
        membershipsTable.add(m8);

        Membership m9 = new Membership();
        m9.setMemberId(2);
        m9.setTeamId(1);
        m9.setRole("Developer");
        membershipsTable.add(m9);

        Membership m10 = new Membership();
        m10.setMemberId(4);
        m10.setTeamId(1);
        m10.setRole("Tester");
        membershipsTable.add(m10);

        Membership m11 = new Membership();
        m11.setMemberId(1);
        m11.setTeamId(1);
        m11.setRole("Scrum Master");
        membershipsTable.add(m11);

        Membership m12 = new Membership();
        m12.setMemberId(4);
        m12.setTeamId(3);
        m12.setRole("Developer");
        membershipsTable.add(m12);

        return membershipsTable;
    }

    public List<TeamRolesDTO> setUpTeamRoles() {
        List<TeamRolesDTO> teamRoleTable = new ArrayList<TeamRolesDTO>();

        TeamRolesDTO r1 = new TeamRolesDTO();
        r1.setTeamId(1);
        r1.addRole("Developer");
        r1.addRole("Tester");
        r1.addRole("Product Owner");
        r1.addRole("Lead");
        teamRoleTable.add(r1);

        TeamRolesDTO r2 = new TeamRolesDTO();
        r2.setTeamId(2);
        r2.addRole("Developer");
        r2.addRole("Tester");
        r2.addRole("Product Owner");
        r2.addRole("Lead");
        teamRoleTable.add(r2);

        TeamRolesDTO r3 = new TeamRolesDTO();
        r3.setTeamId(3);
        r3.addRole("Developer");
        r3.addRole("Tester");
        r3.addRole("Product Owner");
        r3.addRole("Lead");
        r3.addRole("Scrum master");
        teamRoleTable.add(r3);

        return teamRoleTable;
    }
}