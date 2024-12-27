import java.util.NoSuchElementException;

public class AppPilha {
	public static void main(String[] args) {
		Pilha<String> pilha = new Pilha<>();
		pilha.empilhar("ovo");
		pilha.empilhar("maçã");
		pilha.empilhar("laranja");
		pilha.empilhar("banana");
		pilha.empilhar("melancia");
		pilha.inverter();
		Pilha<String> pilha2 = new Pilha<>();
		pilha2.empilhar("uva");
		pilha2.empilhar("abacaxi");
		pilha2.empilhar("manga");
		pilha.concatenar(pilha2);
		System.out.println("Item no topo da pilha: " + pilha.consultarTopo());
		System.out.println("Desempilhando item: " + pilha.desempilhar());
		System.out.println("Item no topo da pilha após desempilhar: " + pilha.consultarTopo());
		System.out.println("Número de itens na pilha: " + pilha.obterNumeroItens());
	}

	public static boolean isPalindromo(String str){
		Pilha<Character> aux = new Pilha<>();		
		boolean flag = true;

		for(int i=0; i<str.length();i++){
			aux.empilhar(str.charAt(i));
		}

		int index = 0;
		while(!aux.vazia()){
			if(aux.desempilhar() != str.charAt(index)){
				flag = false;
			}
			index++;
		}

		return flag;
	}
}

class Pilha<E> {

	private Celula<E> topo;
	private Celula<E> fundo;
	private int tamanho = 0;

	public Pilha() {
		Celula<E> sentinela = new Celula<>();
		fundo = sentinela;
		topo = sentinela;
	}

	public boolean vazia() {
		return fundo == topo;
	}

	public void empilhar(E item) {
		topo = new Celula<>(item, topo);
		tamanho++;
	}

	public E desempilhar() {

		E desempilhado = consultarTopo();
		topo = topo.getProximo();
		tamanho--;
		return desempilhado;

	}

	public E consultarTopo() {

		if (vazia()) {
			throw new NoSuchElementException("Nao há nenhum item na pilha!");
		}

		return topo.getItem();
	}


	public void concatenar(Pilha<E> pilha){
		while(!pilha.vazia()){
			this.empilhar(pilha.desempilhar());
		}
	}

	public int obterNumeroItens(){
		int quant=0;
		Celula<E> aux = this.fundo;
		while(aux.getProximo() != null){
			aux = aux.getProximo();
			quant++;
		}
		
		return quant;
	}
 	// ou se adicionar uma variavel de tamanho e atualizar em todo desempilhar/empilhar
	public int obterNumeroItensInt(){
		return this.tamanho;
	}

	public void inverter(){
		Pilha<E> aux  = new Pilha<>();
		while(!this.vazia()){
			aux.empilhar(this.desempilhar());
		}
		while(!aux.vazia()){
			this.empilhar(aux.desempilhar());
		}
	}



}

final class Celula<T> {

	private final T item;
	private Celula<T> proximo;

	public Celula() {
		this.item = null;
		setProximo(null);
	}

	public Celula(T item) {
		this.item = item;
		setProximo(null);
	}

	public Celula(T item, Celula<T> proximo) {
        this.item = item;
        this.proximo = proximo;
    }
	
	public T getItem() {
		return item;
	}

	public Celula<T> getProximo() {
		return proximo;
	}

	public void setProximo(Celula<T> proximo) {
		this.proximo = proximo;
	}
}