package io.swagger.service;

import io.swagger.api.AlreadyExistsException;
import io.swagger.api.NotFoundException;
import io.swagger.model.*;

import java.util.List;

public interface ITeamService {
    List<Team> getAllTeams();
    Team getTeamByID(int teamID) throws NotFoundException;
    List<UserRole> getUsersByRole(Integer teamID, String role) throws NotFoundException;
    TeamRolesDTO teamRoles(Integer teamID) throws NotFoundException;
    TeamRolesDTO addRoleToTeam(Integer teamID, String role) throws AlreadyExistsException, NotFoundException;
    UserRole getUserRole(Integer teamID, Integer userID) throws NotFoundException;
    UserRole addTeamRoleToUser(Integer teamID, Integer userID, String role) throws NotFoundException;
}
