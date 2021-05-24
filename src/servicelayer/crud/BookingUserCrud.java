package servicelayer.crud;

import datalayer.BookingAccess;
import datalayer.BookingUserAccess;
import tables.BookingUser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingUserCrud implements CrudMethods<BookingUser> {

    @Override
    public List<BookingUser> getAll() throws SQLException {

        Connection dbConnection = null;
        List<BookingUser> bookingUsers = new ArrayList<>();

        try {
            dbConnection = serviceConnection.getConnection();
            BookingUserAccess bookingUserAccess = new BookingUserAccess(dbConnection);
            bookingUserAccess.readAllBookingUsers();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        return bookingUsers;
    }

    @Override
    public void add(BookingUser bookingUser) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            BookingUserAccess bookingUserAccess = new BookingUserAccess(dbConnection);
            bookingUserAccess.addBookingUser(bookingUser);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public void update(BookingUser bookingUser) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            BookingUserAccess bookingUserAccess = new BookingUserAccess(dbConnection);
            bookingUserAccess.updateBookingUser(bookingUser);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public void delete(BookingUser bookingUser) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            BookingUserAccess bookingUserAccess = new BookingUserAccess(dbConnection);
            bookingUserAccess.deleteBookingUser(bookingUser);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }
}
