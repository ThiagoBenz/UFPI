import time


def bubble_sort(A):
    n = len(A)
    comparisons = 0

    for i in range(n - 1):
        for j in range(n - i - 1):
            comparisons += 1  # Incrementa o contador de comparações
            if A[j] > A[j + 1]:
                A[j], A[j + 1] = A[j + 1], A[j]
    return comparisons


with open('numeros.txt', 'r') as file:
    data = file.readlines()
    numeros = [int(num.strip()) for num in data]

inicio = time.time()
comparisons = bubble_sort(numeros)
fim = time.time()
tempo_decorrido = fim - inicio



print("Tempo de execução: ", tempo_decorrido)
print("Comparações realizadas: ", comparisons)
