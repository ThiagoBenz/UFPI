import time
import random

def Partition(A, p, r):
    # Escolher um índice aleatório entre p e r
    pivot_index = random.randint(p, r)
    A[pivot_index], A[r] = A[r], A[pivot_index]  # Trocar o pivô com o último elemento

    x = A[r]
    i = p - 1
    comparações = 0

    for j in range(p, r):
        if A[j] <= x:
            i = i + 1
            A[i], A[j] = A[j], A[i]
            comparações += 1

    A[i + 1], A[r] = A[r], A[i + 1]
    return i + 1, comparações


def QuickSort(A, p, r):
    if p < r:
        q, comparações = Partition(A, p, r)
        comparações += QuickSort(A, p, q - 1)
        comparações += QuickSort(A, q + 1, r)
        return comparações
    else:
        return 0


# Lendo os números do arquivo e atribuindo ao array
with open('numeros.txt', 'r') as arquivo:
    arr = [int(line.strip()) for line in arquivo]

# Executando o Heapsort e medindo o tempo de processamento
inicio = time.time()
comparações = QuickSort(arr, 0, len(arr)-1)
fim = time.time()
tempo_execucao = fim - inicio

print("Tempo de execução: ", tempo_execucao)
print("Comparações realizadas: ", comparações)
