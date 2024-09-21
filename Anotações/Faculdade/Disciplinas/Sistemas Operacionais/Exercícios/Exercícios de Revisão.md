
1) Quais são as principais funções dos Sistemas Operacionais?

**Resposta**: O SO é responsável por todo o controle do sistema, como gerenciamento de memória, controle de hardware, identificar dispositivos I/O, segurança do sistema,  gerenciar processos, e alocar recursos. 

2) Como funcionam os sistemas de tempo compartilhado? Quais são as vantagens em  
utilizá-los

**Resposta**: São sistemas onde diversos usuários conseguem utilizar seus recursos ao mesmo tempo, as principais vantages são a utilização de recursos, e o custo benefício, visto que o sistema será utilizado por completo, seus recursos não ficarão ociosos. 

3) O que é um System Call e qual sua importância para a segurança do sistema?

**Resposta**: Chamadas se sistema são responsáveis por assegurar a comunicação do lado se usuário com o Kernel, são elas que traduzem o que os softwares que o usuário esta utilizando para a linguagem de maquina, possibilitando a execução do que é pedido. As chamadas de sistema são utilizadas a fim de não dar ao usuário o poder de realizar tarefas a nível de núcleo, dando apenas ao SO esta capacidade. 

4) Quais são as principais atividades de um sistema operacional em relação à gerência de  
processos?

**Resposta:** Estados do processo: pronto, em execução, em espera, terminado e novo. Escalonamento de processos, a fim de organiza-los. Troca de contexto (o que acontece enquanto um processo sai da CPU e outro toma seu lugar). Gerenciamento de recurso (Gerencia os recursos que cada processo irá utilizar). Thread (Partes de um processo)

5) Explique o conceito de máquina virtual. Quais as vantagens em utilizar esse modelo?

**Resposta**: Máquinas virtuais são a simulação de uma máquina dentro da outra, são alocadas no espaço de usuário dentro do SO e possuem seus próprios recursos, que são disponibilizados pela máquina física. As principais vantagens são a segurança, e diminuição dos custos. 

6) Qual a diferença entre as estruturas de processos e threads?

**Resposta**: Threads são repartições de processos completos, que tem o principal objetivo de diminuir o tempo de execução e a utilização de recursos.

7) Qual o principal objetivo de um escalonamento de CPU? Considere os critérios de  
escalonamento e troca de contexto na sua resposta.

**Resposta:** Gerencia e organiza os processos, com base no algoritmo que esta sendo utilizado, levando em consideração: o tempo de execução, idade do processo, prioridade. O objetivo é sempre diminuir o tempo de resposta, de retorno e de espera enquanto a quantidade de processos aumenta. A troca de contexto influencia o tempo, ter um algoritmo que aplica a preempção, a troca de acontecerá.

8) Diferencie: escalonadores de longo, curto e médio prazo

**Resposta**: Um escalonador (responsável pela troca de contexto) de curto prazo é aquele que trabalho em tempos curtos, ou seja, é muito rápido. O escalonador de curto prazo é responsável pela troca de contexto entre a CPU e a memória, visto que os tempos de troca são muito curtos. Os escalonadores de longo prazo são responsáveis por "buscar" um processo que esta no disco e traze-lo para a memória, é de longo prazo porque a memória virtual (localizada no disco) tem uma velocidade menor. Por fim, os escalonadores de médio prazo são responsáveis por aqueles processos que estão localizados em um memória virtual, que por se tratar de uma memória mais rápida que o disco, porém mais lenta que a memória principal, ele é considerado de médio prazo. 

9) Qual a maior vantagem de implementar threads no espaço do usuário? Qual a maior  
desvantagem?

**Resposta**: As threads auxiliam na diminuição do tempo de execução de um processo, além de garantir a continuidade da execução, liberação e economia de recursos. É possível ocorrer um [[Problema de Deadlock]] entre as threads, caso exista uma dependência muito grande entre as threads. 

...

11) O que são filas múltiplas? Qual a diferença entre filas múltiplas e filas múltiplas com  
realimentação?

**Resposta**: Fila de prioridade que tem o objetivo de evitar o starvation (envelhecimento exagerado do processo), definindo prioridades de acordo com a idade de cada processo. As filas com realimentação tem a capacidade de trocar processos entre elas, aumentando ou diminuindo sua prioridade, para que um processo em uma fila de menor prioridade seja executado e não ocorra um starvation

12) O que é um deadlock e quais são as condições necessárias para sua ocorrência?

**Resposta**:  Um deadlock acontece quando um grupo de processos se encontram em uma situação onde nenhum destes processos consegue ser executado, porque existe uma dependência circular de um para outro. As 4 situações que devem acontecer simultaneamente para que um deadlock aconteça são: Não preempção, posse e espera, espera circular e exclusão mútua. 

13) Por que o sincronismo de processos é importante quando há comunicação entre  
processos?

**Resposta**: Um processo que produz uma informação deve ser executado anteriormente daquele que consome a informação, ou seja, o sincronismo de processos está ligada ao tempo correto para executar cada processo. 

14) O que caracteriza uma região crítica? E por que a exclusão mútua é importante nesse  
caso?

**Resposta**: Uma região critica é um espaço da memória onde dois ou mais processos compartilham memória, ou seja, se comunicam. Neste caso, é possível que aconteça uma Condição de Corrida, na região critica, o SO trava, executa uma chamada de sistema e executa o processo que deve ser executado primeiro. 
