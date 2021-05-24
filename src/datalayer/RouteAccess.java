package datalayer;

import tables.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteAccess extends DataAccess<Route> {

    public RouteAccess(Connection dbConnection) {
        super(dbConnection);
    }

    public void addRoute(Route route) throws SQLException {
        save("INSERT into route (origin_id, destination_id) values (?, ?)",
                new Object[] {route.getOrigin().getAirportCode(), route.getDestination().getAirportCode()});
    }

    public void updateRoute(Route route) throws SQLException {
        save("UPDATE route set origin_id = ?, destination_id = ?, where id = ?",
                new Object[] {route.getOrigin().getAirportCode(), route.getDestination().getAirportCode(), route.getId()});
    }

    public void deleteRoute(Route route) throws SQLException {
        save("DELETE from route where id = ?", new Object[] {route.getId()});
    }

    public List<Route> readAllRoutes() throws SQLException {
        return read("SELECT * from route", null);
    }

    public List<Route> readAllRoutesByIATA(String iata) throws SQLException {
        return read("SELECT * from route where origin_id = ? OR destination_id = ?", new Object[] {iata, iata});
    }

    @Override
    protected List<Route> getData(ResultSet results) throws SQLException {
        List<Route> routes = new ArrayList<>();
        while (results.next()) {
            Route route = new Route();
            route.setId(results.getInt("id"));
            route.getOrigin().setAirportCode(results.getString("origin_id"));
            route.getDestination().setAirportCode(results.getString("destination_id"));
            routes.add(route);
        }
        return routes;
    }
}
