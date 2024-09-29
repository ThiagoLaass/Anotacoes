## Introdução
### Terminologias
- Sistemas especialistas
- Visão computacional
- Processamento de linguagem natural
- Machine learning
	- Métodos matematicos para treinar algoritmos
- Algoritmos genéticos
- Sistemas multiagente
- Mineração de dados
	- Extrair conhecimento de uma base de dados utilizando métodos de aprendizado de máquina
- Resolução de problemas de busca
- Lógica Fuzzy
- Raciocínio baseado em casos
- Robótica
- Redes neurais
	- Um tipo de aprendizado de máquina
	- Deep learning
		- Muito mais dados e processadores mais potentes
		- Rede Neural Com muitas camadas
- Big data
	- Imenso volume de dados
- Ciência de dados
	- Exploração e análise de dados
	- Machine learning
	- Estatística

## Métodos preditivos
### Classificação
- Definir classe para cada tipo de registro
	- Ou cada tipo de entrada, ou seja, classifica os dados
- Exemplo:
	- Separação de emails, como spam, importantes, etc
- Atributos previsores
	- Aqueles dados que definem qual classe determinado dado será caracterizado
	- Os dados novos são submetidos à uma avaliação de acordo com os dados já existentes, a fim de treinar a inteligência artificial
- Cada registro de dado é pertencente à uma classe e possui dados previsores, que o fizeram ser alocado para aquela classe
	- O objetivo é atribuir um relacionamento entre os atributos previsores e o atributo meta
		- O valor do atributo meta é conhecido
#### Método indutivo
##### Fase 1
- Sendo cex o *Conjunto de exemplos*, *atp* os *Atributos previsores* e am o *Atributo meta*, temos a equação
	- $cex-atp+am$
- Que leva ao sistema de aprendizado (Algoritmos)
- Que gera o Classificador
##### Fase 2
- Caso a ser classificado
	- Atributo meta não conhecido
- Classificador (Gerado na fase 1)
- Decisão
	- Onde é definida a classe onde o dado estará presente
### Regressão
- Faz a previsão de valores numéricos
	- Baseado em valores anteriores, se define um novo valor
- Exemplo:
	- Prever quanto a empresa terá de lucro, baseado em dados numéricos
## Métodos descritivos
### Associação
- Encontrar uma associação entre dados
	- Assimilar um valor com outro
- Exemplo:
	- Assimilar a venda de produtos com épocas especificas do ano
### Agrupamento
- Analisar os dados e encontrar grupos específicos
- Exemplo
	- Segmentar o mercado, encontrando grupos de pessoas que comprariam ou não um produto
### Detecção de desvios (Outliers)
- Detecção de dados que desviam do padrão
- Exemplo
	- Intrusão em redes
### Descoberta de padrões sequenciais
- Algo que acontece mais de uma vez, tende a acontecer de novo
- Exemplo
	- Clientes que compram em uma loja, e esta envia email de promoções para os usuários