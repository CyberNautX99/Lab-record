def bisection_sqrt(x, epsilon=1e-10):
    if x < 0:
        raise ValueError("Cannot compute the square root of a negative number.")

    if x == 0 or x == 1:
        return x

    low = 0
    high = max(1, x)
    guess = (low + high) / 2.0

    while abs(guess**2 - x) > epsilon:
        if guess**2 < x:
            low = guess
        else:
            high = guess
        guess = (low + high) / 2.0

    return guess

# Example usage:
number = 5
result = bisection_sqrt(number)
print(f"The square root of {number} is approximately {result}.")
