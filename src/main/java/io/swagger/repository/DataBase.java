package io.swagger.repository;

import io.swagger.model.*;

import java.util.List;

public interface DataBase {
    List<User> allUsers();
    List<Team> allTeams();
    User getUserByID(int id);
    String getUserRoleInTeam(Integer userID, Integer teamID);
    Team getTeamByID(Integer teamID);
    List<UserRole> getUsersByRole(Integer teamID, String role);
    TeamRolesDTO getTeamRoles(Integer teamID);
    TeamRolesDTO addRoleToTeam(Integer teamID, String role);
    UserRole getMembership(Integer userID, Integer teamID);
    UserRole addTeamRoleToUser(Integer teamID, Integer userID, String role);
}
