def merge_sort(arr):
    if len(arr) <= 1:
        return arr

    # Divide o array ao meio
    mid = len(arr) // 2
    left_half = arr[:mid]
    right_half = arr[mid:]

    # Chama recursivamente o merge sort para as duas metades
    left_half = merge_sort(left_half)
    right_half = merge_sort(right_half)

    # Combina as duas metades ordenadas
    return merge(left_half, right_half)


def merge(left, right):
    merged = []
    left_index = 0
    right_index = 0

    # Combina as duas metades ordenadas em uma única lista ordenada
    while left_index < len(left) and right_index < len(right):
        if left[left_index] < right[right_index]:
            merged.append(left[left_index])
            left_index += 1
        else:
            merged.append(right[right_index])
            right_index += 1

    # Adiciona os elementos restantes da metade esquerda, se houver
    while left_index < len(left):
        merged.append(left[left_index])
        left_index += 1

    # Adiciona os elementos restantes da metade direita, se houver
    while right_index < len(right):
        merged.append(right[right_index])
        right_index += 1

    return merged

