package io.swagger.repository;

import io.swagger.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryStub implements IUserRepository {
    List<User> usersTable = new ArrayList<User>();
    List<Team> teamsTable = new ArrayList<Team>();
    List<Membership> membershipsTable = new ArrayList<Membership>();
    List<TeamRolesDTO> teamRoleTable = new ArrayList<TeamRolesDTO>();

    @Override
    public List<User> getAllUsers() {
        return this.usersTable;
    }

    @Override
    public User getUserByID(int id) {
        for(User u : usersTable) {
            if(u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    @Override
    public String getUserRoleInTeam(Integer userID, Integer teamID) {
        for (Membership m : this.membershipsTable) {
            if(m.getMemberId() == userID && m.getTeamId() == teamID) {
                return m.getRole();
            }
        }

        return null;
    }

    @Override
    public boolean getTeamByID(Integer teamID) {
        for (Team t : this.teamsTable) {
            if(t.getId() == teamID) {
                return true;
            }
        }

        return false;
    }

    public void setUsersTable(List<User> usersTable) {
        this.usersTable = usersTable;
    }

    public void setTeamsTable(List<Team> teamsTable) {
        this.teamsTable = teamsTable;
    }

    public void setMembershipsTable(List<Membership> membershipsTable) {
        this.membershipsTable = membershipsTable;
    }

    public void setTeamRoleTable(List<TeamRolesDTO> teamRoleTable) {
        this.teamRoleTable = teamRoleTable;
    }
}
