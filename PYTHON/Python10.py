import mysql.connector

# Establish connection to MySQL database
try:
    mydb = mysql.connector.connect(
        host="localhost",   # Change if your MySQL server is remote
        user="root",        # Change to your MySQL username
        password="password",  # Change to your MySQL password
        database="your_database"  # Change to your database name
    )

    mycursor = mydb.cursor()

    # c) Update the marks of all students by adding 2 marks
    sql = "UPDATE students SET marks = marks + 2"
    mycursor.execute(sql)
    mydb.commit()
    print(mycursor.rowcount, "record(s) updated.")

    # Display all records after the update
    mycursor.execute("SELECT * FROM students")
    myresult = mycursor.fetchall()
    print("\nStudents after update:")
    for x in myresult:
        print(x)

    # d) Delete a student with a given roll number
    rno = int(input("\nEnter the roll number of the student to delete: "))
    sql = "DELETE FROM students WHERE rollno = %s"
    mycursor.execute(sql, (rno,))
    mydb.commit()
    print(mycursor.rowcount, "record(s) deleted.")

    # e) Display the details of a student with a given roll number
    rno = int(input("\nEnter the roll number of the student to display details: "))
    sql = "SELECT * FROM students WHERE rollno = %s"
    mycursor.execute(sql, (rno,))
    myresult = mycursor.fetchall()
    
    print("\nStudent details:")
    if myresult:
        for x in myresult:
            print(x)
    else:
        print("No student found with the given roll number.")

except mysql.connector.Error as err:
    print("Error:", err)

finally:
    # Close the cursor and database connection
    if 'mycursor' in locals():
        mycursor.close()
    if 'mydb' in locals():
        mydb.close()
