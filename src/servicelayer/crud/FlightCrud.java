package servicelayer.crud;

import datalayer.FlightAccess;
import tables.Flight;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightCrud implements CrudMethods<Flight> {

    @Override
    public List<Flight> getAll() throws SQLException {

        Connection dbConnection = null;
        List<Flight> flights = new ArrayList<>();

        try {
            dbConnection = serviceConnection.getConnection();
            FlightAccess flightAccess = new FlightAccess(dbConnection);
            flights = flightAccess.readAllFlights();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        return flights;
    }

    @Override
    public void add(Flight flight) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            FlightAccess flightAccess = new FlightAccess(dbConnection);
            flightAccess.addFlight(flight);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public void update(Flight flight) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            FlightAccess flightAccess = new FlightAccess(dbConnection);
            flightAccess.updateFlight(flight);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public void delete(Flight flight) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            FlightAccess flightAccess = new FlightAccess(dbConnection);
            flightAccess.deleteFlight(flight);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }
}
