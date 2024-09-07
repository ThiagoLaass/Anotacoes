### Notações
- É considerado apenas a maior potencia de n
- O é o limite superior, ou seja, $g(n) ≤ f(n)$
- Ω é o limite inferior, ou seja, $g(n) ≥ f(n)$
- Θ é o limite justo, ou seja, $g(n) = f(n)$
### Notação O
- Limite assintótico superior
	- Se uma função é $O(n^2)$ ela também é limitada assintoticamente para funções de graus maiores
		- Função é obtida a partir de uma análise de algoritmo
- Se um algoritmo é $O(n^2)$, ele também é O de qualquer coisa maior que n
	- Limite assintoticamente justo
- Define, para o pior caso, o limite superior do tempo de execução de um algoritmo
- f(n) limita g(n) por cima
	- g(n) pode chegar ao valor de f(n) mas nunca ultrapassa-la
- ![[Pasted image 20240829212321.png]]
- Temos que achar as constantes **c** e **m** (m é o valor que f(n) passa a ser maior que g(n))
		- A constante c é a constante que multiplica $n^3$, para ser igual ou maior ao valor de $n^2 + n + log(n)$, que é g(n)$$
c.n^3
$$
- Para acharmos o valor de c, é a constante que multiplica n que torna ela maior ou igual a g(x), pode ser qualquer valor que torne a inequação abaixo verdadeira$$
					g(n) ≥ c.f(n) 
		$$
	- Quando o valor de f(n) é uma constante, a complexidade é $O(1)$ ou qualquer valor acima dele
#### Exemplo de exercício
Prove que $2^n+1 = O(2^n)$
$2^n+1 = 2^n.2^1$, neste caso teremos $2^n$ em ambos os lados, então podemos cancelar este termo. Então a constante é ≥ 2 
![[Pasted image 20240829213809.png]]
##### Dica
Quando o lado de g(n) da inequação está em função de n, é impossível achar o valor de c
### Notação Ω
- Se uma função é $Ω(n^2)$, ela será limitada por função de graus inferiores
- g(n) cresce tão lentamente quanto f(n)
	- f(n) limita g(n) por baixo
- Ou seja, em O queremos os valores máximos, em Ω queremos os valores mínimos
- ![[Pasted image 20240829220225.png]]
	- O sinal da inequação é invertido, uma vez que buscamos valores mínimos
#### Exemplo de exercício
Prove que $n^2 + 10 = Ω(n^2)$
A resposta é simplesmente uma constante que multiplicada a f(n) não ultrapasse o valor de g(n)
![[Pasted image 20240829220823.png]]
### Notação Θ
- $g(n)$ Cresce tão rapidamente quanto $f(n)$ 
	- $f(n)$ limita $g(n)$ tanto inferiormente quanto superiormente 
	- $f(n)$ é um limite assintótico restrito para g(n).
- Se $g(n)$ é $Θ(n)$ então ele também é $O(f(n))$ e $Ω(f(n))$, uma vez que ele o limita por cima e por baixo
- ![[Pasted image 20240903214823.png]]
	- Duas constantes, como se uma fosse para o $O(n)$ e outra para $Ω(n)$
#### Exemplo de exercício
![[Pasted image 20240903215610.png]]
A estrutura da resposta sempre será: Para C1 = 3, C2 = 4 e m = 4, temos que $g(n) = 3n^2 +2n^2+n = Θ(n^2)$
Após achar os valores de C1 e C2, basta testa-los na inequação para achar o valor de n em que torna a inequação verdadeira, ou seja, o valor de m