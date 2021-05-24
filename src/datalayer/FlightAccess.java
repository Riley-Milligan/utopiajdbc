package datalayer;

import tables.Flight;
import tables.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FlightAccess extends DataAccess<Flight> {

    public FlightAccess (Connection dbConnection) {
        super(dbConnection);
    }

    public void addFlight (Flight flight) throws SQLException {
        save("INSERT into flight (route_id, airplane_id, departure_time, reserved_seats, seat_price) values (?, ?, ?, ?, ?)",
                new Object[] {flight.getRoute().getId(), flight.getPlane().getId(), Timestamp.valueOf(flight.getDepartureTime()),
                        flight.getReservedSeats(), flight.getSeatPrice()});
    }

    public void updateFlight (Flight flight) throws SQLException {
        save("UPDATE flight set route_id = ?, airpline_ID = ?, departure_time = ?, reserved_seats = ?, seat_price = ?",
                new Object[] {flight.getRoute().getId(), flight.getPlane().getId(), Timestamp.valueOf(flight.getDepartureTime()),
                        flight.getReservedSeats(), flight.getSeatPrice()});
    }

    public void deleteFlight (Flight flight) throws SQLException {
        save("DELETE from flight where id = ?",
                new Object[] {flight.getId()});
    }

    public List<Flight> readAllFlights() throws SQLException {
        return read("SELECT * from flight", null);
    }

    public List<Flight> readFlightsByRoute(Route route) throws SQLException {
        return read("SELECT * from flight where route_id = ?",
                new Object[] {route.getId()});
    }

    public Flight readFlightByID(Integer id) throws SQLException {
        List<Flight> flights = read("SELECT * from flight where id = ?",  new Object[] {id});
        return flights.get(0);
    }

    @Override
    protected List<Flight> getData(ResultSet results) throws SQLException {
        List<Flight> flights = new ArrayList<>();
        while (results.next()) {
            Flight flight = new Flight();
            flight.setId(results.getInt("id"));
            flight.getRoute().setId(results.getInt("route_id"));
            flight.getPlane().setId(results.getInt("airplane_id"));
            flight.setDepartureTime(results.getTimestamp("departure_time").toLocalDateTime());
            flight.setReservedSeats(results.getInt("reserved_seats"));
            flight.setSeatPrice(results.getFloat("seat_price"));
            flights.add(flight);
        }

        return flights;
    }
}
