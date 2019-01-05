package io.swagger.api;

import io.swagger.model.Membership;
import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.repository.UserRepository;
import io.swagger.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-12-17T23:34:02.537Z")

@Controller
public class UsersApiController implements UsersApi {

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    private UserService userService;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.userService = new UserService(new UserRepository());
    }

    /**
     * Get all users
     * @return a list of all users
     */
    public ResponseEntity<List<User>> usersGet() {
        try {
            List<User> users = userService.getAllUsers();

            if(users != null) {
                return new ResponseEntity<List<User>>(objectMapper.readValue(users.toString(), List.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get a specific user
     * @param userID
     * @return a specific user. 404 if the user does not exist
     */
    @Override
    public ResponseEntity<User> usersUserIDGet(@ApiParam(value = "",required=true) @PathVariable("userID") Integer userID) {
        try {
            User u = this.userService.getUserByID(userID);

            return new ResponseEntity<User>(objectMapper.readValue(u.toString(), User.class), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get a user role in team
     * @param userID
     * @param teamID
     * @return A membership item that contains the userId, teamID and the user's role in the team. 404 if the user/team does not exist or the user is not in the team.
     */
    @Override
    public ResponseEntity<Membership> getUserRoleInTeam(@ApiParam(value = "",required=true) @PathVariable("userID") Integer userID, @PathVariable("teamID") Integer teamID) {
        try {
            Membership role = this.userService.getUserRoleInTeam(userID, teamID);

            if (role != null) {
                return new ResponseEntity<Membership>(objectMapper.readValue(role.toString(), Membership.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<Membership>(HttpStatus.NOT_FOUND);
            }
        } catch (NotFoundException e) {
            return new ResponseEntity<Membership>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<Membership>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
