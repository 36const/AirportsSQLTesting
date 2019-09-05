package SQL_Checking;

import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Airlines {

    @Test
    public void flightNumberQuantity() throws SQLException {

        ResultSet result = DBConnecting.sendQuery("SELECT * FROM airlines");
        List<String> airlines = new ArrayList<>();
        while (result.next()) {
            airlines.add(result.getString(1));
        }
        Assert.assertEquals(35, airlines.size());
    }

    @Test
    public void notNullQuantity() throws SQLException {

        ResultSet result = DBConnecting.sendQuery
                ("SELECT * FROM airlines WHERE (flightNumber OR airline OR webRegistration OR isMealincluded) IS NOT NULL");
        List<String> airlines = new ArrayList<>();
        while (result.next()) {
            airlines.add(result.getString(1));
        }
        Assert.assertEquals(35, airlines.size());
    }

    @Test
    public void additionalSpaceServiceQuantity() throws SQLException {

        ResultSet result = DBConnecting.sendQuery("SELECT * FROM airlines WHERE additionalSpaceService = 'y'");
        List<String> airlines = new ArrayList<>();
        while (result.next()) {
            airlines.add(result.getString(1));
        }
        Assert.assertEquals(17, airlines.size());
    }

    @Test
    public void webRegistrationQuantity() throws SQLException {

        ResultSet result = DBConnecting.sendQuery("SELECT * FROM airlines WHERE webRegistration = 'y'");
        List<String> airlines = new ArrayList<>();
        while (result.next()) {
            airlines.add(result.getString(1));
        }
        Assert.assertEquals(17, airlines.size());
    }

    @Test
    public void isMealincludedQuantity() throws SQLException {

        ResultSet result = DBConnecting.sendQuery("SELECT * FROM airlines WHERE isMealincluded = 'y';");
        List<String> airlines = new ArrayList<>();
        while (result.next()) {
            airlines.add(result.getString(1));
        }
        Assert.assertEquals(17, airlines.size());
    }

    @Test
    public void uniqueAirlinesQuantity() throws SQLException {

        ResultSet result = DBConnecting.sendQuery("SELECT count(airline) FROM airlines GROUP BY airline;");
        List<String> airlines = new ArrayList<>();
        while (result.next()) {
            airlines.add(result.getString(1));
        }
        Assert.assertEquals(11, airlines.size());
    }

}
