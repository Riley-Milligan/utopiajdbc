package datalayer;

import tables.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DataAccess<T> {

    public static Connection dbConnection = null;

    public DataAccess(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void save(String sql, Object[] inputs) throws SQLException {

        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        if (inputs != null) {
            int i = 1;
            for (Object o : inputs) {
                preparedStatement.setObject(i, o);
                i++;
            }
        }

        preparedStatement.executeUpdate();
    }

    public List<T> read(String sql, Object[] inputs) throws SQLException {

        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        if(inputs != null) {
            int i = 1;
            for (Object o : inputs) {
                preparedStatement.setObject(i, o);
                i++;
            }
        }

        return getData(preparedStatement.executeQuery());
    }

    protected abstract List<T> getData(ResultSet results) throws SQLException;
}
