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
	- Matando processos ate o deadlock ser resolvido
### Sincronismo de processos
- Existência de uma comunicação entre os processos
	- Situações
		 - Como o processo compartilha informação com o outro
		 - Por meio de memória compartilhada
		 - Garante que processos não entrem conflito
		 - Sequência adequada quando existirem dependências
- Garante que a execução dos processos e threads que compartilham espaços no endereço lógico
#### Condição de corrida
- Dois ou mais processos estão lendo ou escrevendo um dado compartilhado e cujo resultado final depende de quem executa e quando
	- Um processo depende de uma informação produzida por outro
- Toda condição de corrida possui uma ***Região crítica***
- O problema é a ***Condição de corrida***, a solução é a ***Região crítica***
- Alternativas para evitar uma ***Condição de corrida***
	- ***Exclusão mútua***
		- ***Desabilitando interrupções***: cada processo desabilita as interrupções depois de entrar na região crítica
			- Processos de usuário não tem esse poder
		- ...continuação no slide
#### Regiões críticas: 
- Parte do programa que há acesso á memória compartilhada
- Podem ser evitadas se nunca forem executadas ao mesmo tempo
- Regiões criticas são chamadas de sistema
	- Geradas para a região critica
- O SO entende que quando ele chega na região critica, cada seção critica executa por vez
	- Cada processo executa por vez
