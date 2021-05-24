package Menus.Admin;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public interface CrudMenu {

    Scanner scanner = new Scanner(System.in);
    AdminMenu adminMenu = new AdminMenu();

    void entryMenu() throws SQLException;
    void addMenu() throws SQLException;
    void updateMenu() throws SQLException;
    void deleteMenu() throws SQLException;
    void readMenu() throws SQLException;
}
