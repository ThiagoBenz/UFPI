def insertion_sort(arr):
    # Percorre o array a partir do segundo elemento
    for i in range(1, len(arr)):
        key = arr[i]  # Elemento atual sendo comparado
        j = i - 1  # Índice do elemento anterior

        # Desloca os elementos maiores que a chave para a direita
        while j >= 0 and arr[j] > key:
            arr[j + 1] = arr[j]
            j -= 1

        # Insere a chave na posição correta
        arr[j + 1] = key

