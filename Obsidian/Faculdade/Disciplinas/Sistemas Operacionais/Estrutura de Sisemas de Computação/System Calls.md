### Modo usuário
- Apenas um subconjunto de instruções estão disponíveis
- Instruções que afetam o controle do Hardware e as que realizam operações de entrada e saída de dados
	**EX**: Não é possível mudar clock do processador

### Modo Núcleo
- Tem acesso a toda capacidade do hardware, incluindo toda instrução que estes podem executar
	**EX**: É possível alterar o clock do processador

### Interação entre Modo Usuário e Modo Núcleo
- Software (Modo Usuário) pede ao SO para executar uma instrução via uma chamada de sistema, que o "traduz" para o Kernel (Modo Núcleo)

### Linguagens de programação
- Fornecem uma interface para chamadas (System calls) ao SO
- Detalhes de implementação são escondidos do usuário
- O chamador não sabe como a chamada é implementada, apenas o nome parâmetro e retorno
	**EX**: Comandos de print, como em Python são os responsáveis por criar a System Call para realizar a instrução

### Processamento de Chamadas
- Número identificador de cada chamada 
	- Cada chamada possuí um identificador, que o SO os reconhece de acordo com uma tabela indexada de acordo com esses números

### Passagem de Parâmetros
Programas em execução podem passar parâmetros ao SO por meio de 
	1. Registradores
	2. Blocos (ou tabelas) de memória
	3. Pilha (itens adicionais pelo programa e removidos pelo SO)