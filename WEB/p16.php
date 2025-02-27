<!DOCTYPE HTML>
<html>
<head>
    <title>PHP Form Validation</title>
    <style>
        .error { color: #FF0000; }
        body { font-family: Arial, sans-serif; text-align: center; background-color: #f4f4f4; }
        form { background: #fff; padding: 20px; border-radius: 10px; display: inline-block; box-shadow: 0 0 10px #aaa; text-align: left; }
    </style>
</head>
<body>

<?php
// Define variables and set to empty values
$nameErr = $emailErr = $genderErr = $websiteErr = "";
$name = $email = $gender = $comment = $website = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (empty($_POST["name"])) {
        $nameErr = "⚠ Name is required";
    } else {
        $name = test_input($_POST["name"]);
        // Check if name contains only letters and whitespace
        if (!preg_match("/^[a-zA-Z ]*$/", $name)) {
            $nameErr = "⚠ Only letters and spaces allowed";
        }
    }

    if (empty($_POST["email"])) {
        $emailErr = "⚠ Email is required";
    } else {
        $email = test_input($_POST["email"]);
        // Check if email format is valid
        if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            $emailErr = "⚠ Invalid email format";
        }
    }

    if (!empty($_POST["website"])) {
        $website = test_input($_POST["website"]);
        // Validate URL format
        if (!preg_match("/\b(?:https?:\/\/|www\.)[-a-zA-Z0-9@:%._+~#=]{1,256}\.[a-z]{2,6}\b/i", $website)) {
            $websiteErr = "⚠ Invalid URL format";
        }
    } else {
        $website = "";
    }

    $comment = test_input($_POST["comment"]);

    if (empty($_POST["gender"])) {
        $genderErr = "⚠ Gender is required";
    } else {
        $gender = test_input($_POST["gender"]);
    }
}

// Function to sanitize input data
function test_input($data) {
    return htmlspecialchars(stripslashes(trim($data)));
}
?>

<h2>📝 PHP Form Validation Example</h2>
<p><span class="error">* Required field.</span></p>

<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
    <label>Name:</label>
    <input type="text" name="name" value="<?php echo $name; ?>">
    <span class="error">* <?php echo $nameErr; ?></span>
    <br><br>

    <label>Email:</label>
    <input type="text" name="email" value="<?php echo $email; ?>">
    <span class="error">* <?php echo $emailErr; ?></span>
    <br><br>

    <label>Website:</label>
    <input type="text" name="website" value="<?php echo $website; ?>">
    <span class="error"><?php echo $websiteErr; ?></span>
    <br><br>

    <label>Comment:</label>
    <textarea name="comment" rows="5" cols="40"><?php echo $comment; ?></textarea>
    <br><br>

    <label>Gender:</label>
    <input type="radio" name="gender" value="Female" <?php if ($gender == "Female") echo "checked"; ?>> Female
    <input type="radio" name="gender" value="Male" <?php if ($gender == "Male") echo "checked"; ?>> Male
    <span class="error">* <?php echo $genderErr; ?></span>
    <br><br>

    <input type="submit" name="submit" value="Submit">
</form>

<?php
// Display results only if there are no validation errors
if ($_SERVER["REQUEST_METHOD"] == "POST" && !$nameErr && !$emailErr && !$websiteErr && !$genderErr) {
    echo "<h2>✅ Your Input:</h2>";
    echo "Name: $name <br>";
    echo "Email: $email <br>";
    echo "Website: " . ($website ? $website : "N/A") . "<br>";
    echo "Comment: $comment <br>";
    echo "Gender: $gender <br>";
}
?>

</body>
</html>
