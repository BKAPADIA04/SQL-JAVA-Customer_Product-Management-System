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
            System.out.println("13. Print products of a brand");
            System.out.println("14. Print products of a customer");
            System.out.println("15. Fetch All Customers");
            System.out.println("16. Fetch All Products");
            System.out.println("17. Fetch All Brands");
            System.out.println("18. Fetch All Reviews");
            System.out.println("19. Most Rich Customer");
            System.out.println("20. Exit");
            
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
                  conn.setAutoCommit(false);
                  if(rowsAffected > 0) { System.out.println("Customer added successfully"); conn.commit();}
                  else { System.out.println("Failed to add customer"); conn.rollback();}
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
               ResultSet rs = stmt.executeQuery(query);int flag = 0;int flag1 = 0;
               while(rs.next()) {
                  flag = 1;
                  System.out.println("Product already exists with Product ID = "+product_ID_input);
                  break;
               }
               String q1 = "SELECT * from Brand WHERE BrandID = " + brand_ID_input;
               ResultSet rs1 = stmt.executeQuery(q1);
               while(rs1.next()) {
                  flag1 = 1;
               }
               rs1.close();
               if(flag == 0 && flag1 == 1)
               {
               // query = "INSERT INTO Customer VALUES(" + customer_ID_input + ", " + first_name_input + ", " + last_name_input + ", " + emailid_input + ")";
                  query = "INSERT INTO Product VALUES(" + product_ID_input + ", " + brand_ID_input + ", '" + product_name_input + "', " + price_input + ", "+quantity_input+")";
                  // System.out.println((query));
                  conn.setAutoCommit(false);
                  int rowsAffected = stmt.executeUpdate(query);
                  if(rowsAffected > 0) { System.out.println("Product added successfully"); conn.commit();}
                  else { System.out.println("Failed to add product");conn.rollback();}
               }
               else {
                  System.out.println("BrandID Not Available");
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
                  conn.setAutoCommit(false);
                  int rowsAffected = stmt.executeUpdate(query);
                  if (rowsAffected > 0) {
                     System.out.println("Brand added successfully");
                     conn.commit();
                  } else {
                     System.out.println("Failed to add brand");
                     conn.rollback();
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
                  conn.setAutoCommit(false);
                  int rowsAffected = stmt.executeUpdate(query);
                  if (rowsAffected > 0) {
                     query = "SELECT quantity from product where productid = " + product_ID_input;
                     rs = stmt.executeQuery(query);
                     Integer q = 0;
                     while(rs.next())
                     {
                        q = rs.getInt("quantity");
                        q = q-1;
                     }
                     query = "update product set quantity = " +q+ " where productid = " + product_ID_input;
                     if(stmt.executeUpdate(query) >= 0) {
                     System.out.println("Review added successfully");
                     conn.commit();
                     }
                     else {conn.rollback();}
                  } else {
                     System.out.println("Failed to add review");
                     conn.rollback();
                  }
               }
               rs.close();
            }
            else if(choice == 5) {
               System.out.println("Enter the customer ID to be removed");
               int customer_ID_input = sc.nextInt();
               String query = "DELETE FROM Customer WHERE CustomerID = " + customer_ID_input;
               conn.setAutoCommit(false);
               int rowsAffected = stmt.executeUpdate(query);
               if (rowsAffected > 0) {
                  System.out.println("Customer removed successfully");conn.commit();
               } else {
                  System.out.println("Failed to remove customer");conn.rollback();
               }
            }
            else if(choice == 6) {
               System.out.println("Enter the product ID to be removed");
               int product_ID_input = sc.nextInt();
               String query = "DELETE FROM Product WHERE ProductID = " + product_ID_input;
               conn.setAutoCommit(false);
               int rowsAffected = stmt.executeUpdate(query);
               if (rowsAffected > 0) {
                  System.out.println("Product removed successfully");conn.commit();
               } else {
                  System.out.println("Failed to remove product");conn.rollback();
               }
            }
            else if(choice == 7) {
               System.out.println("Enter the brand ID to be removed");
               int brand_ID_input = sc.nextInt();
               String query = "DELETE FROM Brand WHERE BrandID = " + brand_ID_input;
               conn.setAutoCommit(false);
               int rowsAffected = stmt.executeUpdate(query);
               if (rowsAffected > 0) {
                  System.out.println("Brand removed successfully");conn.commit();
               } else {
                  System.out.println("Failed to remove brand");conn.rollback();
               }
            }
            else if(choice == 8) {
               System.out.println("Enter the review ID to be removed");
               int review_ID_input = sc.nextInt();
               String query = "DELETE FROM Review WHERE ReviewID = " + review_ID_input;
               conn.setAutoCommit(false);
               int rowsAffected = stmt.executeUpdate(query);
               if (rowsAffected > 0) {
                  System.out.println("Review removed successfully");conn.commit();
               } else {
                  System.out.println("Failed to remove Review");conn.rollback();
               }
            }
            else if(choice == 9) {
               System.out.println("Enter the customer id:");
               int customer_ID_input = sc.nextInt();
               System.out.println("Enter the new phone number");
               String phone_number_input = sc.next();
               String query = "UPDATE Customer SET Phone = " + phone_number_input + " WHERE CustomerID = " + customer_ID_input;
               conn.setAutoCommit(false);
               int rowsAffected = stmt.executeUpdate(query);
               if (rowsAffected > 0) {
                  System.out.println("Customer Phone Number updated successfully");conn.commit();
               } else {
                  System.out.println("Failed to update customer's phone number");conn.rollback();
               }
            }
            else if(choice == 10) {
               System.out.println("Enter the product id:");
               int product_ID_input = sc.nextInt();
               System.out.println("Enter the new price of the product");
               double price_input = sc.nextDouble();
               String query = "UPDATE Product SET Price = " + price_input + " WHERE ProductID = " + product_ID_input;
               conn.setAutoCommit(false);
               int rowsAffected = stmt.executeUpdate(query);
               if (rowsAffected > 0) {
                  System.out.println("Product Price updated successfully");conn.commit();
               } else {
                  System.out.println("Failed to update price of the product");conn.rollback();
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
               String query = "SELECT * from Product INNER JOIN Brand ON Product.BrandID = Brand.BrandID WHERE ProductID = " + product_ID_input;
               ResultSet rs = stmt.executeQuery(query);
               int flag = 0;
               while (rs.next()) {
                  int BrandID = rs.getInt("BrandID");
                  String ProductName = rs.getString("ProductName");
                  double Price = rs.getDouble("Price");
                  String BrandName = rs.getString("BrandName");
                  int Quantity = rs.getInt("quantity");

                  System.out.print("Product ID : "+product_ID_input+"\n");
                  System.out.print("Brand ID : "+BrandID+"\n");
                  System.out.print("Product Name : "+ProductName+"\n");
                  System.out.print("Price : "+Price+"\n");
                  System.out.print("Brand : "+BrandName+"\n");
                  System.out.print("Quantity : "+Quantity+"\n");

                  flag = 1;
               }
               if(flag == 0) {
                  System.out.println("Product Not Found");
               }
               rs.close();
            }
            else if(choice == 13) {
               System.out.println("Enter the BrandID for Brand Specific Products");
               int brand_ID_input = sc.nextInt();
               String query = "SELECT Brand.*,Product.ProductID,Product.ProductName,Product.Price,Product.quantity FROM Brand INNER JOIN Product ON Brand.brandID = Product.BrandID WHERE Brand.brandID = " + brand_ID_input;
               ResultSet rs = stmt.executeQuery(query);
               int flag = 0;
               while (rs.next()) {
                  int BrandID = rs.getInt("BrandID");
                  int ProductID = rs.getInt("ProductID");
                  String ProductName = rs.getString("ProductName");
                  double Price = rs.getDouble("Price");
                  String BrandName = rs.getString("BrandName");
                  int Quantity = rs.getInt("quantity");
              
                  System.out.println("Brand ID: " + BrandID);
                  System.out.println("Brand: " + BrandName);
                  System.out.println("Product ID: " + ProductID);
                  System.out.println("Product Name: " + ProductName);
                  System.out.println("Price: " + Price);
                  System.out.print("Quantity : "+Quantity+"\n");

                  flag = 1;
               }
               if(flag == 0) {
                  System.out.println("Brand Products Not Found");
               }
               rs.close();
            }
            else if(choice == 14) {
               System.out.println("Enter the customer id:");
               int customer_ID_input = sc.nextInt();
               String query = "SELECT Customer.CustomerID, Product.* FROM Customer INNER JOIN Review ON Customer.CustomerID = Review.CustomerID INNER JOIN Product ON Review.ProductID = Product.ProductID WHERE Customer.CustomerID = " +customer_ID_input;
               ResultSet rs = stmt.executeQuery(query);
               int flag = 0;
               while (rs.next()) {
                  int BrandID = rs.getInt("BrandID");
                  int ProductID = rs.getInt("ProductID");
                  int CustomerID = rs.getInt("CustomerID");
                  String ProductName = rs.getString("ProductName");
                  double Price = rs.getDouble("Price");
                  int Quantity = rs.getInt("quantity");
              

                  System.out.println("Customer ID: " + CustomerID);
                  System.out.println("Brand ID: " + BrandID);
                  System.out.println("Product ID: " + ProductID);
                  System.out.println("Product Name: " + ProductName);
                  System.out.println("Price: " + Price);
                  System.out.print("Quantity : "+Quantity+"\n");

                  flag = 1;
               }
               if(flag == 0) {
                  System.out.println("Customer Products Not Found");
               }
               rs.close();
            }
            else if(choice == 15) {
               String query = "SELECT * from Customer";
               ResultSet rs = stmt.executeQuery(query);
               int flag = 0;
               while (rs.next()) {
                  int CustomerID = rs.getInt("CustomerID");
                  String FirstName = rs.getString("FirstName");
                  String LastName = rs.getString("LastName");
                  String Phone = rs.getString("Phone");
                  String EmailID = rs.getString("EmailID");

                  System.out.print("Customer ID : "+CustomerID+"\n");
                  System.out.print("Customer First Name : "+FirstName+"\n");
                  System.out.print("Customer Last Name : "+LastName+"\n");
                  System.out.print("Phone : "+Phone+"\n");
                  System.out.print("EmailID : "+EmailID+"\n");
                  flag = 1;
               }
               rs.close();
            }
            else if(choice == 16) {
               String query = "SELECT * from Product";
               ResultSet rs = stmt.executeQuery(query);
               int flag = 0;
               while (rs.next()) {
                  int ProductID = rs.getInt("ProductID");
                  int BrandID = rs.getInt("BrandID");
                  String ProductName = rs.getString("ProductName");
                  double Price = rs.getDouble("Price");
                  int Quantity = rs.getInt("quantity");

                  System.out.print("Product ID : "+ProductID+"\n");
                  System.out.print("Brand ID : "+BrandID+"\n");
                  System.out.print("Product Name : "+ProductName+"\n");
                  System.out.print("Price : "+Price+"\n");
                  System.out.print("Quantity : "+Quantity+"\n");

                  flag = 1;
               }
               rs.close();
            }
            else if(choice == 17) {
               String query = "SELECT * from Brand";
               ResultSet rs = stmt.executeQuery(query);
               int flag = 0;
               while (rs.next()) {
                  int BrandID = rs.getInt("BrandID");
                  String BrandName = rs.getString("BrandName");

                  System.out.print("Brand ID : "+BrandID+"\n");
                  System.out.print("Brand Name : "+BrandName+"\n");

                  flag = 1;
               }
               rs.close();
            }
            else if(choice == 18) {
               String query = "SELECT * from Review";
               ResultSet rs = stmt.executeQuery(query);
               int flag = 0;
               while (rs.next()) {
                  int CustomerID = rs.getInt("CustomerID");
                  int ProductID = rs.getInt("ProductID");
                  int ReviewID = rs.getInt("ReviewID");
                  String Review = rs.getString("Customer_Product_Review");

                  System.out.print("Review ID : "+ReviewID+"\n");
                  System.out.print("Customer ID : "+CustomerID+"\n");
                  System.out.print("Product ID : "+ProductID+"\n");
                  System.out.print("Review : "+Review+"\n");

                  flag = 1;
               }
               rs.close();
            }
            else if(choice == 19) {
               String query = "SELECT Customer.CustomerID,Customer.FirstName,Customer.LastName, SUM(Product.Price) AS TotalPrice" + 
                              " FROM Customer" + 
                              " INNER JOIN Review ON Customer.CustomerID = Review.CustomerID" + 
                              " INNER JOIN Product ON Review.ProductID = Product.ProductID" + 
                              " GROUP BY Customer.CustomerID" + 
                              " ORDER BY TotalPrice DESC LIMIT 1";
               ResultSet rs = stmt.executeQuery(query);
               while(rs.next()) {
                  int CustomerID = rs.getInt("CustomerID");
                  String FirstName = rs.getString("FirstName");
                  String LastName = rs.getString("LastName");
                  Double TotalPrice = rs.getDouble("TotalPrice");
                  System.out.print("Customer ID : "+CustomerID+"\n");
                  System.out.print("Customer First Name : "+FirstName+"\n");
                  System.out.print("Customer Last Name : "+LastName+"\n");
                  System.out.print("Total Price : "+TotalPrice+"\n");
               }
               rs.close();
            }
            else {
               break;
            }
         }

         
         // STEP 5: Clean-up environment
         // rs.close();
         stmt.close();
         conn.close();
      } catch (SQLException se) { // Handle errors for JDBC
         se.printStackTrace();
         System.out.println("Rolling back data here....");
      try{
         if(conn!=null)
             conn.rollback();
      }catch(SQLException se2){
	      System.out.println("Rollback failed....");
              se2.printStackTrace();
      }
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
