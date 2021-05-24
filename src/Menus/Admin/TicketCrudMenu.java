package Menus.Admin;

import servicelayer.ServiceMethods;
import servicelayer.crud.*;
import tables.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class TicketCrudMenu implements CrudMenu {

    Random random = new Random();
    FlightCrud flightCrud = new FlightCrud();
    UserCrud userCrud = new UserCrud();
    BookingUserCrud bookingUserCrud = new BookingUserCrud();
    BookingCrud bookingCrud = new BookingCrud();
    FlightBookingCrud flightBookingCrud = new FlightBookingCrud();

    @Override
    public void entryMenu() throws SQLException {
        System.out.println("What would you like to do?");
        System.out.println("Type 1 to add tickets");
        System.out.println("Type 2 to update tickets");
        System.out.println("Type 3 to delete tickets");
        System.out.println("Type 4 to view all tickets");
        System.out.println("Type 5 to return to the previous menu");

        String response = scanner.next();

        switch (response) {
            case "1":
                addMenu();
            case "2":
                updateMenu();
            case "3":
                deleteMenu();
            case "4":
                readMenu();
            case "5":
                adminMenu.menuOne();
            default:
                System.out.println("Sorry, that was not a valid option");
                entryMenu();
        }
    }

    @Override
    public void addMenu() throws SQLException {
        Flight flight = new Flight();
        User user = new User();

        System.out.println("What user ID is this ticket for?");
        List<User> users = userCrud.getAll();
        for (User u : users) {
            System.out.println("User " + user.getId() + ": " + user.getGivenName() + " " + user.getFamilyName());
        }
        user = ServiceMethods.getUserByID(scanner.nextInt());

        System.out.println("What flight ID is this ticket for?");
        List<Flight> flights = flightCrud.getAll();
        for (Flight f : flights) {
            System.out.println(f.getId() + " from " + f.getRoute().getOrigin().getAirportCode() + " to " + f.getRoute().getDestination().getAirportCode());
        }
        flight = ServiceMethods.getFlight(scanner.nextInt());

        int confirmationCode = random.nextInt();

        Booking booking = new Booking(true, String.valueOf(confirmationCode));
        FlightBooking flightBooking = new FlightBooking(flight, booking);
        BookingUser bookingUser = new BookingUser(booking, user);

        ServiceMethods.bookingSimultaneousAdd(booking, bookingUser, flightBooking);

        System.out.println("Ticket added!");
        entryMenu();
    }

    @Override
    public void updateMenu() throws SQLException {

        System.out.println("Which Ticket ID would you like to update?");
        List<Booking> bookings = bookingCrud.getAll();
        for (Booking booking : bookings) {
            BookingUser bookingUser = ServiceMethods.getBookingUserByBooking(booking);
            FlightBooking flightBooking = ServiceMethods.getFlightBookingByBooking(booking);

            System.out.println("Ticket " + booking.getId() + " for " + bookingUser.getUser().getGivenName() + " " + bookingUser.getUser().getGivenName() +
                    " on flight " + flightBooking.getFlight().getId());
        }

        System.out.println("Or type 0 to change no tickets and return to the previous menu");

        int response = scanner.nextInt();

        if (response == 0) {
            entryMenu();
        }

        Booking booking = ServiceMethods.getBookingByID(response);
        BookingUser bookingUser = ServiceMethods.getBookingUserByBooking(booking);
        FlightBooking flightBooking = ServiceMethods.getFlightBookingByBooking(booking);

        System.out.println("Please enter the new flight ID or type N/A for no change");
        if (scanner.hasNextInt()) {
            flightBooking.setFlightID(ServiceMethods.getFlightByID(scanner.nextInt()));
        } else if (scanner.next().equalsIgnoreCase("n/a")) {
            System.out.println("No changes made to flight");
        } else {
            System.out.println("That is not a valid option. Please try again.");
            updateMenu();
        }

        System.out.println("Please enter the new user ID or type N/A for no change");
        if (scanner.hasNextInt()) {
            bookingUser.setUser(ServiceMethods.getUserByID(scanner.nextInt()));
        } else if (scanner.next().equalsIgnoreCase("n/a")) {
            System.out.println("No changes made to flight");
        } else {
            System.out.println("That is not a valid option. Please try again.");
            updateMenu();
        }

        System.out.println("Please enter 1 for an active booking, 0 for an inactive booking, or N/A for no change");
        if (scanner.hasNextInt()) {
            response = scanner.nextInt();
            if (response == 1 || response == 0) {
                booking.setActive(response);
            } else {
                System.out.println("That is not a valid option. Please try again.");
                updateMenu();
            }
        } else if (scanner.next().equalsIgnoreCase("n/a")) {
            System.out.println("No changes made to flight");
        } else {
            System.out.println("That is not a valid option. Please try again.");
            updateMenu();
        }

        ServiceMethods.bookingSimultaneousUpdate(booking, bookingUser, flightBooking);

        System.out.println("Updates made successfully!");
        entryMenu();
    }

    @Override
    public void deleteMenu() throws SQLException {

        System.out.println("Which Ticket ID would you like to delete?");
        List<Booking> bookings = bookingCrud.getAll();
        for (Booking booking : bookings) {
            BookingUser bookingUser = ServiceMethods.getBookingUserByBooking(booking);
            FlightBooking flightBooking = ServiceMethods.getFlightBookingByBooking(booking);

            System.out.println("Ticket " + booking.getId() + " for " + bookingUser.getUser().getGivenName() + " " + bookingUser.getUser().getGivenName() +
                    " on flight " + flightBooking.getFlight().getId());
        }

        System.out.println("Or type 0 to delete none and return to the previous menu");

        int response = scanner.nextInt();

        if (response == 0) {
            entryMenu();
        }

        Booking booking = ServiceMethods.getBookingByID(response);
        BookingUser bookingUser = ServiceMethods.getBookingUserByBooking(booking);
        FlightBooking flightBooking = ServiceMethods.getFlightBookingByBooking(booking);

        System.out.println("Are you sure you want to delete this ticket? Type Y for yes or any other key for no");
        if (scanner.next().equalsIgnoreCase("y")) {
            ServiceMethods.bookingSimultaneousDelete(booking, bookingUser, flightBooking);
            System.out.println("Ticket Deleted");
        } else {
            System.out.println("Ticket not deleted");
        }
        entryMenu();
    }

    @Override
    public void readMenu() throws SQLException {
        List<Booking> bookings = bookingCrud.getAll();
        for (Booking booking : bookings) {
            FlightBooking flightBooking = ServiceMethods.getFlightBookingByBooking(booking);
            BookingUser bookingUser = ServiceMethods.getBookingUserByBooking(booking);
            System.out.println("Ticket " + booking.getId() + " for " + bookingUser.getUser().getGivenName() + " " + bookingUser.getUser().getGivenName() +
                    " on flight " + flightBooking.getFlight().getId());
        }

        System.out.println("type anything to return to the previous menu");

        String response = scanner.next();
        while (response.equals("")) {
            response = scanner.next();
        }

        entryMenu();
    }
}
