package Menus.Admin;

import servicelayer.crud.AirportCrud;
import tables.Airport;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class AirportCrudMenu implements CrudMenu {

    AirportCrud airportCrud = new AirportCrud();
    @Override
    public void entryMenu() throws SQLException {
        System.out.println("What would you like to do?");
        System.out.println("Type 1 to add airports");
        System.out.println("Type 2 to update airports");
        System.out.println("Type 3 to delete airports");
        System.out.println("Type 4 to view all airports");
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

        Airport airport = new Airport();

        System.out.println("What is the airport's IATA ID?");
        airport.setAirportCode(scanner.next().toUpperCase());

        System.out.println("What city is the airport in?");
        airport.setCity(scanner.next());

        System.out.println("Create airport " + airport.getAirportCode() + " in " + airport.getCity() + " ?");
        System.out.println("Type Y for yes or N for no");
        String response = scanner.next();
        if (response.equalsIgnoreCase("y")) {
            airportCrud.add(airport);
            System.out.println("Airport added");
        } else if (response.equalsIgnoreCase("n")) {
            System.out.println("Airport not added");
            entryMenu();
        } else {
            System.out.println("Sorry, that was not a valid option");
            addMenu();
        }

    }

    @Override
    public void updateMenu() throws SQLException {

        List<Airport> airports = airportCrud.getAll();
        Airport airport = new Airport();
        Integer option = 1;

        System.out.println("Which airport would you like to change?");

        for (Airport a : airports) {
            System.out.println("Type " + option + " for " + airport.getAirportCode() + " in " + airport.getCity());
            option++;
        }

        System.out.println("Or type " + option + " to return to the previous menu.");

        Integer response = scanner.nextInt();

        if (response == option) {
            entryMenu();
        } else if (response <= airports.size()) {
            airport = airports.get(response - 1);
        } else {
            System.out.println("Sorry, that was not a valid option");
            updateMenu();
        }

        System.out.println("Enter the new IATA code, or type N/A for no change");
        String input = scanner.next();
        if (input.equalsIgnoreCase("n/a")) {
            System.out.println("Airport code not updated");
        } else {
            airport.setAirportCode(input);
        }

        System.out.println("Enter the new city, or type N/A for no change");
        input = scanner.next();
        if (input.equalsIgnoreCase("n/a")) {
            System.out.println("Airport code not updated");
        } else {
            airport.setCity(input);
        }

        System.out.println("Update airport?  Type Y for yes or anything else for no");
        input = scanner.next();
        if (input.equalsIgnoreCase("y")) {
            airportCrud.update(airport);
            System.out.println("Airport updated!");
        } else {
            System.out.println("Airport not updated");
        }
        entryMenu();
    }

    @Override
    public void deleteMenu() throws SQLException {

        List<Airport> airports = airportCrud.getAll();
        Airport airport = new Airport();
        Integer option = 1;

        System.out.println("What airport would you like to delete?");

        for (Airport a : airports) {
            System.out.println("Type " + option + " for " + airport.getAirportCode() + " in " + airport.getCity());
            option++;
        }

        System.out.println("Or type " + option + " to return to the previous menu.");

        Integer response = scanner.nextInt();

        if (response == option) {
            entryMenu();
        } else if (response <= airports.size()) {
            airport = airports.get(response - 1);
        } else {
            System.out.println("Sorry, that was not a valid option");
            deleteMenu();
        }

        System.out.println("Are you sure you want to delete this airport?  Type Y for yes or any other key for no.");
        String input = scanner.next();
        if (input.equalsIgnoreCase("y")) {
            airportCrud.delete(airport);
            System.out.println("Airport deleted");
        } else {
            System.out.println("Airport not deleted");
        }
        entryMenu();
    }

    @Override
    public void readMenu() throws SQLException {

        List<Airport> airports = airportCrud.getAll();

        System.out.println("What airport would you like to delete?");

        for (Airport a : airports) {
            System.out.println("Airport " + a.getAirportCode() + " in " + a.getCity());
        }

        System.out.println("Type anything to return to the previous menu");

        String input = scanner.next();

        while (input.equals("")) {
            input = scanner.next();
        }

        entryMenu();
    }
}
