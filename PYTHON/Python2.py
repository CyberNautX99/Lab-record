# def is_perfect(num):
#     """Function to check if a number is perfect"""
#     sum_divisors = sum([i for i in range(1, num) if num % i == 0])
#     return sum_divisors == num

# def generate_perfect_numbers(n):
#     """Function to generate first n perfect numbers"""
#     perfect_numbers = []
#     num = 2  # Start checking from 2
#     while len(perfect_numbers) < n:
#         if is_perfect(num):
#             perfect_numbers.append(num)
#         num += 1
#     return perfect_numbers

# # Example usage:
# n = int(input("Enter the number of perfect numbers to generate: "))
# print(generate_perfect_numbers(n))



def PerfectNumbers(start, end):
    for i in range(start, end + 1):
        sum = 0
        for x in range(1, i):
            if i % x == 0:
                sum += x
        if sum == i:
            print(i)

n=int(input("Enter the starting range:"))
m=int(input("Enter the ending range:"))
PerfectNumbers(n,m)

