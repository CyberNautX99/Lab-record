import tkinter as tk
from tkinter import messagebox

# Function to handle the login logic
def login():
    username = entry_username.get()
    password = entry_password.get()
    
    # Simple validation logic (you can replace this with actual authentication)
    if username == "user" and password == "password":
        messagebox.showinfo("Login Successful", "Welcome, " + username + "!")
    else:
        messagebox.showerror("Login Failed", "Invalid username or password")

# Create the main application window
root = tk.Tk()
root.title("Login Window")

# Set window size
root.geometry("300x150")

# Create and place the username label and entry field
label_username = tk.Label(root, text="Username:")
label_username.pack(pady=5)

entry_username = tk.Entry(root)
entry_username.pack(pady=5)

# Create and place the password label and entry field
label_password = tk.Label(root, text="Password:")
label_password.pack(pady=5)

entry_password = tk.Entry(root, show="*")
entry_password.pack(pady=5)

# Create and place the login button
login_button = tk.Button(root, text="Login", command=login)
login_button.pack(pady=20)

# Run the application
root.mainloop()