
1) **public static int alg1(int n){**
	**int res = 1;**
	**for(int i = n; i>1; i--){**
		**res*=i;**
	**}**
	**return res;**
**}**

a) Operação mais relevante: 
	multiplicação
	
b) Avaliar sequência e laços: 
	Faz o fatorial de n e o guarda na variável res
	
c) Há variação de casos? 
	Não
	
d) Qual o número de operações feitas quando n = {4, 8, 12}? 
	O número de multiplicações será sempre o valor de n - 1


2) **for (int i = 0;  i<n; i++){**
	**for(int j=0; j<n-3;j++){**
		**a*=2;**
	**}**
**}**

a) Operação mais relevante?
	Multiplicação
	
b) Avaliar a sequência de laços:
	
c) Há variações de casos?
	Não
	
d) Qual o número de operações quando n = {4, 8}?
	Para n = 4, serão efetuadas 8 multiplicações, para n = 8 serão efetuadas 40 multiplicações
	
e) Complexidade:
	n(n-3)


3) **for(int i=0; i<n; i++){**
	**for(int j=0; j<n; j++){**
		**for(int k=0; k<n; j++){**
			**a++;**
		**}**
	**}**
**}**

a) Qual o número de somas feitas quando n=3?
	Serão efetuadas 27 somas
	
b) Defina a complexidade do algoritmo:
	n^3

4) **for(int i = 1; i<=n; i*=2){**
	**a= a * 2
}** 

a) Qual o número de multiplicações feitas quando n {2, 4, 8}?
	2: 2
	4: 3
	8: 4
b) Defina a complexidade deste algoritmo
	Complexidade de log(n) + 1