package SQL_Checking;

import org.junit.AfterClass;

import java.sql.*;

public class DBConnecting {

    private static Connection con;
    private static Statement stmt;
    private static String url = "jdbc:mysql://localhost:3306/air"+  "?verifyServerCertificate=false"+
            "&useSSL=false"+ "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+ "&amp"+ "&serverTimezone=UTC"+
            "&allowPublicKeyRetrieval=true";


    public static ResultSet sendQuery(String query) throws SQLException {

        con = DriverManager.getConnection(url, "admin", "122333");
        stmt = con.createStatement();
        return stmt.executeQuery(query);

    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        stmt.close();
        con.close();
    }
}