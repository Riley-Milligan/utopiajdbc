package servicelayer.crud;

import datalayer.FlightBookingAccess;
import tables.FlightBooking;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightBookingCrud implements CrudMethods<FlightBooking> {

    @Override
    public List<FlightBooking> getAll() throws SQLException {

        Connection dbConnection = null;
        List<FlightBooking> flightBookings = new ArrayList<>();

        try {
            dbConnection = serviceConnection.getConnection();
            FlightBookingAccess flightBookingAccess = new FlightBookingAccess(dbConnection);
            flightBookings = flightBookingAccess.readAllFlightBookings();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        return flightBookings;
    }

    @Override
    public void add(FlightBooking flightBooking) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            FlightBookingAccess flightBookingAccess = new FlightBookingAccess(dbConnection);
            flightBookingAccess.addFlightBooking(flightBooking);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public void update(FlightBooking flightBooking) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            FlightBookingAccess flightBookingAccess = new FlightBookingAccess(dbConnection);
            flightBookingAccess.updateFlightBooking(flightBooking);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public void delete(FlightBooking flightBooking) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            FlightBookingAccess flightBookingAccess = new FlightBookingAccess(dbConnection);
            flightBookingAccess.deleteFlightBooking(flightBooking);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }
}
