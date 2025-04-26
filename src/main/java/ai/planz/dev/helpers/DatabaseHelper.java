package ai.planz.dev.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseHelper {

    public Connection connectToDB() {
        try {
            String dbUrl = "jdbc:mysql://http://dev.planz.ai/:33069/planz_dev";
            String dbUser = "planz_dev";
            String dbPassword = "123@123A123a!";

            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public String getCustomerNameFromDB(int customerId) //Retrieve Customer name knowing Customer ID from DB
//    {
//        String customerName = null;
//        try (Connection conn = connectToDB()) {
//            Statement stmt = conn.createStatement();
//            String query = "SELECT name FROM clients WHERE id = " + customerId;
//            ResultSet rs = stmt.executeQuery(query);
//            if (rs.next()) {
//                customerName = rs.getString("name");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return customerName;
//    }
//
//    // Function to get the newest customer record based on created_at field
//    public String getNewestCustomerNameFromDB() {
//        String customerName = null;
//        try (Connection conn = connectToDB()) {
//            // Create a statement to execute SQL queries
//            Statement stmt = conn.createStatement();
//
//            // SQL query to get the most recent record ordered by created_at in descending order
//            String query = "SELECT name FROM clients ORDER BY created_at DESC LIMIT 1";
//
//            // Execute the query
//            ResultSet rs = stmt.executeQuery(query);
//
//            // If the result set has data, retrieve the customer name
//            if (rs.next()) {
//                customerName = rs.getString("name");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return customerName;
//    }
}
