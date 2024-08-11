### Estudo da complexidade de um algoritmo:
Com o objetivo de tomar conhecimento das limitações de memória, processamento, e tempo de execução. O tempo de execução tende a crescer com base na quantidade de entradas que o algoritmo tem
### Como calcular a complexidade de um algoritmo:
Define-se o custo de cada operação a ser executada por um algoritmo. 
	Levando em consideração apenas as operações mais significativas, como exemplificado nos [[Exercícios]] feitos em sala
### Como investigar o custo de um algoritmo:
Não é feita em relação ao tempo demorado para executar as funções, visto que o tempo pode variar de uma maquina para outra
### Função de complexidade:
Quantidade de vezes que a operação mais relevante é executada
	Ou o tempo necessário para a execução de um programa
##### Exemplo:
	![[Pasted image 20240810230333.png]]
	Qual a operação mais relevante?
		Comparação
	Quanto tempo se gasta para executar a função?
		A comparação acontece n-1 vezes para n > 0

### Casos de complexidade
#### Tamanho da entrada
1. Características peculiares da entrada
	1. Ordenação com dados já ordenados
	2.  Ordenação com dados em ordem decrescentes
	3. Ordenação com dados aleatórios
#### Três cenários (Para todas entradas possíveis, interesse no pior caso)
1. Melhor caso: menor tempo de execução
	f(n) = 1
2. Pior caso: maior tempo de execução, tempo máximo de execução do algoritmo
	f(n) = n
3. Caso médio: média de tempo de execução
	f(n) = (n+1)/2	
#### Tipos de Complexidade
1. **Complexidade Fixa (Constante)**
	1. As instruções são sempre executadas um número fixo de vezes
	
2. **Complexidade Logarítmica**
	Algoritmos que resolvem problemas os dividindo em partes menores
	Calcula-se a complexidade com o log em função de n
	**Complexidade nlog(n)**
		Quebram o problemas em partes menores, agrupando as soluções
		
3. **Complexidade Linear**
	Uma operação é realizada para cada elemento da entrada
		fn(n) = n
	
4. **Complexidade Quadrática**
	Itens processados por pares. 
	Tipicamente um laço dentro do outro. 
	Útil para problemas pequenos
		f(n) = n^2
	
5. **Complexidade Cúbica**
	Utilizados em problemas ainda menores do que aqueles na complexidae quadrática
		f(n) = n^3
	
6. **Complexidade Exponencial**
	Utilizados em algoritmos de força bruta
		f(n) = c^n
	
7. **Complexidade Fatorial**
	Comportamento similar a exponencial, pórem muito pior na prática
		f(n) = n!