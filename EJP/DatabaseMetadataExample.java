import java.sql.*;

public class DatabaseMetadataExample {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/employee"; // Adjust the DB URL as per your database
    static final String USER = "root";
    static final String PASS = "Mysql";

    public static void main(String[] args) {
        try {
            // Load JDBC driver
            Class.forName(JDBC_DRIVER);

            // Establish connection
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                // Get database metadata
                DatabaseMetaData dbMetaData = conn.getMetaData();

                // Display general database information
                System.out.println("Database Product Name: " + dbMetaData.getDatabaseProductName());
                System.out.println("Database Product Version: " + dbMetaData.getDatabaseProductVersion());
                System.out.println("Database URL: " + dbMetaData.getURL());
                System.out.println("Database Username: " + dbMetaData.getUserName());
                System.out.println("Driver Name: " + dbMetaData.getDriverName());
                System.out.println("Driver Version: " + dbMetaData.getDriverVersion());

                // Display tables information
                System.out.println("\nTables in the database:");
                try (ResultSet tables = dbMetaData.getTables(null, null, "%", new String[] { "TABLE" })) {
                    while (tables.next()) {
                        System.out.println("Table: " + tables.getString("TABLE_NAME"));
                    }
                }

                // Display columns information for a specific table (e.g., Employee1)
                String tableName = "Employee1";
                System.out.println("\nColumns in the table " + tableName + ":");
                try (ResultSet columns = dbMetaData.getColumns(null, null, tableName, "%")) {
                    while (columns.next()) {
                        System.out.println("Column: " + columns.getString("COLUMN_NAME") + " | Type: " + columns.getString("TYPE_NAME") + " | Size: " + columns.getInt("COLUMN_SIZE"));
                    }
                }

            } catch (SQLException e) {
                System.out.println("Connection failed: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load driver");
        }
    }
}
