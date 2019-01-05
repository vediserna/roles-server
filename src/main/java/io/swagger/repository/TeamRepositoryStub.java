package io.swagger.repository;

import io.swagger.model.*;

import java.util.ArrayList;
import java.util.List;

public class TeamRepositoryStub implements ITeamRepository {
    List<User> usersTable = new ArrayList<User>();
    List<Team> teamsTable = new ArrayList<Team>();
    List<Membership> membershipsTable = new ArrayList<Membership>();
    List<TeamRolesDTO> teamRoleTable = new ArrayList<TeamRolesDTO>();

    @Override
    public List<Team> getAllTeams() {
        return teamsTable;
    }

    @Override
    public Team getTeamByID(int teamID) {
        for(Team t : teamsTable) {
            if(t.getId() == teamID) {
                return t;
            }
        }

        return null;
    }

    @Override
    public List<UserRole> getUsersByRole(Integer teamID, String role) {
        List<UserRole> ur = new ArrayList<UserRole>();

        for(Membership m : membershipsTable) {
            if(m.getTeamId() == teamID && m.getRole().equals(role)) {
                UserRole tmp = new UserRole();
                tmp.setUserId(m.getMemberId());
                tmp.setRole(m.getRole());
                ur.add(tmp);
            }
        }

        return ur;
    }

    @Override
    public TeamRolesDTO addRoleToTeam(Integer teamID, String role) {
        for(TeamRolesDTO tr : teamRoleTable) {
            if(tr.getTeamId() == teamID) {
                tr.addRole(role);
                return tr;
            }
        }

        return null;
    }

    @Override
    public TeamRolesDTO teamRoles(Integer teamID) {
        for(TeamRolesDTO tr : teamRoleTable) {
            if(tr.getTeamId() == teamID) {
                return tr;
            }
        }

        return null;
    }

    @Override
    public User getUserByID(Integer userID) {
        for(User u : usersTable) {
            if(u.getId() == userID) {
                return u;
            }
        }

        return null;
    }

    @Override
    public UserRole isUserInTeam(Integer userID, Integer teamID) {
        for(Membership m : membershipsTable) {
            if(m.getTeamId() == teamID && m.getMemberId() == userID) {
                UserRole ur = new UserRole();
                ur.setRole(m.getRole());
                ur.setUserId(m.getMemberId());

                return ur;
            }
        }

        return null;
    }

    @Override
    public UserRole addTeamRoleToUser(Integer teamID, Integer userID, String role) {
        for(Membership m : membershipsTable) {
            if(m.getMemberId() == userID && m.getTeamId() == teamID) {
                m.setRole(role);

                UserRole ur = new UserRole();
                ur.setUserId(m.getMemberId());
                ur.setRole(m.getRole());

                return ur;
            }
        }

        return null;
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
