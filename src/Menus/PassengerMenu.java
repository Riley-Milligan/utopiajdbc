package Menus;

import servicelayer.ServiceMethods;
import servicelayer.crud.FlightCrud;
import tables.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PassengerMenu {

    Scanner scanner = new Scanner(System.in);
    MainMenu mainMenu = new MainMenu();
    Flight flight = new Flight();
    User activeUser = new User();
    Booking booking = new Booking();

    FlightCrud flightCrud = new FlightCrud();

    List<Flight> flights = new ArrayList<>();

    public void menuOne() throws SQLException {

        System.out.println("Please enter your membership number");

        activeUser = ServiceMethods.getUserByID(scanner.nextInt());

        passengerMenuTwo();
    }

    private void passengerMenuTwo() throws SQLException {

        System.out.println("Type 1 to book a ticket");
        System.out.println("Type 2 to cancel an upcoming trip");
        System.out.println("Type 3 to return to the main menu");

        String response = scanner.next();

        switch (response) {
            case "1":
                passengerBookingMenu();
            case "2":
                passengerCancelMenu();
            case "3":
                mainMenu.mainMenu();
            default:
                System.out.println("Sorry, that is not a valid option");
                passengerMenuTwo();
        }
    }

    private void passengerBookingMenu() throws SQLException {

        System.out.println("Please pick the flight you would like to book a ticket for");

        flights = flightCrud.getAll();
        List<Flight> flightsWithOpenSeats = new ArrayList<>();
        int option = 1;

        for (Flight flight : flights) {
            if (flight.getPlane().getPlaneType().getMaxCapacity() - flight.getReservedSeats() >= 0) {
                flightsWithOpenSeats.add(flight);
            }
        }

        for (Flight flight : flightsWithOpenSeats) {
            System.out.println("Type " + option + " for flight " + flight.getId() + " from " + flight.getRoute().getOrigin().getAirportCode() + ", " +
                    flight.getRoute().getOrigin().getCity() + " to " + flight.getRoute().getDestination().getAirportCode() + ", " +
                    flight.getRoute().getDestination().getCity()
            );
            option++;
        }

        System.out.println("Or type " + option + " to return to the previous menu");

        int response = scanner.nextInt();

        if (response == option) {
            menuOne();
        } else if (response <= flightsWithOpenSeats.size()) {
            flight = flightsWithOpenSeats.get(response - 1);
        } else {
            System.out.println("Sorry, that's not a valid response");
            passengerBookingMenu();
        }

        System.out.println("How many seats would you like to book?");
        response = scanner.nextInt();
        flight.setReservedSeats(flight.getReservedSeats() + response);

        Random random = new Random();
        FlightCrud flightCrud = new FlightCrud();

        Booking booking = new Booking(true, String.valueOf(random.nextInt()));
        FlightBooking flightBooking = new FlightBooking(flight, booking);
        BookingUser bookingUser = new BookingUser(booking, activeUser);

        ServiceMethods.bookingSimultaneousAdd(booking, bookingUser, flightBooking);
        flightCrud.update(flight);
    }


    private void passengerCancelMenu() throws SQLException {

        System.out.println("Please pick the tickets you would like to cancel");

        List<Booking> bookings = ServiceMethods.getBookingsByUser(activeUser);
        flights = ServiceMethods.getFlightsByUser(activeUser);
        Integer option = 1;

        for (Booking booking : bookings) {
            for (Flight flight : flights) {
                System.out.println("Type " + option + " for ticket " + booking.getId() + " from " + flight.getRoute().getOrigin().getAirportCode() + ", " +
                        flight.getRoute().getOrigin().getCity() + " to " + flight.getRoute().getDestination().getAirportCode() + ", " +
                        flight.getRoute().getDestination().getCity()
                );
            }
            option++;
        }

        System.out.println("Or type " + option + " to return to the previous menu");

        int response = scanner.nextInt();

        if (response == option) {
            menuOne();
        } else {
            booking = bookings.get(response - 1);
            System.out.println("Are you sure you want to cancel ticket " + booking.getId() + " from " + flight.getRoute().getOrigin().getAirportCode() + ", " +
                    flight.getRoute().getOrigin().getCity() + " to " + flight.getRoute().getDestination().getAirportCode() + ", " +
                    flight.getRoute().getDestination().getCity() + "?"
            );
            System.out.println("Type y for yes or any other key to cancel");
            String confirm = scanner.next();
            if (confirm.equalsIgnoreCase("y")) {
                ServiceMethods.cancelBooking(booking);
                System.out.println("Canceled successfully!");
                menuOne();
            } else if (confirm.equalsIgnoreCase("n")) {
                menuOne();
            } else {
                System.out.println("Sorry, that was not a valid response.");
                passengerCancelMenu();
            }
        }
    }
}
