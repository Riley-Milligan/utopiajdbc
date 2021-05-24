package Menus.Admin;

import servicelayer.crud.UserCrud;
import tables.User;
import tables.UserRole;

import java.sql.SQLException;
import java.util.List;

public class EmployeeCrudMenu implements CrudMenu {

    UserRole employee = new UserRole(2);
    UserCrud userCrud = new UserCrud();

    @Override
    public void entryMenu() throws SQLException {
        System.out.println("What would you like to do?");
        System.out.println("Type 1 to add employees");
        System.out.println("Type 2 to update employees");
        System.out.println("Type 3 to delete employees");
        System.out.println("Type 4 to view all employees");
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

        User user = new User();
        user.setRole(employee);

        System.out.println("What is the user's given name?");
        String response = scanner.next();
        user.setGivenName(response);

        System.out.println("What is the user's family name?");
        response = scanner.next();
        user.setFamilyName(response);

        System.out.println("What is the user's username?");
        response = scanner.next();
        user.setUserName(response);

        System.out.println("What is the user's email?");
        response = scanner.next();
        user.setEmail(response);

        System.out.println("What is the user's password");
        response = scanner.next();
        user.setPassword(response);

        System.out.println("What is the user's phone number?");
        response = scanner.next();
        user.setPhone(response);

        System.out.println("Add this user?  Type Y for yes or any other key for no");
        response = scanner.next();
        if (response.equalsIgnoreCase("y")) {
            userCrud.add(user);
            System.out.println("User added successfully!");
        } else {
            System.out.println("User not added");
        }
        entryMenu();
    }

    @Override
    public void updateMenu() throws SQLException {

        List<User> employees = userCrud.getUsersByRole(employee);
        User user = new User();
        Integer option = 1;

        System.out.println("Which user would you like to update?");

        for (User u : employees) {
            System.out.println("Type " + option + " for assenger " + u.getId() + ": " + u.getGivenName() + " " + u.getFamilyName());
            option++;
        }

        System.out.println("or type " + option + " to return to the previous menu");
        Integer response = scanner.nextInt();

        if (response == option) {
            entryMenu();
        } else if (response <= employees.size()) {
            user = employees.get(response - 1);
        } else {
            System.out.println("Sorry, that was not a valid selection");
            updateMenu();
        }

        System.out.println("Enter the user's new given name, or type N/A to skip");
        String input = scanner.next();
        if (input.equalsIgnoreCase("n/a")) {
            System.out.println("Airport code not updated");
        } else {
            user.setGivenName(input);
        }

        System.out.println("Enter the user's new family name, or type N/A to skip");
        input = scanner.next();
        if (input.equalsIgnoreCase("n/a")) {
            System.out.println("Airport code not updated");
        } else {
            user.setFamilyName(input);
        }

        System.out.println("Enter the user's new username, or type N/A to skip");
        input = scanner.next();
        if (input.equalsIgnoreCase("n/a")) {
            System.out.println("Airport code not updated");
        } else {
            user.setUserName(input);
        }

        System.out.println("Enter the user's new email, or type N/A to skip");
        input = scanner.next();
        if (input.equalsIgnoreCase("n/a")) {
            System.out.println("Airport code not updated");
        } else {
            user.setEmail(input);
        }

        System.out.println("Enter the user's new password, or type N/A to skip");
        input = scanner.next();
        if (input.equalsIgnoreCase("n/a")) {
            System.out.println("Airport code not updated");
        } else {
            user.setPassword(input);
        }

        System.out.println("Enter the user's new phone number, or type N/A to skip");
        input = scanner.next();
        if (input.equalsIgnoreCase("n/a")) {
            System.out.println("Airport code not updated");
        } else {
            user.setPhone(input);
        }

        System.out.println("Update this user?  Type Y for yes or any other key for no");
        input = scanner.next();
        if (input.equalsIgnoreCase("y")){
            userCrud.update(user);
            System.out.println("User Updated Successfully");
        } else {
            System.out.println("User not updated");
        }
        entryMenu();
    }

    @Override
    public void deleteMenu() throws SQLException {

        List<User> employees = userCrud.getUsersByRole(employee);
        User user = new User();
        Integer option = 1;

        System.out.println("Which user would you like to delete?");

        for (User u : employees) {
            System.out.println("Type " + option + " for assenger " + u.getId() + ": " + u.getGivenName() + " " + u.getFamilyName());
            option++;
        }

        System.out.println("or type " + option + " to return to the previous menu");
        Integer response = scanner.nextInt();

        if (response == option) {
            entryMenu();
        } else if (response <= employees.size()) {
            user = employees.get(response - 1);
        } else {
            System.out.println("Sorry, that was not a valid selection");
            updateMenu();
        }

        System.out.println("Delete this user?  Type Y for yes or any other key for no");
        String input = scanner.next();
        if (input.equalsIgnoreCase("y")){
            userCrud.delete(user);
            System.out.println("User Updated Successfully");
        } else {
            System.out.println("User not updated");
        }
        entryMenu();
    }

    @Override
    public void readMenu() throws SQLException {

        List<User> employees = userCrud.getUsersByRole(employee);
        User user = new User();

        for (User u : employees) {
            System.out.println("User " + u.getId() + ": " + u.getGivenName() + " " + u.getFamilyName());
        }

        System.out.println("Type anything to return to the previous menu");

        String input = scanner.next();

        while (input.equals("")) {
            input = scanner.next();
        }

        entryMenu();
    }
}
