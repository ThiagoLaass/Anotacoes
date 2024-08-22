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