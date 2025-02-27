<!DOCTYPE html>
<html>
<head>
    <title>Student Data</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 8px;
            text-align: left;
        }
        table {
            width: 50%;
            margin: 20px auto;
            border-radius: 5px;
            box-shadow: 0 0 10px #aaa;
        }
        th {
            background: #f2f2f2;
        }
    </style>
</head>
<body>

<h2 style="text-align: center;">📋 Student List</h2>

<?php
// Database connection details
$servername = "localhost";
$username = "root";
$password = "Mysql"; 
$dbname = "college";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("<p style='color: red; text-align: center;'>❌ Connection failed: " . $conn->connect_error . "</p>");
}

// Fetch data from the database
$sql = "SELECT id, firstname, lastname FROM demo";
$result = $conn->query($sql);

// Display data in a table
if ($result->num_rows > 0) {
    echo "<table align='center'>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>";
    while ($row = $result->fetch_assoc()) {
        echo "<tr>
                <td>" . htmlspecialchars($row["id"]) . "</td>
                <td>" . htmlspecialchars($row["firstname"]) . " " . htmlspecialchars($row["lastname"]) . "</td>
              </tr>";
    }
    echo "</table>";
} else {
    echo "<p style='text-align: center; color: red;'>⚠ No results found.</p>";
}

// Close the connection
$conn->close();
?>

</body>
</html>
