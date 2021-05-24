package servicelayer;

import datalayer.*;
import tables.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class ServiceMethods {

    static ServiceConnection serviceConnection = new ServiceConnection();

    public static Flight getFlight(Integer id) throws SQLException {

        Connection dbConnection = null;
        Flight flight = new Flight();

        try {
            dbConnection = serviceConnection.getConnection();
            FlightAccess flightAccess = new FlightAccess(dbConnection);
            flight = flightAccess.readFlightByID(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        return flight;
    }

    public static List<Booking> getBookingsByUser(User user) throws SQLException {

        Connection dbConnection = null;
        List<Booking> bookings = new ArrayList<>();
        try {
            dbConnection = serviceConnection.getConnection();
            BookingUserAccess bookingUserAccess = new BookingUserAccess(dbConnection);
            BookingAccess bookingAccess = new BookingAccess(dbConnection);
            List<BookingUser> bookingUsers = bookingUserAccess.readBookingsByUser(user);
            for (BookingUser bookingUser : bookingUsers) {
                bookings.add(bookingAccess.readBookingByID(bookingUser.getBooking().getId()));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        return bookings;
    }

    public static BookingUser getBookingUserByBooking(Booking booking) throws SQLException {

        Connection dbConnection = null;
        BookingUser bookingUser = new BookingUser();

        try {
            dbConnection = serviceConnection.getConnection();
            BookingUserAccess bookingUserAccess = new BookingUserAccess(dbConnection);
            bookingUser = bookingUserAccess.readBookingUserByBooking(booking);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        return bookingUser;
    }

    public static FlightBooking getFlightBookingByBooking(Booking booking) throws SQLException {

        Connection dbConnection = null;
        FlightBooking flightBooking = new FlightBooking();

        try {
            dbConnection = serviceConnection.getConnection();
            FlightBookingAccess flightBookingAccess = new FlightBookingAccess(dbConnection);
            flightBooking = flightBookingAccess.getFlightBookingByBooking(booking);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        return flightBooking;
    }


    public static List<Flight> getFlightsByUser(User user) throws SQLException {

        Connection dbConnection = null;
        List<Flight> flights = new ArrayList<>();
        try {
            dbConnection = serviceConnection.getConnection();
            BookingAccess bookingAccess = new BookingAccess(dbConnection);
            FlightBookingAccess flightBookingAccess = new FlightBookingAccess(dbConnection);
            FlightAccess flightAccess = new FlightAccess(dbConnection);
            List<Booking> bookings = getBookingsByUser(user);
            List<FlightBooking> flightBookings = new ArrayList<>();
            for (Booking booking : bookings) {
                flightBookings.add(flightBookingAccess.getFlightBookingByBooking(booking));
            }
            for (FlightBooking flightBooking : flightBookings) {
                flights.add(flightAccess.readFlightByID(flightBooking.getFlight().getId()));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        return flights;
    }


    public static void cancelBooking(Booking booking) throws SQLException {

        Connection dbConnection = null;
        booking.setActive(false);
        try {
            dbConnection = serviceConnection.getConnection();
            BookingAccess bookingAccess = new BookingAccess(dbConnection);
            bookingAccess.updateBooking(booking);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }
    }

    public static User getUserByID(Integer id) throws SQLException {

        User user = new User();
        Connection dbConnection = null;

        try {
            dbConnection = serviceConnection.getConnection();
            UserAccess userAccess = new UserAccess(dbConnection);
            user = userAccess.readUserById(id);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        return user;
    }


    public static Booking getBookingByID(Integer id) throws SQLException {

        Connection dbConnection = null;
        Booking booking = new Booking();

        try {
            dbConnection = serviceConnection.getConnection();
            BookingAccess bookingAccess = new BookingAccess(dbConnection);
            booking = bookingAccess.readBookingByID(id);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        return booking;
    }

    public static Flight getFlightByID(Integer id) throws SQLException {

        Connection dbConnection = null;
        Flight flight = new Flight();

        try {
            dbConnection = serviceConnection.getConnection();
            FlightAccess flightAccess = new FlightAccess(dbConnection);
            flight = flightAccess.readFlightByID(id);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        return flight;
    }

    public static void bookingSimultaneousDelete(Booking booking, BookingUser bookingUser, FlightBooking flightBooking) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            BookingAccess bookingAccess = new BookingAccess(dbConnection);
            BookingUserAccess bookingUserAccess = new BookingUserAccess(dbConnection);
            FlightBookingAccess flightBookingAccess = new FlightBookingAccess(dbConnection);
            bookingAccess.deleteBooking(booking);
            bookingUserAccess.deleteBookingUser(bookingUser);
            flightBookingAccess.deleteFlightBooking(flightBooking);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }

    public static void bookingSimultaneousUpdate(Booking booking, BookingUser bookingUser, FlightBooking flightBooking) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            BookingAccess bookingAccess = new BookingAccess(dbConnection);
            BookingUserAccess bookingUserAccess = new BookingUserAccess(dbConnection);
            FlightBookingAccess flightBookingAccess = new FlightBookingAccess(dbConnection);
            bookingAccess.updateBooking(booking);
            bookingUserAccess.updateBookingUser(bookingUser);
            flightBookingAccess.updateFlightBooking(flightBooking);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }

    public static void bookingSimultaneousAdd(Booking booking, BookingUser bookingUser, FlightBooking flightBooking) throws SQLException {

        Connection dbConnection = null;
        try {
            dbConnection = serviceConnection.getConnection();
            BookingAccess bookingAccess = new BookingAccess(dbConnection);
            BookingUserAccess bookingUserAccess = new BookingUserAccess(dbConnection);
            FlightBookingAccess flightBookingAccess = new FlightBookingAccess(dbConnection);
            bookingAccess.addBooking(booking);
            bookingUserAccess.addBookingUser(bookingUser);
            flightBookingAccess.addFlightBooking(flightBooking);
            dbConnection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbConnection.rollback();
        } finally {
            dbConnection.close();
        }
    }

    public static List<Booking> getCancelledTickets() throws SQLException {

        Connection dbConnection = null;
        List<Booking> bookings = new ArrayList<>();

        try {
            dbConnection = serviceConnection.getConnection();
            BookingAccess bookingAccess = new BookingAccess(dbConnection);
            bookings = bookingAccess.readCancelledBookings();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        return bookings;
    }


}
