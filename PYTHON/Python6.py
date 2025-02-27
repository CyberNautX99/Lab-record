def gcd(a, b):
    while b:
        a, b = b, a % b
    return a

def lcm(a, b):
    return a * b // gcd(a, b)

# Example usage:
num1 = int(input('Enter The first number'))
num2 = int(input('Enter the Second number'))

gcd_result = gcd(num1, num2)
lcm_result = lcm(num1, num2)

print(f"The GCD of {num1} and {num2} is {gcd_result}.")
print(f"The LCM of {num1} and {num2} is {lcm_result}.")