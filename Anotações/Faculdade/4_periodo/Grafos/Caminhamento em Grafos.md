# Passeio
### Definição
- Uma sequencia alternante de vertices e arestas. Cada aresta é incidente ao vertice que a segue na sequencia. Em um grafo simples, é possível apenas representar pelo par de vertices
### Passeio aberto
#### Definição
- Um passeio quando o ultimo nó não é igual ao inicial
### Passeio fechado
#### Definição
- Um passeio quando o ultimo nó é igual ao inicial
# Trajeto
### Definição
- É um trajeto onde não se repete os vertices. Existe caminho aberto e fechado, onde só é pérmitido repitir o vertice caso ele seja o ultimo vertice do trajeto, o que o torna um trajeto fechado.
#### Tamanho
- Calculamos o tamanho de um trajeto pelo número de arestas
# Distancia entre vertices
### Definição
- Dado dois vertices *v* e *w*, a distancia entre eles é representada por $d (w,v)$, onde é o tamanho do menor caminho entre *v* e *w*
### Distancia
- É a menor distancia entre dois vertices
### Excentricidade
- Representado por $E(v)$, é a maior distancia de v para qualquer outro vertice, ou seja, o vertice mais longe de *v*
### Raio
- Limite inferior, menor excentricidade
### Diametro
- Limite superior, maior excentricidade
# Grafo Linear
### Definição
- Um grafo $G(v,e)$, com $n>1$ vertices é dito como linear, quando possui apenas dois vertices de grau e o restante de grau 2
# Ciclo
### Definição
- Dado um grafo $G(v,e)$, um ciclo é um caminho fechado, que está dentro desse grafo G
- Em grafos direcionados, são chamados de circuito
## Grafo Ciclo
### Definição
- Um grafo formado por um caminho fechado, onde todos os vertices tem grau 2