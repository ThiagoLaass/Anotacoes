### Fila de tarefas
- Conjunto de todos os processos do sistema
### Fila de Processos Prontos
- Processos que estão na memória com o status Pronto, [[Conceito de Processo]]
### Fila de Dispositivos
- Processo que estão em espera por um dispositivo de I/O
### O processo migra entre estas diversas filas
- O SO cria uma estrutura de filas que contém processos que são de acordo com a fila que se encontra
### Escalonadores
- Quem faz a troca dos processos
	- **De longo prazo:** seleciona quais processos devem ser levados para a memória, na fila de processos prontos
		- São invocados com menos frequência
		- Controla o grau de multiprogramação 
	- **De curto prazo**: Seleciona qual processo vai ser executado pela CPU
		- São invocados com frequência
### Troca de contexto
- O ato de trocar um processo que foi executado por um que esta na memória esperando ser executado
	- O estado do processo que saiu da CPU é salvo
	- O tempo que é gasto depende do hardware
### Surto de CPU
- Tempo que o processo necessita de CPU
	- Tempo que o processo fica na CPU
- Na disciplina, será considerado apenas processadores de um núcleo
- Dispatcher 
	- Quem pega o processo e joga para o processador, e vice-versa
- Tempo de resposta (É sempre o tempo da primeira resposta, ou seja, a primeira vez que ele foi executado)
	- Medidos em surtos de CPU
	- Quando o processo tem alguma resposta (Quando foi executado pelo menos uma vez)
- Tempo de espera
	- Medidos em surtos de CPU
	- Tempo total que o processo espera ser executado na memória

### FCFS (Firs come first served)
- A **troca de contexto** só acontece quando o processo é terminado
- Mesmo conceito da fila (first in first out)
- Diagrama de Gandhi
	- Os processos são executados de forma linear
	- O primeiro que chegar será o primeiro a ser executado
- O tempo de espera médio é calculado por, **em t o tempo de espera, e n a quantidade de processos, o tempo de espera é o tempo de execução menos o tempo em que ele chegou** 
$$
	Σ(t)/n
$$
### SJR (Shortest job first)
- Neste caso, o processo com o menor tempo de CPU necessário será executado primeiro
- É calculado com a mesma formula abaixo, porém seguindo a ordem de processo mais curto
$$
	Σ(t)/n
$$

#### SJR não preemptivo
- Uma vez que a CPU é dada ao processo, não pode ser retirada até completar seu surto
- ![[Pasted image 20240828214120.png]]

#### SJF preemptivo
- Se um processo com menor tempo de execução do que o que está sendo executado atualmente chega na memória, é feita a troca
- É feita a subtração do tempo de execução com o tempo de chegada
- ![[Pasted image 20240828214418.png]]
	- A troca é feita de acordo com o tempo de processamento restante do processo que esta sendo executado
		- Se um processo com um tempo menor do que este, ele começa a ser executado


### Escalonamento por prioridade
### Round Robin
- Cada processo recebe um tempo de CPU para ser executado
	- Não importa o tempo que o processo precisa, se este precisa mais do que estabelecido, ele volta pra memória e aguarda ser executado novamente
	- Se o processo precisa de menos tempo que o estabelecido, a execução termina no tempo em que ele finaliza e outro processo começa a ser executado
- Trabalha com a ideia de fila (first in first out)
	- Porém circular, quando a fila acaba e ainda existem processos, ele volta para o início da fila
- ![[Pasted image 20240902211817.png]]
	- O tempo de espera é calculado da mesma forma
		- Considera o tempo de saída de cada um
### Múltiplas filas
- Separa os processos em filas
	- Cada fila um segue um parâmetro para os processos que estão nela
- Cada fila pode ser independente
- Cad fila pode ter um algoritmo diferente 
- Existe escalonamento entre as filas
	- Prioridade fixa: a primeira tem prioridade
	- Fatia de tempo: cada fila tem seu tempo de CPU
#### Filas múltiplas com realimentação
- Um processo pode mudar de filas
- Definido por:
	- Número de filas
	- escalonamento de cada fila
	- método utilizado para rebaixar ou promover um processo
	- 