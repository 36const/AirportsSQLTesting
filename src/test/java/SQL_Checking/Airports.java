package SQL_Checking;

import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Airports {

    @Test
    public void airportQuantity() throws SQLException {

        ResultSet result = DBConnecting.sendQuery("SELECT * FROM airports");
        List<String> airports = new ArrayList<>();
        while (result.next()) {
            airports.add(result.getString(1));
        }
        Assert.assertEquals(15, airports.size());
    }

    @Test
    public void notNullQuantity() throws SQLException {

        ResultSet result = DBConnecting.sendQuery("SELECT * FROM airports WHERE (airport OR dutyFree OR priorityBoarding) IS NOT NULL");
        List<String> airports = new ArrayList<>();
        while (result.next()) {
            airports.add(result.getString(1));
        }
        Assert.assertEquals(15, airports.size());
    }

    @Test
    public void dutyFreeQuantity() throws SQLException {

        ResultSet result = DBConnecting.sendQuery("SELECT * FROM airports WHERE dutyFree = 'y'");
        List<String> airports = new ArrayList<>();
        while (result.next()) {
            airports.add(result.getString(1));
        }
        Assert.assertEquals(10, airports.size());
    }

    @Test
    public void priorityBoardingQuantity() throws SQLException {

        ResultSet result = DBConnecting.sendQuery("SELECT * FROM airports WHERE priorityBoarding = 'y'");
        List<String> airports = new ArrayList<>();
        while (result.next()) {
            airports.add(result.getString(1));
        }
        Assert.assertEquals(7, airports.size());
    }
}
