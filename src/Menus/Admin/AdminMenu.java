package Menus.Admin;

import Menus.MainMenu;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminMenu {

    AirportCrudMenu airportCrudMenu = new AirportCrudMenu();
    EmployeeCrudMenu employeeMenuAdmin = new EmployeeCrudMenu();
    FlightCrudMenu flightCrudMenu = new FlightCrudMenu();
    PassengerCrudMenu passengerCrudMenu = new PassengerCrudMenu();
    SeatCrudMenu seatCrudMenu = new SeatCrudMenu();
    TicketCrudMenu ticketCrudMenu = new TicketCrudMenu();
    TicketOverrideMenu ticketOverrideMenu = new TicketOverrideMenu();
    MainMenu mainMenu = new MainMenu();

    Scanner scanner = new Scanner(System.in);

    public void menuOne() throws SQLException {
        System.out.println("What would you like to do?");
        System.out.println("Type 1 to Add/Update/Delete/Read flights");
        System.out.println("Type 2 to Add/Update/Delete/Read seats");
        System.out.println("Type 3 to Add/Update/Delete/Read Tickets");
        System.out.println("Type 4 to Add/Update/Delete/Read Airports");
        System.out.println("Type 5 to Add/Update/Delete/Read Passengers");
        System.out.println("Type 6 to Add/Update/Delete/Read Employees");
        System.out.println("Type 7 to override ticket cancellation");
        System.out.println("Type 8 to return to the main menu");

        String response = scanner.next();

        switch (response) {
            case "1":
                flightCrudMenu.entryMenu();
            case "2":
                seatCrudMenu.entryMenu();
            case "3":
                ticketCrudMenu.entryMenu();
            case "4":
                airportCrudMenu.entryMenu();
            case "5":
                passengerCrudMenu.entryMenu();
            case "6":
                employeeMenuAdmin.entryMenu();
            case "7":
                ticketOverrideMenu.entryMenu();
            case "8":
                mainMenu.mainMenu();
            default:
                System.out.println("Sorry, that was not a valid option");
                menuOne();
        }
    }
}
