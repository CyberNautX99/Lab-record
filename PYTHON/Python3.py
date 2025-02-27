def binary_search(sorted_list, target):
    left = 0
    right = len(sorted_list) - 1

    while left <= right:
        mid = (left + right) // 2
        mid_value = sorted_list[mid]

        if mid_value == target:
            return mid  # Target found, return its index
        elif mid_value < target:
            left = mid + 1  # Continue search in the right half
        else:
            right = mid - 1  # Continue search in the left half

    return -1  # Target not found

# Example usage:
sorted_list = [1, 3, 5, 7, 9, 11, 13, 15, 17, 19]
target = 1
result = binary_search(sorted_list, target)

if result != -1: #i.e. target is founded
    print(f"Target {target} found at index {result}.")
else:
    print(f"Target {target} not found in the list.")

