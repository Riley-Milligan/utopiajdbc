package Menus.Admin;

import java.sql.SQLException;

public class SeatCrudMenu implements CrudMenu {


    @Override
    public void entryMenu() throws SQLException {
        System.out.println("Sorry, this feature is under development");
        adminMenu.menuOne();
    }

    @Override
    public void addMenu() throws SQLException {

    }

    @Override
    public void updateMenu() throws SQLException {

    }

    @Override
    public void deleteMenu() throws SQLException {

    }

    @Override
    public void readMenu() throws SQLException {

    }
}
