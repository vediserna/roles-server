package io.swagger.repository;

import io.swagger.model.*;
import java.util.List;

public class TeamRepository implements ITeamRepository {

    private DataBase db;

    public TeamRepository() {
        this.db = new sqliteDBGateway("jdbc:sqlite:src/main/resources/teamsAndRoles.db");
    }

    public List<Team> getAllTeams() {
        return db.allTeams();
    }

    public Team getTeamByID(int teamID) {
        return db.getTeamByID(teamID);
    }

    public List<UserRole> getUsersByRole(Integer teamID, String role) {
        return db.getUsersByRole(teamID, role);
    }

    public TeamRolesDTO addRoleToTeam(Integer teamID, String role) {
        return db.addRoleToTeam(teamID, role);
    }

    public TeamRolesDTO teamRoles(Integer teamID) {
        return db.getTeamRoles(teamID);
    }

    public User getUserByID(Integer userID) {
        return db.getUserByID(userID);
    }

    public UserRole isUserInTeam(Integer userID, Integer teamID) {
        return db.getMembership(userID, teamID);
    }

    public UserRole addTeamRoleToUser(Integer teamID, Integer userID, String role) {
        return db.addTeamRoleToUser(teamID, userID, role);
    }
}
