def find_largest(numbers):
    if not numbers:
        return None  # Return None if the list is empty

    largest = numbers[0]  # Assume the first number is the largest

    for number in numbers:
        if number > largest:
            largest = number  # Update largest if the current number is bigger

    return largest

# Example usage:
numbers = [10, 24, 5, 37, 42, 18, 4]
largest_number = find_largest(numbers)
print("The largest number is:", largest_number)