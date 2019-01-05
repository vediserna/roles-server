package io.swagger.repository;

import io.swagger.model.Team;
import io.swagger.model.TeamRolesDTO;
import io.swagger.model.User;
import io.swagger.model.UserRole;

import java.util.List;

public interface ITeamRepository {
    List<Team> getAllTeams();
    Team getTeamByID(int teamID);
    List<UserRole> getUsersByRole(Integer teamID, String role);
    TeamRolesDTO addRoleToTeam(Integer teamID, String role);
    TeamRolesDTO teamRoles(Integer teamID);
    User getUserByID(Integer userID);
    UserRole isUserInTeam(Integer userID, Integer teamID);
    UserRole addTeamRoleToUser(Integer teamID, Integer userID, String role);
}
