


public class AppLista<E> {
	public static void main(String[] args) {
		Lista<String> lista = new Lista<>();
		lista.inserirFinal("item 1");
		lista.inserirFinal("item 2");
		lista.inserir("item 3", 1);
		// System.out.println(lista.obterNumeroItens());
		// System.out.println(lista.localizar(0));
		// System.out.println(lista.localizar(1));
		// System.out.println(lista.localizar(2));
		// lista.trocar("item 2", "item 2 trocado");
		// System.out.println(lista.localizar(2));
		// System.out.println("Item na posicao 0: " + lista.localizar(0));
		// System.out.println("Removendo item 1: " + lista.removeritemProcurado("item 1"));
		// System.out.println("Número de itens na lista: " + lista.obterNumeroItens());
		// System.out.println("Removendo item do início: " + lista.removerInicio());
		// System.out.println("Número de itens na lista após remoção: " + lista.obterNumeroItens());

        Lista<String> nova = lista.copia(lista);
        Celula<String> aux = nova.getPrimeiro();
        while(aux.getProximo()!=null){
            System.out.println(aux.getProximo().getItem());
            aux = aux.getProximo();
        }

        Celula<String> aux2 = lista.getPrimeiro();
        while(aux2.getProximo()!=null){
            System.out.println(aux2.getProximo().getItem());
            aux2 = aux2.getProximo();
        }
	}
}

class Lista<E> {

	private Celula<E> primeiro;
	private Celula<E> ultimo;
	private int tamanho;

	public Lista() {

		Celula<E> sentinela = new Celula<>();

		this.primeiro = this.ultimo = sentinela;
		this.tamanho = 0;
	}

	public boolean vazia() {

		return (this.primeiro == this.ultimo);
	}

    public Celula<E> getPrimeiro() {
        return this.primeiro;
    }

	public void inserir(E novo, int posicao) {

		Celula<E> anterior, novaCelula, proximaCelula;

		if ((posicao < 0) || (posicao > this.tamanho))
			throw new IndexOutOfBoundsException("Não foi possível inserir o item na lista: "
					+ "a posição informada é inválida!");

		anterior = this.primeiro;
		for (int i = 0; i < posicao; i++)
			anterior = anterior.getProximo();

		novaCelula = new Celula<>(novo);

		proximaCelula = anterior.getProximo();

		anterior.setProximo(novaCelula);
		novaCelula.setProximo(proximaCelula);

		if (posicao == this.tamanho) // a inserção ocorreu na última posição da lista
			this.ultimo = novaCelula;

		this.tamanho++;
	}

	public int obterNumeroItens() {
		Celula<E> aux = this.primeiro.getProximo();
		int count = 0;
		while (aux != null) {
			aux = aux.getProximo();
			count++;
		}
		return count;
	}

	public void inserirFinal(E novo) {
		Celula<E> novaCelula = new Celula<>(novo);
		this.ultimo.setProximo(novaCelula);
		this.ultimo = novaCelula;
		this.tamanho++;
	}

	public E removerInicio() {
		if (this.vazia()) {
			throw new IllegalStateException("Não foi possível remover o item da lista: "
					+ "a lista está vazia!");
		}
		Celula<E> celulaRemovida = this.primeiro.getProximo();
		this.primeiro = primeiro.getProximo();
		return celulaRemovida.getItem();
	}

	public E remover(int posicao) {

		Celula<E> anterior, celulaRemovida, proximaCelula;

		if (vazia())
			throw new IllegalStateException("Não foi possível remover o item da lista: "
					+ "a lista está vazia!");

		if ((posicao < 0) || (posicao >= this.tamanho))
			throw new IndexOutOfBoundsException("Não foi possível remover o item da lista: "
					+ "a posição informada é inválida!");

		anterior = this.primeiro;
		for (int i = 0; i < posicao; i++)
			anterior = anterior.getProximo();

		celulaRemovida = anterior.getProximo();

		proximaCelula = celulaRemovida.getProximo();

		anterior.setProximo(proximaCelula);
		celulaRemovida.setProximo(null);

		if (celulaRemovida == this.ultimo)
			this.ultimo = anterior;

		this.tamanho--;

		return (celulaRemovida.getItem());
	}

	public E removeritemProcurado(E item) {
		if (vazia())
			throw new IllegalStateException("Não foi possível remover o item da lista: "
					+ "a lista está vazia!");

		Celula<E> anterior, celulaRemovida, proximaCelula;

		anterior = this.primeiro;
		while (anterior != null && !anterior.getProximo().getItem().equals(item)) {
			anterior = anterior.getProximo();
		}

		if (anterior == null) {
			throw new IllegalStateException("item não encontrado");
		}

		celulaRemovida = anterior.getProximo();
		proximaCelula = celulaRemovida.getProximo();
		anterior.setProximo(proximaCelula);
		tamanho--;
		return celulaRemovida.getItem();
	}

	public E localizar(int posicao) {
		if (posicao < 0 || posicao >= this.tamanho) {
			throw new IllegalArgumentException("Posição invalida");
		}

		if (this.vazia()) {
			throw new IllegalStateException("A lista está vazia");
		}

		Celula<E> anterior, celulaEscolhida;

		anterior = this.primeiro;
		for (int i = 0; i < posicao; i++) {
			anterior = anterior.getProximo();
		}

		celulaEscolhida = anterior.getProximo();
		return celulaEscolhida.getItem();
	}

	/* 
	* 1. Encontrar a célula que contém o itemX. 2. Criar uma nova célula com o itemY.
	* A celula aux é a celula anterior a celula onde o itemx (que será trocado)
	* após encontrala, fazemos: celulaSubstituta.setProximo(aux.getProximo().getProximo()), assim, a celulaSubstituta
	* se econtra apos a celula anterior e antes a celula posterior
	* Depois, usamos aux.setProximo(), para referenciar a celula trocada
	*/
	public void trocar(E itemX, E itemY) {
		Celula<E> celulaSubstituta = new Celula<>(itemY);
		Celula<E> aux = this.primeiro;
		if (vazia())
			throw new IllegalStateException("Não foi possível trocar os itens da lista: "
					+ "a lista está vazia!");

		while(aux.getProximo()!=null) {
			if(aux.getProximo().getItem().equals(itemX)) {
				celulaSubstituta.setProximo(aux.getProximo().getProximo());
				aux.setProximo(celulaSubstituta); 
				return;
			}
			aux = aux.getProximo();
		}
	}

    public Lista<E> copia(Lista<E> l1) {
        Lista<E> nova = new Lista<>();
        Celula<E> aux = l1.primeiro;
        while(aux.getProximo()!=null){
            nova.inserirFinal(aux.getProximo().getItem());
            aux = aux.getProximo();
        }
        return nova;
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