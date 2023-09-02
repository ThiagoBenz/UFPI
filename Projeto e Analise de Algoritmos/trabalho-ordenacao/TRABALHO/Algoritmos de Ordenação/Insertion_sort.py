import time

def insertion_sort(arr):
    comparisons = 0  # Variável contador de comparações

    # Percorre o array a partir do segundo elemento
    for i in range(1, len(arr)):
        key = arr[i]  # Elemento atual sendo comparado
        j = i - 1  # Índice do elemento anterior

        # Desloca os elementos maiores que a chave para a direita
        while j >= 0 and arr[j] > key:
            arr[j + 1] = arr[j]
            j -= 1
            comparisons += 1  # Incrementa a contagem de comparações

        # Insere a chave na posição correta
        arr[j + 1] = key

    return comparisons


with open('numeros.txt', 'r') as file:
    data = file.readlines()
    arr = [int(num.strip()) for num in data]

inicio = time.time()
comparisons = insertion_sort(arr)
fim = time.time()
tempo_decorrido = fim - inicio


# print("Array ordenado:", arr)
print("Tempo de execução: ", tempo_decorrido)
print("Quantidade de comparações:", comparisons)