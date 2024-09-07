### Definição
- Programa em execução
- Unidade que representa o que está sendo utilizado naquela hora
### Do que o processo é formado
- Contador de programa
- Pilha
- Seção de dados
### Estados de Processo
#### 1. Novo
- Estado acabou de ser criado
#### 2. Em execução
- Instruções estão sendo executadas, o processo esta transitando entre processador e memória
#### 3. Pronto (Para ser executado)
- Sai do processador, volta pra memória
	- Ex: O processo tem um tempo de execução maior do que o tempo de processador
#### 4. Em espera
- Depende de alguma coisa que é necessário para a continuação do processo
#### 5. Encerrado
- A execução do processo foi concluída
- Não significa que será sobrescrito com outro processo imediatamente
	- O SO aloca os processos nos espaços que estão vazios
	- O processo não fica na memória pra sempre, o SO verifica quando o processo foi encerrado -> quanto mais antigo, menor probabilidade dele ser necessário novamente
### Bloco de controle do Processo
#### Informação de cada processo
1. Estado do processo
2. Contador de programa
3. Registradores da CPU
4. Informações de escalonamento de CPU
5. Informações de gerência de memória
6. Informações de contabilidade
7. Informações de I/O
	1. Precisa do retorno de operação de entrada e saída -> concluída ou não
### Criação dos Processos
- Processos podem criar outros processos
	- Gera uma árvore de processos (que podem depender daquele que os criou)
- Compartilhamento de Recursos
	- Ambos os processos que foram gerados e os que o gerou compartilham recursos
