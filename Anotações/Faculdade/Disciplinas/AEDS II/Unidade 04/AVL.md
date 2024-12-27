### Corrigir o equilibrio da árvore
#### Rotação a esquerda
- Mover os nós que estão na subarvore da direita para a esquerda
- Raiz original vira filho da esquerda da nova raiz
- O filho da esquerda do filho a direita vira o filho a direita do filho a esquerda
- ![[Pasted image 20241119183956.png]]
#### Rotação a direita
- Ocorre de forma análoga a rotação da esquerda
- ![[Pasted image 20241119184222.png]]

#### Rotação Dupla à esquerda
- ![[Pasted image 20241119184446.png]]

#### Rotação dupla à direita
- ![[Pasted image 20241119184547.png]]

## Como decidir qual utilizar
- Calcular fator de equilibrio
	- Se for maior que um
		- Se a subarvore a direita tem o valor menor que 0, utilizamos a rotação dupla a esquerda
		- Senão, utilizamos a rotação a esquerda
	- Se for menor que -1
		- Se a subarvore da esquerda tem o valor maior que 0, utilizamos a rotação dupla a direita
		- Senão utilizamos a rotação a direita