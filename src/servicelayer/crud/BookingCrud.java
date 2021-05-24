package servicelayer.crud;

import datalayer.BookingAccess;
import tables.Booking;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingCrud implements CrudMethods<Booking> {

    @Override
    public List<Booking> getAll() throws SQLException {

        Connection dbConnection = null;
        List<Booking> bookings = new ArrayList<>();
        try {
            dbConnection = serviceConnection.getConnection();
            BookingAccess bookingAccess = new BookingAccess(dbConnection);
            bookings = bookingAccess.readAllBookings();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert dbConnection != null;
            dbConnection.close();
        }

        return bookings;
    }

    @Override
    public void add(Booking booking) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            BookingAccess bookingAccess = new BookingAccess(dbConnection);
            bookingAccess.addBooking(booking);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }

    }

    @Override
    public void update(Booking booking) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            BookingAccess bookingAccess = new BookingAccess(dbConnection);
            bookingAccess.updateBooking(booking);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }

    }

    @Override
    public void delete(Booking booking) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            BookingAccess bookingAccess = new BookingAccess(dbConnection);
            bookingAccess.updateBooking(booking);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }
}
