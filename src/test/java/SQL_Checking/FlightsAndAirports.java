package SQL_Checking;

import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightsAndAirports {

    @Test
    public void flightNumberQuantity() throws SQLException {

        ResultSet resultFlights = DBConnecting.sendQuery("SELECT COUNT(DISTINCT departureAirport) FROM flights");
        List<String> flights = new ArrayList<String>();
        while (resultFlights.next()) {
            flights.add(resultFlights.getString(1));
        }

        ResultSet resultAirports = DBConnecting.sendQuery("SELECT COUNT(*) FROM airports");
        List<String> airports = new ArrayList<String>();
        while (resultAirports.next()) {
            airports.add(resultAirports.getString(1));
        }

        Assert.assertTrue(flights.equals(airports));
    }

}
