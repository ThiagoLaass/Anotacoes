1. **Estudo da complexidade de um algoritmo**
2. **Como calcular a complexidade de um algoritmo**
3. **Porque estudar a complexidade de algoritmos**
4. **Como investigar o custo de um algoritmo**
	1. Não é feita em relação ao tempo demorado para executar as funções, visto que o tempo pode variar de uma maquina para outra
5. **Função de complexidade**
	1. Quantidade de vezes que a operação mais relevante é executada
6. **Casos de complexidade**
	1. Tamanho da entrada
	2. Características peculiares da entrada
		1. Ordenação com dados já ordenados
		2. Ordenação com dados em ordem decrescentes
		3. Ordenação com dados aleatórios 
	3. Três cenários (Para todas entradas possíveis, interesse no pior caso)
		1. Melhor caso: menor tempo de execução
			1. f(n) = 1
		2. Pior caso: maior tempo de execução, tempo máximo de execução do algoritmo
			1. f(n) = n
		3. Caso médio: média de tempo de execução
			1. f(n) = (n+1)/2
7. **Complexidade Fixa (Constante)
	1. As instruções são sempre executadas um número fixo de vezes
8. **Complexidade Logarítmica 
	1. Algoritmos que resolvem problemas os dividindo em partes menores
	2. Calcula-se a complexidade com o log em função de n
	3. **Complexidade nlog(n)
		1. Quebram o problemas em partes menores
9. **Complexidade Linear
	1. Uma operação é realizada para cada elemento da entrada
10. **Complexidade Quadrática
	1. f(n) = n^2
	2. Itens processados por pares
		1. Tipicamente um laço dentro do outro
	3. Útil para problemas pequenos
11. **Complexidade Cúbica
	1. f(n) = n^3
12. **Complexidade Exponencial
	1. f(n) = c^n
13. **Complexidade Fatorial
	1. f(n) = n!