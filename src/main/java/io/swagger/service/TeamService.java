package io.swagger.service;

import io.swagger.api.AlreadyExistsException;
import io.swagger.api.NotFoundException;
import io.swagger.model.*;
import io.swagger.repository.ITeamRepository;

import java.util.List;

public class TeamService implements ITeamService {

    private ITeamRepository teamRepository;

    public TeamService(ITeamRepository teamRepo) {
        this.teamRepository = teamRepo;
    }

    public List<Team> getAllTeams() {
        return teamRepository.getAllTeams();
    }

    @Override
    public Team getTeamByID(int teamID) throws NotFoundException {
        Team t = teamRepository.getTeamByID(teamID);

        if (t != null) {
            return t;
        }

        throw new NotFoundException(0, "Team not found");
    }

    @Override
    public List<UserRole> getUsersByRole(Integer teamID, String role) throws NotFoundException {
        this.getTeamByID(teamID); // NotFoundException is thrown when this line runs if the team does not exist

        return this.teamRepository.getUsersByRole(teamID, role);
    }

    @Override
    public TeamRolesDTO teamRoles(Integer teamID) throws NotFoundException {
        getTeamByID(teamID); // NotFoundException is thrown when this line runs if the team does not exist

        return this.teamRepository.teamRoles(teamID);
    }

    @Override
    public TeamRolesDTO addRoleToTeam(Integer teamID, String role) throws AlreadyExistsException, NotFoundException {
        if(this.teamRoles(teamID).isRoleInTeam(role)) {
            throw new AlreadyExistsException(0, "This role is already registered for this team");
        } else {
            return this.teamRepository.addRoleToTeam(teamID, role);
        }
    }

    @Override
    public UserRole getUserRole(Integer teamID, Integer userID) throws NotFoundException {
        this.getTeamByID(teamID); // NotFoundException is thrown when this line runs if the team does not exist
        this.getUserByID(userID); // NotFoundException is thrown when this line runs if the user does not exist

        return this.userInTeam(userID, teamID); // NotFoundException is thrown when this line runs if the user is not in the team
    }

    @Override
    public UserRole addTeamRoleToUser(Integer teamID, Integer userID, String role) throws NotFoundException {
        this.getUserByID(userID);           // NotFoundException is thrown when this line runs if the user does not exist
        this.userInTeam(userID, teamID);    // NotFoundException is thrown when this line runs if the user is not in the team

        try {
            // Add the role to the team if it is not in the team already
            // NotFoundException is thrown if the team does not exist
            this.addRoleToTeam(teamID, role);
        } catch (AlreadyExistsException e) {
            ;
        }

        return this.teamRepository.addTeamRoleToUser(teamID, userID, role);
    }

    private UserRole userInTeam(Integer userID, Integer teamID) throws NotFoundException {
        UserRole m = this.teamRepository.isUserInTeam(userID, teamID);

        if (m == null) {
            throw new NotFoundException(0, "The user is not in this team");
        } else {
            return m;
        }
    }

    private void getUserByID(Integer userID) throws NotFoundException {
        if (this.teamRepository.getUserByID(userID) == null) {
            throw new NotFoundException(0, "User not found");
        }
    }
}
