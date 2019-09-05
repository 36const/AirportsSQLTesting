package SQL_Checking;

import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserStories {

    @Test
    public void story1() throws SQLException {
        ResultSet result = DBConnecting.sendQuery(
                "select fl.departureAirport, fl.arrivalAirport, fl.averageTicketPrice, al.additionalSpaceService, ap.priorityBoarding " +
                "from flights fl " +
                "inner join airlines al on fl.flightNumber = al.flightNumber " +
                "inner join airports ap on fl.arrivalAirport = ap.airport " +
                "where fl.averageTicketPrice < 120 and fl.availableSeats >= 4 and priorityBoarding='y' and additionalSpaceService='y' " +
                "and departureAirport='London' and arrivalAirport='Munich';");

        List<String> departureAirport = new ArrayList<>();

        while (result.next()) {
            departureAirport.add(result.getString("departureAirport"));
        }
        if (departureAirport.size() != 0) {
            System.out.println(departureAirport);
            Assert.assertTrue(departureAirport.stream().allMatch(x -> x.equals("departureAirport")));
        } else System.out.println("Matches are not found");
    }

    @Test
    public void story2() throws SQLException {
        ResultSet result = DBConnecting.sendQuery(
                "select al.flightNumber, al.airline, fl.departureAirport, fl.arrivalAirport, fl.averageTicketPrice, al.isMealincluded, ap.dutyFree " +
                        "from flights fl " +
                        "inner join airlines al on fl.flightNumber = al.flightNumber " +
                        "inner join airports ap on ap.airport = fl.arrivalAirport " +
                        "where fl.departureAirport='New York' and fl.averageTicketPrice <= 500 and ap.dutyFree='y' " +
                        "and al.isMealincluded='y' and fl.availableSeats >= 1 ;");

        List<String> departureAirports = new ArrayList<>();
        List<String> arrivalAirports = new ArrayList<>();
        List<Integer> avgTicketPrice = new ArrayList<>();
        List<String> isMealincluded = new ArrayList<>();
        List<String> dutyFree = new ArrayList<>();

        while (result.next()) {
            departureAirports.add(result.getString("departureAirport"));
            arrivalAirports.add(result.getString("arrivalAirport"));
            avgTicketPrice.add(result.getInt("averageTicketPrice"));
            isMealincluded.add(result.getString("isMealincluded"));
            dutyFree.add(result.getString("dutyFree"));
        }

        if (departureAirports.size() != 0) {
            System.out.println(departureAirports);
            Assert.assertEquals(3, departureAirports.size());
            Assert.assertTrue(departureAirports.stream().allMatch(x -> x.equals("New York")));

            System.out.println(arrivalAirports);
            Assert.assertFalse(arrivalAirports.stream().anyMatch(x -> x.equals("New York")));

            System.out.println(avgTicketPrice);
            Assert.assertTrue(avgTicketPrice.stream().allMatch(x -> x <= 500));

            System.out.println(isMealincluded);
            Assert.assertTrue(isMealincluded.stream().allMatch(x -> x.equals("y")));

            System.out.println(dutyFree);
            Assert.assertTrue(dutyFree.stream().allMatch(x -> x.equals("y")));
        } else System.out.println("Matches are not found");
    }

    @Test
    public void story3() throws SQLException {
        ResultSet result = DBConnecting.sendQuery(
                "select al.flightNumber, al.airline, fl.departureAirport, fl.arrivalAirport, fl.averageTicketPrice, al.webRegistration " +
                        "from flights fl " +
                        "inner join airlines al on fl.flightNumber = al.flightNumber " +
                        "where (fl.arrivalAirport = 'Milan' or fl.arrivalAirport = 'Helsinki') and al.webRegistration = 'y' and fl.averageTicketPrice < 100 " +
                        "order by airline;");

        List<String> arrivalAirport = new ArrayList<>();
        List<Integer> averageTicketPrice = new ArrayList<>();
        List<String> webRegistration = new ArrayList<>();

        while (result.next()) {
            arrivalAirport.add(result.getString("arrivalAirport"));
            averageTicketPrice.add(result.getInt("averageTicketPrice"));
            webRegistration.add(result.getString("webRegistration"));
        }

        if (arrivalAirport.size() != 0) {

            System.out.println(arrivalAirport);
            for (String a : arrivalAirport) {
                Assert.assertTrue(a != "Milan" && a != "Helsinki");
            }

            System.out.println(averageTicketPrice);
            Assert.assertTrue(averageTicketPrice.stream().allMatch(x -> x <= 100));

            System.out.println(webRegistration);
            Assert.assertTrue(webRegistration.stream().allMatch(x -> x.equals("y")));
        } else System.out.println("Matches are not found");
    }
}
