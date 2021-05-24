package servicelayer.crud;

import datalayer.UserAccess;
import tables.User;
import tables.UserRole;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserCrud implements CrudMethods<User> {

    @Override
    public List<User> getAll() throws SQLException {

        Connection dbConnection = null;
        List<User> users = new ArrayList<>();
        try {
            dbConnection = serviceConnection.getConnection();
            UserAccess userAccess = new UserAccess(dbConnection);
            users = userAccess.readAllUsers();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        return users;
    }

    @Override
    public void add(User user) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            UserAccess userAccess = new UserAccess(dbConnection);
            userAccess.addUser(user);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public void update(User user) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            UserAccess userAccess = new UserAccess(dbConnection);
            userAccess.updateUser(user);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public void delete(User user) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            UserAccess userAccess = new UserAccess(dbConnection);
            userAccess.deleteUSer(user);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }

    public List<User> getUsersByRole(UserRole role) throws SQLException {

        Connection dbConnection = null;
        List<User> users = new ArrayList<>();

        try {
            dbConnection = serviceConnection.getConnection();
            UserAccess userAccess = new UserAccess(dbConnection);
            users = userAccess.readUsersByRole(role);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        return users;
    }
}
