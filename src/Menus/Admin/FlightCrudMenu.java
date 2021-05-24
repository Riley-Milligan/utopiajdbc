package Menus.Admin;

import servicelayer.crud.FlightCrud;
import tables.Flight;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FlightCrudMenu implements CrudMenu {

    FlightCrud flightCrud = new FlightCrud();

    @Override
    public void entryMenu() throws SQLException {
        System.out.println("What would you like to do?");
        System.out.println("Type 1 to add flights");
        System.out.println("Type 2 to update flights");
        System.out.println("Type 3 to delete flights");
        System.out.println("Type 4 to view all flights");
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
        String response;

        System.out.println("What airport is your flight starting at?");
        response = scanner.next().toUpperCase();
        flight.getRoute().getOrigin().setAirportCode(response);

        System.out.println("What airport is your flight landing at?");
        response = scanner.next().toUpperCase();
        flight.getRoute().getDestination().setAirportCode(response);

        System.out.println("What's the ID of the plane for this flight?");
        response = scanner.next();
        flight.getPlane().setId(Integer.parseInt(response));

        System.out.println("When is this flight leaving, in MM-DD-YYYY HH:MM format?");
        response = scanner.nextLine();
        flight.setDepartureTime(LocalDateTime.parse(response, DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm")));

        System.out.println("How many seats are reserved on this flight?");
        response = scanner.next();
        flight.setReservedSeats(Integer.parseInt(response));

        System.out.println("How much does a seat on this flight cost?");
        response = scanner.next();
        flight.setSeatPrice(Float.parseFloat(response));

        System.out.println("Add this flight?  Type Y for yes or any other key for no");
        response = scanner.next();
        if (response.equalsIgnoreCase("y")) {
            flightCrud.add(flight);
            System.out.println("Flight added!");
        } else {
            System.out.println("Flight not added");
        }
        entryMenu();
    }

    @Override
    public void updateMenu() throws SQLException {

        List<Flight> flights = flightCrud.getAll();
        Flight flight = new Flight();
        Integer option = 1;
        String dateString = "";

        System.out.println("What flight would you like to update?");
        for (Flight f : flights) {
            System.out.println("Type " + option + " for flight " + flight.getId() + " from " + flight.getRoute().getOrigin().getAirportCode() + ", " +
                    flight.getRoute().getOrigin().getCity() + " to " + flight.getRoute().getDestination().getAirportCode() + ", " +
                    flight.getRoute().getDestination().getCity()
            );
            option++;
        }

        System.out.println("Or type " + option + " to return to the previous menu.");

        Integer response = scanner.nextInt();
        if (response == option) {
            entryMenu();
        } else if (response <= flights.size()) {
            flight = flights.get(response - 1);
        } else {
            System.out.println("Sorry, that was not a valid option");
            updateMenu();
        }

        System.out.println("Type 'quit' at any time to cancel and return to the previous menu");


        System.out.println("Please enter new origin airport ID or enter N/A for no change");
        String input = scanner.next();
        if (input.equalsIgnoreCase("quit")) {
            System.out.println("Canceling the update");
            entryMenu();
        } else if (input.equalsIgnoreCase("n/a")) {
            System.out.println("Origin airport not updated");
        } else {
            flight.getRoute().getOrigin().setAirportCode(input.toUpperCase());
        }

        System.out.println("Please enter new destination airport ID or enter N/A for no change");
        input = scanner.next();
        if (input.equalsIgnoreCase("quit")) {
            System.out.println("Canceling the update");
            entryMenu();
        } else if (input.equalsIgnoreCase("n/a")) {
            System.out.println("Destination airport not updated");
        } else {
            flight.getRoute().getDestination().setAirportCode(input.toUpperCase());
        }

        System.out.println("Please enter a new departure date as MM-DD-YYYY or enter N/A for no change");
        input = scanner.next();
        if (input.equalsIgnoreCase("quit")) {
            System.out.println("Canceling the update");
            entryMenu();
        } else if (input.equalsIgnoreCase("n/a")) {
            dateString = flight.getDepartureTime().toLocalDate().toString();
            System.out.println("Departure date not updated");
        } else {
            dateString = input;
        }

        System.out.println("Please enter a new departure time as HH:MM or enter N/A for no change");
        if (input.equalsIgnoreCase("quit")) {
            System.out.println("Canceling the update");
            entryMenu();
        } else if (input.equalsIgnoreCase("n/a")) {
            dateString = dateString.concat(flight.getDepartureTime().toLocalTime().toString());
            System.out.println("Departure date not updated");
        } else {
            dateString = dateString.concat(" " + input);
        }

        flight.setDepartureTime(LocalDateTime.parse(input, DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm")));

        System.out.println("Update this flight? Type Y for yes or any other key for no");
        input = scanner.next();
        if (input.equalsIgnoreCase("y")) {
            flightCrud.update(flight);
            System.out.println("Update successful!");
        } else {
            System.out.println("Flight not updated");
        }
        entryMenu();
    }

    @Override
    public void deleteMenu() throws SQLException {

        List<Flight> flights = flightCrud.getAll();
        Flight flight = new Flight();
        Integer option = 1;

        System.out.println("What flight would you like to delete?");
        for (Flight f : flights) {
            System.out.println("Type " + option + " for flight " + flight.getId() + " from " + flight.getRoute().getOrigin().getAirportCode() + ", " +
                    flight.getRoute().getOrigin().getCity() + " to " + flight.getRoute().getDestination().getAirportCode() + ", " +
                    flight.getRoute().getDestination().getCity()
            );
            option++;
        }

        System.out.println("Or type " + option + " to return to the previous menu");

        Integer response = scanner.nextInt();

        if (response == option) {
            entryMenu();
        } else if (response <= flights.size()) {
            flight = flights.get(response - 1);
        } else {
            System.out.println("Sorry, that wasn't a valid option.");
            deleteMenu();
        }

        System.out.println("Are you sure you want to delete this flight?  Type Y for yes or any other key for no.");
        String input = scanner.next();
        if (input.equalsIgnoreCase("y")) {
            flightCrud.delete(flight);
            System.out.println("Flight deleted!");
        } else {
            System.out.println("Flight not deleted");
        }
        entryMenu();
    }

    @Override
    public void readMenu() throws SQLException {

        List<Flight> flights = flightCrud.getAll();

        for (Flight f: flights) {
            System.out.println("Flight " + f.getId() + " from " + f.getRoute().getOrigin().getAirportCode() + " to " + f.getRoute().getDestination().getAirportCode());
        }

        System.out.println("Type any key to return to previous menu");
        String input = scanner.next();

        while (input.equals("")) {
            input = scanner.next();
        }

        entryMenu();
    }
}
