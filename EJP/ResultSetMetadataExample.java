import java.sql.*;

public class ResultSetMetadataExample {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/employee"; // Adjust the DB URL as per your database
    static final String USER = "root";
    static final String PASS = "Mysql";

    public static void main(String[] args) {
        try {
            // Load JDBC driver
            Class.forName(JDBC_DRIVER);

            // Establish connection
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM Employee1")) {

                // Get ResultSet metadata
                ResultSetMetaData rsMetaData = rs.getMetaData();

                // Display number of columns
                int columnCount = rsMetaData.getColumnCount();
                System.out.println("Number of columns: " + columnCount);

                // Display column details
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println("Column " + i + ":");
                    System.out.println("    Name: " + rsMetaData.getColumnName(i));
                    System.out.println("    Type: " + rsMetaData.getColumnTypeName(i));
                    System.out.println("    Size: " + rsMetaData.getColumnDisplaySize(i));
                    System.out.println("    Nullable: " + rsMetaData.isNullable(i));
                    System.out.println("    Auto Increment: " + rsMetaData.isAutoIncrement(i));
                }
            } catch (SQLException e) {
                System.out.println("Connection failed: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load driver");
        }
    }
}
