package Menus;

import servicelayer.crud.FlightCrud;
import tables.Flight;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeMenu {

    private List<Flight> flights = new ArrayList<>();
    private Flight flight = new Flight();

    FlightCrud flightCrud = new FlightCrud();

    MainMenu mainMenu = new MainMenu();
    Scanner scanner = new Scanner(System.in);

    public void menuOne() throws SQLException {

        System.out.println("Type 1 to view your managed flights");
        System.out.println("Type 2 to return to the main menu");

        int response = scanner.nextInt();

        switch (response) {
            case 1:
                empFlightsMenu();
            case 2:
                mainMenu.mainMenu();
            default:
                System.out.println("Sorry, that was not a valid selection");
        }
    }

    private void empFlightsMenu() throws SQLException {

        flights = flightCrud.getAll();
        int option = 1;

        System.out.println("Please select from the following flights: ");

        for (Flight flight : flights) {
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
        } else if (response <= flights.size()) {
            flight = flights.get(response - 1);
            empFlightMenu();
        } else {
            System.out.println("Sorry, that was not a valid option");
            empFlightsMenu();
        }
    }

    private void empFlightMenu() throws SQLException {

        System.out.println("Type 1 to view more details about this flight");
        System.out.println("Type 2 to update the details of this flight");
        System.out.println("Type 3 to add seats to this flight");
        System.out.println("Type 4 to return to the previous menu");

        int response = scanner.nextInt();

        switch (response) {
            case 1:
                empFlightDetails();
            case 2:
                empFlightUpdate();
            case 3:
                empFlightAddSeats();
            default:
                System.out.println("Sorry, that was not a valid option");
                empFlightMenu();
        }
    }

    private void empFlightDetails() throws SQLException {

        System.out.println("You have chosen to view the flight with ID " + flight.getId() + ", departure airport " + flight.getRoute().getOrigin().getAirportCode() +
                ", and arrival airport " + flight.getRoute().getDestination().getAirportCode() + ". \n");
        System.out.println("Departure date and time: " + flight.getDepartureTime());
        System.out.println("Available Seats: " + (flight.getPlane().getPlaneType().getMaxCapacity() - flight.getReservedSeats()));

        System.out.println("Type 1 to return to the previous menu");

        int response = scanner.nextInt();

        if (response == 1) {
            empFlightMenu();
        } else {
            System.out.println("Sorry, that was not a valid option");
            empFlightDetails();
        }
    }

    private void empFlightUpdate() throws SQLException {

        String dateString = "";

        System.out.println("You have chosen to update the flight with ID + " + flight.getId() + ", departure airport " + flight.getRoute().getOrigin().getAirportCode() +
                ", and arrival airport " + flight.getRoute().getDestination().getAirportCode());
        System.out.println("Type 'quit' at any time to cancel and return to the previous menu");

        System.out.println("Please enter new origin airport ID or enter N/A for no change");
        String response = scanner.next();
        if (response.equalsIgnoreCase("quit")) {
            System.out.println("Canceling the update");
            empFlightDetails();
        } else if (response.equalsIgnoreCase("n/a")) {
            System.out.println("Origin airport not updated");
        } else {
            flight.getRoute().getOrigin().setAirportCode(response.toUpperCase());
        }

        System.out.println("Please enter new destination airport ID or enter N/A for no change");
        response = scanner.next();
        if (response.equalsIgnoreCase("quit")) {
            System.out.println("Canceling the update");
            empFlightDetails();
        } else if (response.equalsIgnoreCase("n/a")) {
            System.out.println("Destination airport not updated");
        } else {
            flight.getRoute().getDestination().setAirportCode(response.toUpperCase());
        }

        System.out.println("Please enter a new departure date as MM-DD-YYYY or enter N/A for no change");
        response = scanner.next();
        if (response.equalsIgnoreCase("quit")) {
            System.out.println("Canceling the update");
            empFlightDetails();
        } else if (response.equalsIgnoreCase("n/a")) {
            dateString = flight.getDepartureTime().toLocalDate().toString();
            System.out.println("Departure date not updated");
        } else {
            dateString = response;
        }

        System.out.println("Please enter a new departure time as HH:MM or enter N/A for no change");
        if (response.equalsIgnoreCase("quit")) {
            System.out.println("Canceling the update");
            empFlightDetails();
        } else if (response.equalsIgnoreCase("n/a")) {
            dateString = dateString.concat(flight.getDepartureTime().toLocalTime().toString());
            System.out.println("Departure date not updated");
        } else {
            dateString = dateString.concat(" " + response);
        }

        flight.setDepartureTime(LocalDateTime.parse(response, DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm")));

        flightCrud.update(flight);
        System.out.println("Update successful!  returning to previous menu.");
        empFlightDetails();
    }

    public void empFlightAddSeats() throws SQLException {
        System.out.println("Current available seats: " + (flight.getPlane().getPlaneType().getMaxCapacity() - flight.getReservedSeats()));
        System.out.println("How many seats would you like to add?");
        int response = scanner.nextInt();
        flight.setReservedSeats(flight.getReservedSeats() - response);

        flightCrud.update(flight);
        System.out.println("Successfully added " + response + "seats");
        empFlightDetails();
    }
}
