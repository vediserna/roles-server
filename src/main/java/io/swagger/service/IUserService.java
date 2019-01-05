package io.swagger.service;

import io.swagger.api.NotFoundException;
import io.swagger.model.Membership;
import io.swagger.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    List<User> getAllUsers();
    User getUserByID(int ID) throws NotFoundException;
    Membership getUserRoleInTeam(Integer userID, Integer teamID) throws NotFoundException;
}
