import time


def constroiHeapMax(A, tamHeap):
    comparações = 0  # Variável contador de comparações

    for i in range(tamHeap // 2 - 1, -1, -1):
        comparações += refazHeapMax(A, tamHeap, i)

    return comparações


def refazHeapMax(A, tamHeap, i):
    esq = 2 * i + 1
    dir = 2 * i + 2
    maior = i
    comparações = 0  # Variável contador de comparações

    if esq < tamHeap and A[esq] > A[maior]:
        maior = esq
        comparações += 1

    if dir < tamHeap and A[dir] > A[maior]:
        maior = dir
        comparações += 1

    if maior != i:
        A[i], A[maior] = A[maior], A[i]
        comparações += 1
        comparações += refazHeapMax(A, tamHeap, maior)

    return comparações

def HeapSort(A):
    tamHeap = len(A)

    comparações = constroiHeapMax(A, tamHeap)

    for i in range(tamHeap - 1, 0, -1):
        A[0], A[i] = A[i], A[0]
        tamHeap -= 1
        comparações += refazHeapMax(A, tamHeap, 0)

    return comparações


# Lendo os números do arquivo e atribuindo ao array
with open('numeros.txt', 'r') as arquivo:
    arr = [int(line.strip()) for line in arquivo]

# Executando o Heapsort e medindo o tempo de processamento
inicio = time.time()
comparações = HeapSort(arr)
fim = time.time()
tempo_execucao = fim - inicio


print("Tempo de execução: ", tempo_execucao)
print("Comparações realizadas: ", comparações)
