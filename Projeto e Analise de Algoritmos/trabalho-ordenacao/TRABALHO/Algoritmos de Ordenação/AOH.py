import time

def insertion_sort(arr, left=0, right=None):
    if right is None:
        right = len(arr) - 1

    comparisons = 0
    for i in range(left + 1, right + 1):
        j = i
        while j > left and arr[j] < arr[j - 1]:
            arr[j], arr[j - 1] = arr[j - 1], arr[j]
            j -= 1
            comparisons += 1

    return comparisons

def merge(arr, left, mid, right):
    left_arr = arr[left:mid + 1]
    right_arr = arr[mid + 1:right + 1]

    left_index = 0
    right_index = 0
    index = left

    comparisons = 0
    while left_index < len(left_arr) and right_index < len(right_arr):
        if left_arr[left_index] <= right_arr[right_index]:
            arr[index] = left_arr[left_index]
            left_index += 1
        else:
            arr[index] = right_arr[right_index]
            right_index += 1

        index += 1
        comparisons += 1

    while left_index < len(left_arr):
        arr[index] = left_arr[left_index]
        left_index += 1
        index += 1

    while right_index < len(right_arr):
        arr[index] = right_arr[right_index]
        right_index += 1
        index += 1

    return comparisons

def hibrido(arr):
    min_run = 32
    n = len(arr)

    total_comparisons = 0
    for i in range(0, n, min_run):
        total_comparisons += insertion_sort(arr, i, min((i + min_run - 1), n - 1))

    size = min_run
    while size < n:
        for start in range(0, n, size * 2):
            mid = start + size - 1
            end = min((start + size * 2 - 1), (n-1))

            total_comparisons += merge(arr, start, mid, end)

        size *= 2

    return total_comparisons


with open('numeros.txt', 'r') as file:
    data = file.readlines()
    arr = [int(num.strip()) for num in data]


start_time = time.time()
comparisons = hibrido(arr)
end_time = time.time()

# print(f"Sorted array: {arr}")
print(f"Comparações realizadas: {comparisons}")
print(f"Tempo de execução: {end_time - start_time:.6f} seconds")