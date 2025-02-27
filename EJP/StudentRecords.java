import java.io.*;
import java.sql.*;

public class StudentRecords {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/employee"; // Adjust the DB URL as per your database
    static final String USER = "root";
    static final String PASS = "Mysql";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load driver");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery("SELECT * FROM Student")) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No records in Student table");
                return;
            }

            while (true) {
                System.out.println("\nMENU\n1.First\n2.Last\n3.Next\n4.Previous\n5.Before First\n6.After Last\n7.Absolute\n8.Relative\n9.Exit\nEnter your choice:");
                int choice = Integer.parseInt(br.readLine());
                switch (choice) {
                    case 1:
                        if (rs.first()) {
                            displayRecord(rs);
                        } else {
                            System.out.println("No record found");
                        }
                        break;
                    case 2:
                        if (rs.last()) {
                            displayRecord(rs);
                        } else {
                            System.out.println("No record found");
                        }
                        break;
                    case 3:
                        if (rs.next()) {
                            displayRecord(rs);
                        } else {
                            System.out.println("No more records");
                        }
                        break;
                    case 4:
                        if (rs.previous()) {
                            displayRecord(rs);
                        } else {
                            System.out.println("No previous records");
                        }
                        break;
                    case 5:
                        rs.beforeFirst();
                        System.out.println("Moved before first record");
                        break;
                    case 6:
                        rs.afterLast();
                        System.out.println("Moved after last record");
                        break;
                    case 7:
                        System.out.println("Enter the row number to move to:");
                        int row = Integer.parseInt(br.readLine());
                        if (rs.absolute(row)) {
                            displayRecord(rs);
                        } else {
                            System.out.println("No such record");
                        }
                        break;
                    case 8:
                        System.out.println("Enter the relative position to move by:");
                        int pos = Integer.parseInt(br.readLine());
                        if (rs.relative(pos)) {
                            displayRecord(rs);
                        } else {
                            System.out.println("No such record");
                        }
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Enter a valid choice");
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    private static void displayRecord(ResultSet rs) throws SQLException {
        System.out.println("Student ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Age: " + rs.getInt("age"));
    }
}

