def fibonacci(n):
    if n <= 1:
        return n
    else:
        return fibonacci(n - 1) + fibonacci(n - 2)


n = int(input("Enter number of terms: "))


if n <= 0:
    print("Please enter a positive integer")
else:
    print("Fibonacci sequence:")
    for i in range(n):
        print(fibonacci(i))






#  def fibonacci(n):
#     if n <= 0:
#         raise ValueError("The input number must be a positive integer.")
#     if n == 1:
#         return 0
#     if n == 2:
#         return 1
#     return fibonacci(n - 1) + fibonacci(n - 2)

# def generate_fibonacci_series(length):
#     if length <= 0:
#         raise ValueError("The length of the series must be a positive integer.")
#     series = []
#     for i in range(1, length + 1):
#         series.append(fibonacci(i))
#     return series

# # Example usage:
# length = 10
# series = generate_fibonacci_series(length)
# print(f"The first {length} numbers in the Fibonacci series are: {series}")