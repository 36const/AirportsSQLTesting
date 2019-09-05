package SQL_Checking;

import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class FlightsAndAirlines {

    @Test
    public void airlinesPerDepartureAirport() throws SQLException {
        ResultSet result = DBConnecting.sendQuery
                ("SELECT fl.departureAirport, COUNT(al.airline) " +
                        "FROM airlines al " +
                        "INNER JOIN flights fl ON fl.flightnumber = al.flightnumber " +
                        "GROUP BY fl.departureAirport;");

        Map<String, Integer> airlinesPerDep = new TreeMap<>();
        while (result.next()) {
            airlinesPerDep.put(result.getString("departureAirport"), result.getInt("COUNT(al.airline)"));
        }

        Assert.assertEquals(1, airlinesPerDep.get("Barcelona").longValue());
        Assert.assertEquals(2, airlinesPerDep.get("Beijing").longValue());
        Assert.assertEquals(1, airlinesPerDep.get("Berlin").longValue());
        Assert.assertEquals(3, airlinesPerDep.get("Budapest").longValue());
        Assert.assertEquals(1, airlinesPerDep.get("Helsinki").longValue());
        Assert.assertEquals(2, airlinesPerDep.get("Kiev").longValue());
        Assert.assertEquals(4, airlinesPerDep.get("London").longValue());
        Assert.assertEquals(2, airlinesPerDep.get("Milan").longValue());
        Assert.assertEquals(1, airlinesPerDep.get("Munich").longValue());
        Assert.assertEquals(6, airlinesPerDep.get("New York").longValue());
        Assert.assertEquals(3, airlinesPerDep.get("Ottava").longValue());
        Assert.assertEquals(4, airlinesPerDep.get("Paris").longValue());
        Assert.assertEquals(1, airlinesPerDep.get("Prague").longValue());
        Assert.assertEquals(3, airlinesPerDep.get("Sydney").longValue());
        Assert.assertEquals(1, airlinesPerDep.get("Vilnius").longValue());
    }

    @Test
    public void airlinesPerArrivalAirport() throws SQLException {
        ResultSet result = DBConnecting.sendQuery
                ("SELECT fl.arrivalAirport, COUNT(al.airline) " +
                        "FROM airlines al " +
                        "INNER JOIN flights fl ON fl.flightnumber = al.flightnumber " +
                        "GROUP BY fl.arrivalAirport;");

        Map<String, Integer> airlinesPerArr = new TreeMap<>();
        while (result.next()) {
            airlinesPerArr.put(result.getString("arrivalAirport"), result.getInt(2));
        }

        Assert.assertEquals(3, airlinesPerArr.get("Barcelona").longValue());
        Assert.assertEquals(1, airlinesPerArr.get("Beijing").longValue());
        Assert.assertEquals(1, airlinesPerArr.get("Berlin").longValue());
        Assert.assertEquals(1, airlinesPerArr.get("Budapest").longValue());
        Assert.assertEquals(4, airlinesPerArr.get("Helsinki").longValue());
        Assert.assertEquals(3, airlinesPerArr.get("Kiev").longValue());
        Assert.assertEquals(2, airlinesPerArr.get("London").longValue());
        Assert.assertEquals(5, airlinesPerArr.get("Milan").longValue());
        Assert.assertEquals(3, airlinesPerArr.get("Munich").longValue());
        Assert.assertEquals(3, airlinesPerArr.get("New York").longValue());
        Assert.assertEquals(3, airlinesPerArr.get("Ottava").longValue());
        Assert.assertEquals(1, airlinesPerArr.get("Prague").longValue());
        Assert.assertEquals(2, airlinesPerArr.get("Sydney").longValue());
        Assert.assertEquals(3, airlinesPerArr.get("Vilnius").longValue());
    }

}
