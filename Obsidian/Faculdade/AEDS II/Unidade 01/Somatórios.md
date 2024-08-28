### Notação do somatório

![[Pasted image 20240820213159.png]]

- A exemplo de código, o somatório pode ocorrer em laços de repetição, em um for que vai ate **i < n**, o índice final será **n - 1**, **visto que i não assume o valor de n.**
- Neste caso, m ou índice inicial, será o valor que i é inicializado dentro do for

#### Exemplo de exercício de somatório

![[Pasted image 20240820220200.png]]
Resposta: 0 + 0 + 6 + 12 + 12 + 0  = 30

### Manipulação de somas
 - Constantes podem ser jogadas para fora (Igual é feito com integrais)
 - Associatividade: um somatório pode ser desmembrado, ou unificado (igual é feito com integrais)
	 - Para unificar somatórios com intervalos diferentes, basta 
		 - Trabalhar com um dos somatórios, e adicionar o intervalo que ficará de "fora"
		 - Adotar o intervalo que inclui ambos os intervalos subtraindo a parte que não faz parte do somatório
		 - ![[Pasted image 20240822212817.png]]
		- Quando o limite diferente é o superior, basta
			- Fazer a mesma coisa, porem no final do somatório
- Comutatividade
	- Permite colocar os termos do somatório em qualquer ordem
	- Exemplo em código (Somatório dos elementos de uma matriz)
		![[Pasted image 20240822214026.png]]

### Formula fechada para resolver somatório
- Aplicando a comutatividade 
	- ![[Pasted image 20240822215653.png]]
		- Aplicamos a regra para obter a formula fechada do somatório
		- Chegando em:
			- ![[Pasted image 20240822215934.png]]
				- Podemos dividir por 2, neste caso S = a função dividida por 2
					- ![[Pasted image 20240822220411.png]]