package io.swagger.api;

import io.swagger.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.repository.TeamRepository;
import io.swagger.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-12-17T23:34:02.537Z")

@Controller
public class TeamsApiController implements TeamsApi {

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    private TeamService teamService;

    @org.springframework.beans.factory.annotation.Autowired
    public TeamsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.teamService = new TeamService(new TeamRepository());
    }

    /**
       Get a list of all teams
     */
    @Override
    public ResponseEntity<List<Team>> teamsGet() {
        try {
            List<Team> teams = teamService.getAllTeams();

            if(teams != null) {
                return new ResponseEntity<List<Team>>(objectMapper.readValue(teams.toString(), List.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<List<Team>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            return new ResponseEntity<List<Team>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get a specific team by ID
     * @param teamID
     * @return Team json item if team is found. 404 other wise.
     */
    @Override
    public ResponseEntity<Team> teamsTeamIDGet(@ApiParam(value = "",required=true) @PathVariable("teamID") Integer teamID) {
        try {
            Team t = this.teamService.getTeamByID(teamID);

            if (t == null) {
                return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<Team>(objectMapper.readValue(t.toString(), Team.class), HttpStatus.OK);
            }
        } catch (NotFoundException e) {
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<Team>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all members that have a specific role in a specific team
     * @param teamID
     * @param role (query parameter)
     * @return A list of users. 404 if the team does not exist
     */
    @Override
    public ResponseEntity<List<UserRole>> teamMembersByRole(@ApiParam(value = "",required=true) @PathVariable("teamID") Integer teamID, @RequestParam("role") String role) {
        try {
            return new ResponseEntity<List<UserRole>>(objectMapper.readValue(this.teamService.getUsersByRole(teamID, role).toString(), List.class), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<List<UserRole>>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<List<UserRole>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<TeamRolesDTO> teamRoles(@ApiParam(value = "", required = true) @PathVariable("teamID") Integer teamId) {
        try {
            return new ResponseEntity<TeamRolesDTO>(objectMapper.readValue(this.teamService.teamRoles(teamId).toString(), TeamRolesDTO.class), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<TeamRolesDTO>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<TeamRolesDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add a new role to a specific team
     * @param teamID
     * @param role (query parameter)
     * @return A team roles DTO after the update. 404 if the team is not found. 409 if the role already exists in the team.
     */
    @Override
    public ResponseEntity<TeamRolesDTO> addNewRoleToTeam(@ApiParam(value = "",required=true) @PathVariable("teamID") Integer teamID, @RequestParam("role") String role) {
        try {
            return new ResponseEntity<TeamRolesDTO>(objectMapper.readValue(this.teamService.addRoleToTeam(teamID, role).toString(), TeamRolesDTO.class), HttpStatus.CREATED);
        } catch (NotFoundException e) {
            return new ResponseEntity<TeamRolesDTO>(HttpStatus.NOT_FOUND);
        }catch (AlreadyExistsException e) {
            return new ResponseEntity<TeamRolesDTO>(HttpStatus.CONFLICT);
        } catch (IOException e) {
            return new ResponseEntity<TeamRolesDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Return the role of a specific user in a specific team
     * @param userID
     * @param teamID
     * @return UserRole object. 404 if the user/team is not found or if the user is not in the team.
     */
    @Override
    public ResponseEntity<UserRole> getUserRoleInTeam(@ApiParam(value = "",required=true) @PathVariable("userID") Integer userID, @ApiParam(value = "", required = true) @PathVariable("teamID") Integer teamID) {
        try {
            return new ResponseEntity<UserRole>(objectMapper.readValue(this.teamService.getUserRole(teamID, userID).toString(), UserRole.class), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<UserRole>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<UserRole>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Assign a new role to a user in a team
     * @param teamID
     * @param userID
     * @param role (query parameter)
     * @return UserRole object. 404 if the user/team is not found or if the user is not in the team.
     */
    @Override
    public ResponseEntity assignUserRoleInTeam(@ApiParam(value = "",required=true) @PathVariable("teamID") Integer teamID, @ApiParam(value = "",required=true) @PathVariable("userID") Integer userID, @RequestParam("role") String role) {
        try {
            return new ResponseEntity<UserRole>(objectMapper.readValue(this.teamService.addTeamRoleToUser(teamID, userID, role).toString(), UserRole.class), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<UserRole>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<UserRole>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
