## Mergesort
- Segue o paradigma "Dividir e conquistar"
	- Divide o problema em problemas menores
	- Resolve cada subproblema
	- Agrupa a resolução para cada subproblema
- Tem o comportamento $n.log(n)$
- Usa o principio das intercalações para ordenar dados
- É um método recursivo
- Não é um algoritmo in-place (a quantidade de memória alocada depende do tamanho do vetor)
- Faz a intercalação ordenada de dois vetores
	- Ambos os vetores tem que estar ordenados
- Para ordenar um vetor, é feito a separação dele, ate chegar em vetores de tamanho 1, ordenando os subvetores ate ordenar o vetor principal
- No mergesort, o método é chamado até que o sub-array seja de tamanho 1, e então é chamado o método intercalar, que ordena de acordo com as variáveis que delimitam a esquerda, direita e o meio.  
## HeapSort
- O Heapsort não trabalha com a posição 0
- A primeira coisa a ser feita é construir o Heap
	- A fim de ter certeza que sempre o primeiro elemento do vetor é o maior
- Toma como referencia o ultimo elemento da Heap e troca com o primeiro (maior)
- Tem um desempenho $O(nlogn)$ para todos os casos
- Algoritmo in-place
- Não é estável
	- A ordem dos elementos iguais podem ser trocadas
## QuickSort
