### Definição
- Pedaço de um processo
	- São processos leves
		- Unidade básica de CPU
		- Possuem registradores e pilhas
	- Threads de um mesmo processo compartilham dados e recursos do sistema
	- Compartilham o mesmo espaço de endereçamento do processo em que ela faz parte
- Se o CPU possui multi-thread, é possível realizar varias threads ao mesmo tempo
	- Se o CPU possui apenas um núcleo, a execução das threads são em sequência
### Multi-core
- Um CPU que possui vários núcleos físicos
	- Permite a execução de vários processos ao mesmo tempo, assim como varias threads
		- Cada thread em núcleo, caso o processador tenha capacidade para mais de uma thread por núcleo, são executadas paralelamente 
### Criação, término e cancelamento
- É iniciado por uma chamada de sistema
	- É definido como um fluxo de execução
- Uma thread é finalizada quando sua execução termina
- É possível cancelar uma thread
	- Uma thread é finalizada, quando não é mais necessária
	- A própria thread verifica periodicamente se ela deve ser finalizada

### Pool de threads
- Limite de threads que podem ser criados
	- Definido pelo próprio SO
- As threads são definidas na inicialização do sistema
	- Para cada requisição, uma thread é "enviada" 
		- Quando o processo daquela thread é finalizado, ela volta para o pool 
	- Se uma requisição precisa de uma thread mas o pool está vazio, ele aguarda até que alguma thread seja liberada
	- 