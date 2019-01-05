package io.swagger.repository;

import io.swagger.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository {
    List<User> getAllUsers();
    User getUserByID(int id);
    String getUserRoleInTeam(Integer userID, Integer teamID);
    boolean getTeamByID(Integer teamID);
}
