def merge_sort(arr):
    if len(arr) <= 1:
        return arr

    # Find the middle point to divide the array into two halves
    mid = len(arr) // 2

    # Call merge_sort for the first half
    left_half = merge_sort(arr[:mid])

    # Call merge_sort for the second half
    right_half = merge_sort(arr[mid:])

    # Merge the two halves sorted in step 2 and 3
    return merge(left_half, right_half)

def merge(left, right):
    merged = []
    left_index, right_index = 0, 0

    # Traverse both arrays and insert smaller of both elements in merged array
    while left_index < len(left) and right_index < len(right):
        if left[left_index] < right[right_index]:
            merged.append(left[left_index])
            left_index += 1
        else:
            merged.append(right[right_index])
            right_index += 1

    # Collect remaining elements (if any)
    merged.extend(left[left_index:])
    merged.extend(right[right_index:])

    return merged

# Example usage:
arr = [38, 27, 43, 3, 9, 82, 10]
sorted_arr = merge_sort(arr)
print(f"Sorted array: {sorted_arr}")