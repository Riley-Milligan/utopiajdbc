package datalayer;

import tables.Booking;
import tables.Flight;
import tables.FlightBooking;
import tables.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlightBookingAccess extends DataAccess<FlightBooking> {

    public FlightBookingAccess(Connection dbConnection) {
        super(dbConnection);
    }

    public void addFlightBooking(FlightBooking flightBooking) throws SQLException {
        save("INSERT into flight_bookings (flight_id, booking_id) values (?, ?)",
                new Object[] {flightBooking.getFlight().getId(), flightBooking.getBooking().getId()});
    }

    public void updateFlightBooking(FlightBooking flightBooking) throws SQLException {
        save("UPDATE flight_bookings flight_id = ?, booking_id = ?",
                new Object[] {flightBooking.getFlight().getId(), flightBooking.getBooking().getId()});
    }

    public void deleteFlightBooking(FlightBooking flightBooking) throws SQLException {
        save("DELETE from flight_bookings where booking_id = ?", new Object[] {flightBooking.getBooking().getId()});
    }

    public List<FlightBooking> readAllFlightBookings() throws SQLException {
        return read("Select * from flight_bookings", null);
    }

    public FlightBooking getFlightBookingByBooking(Booking booking) throws SQLException {
        List<FlightBooking> flightBookings = read("Select * from flight_bookings where booking_id = ?",  new Object[] {booking.getId()});
        return flightBookings.get(0);
    }

    @Override
    protected List<FlightBooking> getData(ResultSet results) throws SQLException {
        List<FlightBooking> flightBookings = new ArrayList<>();
        while (results.next()) {
            FlightBooking flightBooking = new FlightBooking();
            flightBooking.getFlight().setId(results.getInt("flight_id"));
            flightBooking.getBooking().setId(results.getInt("booking_id"));
        }

        return flightBookings;
    }
}
