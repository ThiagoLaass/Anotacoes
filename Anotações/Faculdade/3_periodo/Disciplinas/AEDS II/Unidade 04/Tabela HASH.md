...no caderno

- Considera-se uma tabela (vetor)
	- é uma função de transformação sobre a chave de pesquisa
- Endereçamento direta
	- por meio da transformação atitmetica da chave de pesquisa (transformar os valores em posições dentro das tabelas)
	- Chaves diferentes podem ser mapeadas para a mesma posição
- Cada elemento pode ser acessado com Theta(1) no caso médio
- O número de comparações de pesquisa, inserção e remoção depende
	- tamanho da tabela
	- quantidade de elementos ja inseridos
	- **fator de carga de uma tabela FC , onde N = número de elementos inseridos, M = tamanho da tabela
$$
	N/M
$$
- Computa-se o valor da função da transformação
	- localiza-se a posição correspondente na tabela 
- **Colisão**: quando duas chaves são endereçadas para a mesma posição na tabela hash
	- Tarefa adicional: tratar possíveis colisões
- **Econtrar uma boa função de transformação** 
	- Mapeia chaves de pesquisa em inteiros
		- Trasforma valores de 0 entre M  - 1, onde M é o tamanho da tabela Hash
	- **Características** 
		- Boa previsão de espaço de ocupação
		- apresentar pequena probabilidade de colisões
		- ser simples de ser computada
		- gerar saídas possíveis com prababilidades iguais
			- as chaves de pesquisa devem ser uniformimente distribuidas entre M entradas possiveis
- **Função identidade** 
	- h(x) = x, onde o valor é a própria chave
- **Resto da divisão**
	- h(x) = x % M
	- M é o tamanho da tabela
	- M influencia na possibilidade de colisão
	- Se as chaves não são númericas, aplica-se uma tranformação a chave antes de submete-la à função de tranformação da tabela hash
- **Tratamento de colisão**
	- Métodos que devem ser empregados para endereçar 
### Resto da Divisão
- Método de transformação a ser utilizado
- É recomendavel escolher um número primo para M onde: h(x) = x % M
### Métodos de transformação indireta
- endereçamento em separado
- resolução por encadeamento
	- dentro de cada posição do vetor, existe uma lista encadeada
	- Hash indireta com lista encadeada simples
### Tratamento de colisões
- Resoulção por calculo
	- Calcula-se uma nova posição na tabela hash
### Hash direta simples com lista encadeada simples
- As posições do vetor são listas encadeadas
- É possível armazenar um número imprevisível de registros
- A tabela pode ter um fator de carga maior do que um
	- Ou seja, pode ter mais elementos que o tamanho da tabela
- Problemas:
	- Clustering
		- Tempo médio de pesquisa aumenta
### Hash direto com Rehash
- Calcula-se uma nova posição no tabela hash para a chave em caso de colisão
- Mais popular
	- Rehash linear, M é o ta4manho da tabela
		- h(x) = (h(x) + i) % M, para 1 <= i <= (M - 1)
	- Degeneração:
		- Tentar inserir um elemento que está em uma posição que ja passou pelo rehash
##### Rehash quadrático
- h(x) = (h(x) + i^2) % M, para 1<= i <= (M - 1)
### Tabela Hash com buckets
- Áreas de registros
- Cada posição tem um vetor dentro dela, um "bucket", que os elementos podem ser alocados se uma colisão acontecer
### Remoção
- marcar o item como removido com uma flag, após isso, restaura a hash
### Restauração da tabela Hash
- Remoção dos itens marcados como removidos