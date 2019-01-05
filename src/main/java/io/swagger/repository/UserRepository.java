package io.swagger.repository;

import io.swagger.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private DataBase db;

    public UserRepository() {
        this.db = new sqliteDBGateway("jdbc:sqlite:src/main/resources/teamsAndRoles.db");
    }

    public List<User> getAllUsers() {
        return db.allUsers();
    }

    public User getUserByID(int id) {
        return db.getUserByID(id);
    }

    public String getUserRoleInTeam(Integer userID, Integer teamID) {
        return db.getUserRoleInTeam(userID, teamID);
    }

    public boolean getTeamByID(Integer teamID) {
        if (db.getTeamByID(teamID) != null) {
            return true;
        }
        return false;
    }
}
