package io.swagger.service;

import io.swagger.api.NotFoundException;
import io.swagger.model.Membership;
import io.swagger.model.User;
import io.swagger.repository.IUserRepository;

import java.util.List;

public class UserService implements IUserService {

    private IUserRepository userRepository;

    public UserService(IUserRepository userRepo) {
        this.userRepository = userRepo;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.getAllUsers();
    }

    @Override
    public User getUserByID(int ID) throws NotFoundException {
        User u = this.userRepository.getUserByID(ID);

        if (u == null) {
            throw new NotFoundException(0, "User is not found");
        }

        return u;
    }

    @Override
    public Membership getUserRoleInTeam(Integer userID, Integer teamID) throws NotFoundException {
        if(this.userRepository.getUserByID(userID) == null) {
            throw new NotFoundException(0, "User not found");
        } else if (!this.teamExists(teamID)) {
            throw new NotFoundException(0, "Team not found");
        } else {
            String role = this.userRepository.getUserRoleInTeam(userID, teamID);

            if (role == null) {
                throw new NotFoundException(0, "The user is not in this team");
            } else {
                Membership m = new Membership();
                m.setMemberId(userID);
                m.setTeamId(teamID);
                m.setRole(role);
                return m;
            }
        }
    }

    private boolean teamExists(Integer teamID) {
        if (this.userRepository.getTeamByID(teamID)) {
            return true;
        }

        return false;
    }
}
