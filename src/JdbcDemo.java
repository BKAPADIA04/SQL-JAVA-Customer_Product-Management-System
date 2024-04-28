//STEP 1. Import required packages
import java.util.*;
import java.sql.*;

public class JdbcDemo {

   // Set JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost:3306/companydb?useSSL=false";

   // Database credentials
   static final String USER = "root";// add your user
   static final String PASSWORD = "bhavya45*K";// add password

   public static void main(String[] args) {
      Connection conn = null;
      Statement stmt = null;
      Scanner sc = new Scanner(System.in);
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

         while(true) 
         {
            System.out.println("Please select an option:");
            System.out.println("1. Add a new Customer");
            System.out.println("2. Add a new Product");
            System.out.println("3. Add a new Brand");
            System.out.println("4. Add a new Review");
            System.out.println("5. Remove a customer");
            System.out.println("6. Remove a product");
            System.out.println("7. Remove a brand");
            System.out.println("8. Remove a review");
            System.out.println("9. Update the phone number of the customer");
            System.out.println("10. Update the price of the product");
            System.out.println("11. Retrieve data of a customer");
            System.out.println("12. Retrieve data of a product");
            System.out.println("13. Exit");

            int choice = sc.nextInt();
            // String temp = sc.nextLine();
            if (choice == 1) {
               // String temp = sc.nextLine();
               System.out.println("Enter CustomerID:"); int customer_ID_input = sc.nextInt();
               System.out.println("Enter First Name:"); String first_name_input = sc.next();
               System.out.println("Enter Last Name:"); String last_name_input = sc.next();
               System.out.println("Enter Phone Number:"); String phone_number_input = sc.next();
               System.out.println("Enter EmailID:"); String emailid_input = sc.next();
               
               String query = "SELECT * from Customer WHERE CustomerID = " + customer_ID_input;
               ResultSet rs = stmt.executeQuery(query);int flag = 0;
               while(rs.next()) {
                  flag = 1;
                  System.out.println("Customer already exists with Customer ID = "+customer_ID_input);
                  break;
               }
               if(flag == 0)
               {
               // query = "INSERT INTO Customer VALUES(" + customer_ID_input + ", " + first_name_input + ", " + last_name_input + ", " + emailid_input + ")";
                  query = "INSERT INTO Customer VALUES(" + customer_ID_input + ", '" + first_name_input + "', '" + last_name_input + "', '" + phone_number_input + "', '" + emailid_input + "')";
                  // System.out.println((query));
                  int rowsAffected = stmt.executeUpdate(query);
                  if(rowsAffected > 0) { System.out.println("Customer added successfully");}
                  else { System.out.println("Failed to add customer");}
               }
               rs.close();
            }    
            else if(choice == 2) {
               System.out.println("Enter ProductID:"); int product_ID_input = sc.nextInt();
               System.out.println("Enter BrandID:"); int brand_ID_input = sc.nextInt();
               System.out.println("Enter Product Name:"); String product_name_input = sc.next();
               System.out.println("Enter Price:"); double price_input = sc.nextDouble();
               System.out.println("Enter Quantity:"); int quantity_input = sc.nextInt();
               
               String query = "SELECT * from Product WHERE ProductID = " + product_ID_input;
               ResultSet rs = stmt.executeQuery(query);int flag = 0;
               while(rs.next()) {
                  flag = 1;
                  System.out.println("Product already exists with Product ID = "+product_ID_input);
                  break;
               }
               if(flag == 0)
               {
               // query = "INSERT INTO Customer VALUES(" + customer_ID_input + ", " + first_name_input + ", " + last_name_input + ", " + emailid_input + ")";
                  query = "INSERT INTO Product VALUES(" + product_ID_input + ", " + brand_ID_input + ", '" + product_name_input + "', " + price_input + ", " + quantity_input + ")";
                  // System.out.println((query));
                  int rowsAffected = stmt.executeUpdate(query);
                  if(rowsAffected > 0) { System.out.println("Product added successfully");}
                  else { System.out.println("Failed to add product");}
               }
               rs.close();
            }
            else if(choice == 3) {
               System.out.println("Enter BrandID:");
               int brand_ID_input = sc.nextInt();
               System.out.println("Enter Brand Name:");
               String brand_name_input = sc.next();

               String query = "SELECT * FROM Brand WHERE BrandID = " + brand_ID_input;
               ResultSet rs = stmt.executeQuery(query);
               int flag = 0;
               while (rs.next()) {
                  flag = 1;
                  System.out.println("Brand already exists with Brand ID = " + brand_ID_input);
                  break;
               }
               if (flag == 0) {
                  query = "INSERT INTO Brand VALUES(" + brand_ID_input + ", '" + brand_name_input + "')";
                  int rowsAffected = stmt.executeUpdate(query);
                  if (rowsAffected > 0) {
                     System.out.println("Brand added successfully");
                  } else {
                     System.out.println("Failed to add brand");
                  }
               }
               rs.close();
            }
            else if(choice == 4) {
               System.out.println("Enter ReviewID:");
               int review_ID_input = sc.nextInt();
               System.out.println("Enter CustomerID:");
               int customer_ID_input = sc.nextInt();
               System.out.println("Enter ProductID:");
               int product_ID_input = sc.nextInt();
               String temp = sc.nextLine();
               System.out.println("Enter Review:");
               String review_input = sc.nextLine();
               System.out.println(review_input);
               String query = "SELECT * FROM Review WHERE ReviewID = " + review_ID_input;
               ResultSet rs = stmt.executeQuery(query);
               int flag = 0;
               while (rs.next()) {
                  flag = 1;
                  System.out.println("Review already exists with Review ID = " + review_ID_input);
                  break;
               }

               if (flag == 0) {
                  query = "INSERT INTO Review VALUES(" + review_ID_input + ", " + customer_ID_input + ", " + product_ID_input + ", '" + review_input + "')";
                  // System.out.println(query);
                  int rowsAffected = stmt.executeUpdate(query);
                  if (rowsAffected > 0) {
                     System.out.println("Review added successfully");
                  } else {
                     System.out.println("Failed to add review");
                  }
               }
               rs.close();
            }
            else if(choice == 5) {
               System.out.println("Enter the customer ID to be removed");
               int customer_ID_input = sc.nextInt();
               String query = "DELETE FROM Customer WHERE CustomerID = " + customer_ID_input;
               int rowsAffected = stmt.executeUpdate(query);
               if (rowsAffected > 0) {
                  System.out.println("Customer removed successfully");
               } else {
                  System.out.println("Failed to remove customer");
               }
            }
            else if(choice == 6) {
               System.out.println("Enter the product ID to be removed");
               int product_ID_input = sc.nextInt();
               String query = "DELETE FROM Product WHERE ProductID = " + product_ID_input;
               int rowsAffected = stmt.executeUpdate(query);
               if (rowsAffected > 0) {
                  System.out.println("Product removed successfully");
               } else {
                  System.out.println("Failed to remove product");
               }
            }
            else if(choice == 7) {
               System.out.println("Enter the brand ID to be removed");
               int brand_ID_input = sc.nextInt();
               String query = "DELETE FROM Brand WHERE BrandID = " + brand_ID_input;
               int rowsAffected = stmt.executeUpdate(query);
               if (rowsAffected > 0) {
                  System.out.println("Brand removed successfully");
               } else {
                  System.out.println("Failed to remove brand");
               }
            }
            else if(choice == 8) {
               System.out.println("Enter the review ID to be removed");
               int review_ID_input = sc.nextInt();
               String query = "DELETE FROM Review WHERE ReviewID = " + review_ID_input;
               int rowsAffected = stmt.executeUpdate(query);
               if (rowsAffected > 0) {
                  System.out.println("Review removed successfully");
               } else {
                  System.out.println("Failed to remove Review");
               }
            }
            else if(choice == 9) {
               System.out.println("Enter the customer id:");
               int customer_ID_input = sc.nextInt();
               System.out.println("Enter the new phone number");
               String phone_number_input = sc.next();
               String query = "UPDATE Customer SET Phone = " + phone_number_input + " WHERE CustomerID = " + customer_ID_input;
               int rowsAffected = stmt.executeUpdate(query);
               if (rowsAffected > 0) {
                  System.out.println("Customer Phone Number updated successfully");
               } else {
                  System.out.println("Failed to update customer's phone number");
               }
            }
            else if(choice == 10) {
               System.out.println("Enter the product id:");
               int product_ID_input = sc.nextInt();
               System.out.println("Enter the new price of the product");
               double price_input = sc.nextDouble();
               String query = "UPDATE Product SET Price = " + price_input + " WHERE ProductID = " + product_ID_input;
               int rowsAffected = stmt.executeUpdate(query);
               if (rowsAffected > 0) {
                  System.out.println("Product Price updated successfully");
               } else {
                  System.out.println("Failed to update price of the product");
               }
            }
            else if(choice == 11) {
               System.out.println("Enter the customer id:");
               int customer_ID_input = sc.nextInt();
               String query = "SELECT * from Customer WHERE CustomerID = " + customer_ID_input;
               ResultSet rs = stmt.executeQuery(query);
               int flag = 0;
               while (rs.next()) {
                  String FirstName = rs.getString("FirstName");
                  String LastName = rs.getString("LastName");
                  String Phone = rs.getString("Phone");
                  String EmailID = rs.getString("EmailID");

                  System.out.print("Customer ID : "+customer_ID_input+"\n");
                  System.out.print("Customer First Name : "+FirstName+"\n");
                  System.out.print("Customer Last Name : "+LastName+"\n");
                  System.out.print("Phone : "+Phone+"\n");
                  System.out.print("EmailID : "+EmailID+"\n");
                  flag = 1;
               }
               if(flag == 0) {
                  System.out.println("Customer Not Found");
               }
               rs.close();
            }
            else if(choice == 12) {
               System.out.println("Enter the product id:");
               int product_ID_input = sc.nextInt();
               String query = "SELECT * from Product WHERE ProductID = " + product_ID_input;
               ResultSet rs = stmt.executeQuery(query);
               int flag = 0;
               while (rs.next()) {
                  int BrandID = rs.getInt("BrandID");
                  String ProductName = rs.getString("ProductName");
                  double Price = rs.getDouble("Price");
                  int Quantity = rs.getInt("Quantity");

                  System.out.print("Product ID : "+product_ID_input+"\n");
                  System.out.print("Brand ID : "+BrandID+"\n");
                  System.out.print("Product Name : "+ProductName+"\n");
                  System.out.print("Price : "+Price+"\n");
                  System.out.print("Quantity : "+Quantity+"\n");
                  flag = 1;
               }
               if(flag == 0) {
                  System.out.println("Product Not Found");
               }
               rs.close();
            }
            else {
               break;
            }
         }
         // }

         // String query = "SELECT FirstName, LastName, Phone, EmailID from Customer";
         // ResultSet rs = stmt.executeQuery(query);

         // // STEP 4: Extract data from result set
         // while (rs.next()) {

         // // Retrieve by column name
         // String fname = rs.getString("FirstName");
         // String lname = rs.getString("LastName");
         // String birthDate = rs.getString("Phone");
         // String dno = rs.getString("EmailID");

         // // Display values
         // System.out.print("fname: " + fname);
         // System.out.print(", lname: " + lname);
         // System.out.print(", birth date: " + birthDate.toString());
         // System.out.println(", department number: " + dno);
         // }

         
         // STEP 5: Clean-up environment
         // rs.close();
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
