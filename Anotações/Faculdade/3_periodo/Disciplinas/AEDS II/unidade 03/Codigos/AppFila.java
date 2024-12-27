

import java.util.NoSuchElementException;
import java.util.Stack;

public class AppFila {
	public static void main(String[] args) {

		Fila<String> fila = new Fila<>();
		fila.enfileirar("item 1");
		fila.enfileirar("item 2");
		fila.enfileirar("item 3");
		fila.enfileirar("item 4");
		fila.concatenar(fila.copiar());
		fila.imprimir();
		System.out.println("Itens a frente de  item 2: " + fila.obterItensAFrente("item 2"));
		System.out.println("Numero de itens na fila: " + fila.obterNumeroItens());
		Fila<String> novaFila;
		System.out.println("fila divida: ");
		novaFila = fila.dividir();
		novaFila.imprimir();
		System.out.println("Fila original: ");
		fila.imprimir();
		System.out.println("Fila invertida: ");
		Fila<String> filaInvertida = fila.inverter();
		filaInvertida.imprimir();
	}

}

class Fila<E> {

	private Celula<E> frente;
	private Celula<E> tras;

	Fila() {

		Celula<E> sentinela = new Celula<>();
		frente = tras = sentinela;
	}

	public boolean vazia() {

		return (frente == tras);
	}

	public void enfileirar(E item) {

		Celula<E> novaCelula = new Celula<>(item);

		tras.setProximo(novaCelula);
		tras = tras.getProximo();
	}

	public E desenfileirar() {

		E item;
		Celula<E> primeiro;

		item = consultarPrimeiro();

		primeiro = frente.getProximo();
		frente.setProximo(primeiro.getProximo());

		primeiro.setProximo(null);

		// Caso o item desenfileirado seja também o último da fila.
		if (primeiro == tras)
			tras = frente;

		return item;
	}

	public E consultarPrimeiro() {

		if (vazia()) {
			throw new NoSuchElementException("Nao há nenhum item na fila!");
		}

		return frente.getProximo().getItem();

	}

	public void imprimir() {

		Celula<E> aux;

		if (vazia())
			System.out.println("A fila está vazia!");
		else {
			aux = this.frente.getProximo();
			while (aux != null) {
				System.out.println(aux.getItem());
				aux = aux.getProximo();
			}
		}
	}

	public void concatenar(Fila<E> fila) {
		while (!fila.vazia()) {
			this.enfileirar(fila.desenfileirar());
		}
	}

	public int obterNumeroItens() {
		int count = 0;
		Celula<E> aux = this.frente;
		while (aux.getProximo() != null) {
			aux = aux.getProximo();
			count++;
		}
		return count;
	}

	public int obterItensAFrente(E item) {
		Celula<E> aux = this.frente;
		Celula<E> celItem = this.frente;
		int count = 0;

		while (aux.getProximo() != null) {
			if (aux.getProximo().getItem().equals(item)) {
				celItem = aux.getProximo();
				break;
			}
			aux = aux.getProximo();
		}

		while (celItem.getProximo() != null) {
			celItem = celItem.getProximo();
			count++;
		}

		if (celItem == this.frente) {
			throw new IllegalStateException("O item informado não está na fila");
		}

		return count;
	}

	public Fila<E> copiar() {
		if (this.vazia()) {
			throw new IllegalStateException("A fila esta vazia");
		}
		Fila<E> filaCopia = new Fila<>();
		Celula<E> aux = this.frente;
		while (aux.getProximo() != null) {
			aux = aux.getProximo();
			filaCopia.enfileirar(aux.getItem());
		}
		return filaCopia;
	}

	public Fila<E> dividir() {

		int posicao = 0;
		Fila<E> novaFila = new Fila<>();

		Celula<E> aux = this.frente;

		while (aux.getProximo() != null) {
			aux = aux.getProximo();
			if (posicao % 2 == 0) {
				novaFila.enfileirar(aux.getItem());
			}
			posicao++;
		}
		return novaFila;
	}

	public Fila<E> inverter() {
		Stack<E> pilha = new Stack<>();

		while (!this.vazia()) {
			pilha.push(this.desenfileirar());
		}

		while (!pilha.isEmpty()) {
			this.enfileirar(pilha.pop());
		}

		return this;
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
