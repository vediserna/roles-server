package io.swagger.repository;

import io.swagger.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class sqliteDBGateway implements DataBase {
    private String url;

    public sqliteDBGateway(String url) {
        this.url = url;
    }

    private Connection connectToDatabase() {
        Connection db = null;
        try {
            db = DriverManager.getConnection(url);

            return db;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<User> allUsers() {
        Connection db = this.connectToDatabase();

        try {
            Statement statement = db.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Users");

            List<User> users = new ArrayList<User>();
            while(result.next()) {
                User tmp = new User();

                tmp.setUsername(result.getString("username"));
                tmp.setName(result.getString("name"));
                tmp.setId(result.getInt("id"));
                users.add(tmp);
            }

            db.close();

            return users;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<Team> allTeams() {
        Connection db = this.connectToDatabase();

        try {
            Statement statement = db.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Teams");

            List<Team> teams = new ArrayList<Team>();
            while(result.next()) {
                Team tmp = new Team();

                tmp.setLead(result.getInt("lead"));
                tmp.setName(result.getString("name"));
                tmp.setId(result.getInt("id"));
                teams.add(tmp);
            }

            db.close();

            return teams;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public User getUserByID(int id) {
        Connection db = this.connectToDatabase();

        try {
            PreparedStatement ps = db.prepareStatement("SELECT * FROM Users WHERE id=?");

            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            User u = new User();
            u.setId(id);
            u.setName(result.getString("name"));
            u.setUsername(result.getString("username"));

            db.close();

            return u;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public String getUserRoleInTeam(Integer userID, Integer teamID) {
        Connection db = this.connectToDatabase();

        try {
            PreparedStatement ps = db.prepareStatement("SELECT Membership.role FROM Membership WHERE userId = ? AND teamId = ?");

            ps.setInt(1, userID);
            ps.setInt(2, teamID);
            ResultSet result = ps.executeQuery();

            String role = result.getString("role");

            db.close();

            return role;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Team getTeamByID(Integer teamID) {
        Connection db = this.connectToDatabase();

        try {
            PreparedStatement ps = db.prepareStatement("SELECT * FROM Teams WHERE id = ?");

            ps.setInt(1, teamID);
            ResultSet result = ps.executeQuery();

            Team team = new Team();
            team.setId(result.getInt("id"));
            team.setName(result.getString("name"));
            team.setLead(result.getInt("lead"));

            db.close();

            return team;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<UserRole> getUsersByRole(Integer teamID, String role) {
        Connection db = this.connectToDatabase();

        try {
            PreparedStatement ps = db.prepareStatement("SELECT * FROM Memberships WHERE Memberships.teamId = ? AND UPPER(Memberships.role) = ?");

            ps.setInt(1, teamID);
            ps.setString(2, role.toUpperCase());
            ResultSet result = ps.executeQuery();

            List<UserRole> users = new ArrayList<UserRole>();
            while(result.next()) {
                UserRole tmp = new UserRole();

                tmp.setRole(result.getString("role"));
                tmp.setUserId(result.getInt("userId"));

                users.add(tmp);
            }

            db.close();

            return users;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public TeamRolesDTO getTeamRoles(Integer teamID) {
        Connection db = this.connectToDatabase();

        try {
            PreparedStatement ps = db.prepareStatement("SELECT * FROM Roles WHERE teamId = ?");

            ps.setInt(1, teamID);
            ResultSet result = ps.executeQuery();

            TeamRolesDTO dto = new TeamRolesDTO();
            dto.setTeamId(teamID);

            while(result.next()) {
                dto.addRole(result.getString("role"));
            }

            db.close();

            return dto;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public TeamRolesDTO addRoleToTeam(Integer teamID, String role) {
        Connection db = this.connectToDatabase();

        try {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Roles VALUES(?,?)");

            ps.setInt(1, teamID);
            ps.setString(2, role);
            ps.executeUpdate();

            db.close();

            return this.getTeamRoles(teamID);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public UserRole getMembership(Integer userID, Integer teamID) {
        Connection db = this.connectToDatabase();

        try {
            PreparedStatement ps = db.prepareStatement("SELECT * FROM Memberships WHERE userId = ? AND teamId = ?");

            ps.setInt(1, userID);
            ps.setInt(2, teamID);
            ResultSet result = ps.executeQuery();

            UserRole role = new UserRole();
            role.setRole(result.getString("role"));
            role.setUserId(result.getInt("userId"));

            db.close();

            return role;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public UserRole addTeamRoleToUser(Integer teamID, Integer userID, String role) {
        Connection db = this.connectToDatabase();

        try {
            PreparedStatement ps = db.prepareStatement("UPDATE Memberships SET role = ? WHERE userId = ? AND teamId = ?");

            ps.setString(1, role);
            ps.setInt(2, userID);
            ps.setInt(3, teamID);
            ps.executeUpdate();

            db.close();

            return this.getMembership(userID, teamID);
        } catch (SQLException e) {
            return null;
        }
    }
}
