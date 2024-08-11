**Estudo da complexidade de um algoritmo**
**Como calcular a complexidade de um algoritmo**
**Porque estudar a complexidade de algoritmos**
**Como investigar o custo de um algoritmo**
	Não é feita em relação ao tempo demorado para executar as funções, visto que o tempo pode variar de uma maquina para outra
**Função de complexidade**
	Quantidade de vezes que a operação mais relevante é executada
### Casos de complexidade
#### 1. Tamanho da entrada
1. Características peculiares da entrada
	1. Ordenação com dados já ordenados
	1. Ordenação com dados em ordem decrescentes
	1. Ordenação com dados aleatórios 
		
#### 2. Três cenários (Para todas entradas possíveis, interesse no pior caso)
1. Melhor caso: menor tempo de execução
	1. f(n) = 1
2. Pior caso: maior tempo de execução, tempo máximo de execução do algoritmo
	1. f(n) = n
3. Caso médio: média de tempo de execução
	1. f(n) = (n+1)/2	
#### 3. Tipos de Complexidade
1. **Complexidade Fixa (Constante)
	1. As instruções são sempre executadas um número fixo de vezes
	
4. **Complexidade Logarítmica 
	1. Algoritmos que resolvem problemas os dividindo em partes menores
	1. Calcula-se a complexidade com o log em função de n
	1. **Complexidade nlog(n)
		1. Quebram o problemas em partes menores
		
5. **Complexidade Linear
	1. Uma operação é realizada para cada elemento da entrada
	
6. **Complexidade Quadrática
	1. f(n) = n^2
	1. Itens processados por pares
		1. Tipicamente um laço dentro do outro
	1. Útil para problemas pequenos
	
7. **Complexidade Cúbica
	1. f(n) = n^3
8. **Complexidade Exponencial
	1. f(n) = c^n
9. **Complexidade Fatorial
	1. f(n) = n!