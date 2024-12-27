Todas inserções feitas em um exxtremo
Remoções feitas no outro
Uma fila como qualquer outra, o primeiro que chega é o primeiro a sair (first in first out)
Dados ordenados em ordem de chegada
**Todas as afirmações acima partem do pressuposto que não exite prioridade entre os dados presentes na fila**

### Operações
##### Criar uma fila vazia
##### Inserir um novo item (enfileirar)
1. inserir elemento no final (arr [tras % arr.length] = item)
2. tras++
##### Retirar um item (desenfileirar)
1. retorna o elemento que está na posição frente % arr.length
2. frente++
##### Verificar se a fila está vazia
1. **fila vazia:** trás e frente tem o mesmo valor
2. **fila cheia:** trás + 1 % arr.length ==  frente % arr.length
##### Imprimir toda a fila
1. laço entre frente e trás
### Variáveis de controle
- frente
- trás
