import random
'''
# Gerando números aleatórios e escrevendo no arquivo
numeros = random.sample(range(800000), 100)  # Gera 200.000 números aleatórios de 0 a 800.000
with open('numeros.txt', 'w') as arquivo:
    arquivo.write('\n'.join(map(str, numeros)))
'''

'''
# Gerando números em ordem crescente
numeros = []
for i in range(1, 101):
    numeros.append(i)

# Escrevendo os números no arquivo
with open('numeros.txt', 'w') as arquivo:
    for numero in numeros:
        arquivo.write(str(numero) + '\n')
'''


# Gerando números em ordem decrescente
numeros = []
for i in range(100, 0, -1):
    numeros.append(i)

# Escrevendo os números no arquivo
with open('numeros.txt', 'w') as arquivo:
    for numero in numeros:
        arquivo.write(str(numero) + '\n')

