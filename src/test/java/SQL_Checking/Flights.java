package SQL_Checking;

import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Flights {

    @Test
    public void flightNumberQuantity() throws SQLException {

        ResultSet result = DBConnecting.sendQuery("SELECT * FROM flights");
        List<String> flights = new ArrayList<String>();
        while (result.next()) {
            flights.add(result.getString(1));
        }
        Assert.assertEquals(35, flights.size());
    }

    @Test
    public void notNullQuantity() throws SQLException {

        ResultSet result = DBConnecting.sendQuery
                ("SELECT * FROM flights WHERE (flightNumber OR departureAirport OR arrivalAirport OR stopsNumber " +
                                            "OR averageTicketPrice OR availableSeats) IS NOT NULL");
        List<String> airports = new ArrayList<String>();
        while (result.next()) {
            airports.add(result.getString(1));
        }
        Assert.assertEquals(35, airports.size());
    }

    @Test
    public void stopsNumber0Quantity() throws SQLException {

        ResultSet result = DBConnecting.sendQuery("SELECT * FROM flights WHERE stopsNumber = 0");
        List<String> flights = new ArrayList<String>();
        while (result.next()) {
            flights.add(result.getString(1));
        }
        Assert.assertEquals(14, flights.size());
    }

    @Test
    public void stopsNumber1Quantity() throws SQLException {

        ResultSet result = DBConnecting.sendQuery("SELECT * FROM flights WHERE stopsNumber = 1");
        List<String> flights = new ArrayList<String>();
        while (result.next()) {
            flights.add(result.getString(1));
        }
        Assert.assertEquals(16, flights.size());
    }

    @Test
    public void stopsNumber2Quantity() throws SQLException {

        ResultSet result = DBConnecting.sendQuery("SELECT * FROM flights WHERE stopsNumber = 2");
        List<String> flights = new ArrayList<String>();
        while (result.next()) {
            flights.add(result.getString(1));
        }
        Assert.assertEquals(5, flights.size());
    }

    @Test
    public void averageTicketPrice() throws SQLException {

        ResultSet result = DBConnecting.sendQuery("SELECT * FROM flights WHERE averageTicketPrice >= 0");
        List<String> flights = new ArrayList<String>();
        while (result.next()) {
            flights.add(result.getString(1));
        }
        Assert.assertEquals(35, flights.size());
    }

}
