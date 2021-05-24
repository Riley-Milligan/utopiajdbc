package servicelayer.crud;

import datalayer.AirportAccess;
import tables.Airport;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportCrud implements CrudMethods<Airport> {

    @Override
    public List<Airport> getAll() throws SQLException {
        Connection dbConnection = null;
        List<Airport> airports = new ArrayList<>();

        try {
            dbConnection = serviceConnection.getConnection();
            AirportAccess airportAccess = new AirportAccess(dbConnection);
            airports = airportAccess.readAllAirports();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        return airports;
    }

    @Override
    public void add(Airport airport) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            AirportAccess airportAccess = new AirportAccess(dbConnection);
            airportAccess.addAirport(airport);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public void update(Airport airport) throws SQLException {
        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            AirportAccess airportAccess = new AirportAccess(dbConnection);
            airportAccess.updateAirport(airport);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }

    @Override
    public void delete(Airport airport) throws SQLException {
        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            AirportAccess airportAccess = new AirportAccess(dbConnection);
            airportAccess.deleteAirport(airport);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }
}
