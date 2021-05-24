package servicelayer;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ServiceConnection {

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName(getProperty("driver"));
        Connection dBConnection = DriverManager.getConnection(getProperty("url"), getProperty("username"), getProperty("password"));
        dBConnection.setAutoCommit(false);
        return dBConnection;
    }

    public String getProperty(String property) {
        try (InputStream properties = new FileInputStream("resources/database.properties")){

            Properties fetched = new Properties();
            fetched.load(properties);
            return fetched.getProperty(property);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
