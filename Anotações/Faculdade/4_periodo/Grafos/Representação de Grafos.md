## Matriz de Adjacencia
#### Grafo Direcionado
- Vertice por vertice, com N linhas e N colunas, uma para cada vertice. 
- Na matriz, utilizamos o valor *1* se a aresta está saindo do vertice, caso contrário, 0.
### Grafo não Direcionado
- Neste caso, o valor é -1 para arestas que estão saindo do vertice, e 1 para as que estão entrando, neste caso, sem direção

## Matriz de Incidencia
- Vertices que estão incidindo em uma aresta
### Grafo Direcionado
- Grafo com n arestas e m vertices, permite representação em matriz, onde -1 para o vertice onde a aresta sai, e 1 quando entra e 0 quando não se conecta.
### Grafo não Direcionado
- Apenas coloca 1 se a aresta conecta ao vertice
## Lista de Adjacencia
- Representação de de um grafo não direcionado por meio de listas de adjacencia, uma para cada vertice.
- Também é possível representar o grafo direcionado de modo que a lista de adjacencia representa todos os vertices que são sucessores ou predecessores de v.
- Representa-se na lista os vertices que alcançam aquele vertice, é possível armazenar ambos, mas é custoso