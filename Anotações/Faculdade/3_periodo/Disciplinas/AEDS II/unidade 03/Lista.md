### Lista implementada vetor
- Insere um elemento em qualquer posição
	- Antes de fazer uma inserção, é preciso fazer uma verificação se a posição desejada é menor ou igual a ultimo, caso não seja, não e possível alocar um elemento depois de ultimo, visto que perderiamos a referência da lista
- Desloca os outros elementos para abrir um espaço para o elemento ser alocado
	- Caso queira alocar um elemento na posição 1, e um elemento ja existir na posição, é necessário mover todos os elementos para abrir o espaço
	- Após isso, incrementar a posição último (variavel numérica utilzada para controle)
- Para remover um elemento, é necessário fazer a mesma coisa, porém utilizando um for de i = P ate ultimo - 1
- **NOTE:** ultimo não aponta para o ultimo ELEMENTO da lista, e sim para a posição seguinte a ele, ou seja, se o ultimo elemento está na posição 3, a variavel ultimo tem o valor 4
- Para verificar se a lista está vazia, primeiro == ultimo
### Lista implementada por célula
- ultimo é uma celula que aponta para null e possui o ultimo item de uma lista
- Também chamadas de encadeadas
- Estrutura flexível
- Ultimo sempre referência um null
- Inserir
	- No final da lista
		- ultimo.setProximo(item), após crirar uma nova celula
		- ultimo = novaCelula
		- ultimo.setProximo(null)
#### Inserção dinâmica, em qualquer posição da lista
- Localizar a posição que o novo item será inserido
	- percorrendo a lista
- Criar uma celula nova e colocar o novo item
- liga-la as celulas entre as quais deve ser posicionada, atualizando as referências
- Criar 3 celulas auxiliares
	- Celula anterior, Celula proxima e Celula novaCelula
		- celula anterior referência novaCelula e novaCelula referencia proxima
	- anterior = primeiro, depois um for que i vai ate < P (posição a ser inserida), i++. Dentro do for, temos: anterior = anterior.getProximo(), fora do for: proximo = anteior.getProximo()
		- ate chegar na posicao que queremos alocar o elemento, ai criando as celulas de referencia