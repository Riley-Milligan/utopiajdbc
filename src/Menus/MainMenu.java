package Menus;

import Menus.Admin.AdminMenu;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {

    static Scanner scanner = new Scanner(System.in);

    EmployeeMenu employeeMenu = new EmployeeMenu();
    PassengerMenu passengerMenu = new PassengerMenu();
    AdminMenu adminMenu = new AdminMenu();

    public void mainMenu() throws SQLException {
        System.out.println("Welcome to the Utopia Airlines Management System. Which category of  user are you?");
        System.out.println("Type 1 for employee");
        System.out.println("Type 2 for administrator");
        System.out.println("Type 3 for Passenger");

        int response = scanner.nextInt();

        getRoleMenu(response);
    }

    public void getRoleMenu(int input) throws SQLException {
        switch (input) {
            case 1:
                employeeMenu.menuOne();
            case 2:
                adminMenu.menuOne();
            case 3:
                passengerMenu.menuOne();
            default:
                System.out.println("That was not a valid response.  Please try again.");
                mainMenu();
        }
    }

}
