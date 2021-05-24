package datalayer;

import tables.User;
import tables.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAccess extends DataAccess<User> {

    public UserAccess(Connection dbConnection) {
        super(dbConnection);
    }

    public void addUser(User user) throws SQLException {
        save("INSERT into user (role_id, given_name, family_name, username, email, password, phone) values (?, ?, ?, ?, ?, ?, ?)",
                new Object[] {user.getRole().getId(), user.getGivenName(), user.getFamilyName(), user.getUserName(), user.getPassword(), user.getPhone()});
    }

    public void updateUser(User user) throws SQLException {
        save("UPDATE user set role_id = ?, given_name = ?, family_name = ?, username = ?, email = ?, password = ?, phone = ?",
                new Object[] {user.getRole().getId(), user.getGivenName(), user.getFamilyName(), user.getUserName(), user.getPassword(), user.getPhone()});
    }

    public void deleteUSer(User user) throws SQLException {
        save("DELETE from user where id = ?", new Object[] {user.getId()});
    }

    public User readUserById(Integer id) throws SQLException {
        List<User> users = read("SELECT * from user where id = ?", new Object[] {id});
        return users.get(0);
    }

    public List<User> readUsersByRole(UserRole userRole) throws SQLException {
        return read("SELECT * from user where role_id = ?", new Object[] {userRole.getId()});
    }

    public List<User> readAllUsers() throws SQLException {
        return read("SELECT * from user", null);
    }

    @Override
    protected List<User> getData(ResultSet results) throws SQLException {
        List<User> users = new ArrayList<>();
        while (results.next()) {
            User user = new User();
            user.setId(results.getInt("id"));
            user.getRole().setId(results.getInt("role_id"));
            user.setGivenName(results.getString("given_name"));
            user.setFamilyName(results.getString("family_name"));
            user.setUserName(results.getString("username"));
            user.setEmail(results.getString("email"));
            user.setPassword(results.getString("password"));
            user.setPhone(results.getString("phone"));
            users.add(user);
        }

        return users;
    }
}
