1) O arquivo possui varias caracteristicas que dizem respeito à sua estrutura, sendo elas, a extensão do arquivo, como .ogg, .docx. .dll, .exe... que determina que tipo de arquivo é. Além disso, arquivos executaveis (.exe) devem possuir comandos específicos que tornam ele um arquivo executavel. Em relação à sua alocação na memória, arquivos são alocados em blocos de memória bem definidos
2) Os principais atributos de um arquivo são: seu nome, identificador, tipo, tamanho, proteção e datas de criação e edição. 
3) Diretório é uma pasta, onde vários arquivos podem ser alocados dentro. 
4) Diretórios de dois níveis proporcionam uma melhor organização, visto que os arquivos são localizados em diretorios que indicam que aquele arquivo estará presente.
5) Diretórios do tipo árvore são aqueles onde é possível criar novos diretórios dentro de outros, tem o objetivo de organizar a separação dos arquivos, afunilando mais a posição de cada um.
6) Os diretórios em grafos impedem a existencia de ciclos.
7) A principal característica de proteção de diretorios e arquivos é o controle de acesso, onde apenas usuários especificos tem acesso para ler, editar e excluir aquele arquivo.
8) Tipos de alocação:
	1) Alocação Contigua:
		1) O arquivo é salvo como uma sequencia de blocos no disco, em sequencia linear.
		2) A leitura é feita atraves do bloco fisico inicial e de acordo com o tamanho do arquivo.
		3) Desvantagens: possibilidade de fragmentação externa, além do possivel aumento dos arquivos.
	2) Alocação Encadeada:
		1) A alocação do arquivo é uma lista encadeada, onde cada bloco aponta para o próximo
		2) Os arquivos podem crescer indefinidamente, além de remover o problema da fragmentação externa
		3) Para acessar um bloco, é necessário percorrer a lista
	3) Alocação indexada:
		1) O arquivo mantém um índice de blocos de o pertence.
			1) O índice é mantido em um bloco
		2) O diretório possuí o ponteiro que aponta para o indice onde o arquivo está alocado
		3) Permite acesso randomico
9) O vetor de bits é uma possível implementação para gerenciar os blocos vazios do disco, onde em um vetor, as posições que estiverem com o valor 1 estão livres, e os ocupados, são preenchidos com o valor 0.
10) Qualquer dispositivo externo em relação ao SO, como armazenamento, teclado, mouse, ou qualquer outro hardware. São dispositivos que se comunicam com o SO por um ponto de conexão, uma porta USB por exemplo. 
11) Uma interrupção é uma técnica utilizada por sistemas operacionais quando um novo dispositivo de I/O é conectado, por exemplo. Se trata da interrupção momentanea de processos de usário a fim de identificar o novo dispositivo conectado. 
12) Um dispositivo de caractere transfere bytes de informação, ja o dispositivo de blocos transfere blocos de bytes.
13) Em uma chamada de I/O bloqueante, a continuação do uso do dispositivo é apenas liberada quando essa chamada é respondida, as não bloqueantes, o usuário pode continuar utilizando o dispositivo sem que o SO tenha retornado uma resposta.
14) O subsistema de I/O do Kernel é responsável por todas as operações que diz respeito a dispositivos de I/O, como manipulação de erros, escalonamento...
15) O tratamento de pedidos segue etapas bem definidas, como: 
	1) determinar qual dispositivo de armazenamento o arquivo se econtra
	2) traduzir nome
	3) ler dados do disco
	4) disponibilizar os dados para quem solicitou o pedido
	5) retornar controle para o processo
16) O processo se inicia com uma chamada de sistema para o SO, após isso, é envianda uma requisição para o driver do dispositivo que iniciou a requisição, após essas etapas, o processamento da requisição é efetuado, gerando a interrupção quando aplicavel. 