import time
def merge_sort(arr):
    if len(arr) <= 1:
        return arr, 0

    # Divide o array ao meio
    mid = len(arr) // 2
    left_half = arr[:mid]
    right_half = arr[mid:]

    # Chama recursivamente o merge sort para as duas metades
    left_half, left_comparisons = merge_sort(left_half)
    right_half, right_comparisons = merge_sort(right_half)

    # Combina as duas metades ordenadas
    merged, merge_comparisons = merge(left_half, right_half)

    # Retorna o array ordenado e o total de comparações
    return merged, left_comparisons + right_comparisons + merge_comparisons


def merge(left, right):
    merged = []
    left_index = 0
    right_index = 0
    comparisons = 0  # Variável contador de comparações

    # Combina as duas metades ordenadas em uma única lista ordenada
    while left_index < len(left) and right_index < len(right):
        if left[left_index] < right[right_index]:
            merged.append(left[left_index])
            left_index += 1
        else:
            merged.append(right[right_index])
            right_index += 1
        comparisons += 1  # Incrementa a contagem de comparações

    # Adiciona os elementos restantes da metade esquerda, se houver
    while left_index < len(left):
        merged.append(left[left_index])
        left_index += 1

    # Adiciona os elementos restantes da metade direita, se houver
    while right_index < len(right):
        merged.append(right[right_index])
        right_index += 1

    return merged, comparisons


with open('numeros.txt', 'r') as file:
    data = file.readlines()
    arr = [int(num.strip()) for num in data]

inicio = time.time()
comparisons = merge_sort(arr)
fim = time.time()
tempo_decorrido = fim - inicio


sorted_arr, comparisons = merge_sort(arr)
# print("Array ordenado:", sorted_arr)
print("Tempo de execução: ", tempo_decorrido)
print("Quantidade de comparações:", comparisons)
