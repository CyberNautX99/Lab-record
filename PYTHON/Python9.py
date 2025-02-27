numberList = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
ansList = []

for num in numberList:
    # Check for numbers less than 2
    if num <= 1:
        continue
    
    # Assume num is prime until proven otherwise
    is_prime = True

    # Check for factors from 2 to sqrt(num)
    for i in range(2, int(num**0.5) + 1):
        if num % i == 0:
            is_prime = False
            break
    
    # If no factors were found, num is prime
    if is_prime:
        ansList.append(num)

# Print the result
if ansList:
    print("Prime Numbers:")
    for ans in ansList:
        print(ans)
else:
    print("No number in the given list is Prime")