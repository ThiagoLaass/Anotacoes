## Objetivos
- Permitir que mais processos sejam executados ao mesmo tempo
- Maximizando a utilização do processador, sem deixa-lo ocioso por muito tempo 
## Problemas
- Baixa utilização da memória
- Desperdício de muito tempo 
## Alocação de memória contígua
- Cada processo fica alocado em uma única parte contígua da memória
- Geralmente, a memória é divida em partições diferentes
	- Uma dedicada ao SO
	- Outra para os processos de usuário
#### Memória simples
- Possui apenas uma partição além do SO
- Um processo de usuário por vez
#### Memória múltipla
- Vários processos de usuário podem ser alocados na memória
- O particionamento dos processos pode ser fixo ou variável
### Particionamento fixo MFT
- Os espaços de endereço que serão alocados para os os processos são fixos
- Se um processo for alocado em um espaço da memória que é maior do que ele precisava, é criado uma brecha de memória (espaço restante do espaço de memória
- É possível ter uma fila de processos para cada endereço de memória
### Alocação dinâmica MVT
- As partições de memória não possuem um tamanho fixo
- O SO tem conhecimento de quais partes da memória estão ocupadas e quais não estão
- Fragmentação externa
	- Buracos de memória que não são grandes o suficiente para alocar novos processos
- Quando um processo precisa de menos espaço do que o existente na brecha, ela é dividida
## Fragmentação
- Espaços de memória fragmentados que estão entre processos
### Fragmentação interna
- Fragmentação que ocorre no MFT, onde existe espaços alocados por processos, mas estes processos não utilizam todo o espaço alocado para ele
### Fragmentação externa
- Fragmentação que ocorre no MVT, as brechas se encontram entre os processos
- É um tipo de fragmentação mais fácil de resolver
## Compactação
- O que é feito para corrigir a fragmentação
- Os processos são movidos
	- Todos para cima
	- Todos para baixo
	- Metade para cima e metade para baixo
- O objetivo é separar um espaço da memória onde existe apenas brechas, além de movimentar o menor número de processos possível