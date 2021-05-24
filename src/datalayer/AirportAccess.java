package datalayer;

import tables.Airport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportAccess extends DataAccess<Airport> {

    public AirportAccess(Connection dbConnection) {
        super(dbConnection);
    }

    public void addAirport(Airport airport) throws SQLException {
        save("INSERT into airport (iata_id, city) values (?, ?)", new Object[] {airport.getAirportCode(), airport.getCity()});
    }

    public void updateAirport(Airport airport) throws SQLException {
        save("UPDATE airport set iata_id = ?, city = ?", new Object[] {airport.getAirportCode(), airport.getCity()});
    }

    public void deleteAirport(Airport airport) throws SQLException {
        save("DELETE from airport where iata_it = ?", new Object[] {airport.getAirportCode()});
    }

    public List<Airport> readAllAirports() throws SQLException {
        return read("SELECT * from airport", null);
    }

    @Override
    protected List<Airport> getData(ResultSet results) throws SQLException {
        List<Airport> airports = new ArrayList<>();
        while (results.next()) {
            Airport airport = new Airport();
            airport.setAirportCode(results.getString("iata_id"));
            airport.setCity(results.getString("city"));
            airports.add(airport);
        }
        return airports;
    }
}
