package datalayer;

import servicelayer.ServiceConnection;
import tables.Booking;
import tables.BookingUser;
import tables.FlightBooking;
import tables.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingAccess extends DataAccess<Booking> {

    public BookingAccess(Connection dbConnection) {
        super(dbConnection);
    }

    public void addBooking(Booking booking) throws SQLException {
        save("INSERT into booking (is_active, confirmation_code) values (?, ?)", new Object[] {booking.getActive(), booking.getConfirmationCode()});
    }

    //Confirmation code should remain the same after a booking update, right?  I'll have to check with the customer.
    public void updateBooking(Booking booking) throws SQLException {
        save("UPDATE booking set is_active = ?", new Object[] {booking.getActive()});
    }

    public void deleteBooking(Booking booking) throws SQLException {
        save("DELETE from booking where id = ?", new Object[] {booking.getId()});
    }

    public List<Booking> readAllBookings() throws SQLException {
        return read("SELECT * from booking", null);
    }

    public Booking readBookingByID(Integer id) throws SQLException, ClassNotFoundException {
        List<Booking> bookings = read("SELECT * from booking where id = ?", new Object[] {id});
        return bookings.get(0);
    }

    public List<Booking> readCancelledBookings() throws SQLException {
        return read("SELECT * from booking where is_active = '0'", null);
    }

    @Override
    protected List<Booking> getData(ResultSet results) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        while (results.next()) {
            Booking booking = new Booking();
            booking.setId(results.getInt("id"));
            booking.setActive(results.getInt("is_active"));
            booking.setConfirmationCode("confirmation_code");
            bookings.add(booking);
        }

        return bookings;
    }
}
