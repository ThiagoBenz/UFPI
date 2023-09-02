def kruskal(grafo):

    # Ordene as arestas do grafo pelo menor peso.
    arestas = sorted(grafo, key=lambda x: x[2])
    """
    A função sorted() recebe como parâmetro uma lista e retorna uma nova lista
    ordenada. O parâmetro key recebe uma função que será aplicada a cada
    elemento da lista antes de ordená-la. Neste caso, a função lambda recebe
    uma aresta e retorna o seu peso. x[0] é um vertice, x[1] é outro vertice e
    x[2] é o peso da aresta.
    """

    # Crie um conjunto vazio de arestas.
    arvore_geradora = set()

    # Comece com um conjunto vazio de vértices.
    visitados = set()

    # Para cada aresta no grafo:
    for aresta in arestas:
        # Se a aresta não cria um ciclo, adicione-a ao conjunto de arestas.
        if aresta[0] not in visitados and aresta[1] not in visitados:
            arvore_geradora.add(aresta)
            visitados.add(aresta[0])
            visitados.add(aresta[1])

    return arvore_geradora

