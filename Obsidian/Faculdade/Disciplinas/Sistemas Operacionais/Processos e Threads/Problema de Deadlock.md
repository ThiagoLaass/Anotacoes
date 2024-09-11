- Um recurso de um processo bloqueado, que depende de recursos de outros processos
### Caracterização de Deadlock
Um deadlock apenas acontece se os seguintes itens acontecerem
- Exclusão mútua
	- apenas um processo pode utilizar aquele recurso
- Posse e espera
	- um processo espera por recursos que estão sendo utilizados por outros processos
- Não preempção
	- um recurso só é liberado pelo processo que o mantém, após finalizar seu processamento
- Espera circular
	- Um conjunto de processos que dependem do recurso mantido processo seguinte
	- P1 espera por P2, P2 espera por P3 e P3 por P1
### Grafo de alocação de recursos
![[Pasted image 20240904212412.png]]

#### Exemplo gráfico
![[Pasted image 20240904212511.png]]
#### Exemplo de grafo
![[Pasted image 20240904213906.png]]
#### Exemplo com deadlock
![[Pasted image 20240904214926.png]]
### Métodos de tratamento para Deadlock
- Evitar que ele aconteça
	- Assegurar que pelo menos uma das condições de existência do deadlock nunca aconteça
	- Mapear previamente os recursos que cada processo irá utilizar
	- Abortar um deadlock, abortando os processos que poderiam causar
- Deixar ele acontecer e o tratar caso aconteça