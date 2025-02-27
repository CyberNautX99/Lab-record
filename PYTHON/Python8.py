try:
    # Open the source file in read mode with UTF-8 encoding
    with open("input.txt", "r", encoding="utf-8") as f1:
        # Read the contents of the source file
        content = f1.read()

    # Convert content to uppercase
    upper_content = content.upper()

    # Open the destination file in append mode and write the uppercase content
    with open("output.txt", "a", encoding="utf-8") as f2:
        f2.write(upper_content)

    # Open the destination file in read mode and print its contents
    with open("output.txt", "r", encoding="utf-8") as f2:
        print("\nContents of output.txt:")
        print(f2.read())

# except FileNotFoundError:
#     print("Error: The file 'input.txt' was not found.")
# except UnicodeDecodeError:
#     print("Error: Unable to decode file content. Please check the file encoding.")
# except IOError:
#     print("Error: Can't read or write file data.")
except Exception as e:
    print(f"An unexpected error occurred: {e}")
