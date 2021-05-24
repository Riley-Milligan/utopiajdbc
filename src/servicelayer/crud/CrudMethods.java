package servicelayer.crud;

import servicelayer.ServiceConnection;

import java.sql.SQLException;
import java.util.List;

public interface CrudMethods<T> {

    ServiceConnection serviceConnection = new ServiceConnection();

    List<T> getAll() throws SQLException;

    void add(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
}
