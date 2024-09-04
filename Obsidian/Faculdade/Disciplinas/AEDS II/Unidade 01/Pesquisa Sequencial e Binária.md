
## Sequencial
- A busca acontece sequencialmente dentro do vetor
	- Passando por cada posição ate achar o elemento procurado
#### Exemplo de código

## Binária
- Utiliza o meio do vetor como parâmetro para fazer as comparações 
	- Separa o vetor em dois, descartando a primeira metade se o elemento procurado não for encontrado
- Para a pesquisa binária funcionar, o vetor precisa estar ordenado
	- Não é recomendado utilizar a busca binária quando existem muitas inserções e remoções no vetor
#### Exemplo de código (recursivo)
```
public boolean pesquisaBinariaRecursiva(int[] vet, int x) {
      return pesquisaBinariaRecursiva(vet, x, 0, (vet.length - 1));
}

public boolean pesquisaBinariaRecursiva(int[] vet, int x, int esq, int dir) {

      boolean resp;
      int meio = (esq + dir) / 2;

      if (esq > dir) {
         resp = false;
      } else if (x == vet[meio]) {
         resp = true;
      } else if (x > vet[meio]) {
         resp = pesquisaBinariaRecursiva(vet, x, meio + 1, dir);
      } else {
         resp = pesquisaBinariaRecursiva(vet, x, esq, meio - 1);
      }
      return resp;
}
```
#### Iterativo
```
boolean pesquisaBinaria(int[] vet, int x) {

      boolean resp = false;
      int dir = (vet.length - 1), esq = 0, meio;

      while (esq <= dir) {

         meio = (esq + dir) / 2;

         if (x == vet[meio]) {
            resp = true;
            esq = dir + 1;
         } else if (x > vet[meio]) {
            esq = meio + 1;
         } else {
            dir = meio - 1;
         }
      }
      
      return resp;
}
```
