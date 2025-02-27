import java.io.*;
import java.sql.*;

public class Employee {
    // Database Configuration
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/employee";
    private static final String USER = "root";
    private static final String PASS = "Mysql"; // Change as per your DB password

    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                
                int choice;
                do {
                    System.out.println("\n MENU ");
                    System.out.println("1. Insert\n2. Update\n3. Delete\n4. Display\n5. Exit");
                    System.out.print("Enter your choice: ");
                    
                    try {
                        choice = Integer.parseInt(br.readLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        continue;
                    }

                    switch (choice) {
                        case 1:
                            insertEmployee(conn, br);
                            break;
                        case 2:
                            updateEmployee(conn, br);
                            break;
                        case 3:
                            deleteEmployee(conn, br);
                            break;
                        case 4:
                            displayEmployees(conn);
                            break;
                        case 5:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } while (choice != 5);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found!");
        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void insertEmployee(Connection conn, BufferedReader br) throws IOException, SQLException {
        System.out.print("Enter Employee Number: ");
        int eno = Integer.parseInt(br.readLine());
        System.out.print("Enter Employee Name: ");
        String ename = br.readLine();
        System.out.print("Enter Salary: ");
        int salary = Integer.parseInt(br.readLine());

        String query = "INSERT INTO Employee1 (eno, ename, salary) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, eno);
            pstmt.setString(2, ename);
            pstmt.setInt(3, salary);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee inserted successfully.");
            }
        }
    }

    private static void updateEmployee(Connection conn, BufferedReader br) throws IOException, SQLException {
        System.out.print("Enter Employee Number to Update: ");
        int eno = Integer.parseInt(br.readLine());
        System.out.print("Enter New Name: ");
        String ename = br.readLine();
        System.out.print("Enter New Salary: ");
        int salary = Integer.parseInt(br.readLine());

        String query = "UPDATE Employee1 SET ename=?, salary=? WHERE eno=?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            conn.setAutoCommit(false);
            pstmt.setString(1, ename);
            pstmt.setInt(2, salary);
            pstmt.setInt(3, eno);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                conn.commit();
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Employee not found.");
                conn.rollback();
            }
        } finally {
            conn.setAutoCommit(true);
        }
    }

    private static void deleteEmployee(Connection conn, BufferedReader br) throws IOException, SQLException {
        System.out.print("Enter Employee Number to Delete: ");
        int eno = Integer.parseInt(br.readLine());

        String query = "DELETE FROM Employee1 WHERE eno=?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            conn.setAutoCommit(false);
            pstmt.setInt(1, eno);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                conn.commit();
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee not found.");
                conn.rollback();
            }
        } finally {
            conn.setAutoCommit(true);
        }
    }

    private static void displayEmployees(Connection conn) throws SQLException {
        String query = "SELECT * FROM Employee1";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\nENO\tENAME\tSALARY");
            while (rs.next()) {
                System.out.println(rs.getInt("eno") + "\t" + rs.getString("ename") + "\t" + rs.getInt("salary"));
            }
        }
    }
}




























// import java.io.*;
// import java.sql.*;

// public class Employee {
//     static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//     static final String DB_URL = "jdbc:mysql://localhost/employee";
//     static final String USER = "root";
//     static final String PASS = "Mysql"; // Use the password given to MySQL

//     public static void main(String[] args) throws IOException {
//         int ch, upc;
//         int no, sal;
//         String name;
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//         try {
//             Class.forName(JDBC_DRIVER);
//         } catch (ClassNotFoundException e) {
//             System.out.println("Unable to load driver");
//             return;
//         }

//         try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
//             System.out.println("\n.......Current Records.......\n");
//             System.out.println("ENO\tENAME\tSALARY\n");

//             try (Statement stmt = conn.createStatement();
//                  ResultSet rs = stmt.executeQuery("SELECT * FROM Employee1")) {
//                 while (rs.next()) {
//                     System.out.println(rs.getInt("eno") + "\t" + rs.getString("ename") + "\t" + rs.getInt("salary"));
//                 }
//             }

//             do {
//                 System.out.println("\n MENU \n 1.Insert \n 2.Update \n 3.Delete \n 4.Display \n 5.Exit \n Enter your choice:");
//                 ch = Integer.parseInt(br.readLine());

//                 switch (ch) {
//                     case 1:
//                         System.out.println("Enter employee number, name, and salary:");
//                         no = Integer.parseInt(br.readLine());
//                         name = br.readLine();
//                         sal = Integer.parseInt(br.readLine());

//                         String insertSQL = "INSERT INTO Employee1 (eno, ename, salary) VALUES (?, ?, ?)";
//                         try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
//                             pstmt.setInt(1, no);
//                             pstmt.setString(2, name);
//                             pstmt.setInt(3, sal);
//                             pstmt.executeUpdate();
//                             System.out.println("Records inserted");
//                         }
//                         break;

//                     case 2:
//                         System.out.println("Enter employee number of the record to be updated:");
//                         no = Integer.parseInt(br.readLine());
//                         System.out.println("Enter the new name and salary:");
//                         name = br.readLine();
//                         sal = Integer.parseInt(br.readLine());

//                         String updateSQL = "UPDATE Employee1 SET ename=?, salary=? WHERE eno=?";
//                         try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
//                             conn.setAutoCommit(false); // Start transaction
//                             pstmt.setString(1, name);
//                             pstmt.setInt(2, sal);
//                             pstmt.setInt(3, no);

//                             upc = pstmt.executeUpdate();
//                             if (upc != 0) {
//                                 conn.commit();
//                                 System.out.println("Records updated");
//                             } else {
//                                 System.out.println("No such record exists");
//                             }
//                         } catch (SQLException e) {
//                             System.out.println("Exception occurred: " + e.getMessage() + "\nRecords not updated\n");
//                             conn.rollback();
//                         } finally {
//                             conn.setAutoCommit(true); // Reset auto-commit
//                         }
//                         break;

//                     case 3:
//                         System.out.println("Enter employee number of the record you want to delete:");
//                         no = Integer.parseInt(br.readLine());

//                         String deleteSQL = "DELETE FROM Employee1 WHERE eno=?";
//                         try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
//                             conn.setAutoCommit(false); // Start transaction
//                             pstmt.setInt(1, no);
//                             upc = pstmt.executeUpdate();
//                             if (upc != 0) {
//                                 conn.commit();
//                                 System.out.println("Records deleted");
//                             } else {
//                                 System.out.println("No such record exists");
//                             }
//                         } catch (SQLException e) {
//                             System.out.println("Exception occurred: " + e.getMessage() + "\nRecords not deleted\n");
//                             conn.rollback();
//                         } finally {
//                             conn.setAutoCommit(true); // Reset auto-commit
//                         }
//                         break;

//                     case 4:
//                         String selectSQL = "SELECT * FROM Employee1";
//                         System.out.println("ENO\tENAME\tSALARY\n");
//                         try (Statement stmt = conn.createStatement();
//                              ResultSet rs1 = stmt.executeQuery(selectSQL)) {
//                             while (rs1.next()) {
//                                 System.out.println(rs1.getInt("eno") + "\t" + rs1.getString("ename") + "\t" + rs1.getInt("salary"));
//                             }
//                         }
//                         break;

//                     case 5:
//                         System.out.println("Exiting...");
//                         break;

//                     default:
//                         System.out.println("Enter a valid choice");
//                         break;
//                 }
//             } while (ch != 5);
//         } catch (SQLException e) {
//             System.out.println("Connection failed: " + e.getMessage());
//         }
//     }
// }
