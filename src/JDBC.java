//STEP 1. Import required packages

import java.sql.*;

public class JDBC {

   // Set JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost:3306/companydb?useSSL=false";

   // Database credentials
   static final String USER = "root";// add your user
   static final String PASSWORD = "bhavya45*K";// add password

   public static void main(String[] args) {
      Connection conn = null;
      Statement stmt = null;
      // STEP 2. Connecting to the Database
      try {
         // STEP 2a: Register JDBC driver
         Class.forName(JDBC_DRIVER);
         // STEP 2b: Open a connection
         System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
         // STEP 2c: Execute a query
         System.out.println("Creating statement...");
         stmt = conn.createStatement();

         // INSERT, UPDATE, DELETE

         // STEP 3: Query to database
         String query = "SELECT fname, lname, dno, bdate from employee";
         ResultSet rs = stmt.executeQuery(query);

         // STEP 4: Extract data from result set
         while (rs.next()) {

         // Retrieve by column name
         String fname = rs.getString("fname");
         String lname = rs.getString("lname");
         Date birthDate = rs.getDate("bdate", null);
         Integer dno = rs.getInt("dno");

         // Display values
         System.out.print("fname: " + fname);
         System.out.print(", lname: " + lname);
         System.out.print(", birth date: " + birthDate.toString());
         System.out.println(", department number: " + dno);
         }

         
         // STEP 5: Clean-up environment
         rs.close();
         stmt.close();
         conn.close();
      } catch (SQLException se) { // Handle errors for JDBC
         se.printStackTrace();
      } catch (Exception e) { // Handle errors for Class.forName
         e.printStackTrace();
      } finally { // finally block used to close resources regardless of whether an exception was
                  // thrown or not
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se2) {
         }
         try {
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            se.printStackTrace();
         } // end finally try
      } // end try
      System.out.println("End of Code");
   } // end main
} // end class

// Note : By default autocommit is on. you can set to false using
// con.setAutoCommit(false)



if(choice == 1) 
            {
               String temp = sc.nextLine();
               System.out.println("Enter CustomerID,FirstName,LastName,EmailID");
               int input_customer_ID;
               String input = sc.nextLine();
               String[] arr = input.split("' '");
               System.err.println(arr.length);
               if (!input.isEmpty()) {
                  try {
                     input_customer_ID = Integer.parseInt(arr[0]);
                     System.out.println(input_customer_ID);
                  } catch (NumberFormatException e) {
                      System.out.println("Invalid input: Please enter a valid integer.");
                  }
              } else {
                  System.out.println("Input is empty. Please enter a valid customer ID.");
              }
               String query = "SELECT CustomerID from Customer";
               ResultSet rs = stmt.executeQuery(query);
               while(rs.next()) {
                  int customerID_db = rs.getInt("CustomerID");
                  System.out.println(customerID_db);
               }
               // String query1 = "INSERT INTO Customer VALUES(" + arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3] + ")";
               if(arr.length == 4) {
               String query1 = "INSERT INTO Customer VALUES(" + arr[0] + "' '" + arr[1] + "' '" + arr[2] + "' '" + arr[3] + "')";
               int rowsAffected = stmt.executeUpdate(query1);
               }
               rs.close();
               // rowsAffected.close();
            }