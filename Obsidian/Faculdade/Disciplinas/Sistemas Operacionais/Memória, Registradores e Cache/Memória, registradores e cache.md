## Definições
- A CPU pode acessar diretamente a memória principal
	- A CPU apenas executa processos que estão na memória principal
- Registradores
- A checagem da memória por processos é demorado, pode tomar vários ciclos

## Alocação de processos na memória
- Uma fila é formada pelos programas que estão aguardando a transição para a memória
- Um processo de usuário pode ser alocado em qualquer espaço da memória
### Controle de acesso à memória
- Os primeiros endereços de memória são reservados para processos do sistema
- Multiprogramação
	- Vários processos podem residir na memória
	- Um processo de usuário não modifica outros processos
- Todos os processos tem seu próprio espaço de memória
	- Quando os processos precisam compartilhar espaços de memória, é criado um espaço para isso
- **Intervalo de endereçamentos**: intervalo de endereços legais que o processo pode acessar é definido por dois registradores:
	- Registrador base: delimita o endereço mínimo legal
	- Registrador limite: tamanho do intervalo
- ![[Pasted image 20240918213444.png]]

Esses endereços são vinculados durante a compilação, carga e execução • Compilação: endereços simbólicos são vinculados a endereços relocáveis • Carga: os endereços relocáveis são mapeados em endereços absolutos • Execução: faz-se a vinculação de endereços de processos que podem ser movidos de segmentos da memória durante a execução
### Endereços, lógico e físico
- O endereço lógico é criado pela CPU
	- Conjunto de endereços lógicos gerados por um programa
- Endereço físico é o enxergado pela memória
	- Conjunto de endereços físicos que correspondem aos lógicos
- **O mapeamento é realizado pelo MMU (memory management unit)**
	- Existem várias formas de mapeamento
	- MMU Simples
		- O valor do registrador de relocação é adicionado a cada endereço gerado por um processo do usuário
- 


