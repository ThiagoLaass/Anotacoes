package Verde;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;

public class AplicacaoHash {
    public static void main(String[] args) {
        DAO dao = new DAO();
        Scanner scanner = new Scanner(System.in);
        TabelaHash<String, ABB<Evento>> tabelaHash = new TabelaHash<>(100);
        dao.leitura(tabelaHash);
        try {
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("FIM"))
                    break;

                ABB<Evento> arvoreEventos = tabelaHash.pesquisar(input);
                if (arvoreEventos != null) {
                    System.out.println("Eventos do dia " + input);
                    arvoreEventos.caminhamentoEmOrdemComAcao(evento -> {
                        System.out.println(evento);
                    });
                    System.out.println();
                }
            }
        } finally {
            scanner.close();
        }
    }

    private static Medalhista findMedalhistaInArvoreByNome(String nome, ABB<Medalhista> arvore) {
        No<Medalhista> no = arvore.pesquisar(new Medalhista(nome, null, null, null));
        return (no != null) ? no.getItem() : null;
    }
}

// #region Tipo Medalha
enum TipoMedalha {
    OURO,
    PRATA,
    BRONZE
}
// #endregion

// #region Medalha
class Medalha {
    private final TipoMedalha tipo;
    private final LocalDate data;
    private final String disciplina;
    private final String evento;

    public Medalha(TipoMedalha tipo, LocalDate data, String disciplina, String evento) {
        this.tipo = tipo;
        this.data = data;
        this.disciplina = disciplina;
        this.evento = evento;
    }

    public TipoMedalha getTipo() {
        return tipo;
    }

    public String getEvento() {
        return this.evento;
    }

    public String getDisciplina() {
        return this.disciplina;
    }

    @Override
    public String toString() {
        String dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(data);
        return tipo + " - " + disciplina + " - " + evento + " - " + dataFormatada;
    }
}
// #endregion

// #region DAO
class DAO {
    private static final DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void leitura(TabelaHash<String, ABB<Evento>> tabelaHash) {
        try (BufferedReader br = new BufferedReader(new FileReader("tmp/medallists.csv"))) {
            String linha;
            List<Integer> lista;
            boolean isFirstLine = true;

            while ((linha = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] campos = linha.split(",");
                if (campos.length < 8)
                    continue;

                try {
                    String nome = campos[0].trim();
                    TipoMedalha tipoMedalha = TipoMedalha.valueOf(campos[1].trim().toUpperCase());

                    LocalDate dataMedalha = LocalDate.parse(campos[2].trim(), originalFormatter);
                    String dataMedalhaFormatada = dataMedalha.format(targetFormatter);

                    String genero = campos[3].trim();

                    LocalDate dataNascimento = LocalDate.parse(campos[4].trim(), originalFormatter);
                    String dataNascimentoFormatada = dataNascimento.format(targetFormatter);

                    String pais = campos[5].trim();
                    String disciplina = campos[6].trim();
                    String eventoNome = campos[7].trim();

                    Medalhista medalhista = new Medalhista(nome, genero, dataNascimento, pais);
                    medalhista.incluirMedalha(new Medalha(tipoMedalha, dataMedalha, disciplina, eventoNome));

                    ABB<Evento> arvoreEventos = tabelaHash.pesquisar(dataMedalhaFormatada);
                    if (arvoreEventos == null) {
                        arvoreEventos = new ABB<>();
                        tabelaHash.inserir(dataMedalhaFormatada, arvoreEventos);
                    }

                    Evento evento = new Evento(eventoNome, disciplina);
                    No<Evento> noEvento = arvoreEventos.pesquisar(evento);
                    if (noEvento == null) {
                        arvoreEventos.adicionar(evento);
                    } else {
                        evento = noEvento.getItem();
                    }
                    evento.incluirMedalhista(medalhista);

                } catch (DateTimeParseException e) {
                    System.out.println("Erro ao processar data na linha: " + linha + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }
}
// #endregion

// #region Medalhista
class Medalhista implements Comparable<Medalhista> {
    private final String name;
    private final String gender;
    private final LocalDate birthDate;
    private final String country;
    private final List<Medalha> medals = new ArrayList<>();

    public Medalhista(String nome, String genero, LocalDate nascimento, String pais) {
        this.name = nome;
        this.gender = genero;
        this.birthDate = nascimento;
        this.country = pais;
    }

    public void incluirMedalha(Medalha medalha) {
        this.medals.add(medalha);
    }

    public int getQuantidadeMedalhas(TipoMedalha tipo) {
        return (int) medals.stream().filter(m -> m.getTipo() == tipo).count();
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public List<Medalha> getMedals() {
        return medals;
    }

    public int getMedalhaCount(TipoMedalha tipo) {
        return (int) medals.stream().filter(m -> m.getTipo() == tipo).count();
    }

    public int getAllMedalhaCount() {
        return medals.size();
    }

    @Override
    public String toString() {
        String dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(birthDate);
        return name + ", " + gender.toUpperCase() + ". Nascimento: " + dataFormatada + ". Pais: " + country + ".";
    }

    @Override
    public int compareTo(Medalhista outroMedalhista) {
        return this.name.compareTo(outroMedalhista.getName());
    }
}
// #endregion

// #region Árvore ABB
final class ABB<E extends Comparable<E>> {

    private No<E> raiz; // referência à raiz da árvore.

    /**
     * Construtor da classe.
     * Esse construtor cria uma nova árvore binária de busca vazia. Para isso, esse
     * método atribui null à raiz da árvore.
     */
    public ABB() {
        raiz = null;
    }

    /**
     * Método booleano que indica se a árvore está vazia ou não.
     * 
     * @return
     *         verdadeiro: se a raiz da árvore for null, o que significa que a
     *         árvore está vazia.
     *         falso: se a raiz da árvore não for null, o que significa que a árvore
     *         não está vazia.
     */
    public Boolean vazia() {
        return (this.raiz == null);
    }

    private No<E> pesquisar(No<E> raizArvore, E procurado) {

        int comparacao;

        if (raizArvore == null)
        /// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e então o item não foi encontrado.
            throw new NoSuchElementException("O item não foi localizado na árvore!");

        comparacao = procurado.compareTo(raizArvore.getItem());

        if (comparacao == 0)
        /// O item procurado foi encontrado.
            return raizArvore;
        else if (comparacao < 0)
        /// Se o item procurado for menor do que o item armazenado na raiz da árvore:
            /// pesquise esse item na sub-árvore esquerda.
            return pesquisar(raizArvore.getEsquerda(), procurado);
        else
        /// Se o item procurado for maior do que o item armazenado na raiz da árvore:
            /// pesquise esse item na sub-árvore direita.
            return pesquisar(raizArvore.getDireita(), procurado);
    }

    public No<E> pesquisar(E procurado) {
        return pesquisar(this.raiz, procurado);
    }

    /**
     * Método recursivo responsável por adicionar um item à árvore.
     * 
     * @param raizArvore: raiz da árvore ou sub-árvore em que o item será
     *                    adicionado.
     * @param item:       item que deverá ser adicionado à árvore.
     * @return a raiz atualizada da árvore ou sub-árvore em que o item foi
     *         adicionado.
     */
    protected No<E> adicionar(No<E> raizArvore, E item) {

        int comparacao;

        /// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e então um novo item é inserido.
        if (raizArvore == null)
            raizArvore = new No<>(item);
        else {
            comparacao = item.compareTo(raizArvore.getItem());

            if (comparacao < 0)
            /// Se o item que deverá ser inserido na árvore for menor do que o item armazenado na raiz da árvore:
        		/// adicione esse novo item à sub-árvore esquerda; e atualize a referência para a sub-árvore esquerda modificada. 
                raizArvore.setEsquerda(adicionar(raizArvore.getEsquerda(), item));
            else if (comparacao > 0)
            /// Se o item que deverá ser inserido na árvore for maior do que o item armazenado na raiz da árvore:
        		/// adicione esse novo item à sub-árvore direita; e atualize a referência para a sub-árvore direita modificada.
                raizArvore.setDireita(adicionar(raizArvore.getDireita(), item));
            else
            /// O item armazenado na raiz da árvore é igual ao novo item que deveria ser inserido na árvore.
                throw new RuntimeException("O item já foi inserido anteriormente na árvore.");
        }

        /// Retorna a raiz atualizada da árvore ou sub-árvore em que o item foi adicionado.
        return raizArvore;
    }

    /**
     * Método que encapsula a adição recursiva de itens à árvore.
     * 
     * @param item: item que deverá ser inserido na árvore.
     */
    public void adicionar(E item) {
        /// Chama o método recursivo "adicionar", que será responsável por adicionar, o item passado como parâmetro, à árvore.
        /// O método "adicionar" recursivo receberá, como primeiro parâmetro, a raiz atual da árvore; e, como segundo parâmetro, 
    	/// o item que deverá ser adicionado à árvore.
        /// Por fim, a raiz atual da árvore é atualizada, com a raiz retornada pelo método "adicionar" recursivo.
        this.raiz = adicionar(this.raiz, item);
    }

    public void caminhamentoEmOrdem() {

        if (vazia())
            throw new IllegalStateException("A árvore está vazia!");

        caminhamentoEmOrdem(this.raiz);
    }

    private void caminhamentoEmOrdem(No<E> raizArvore) {
        if (raizArvore != null) {
            caminhamentoEmOrdem(raizArvore.getEsquerda());
            System.out.println(raizArvore.getItem());
            caminhamentoEmOrdem(raizArvore.getDireita());
        }
    }

    /**
     * Método recursivo responsável por localizar na árvore ou sub-árvore o
     * antecessor do nó que deverá ser retirado.
     * O antecessor do nó que deverá ser retirado da árvore corresponde
     * ao nó que armazena o item que é o maior,
     * dentre os itens menores do que o item que deverá ser retirado.
     * Depois de ser localizado na árvore ou sub-árvore,
     * o antecessor do nó que deverá ser retirado da árvore o substitui.
     * Adicionalmente, a árvore ou sub-árvore é atualizada com a remoção do
     * antecessor.
     * 
     * @param itemRetirar: referência ao nó que armazena o item que deverá ser
     *                     retirado da árvore.
     * @param raizArvore:  raiz da árvore ou sub-árvore em que o antecessor do nó
     *                     que deverá ser retirado deverá ser localizado.
     * @return a raiz atualizada da árvore ou sub-árvore após a remoção do
     *         antecessor do nó que foi retirado da árvore.
     */
    protected No<E> removerNoAntecessor(No<E> itemRetirar, No<E> raizArvore) {
        /// Se o antecessor do nó que deverá ser retirado da árvore ainda não foi encontrado...
        if (raizArvore.getDireita() != null)
        /// Pesquise o antecessor na sub-árvore direita.
            raizArvore.setDireita(removerNoAntecessor(itemRetirar, raizArvore.getDireita()));
        else {
            /// O antecessor do nó que deverá ser retirado da árvore foi encontrado e deverá substitui-lo.
            itemRetirar.setItem(raizArvore.getItem());
            /// A raiz da árvore ou sub-árvore é atualizada com os descendentes à esquerda do antecessor.
            /// Ou seja, retira-se o antecessor da árvore.
            raizArvore = raizArvore.getEsquerda();
        }
        return raizArvore;
    }

    /**
     * Método recursivo responsável por localizar um item na árvore e retirá-lo da
     * árvore.
     * 
     * @param raizArvore:  raiz da árvore ou sub-árvore da qual o item será
     *                     retirado.
     * @param itemRemover: item que deverá ser localizado e removido da árvore.
     * @return a raiz atualizada da árvore ou sub-árvore da qual o item foi
     *         retirado.
     */
    protected No<E> remover(No<E> raizArvore, E itemRemover) {

        int comparacao;

        /// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e o item, que deveria ser retirado dessa árvore, não foi encontrado.
        /// Nesse caso, deve-se lançar uma exceção.
        if (raizArvore == null)
            throw new NoSuchElementException("O item a ser removido não foi localizado na árvore!");

        comparacao = itemRemover.compareTo(raizArvore.getItem());

        if (comparacao == 0) {
            /// O item armazenado na raiz da árvore corresponde ao item que deve ser retirado dessa árvore.
            /// Ou seja, o item que deve ser retirado da árvore foi encontrado.
            if (raizArvore.getDireita() == null)
            /// O nó da árvore que será retirado não possui descendentes à direita.
                /// Nesse caso, os descendentes à esquerda do nó que está sendo retirado da árvore passarão a ser descendentes do nó-pai do nó que está sendo retirado.
                raizArvore = raizArvore.getEsquerda();
            else if (raizArvore.getEsquerda() == null)
            /// O nó da árvore que será retirado não possui descendentes à esquerda.
                /// Nesse caso, os descendentes à direita do nó que está sendo retirado da árvore passarão a ser descendentes do nó-pai do nó que está sendo retirado.
                raizArvore = raizArvore.getDireita();
            else
            /// O nó que está sendo retirado da árvore possui descendentes à esquerda e à direita.
                /// Nesse caso, o antecessor do nó que está sendo retirado é localizado na sub-árvore esquerda desse nó. 
                /// O antecessor do nó que está sendo retirado da árvore corresponde
                /// ao nó que armazena o item que é o maior, 
                /// dentre os itens menores do que o item do nó que está sendo retirado.
                /// Depois de ser localizado na sub-árvore esquerda do nó que está sendo retirado, 
                /// o antecessor desse nó o substitui.
                /// A sub-árvore esquerda do nó que foi retirado é atualizada com a remoção do antecessor.
                raizArvore.setEsquerda(removerNoAntecessor(raizArvore, raizArvore.getEsquerda()));
        } else if (comparacao < 0)
        /// Se o item que deverá ser localizado e retirado da árvore for menor do que o item armazenado na raiz da árvore:
        	/// pesquise e retire esse item da sub-árvore esquerda.
            raizArvore.setEsquerda(remover(raizArvore.getEsquerda(), itemRemover));
        else
        /// Se o item que deverá ser localizado e retirado da árvore for maior do que o item armazenado na raiz da árvore:
        	/// pesquise e retire esse item da sub-árvore direita.
            raizArvore.setDireita(remover(raizArvore.getDireita(), itemRemover));

        /// Retorna a raiz atualizada da árvore ou sub-árvore da qual o item foi retirado.
        return raizArvore;
    }

    /**
     * Método que encapsula a remoção recursiva de um item da árvore.
     * 
     * @param itemRemover: item que deverá ser localizado e removido da árvore.
     */
    public void remover(E itemRemover) {
        /// Chama o método recursivo "remover", que será responsável por pesquisar o item passado como parâmetro na árvore e retirá-lo da árvore.
        /// O método "remover" recursivo receberá, como primeiro parâmetro, a raiz atual da árvore; 
    	/// e, como segundo parâmetro, o item que deverá ser localizado e retirado dessa árvore.
    	/// Por fim, a raiz atual da árvore é atualizada, com a raiz retornada pelo método "remover" recursivo.
        this.raiz = remover(this.raiz, itemRemover);
    }

    public void caminhamentoEmOrdemComAcao(Consumer<E> acao) {
        if (vazia()) {
            throw new IllegalStateException("A árvore está vazia!");
        }
        caminhamentoEmOrdemComAcao(this.raiz, acao);
    }

    private void caminhamentoEmOrdemComAcao(No<E> raizArvore, Consumer<E> acao) {
        if (raizArvore != null) {
            caminhamentoEmOrdemComAcao(raizArvore.getEsquerda(), acao);
            acao.accept(raizArvore.getItem());
            caminhamentoEmOrdemComAcao(raizArvore.getDireita(), acao);
        }
    }
}
// #endregion

// #region No
final class No<T extends Comparable<T>> {

    private T item; // contém os dados do item armazenado no nodo da árvore.
    private No<T> direita; // referência ao nodo armazenado, na árvore, à direita do nó em questão.
    private No<T> esquerda; // referência ao nodo armazenado, na árvore, à esquerda do nó em questão.
    private int altura;

    public No() {
        this.setItem(null);
        this.setDireita(null);
        this.setEsquerda(null);
        this.altura = 0;
    }

    public No(T item) {
        this.setItem(item);
        this.setDireita(null);
        this.setEsquerda(null);
        this.altura = 0;
    }

    public No(T item, No<T> esquerda, No<T> direita) {
        this.setItem(item);
        this.setDireita(direita);
        this.setEsquerda(esquerda);
        this.altura = 0;
    }

    public T getItem() {
        return this.item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public No<T> getDireita() {
        return this.direita;
    }

    public void setDireita(No<T> direita) {
        this.direita = direita;
    }

    public No<T> getEsquerda() {
        return this.esquerda;
    }

    public void setEsquerda(No<T> esquerda) {
        this.esquerda = esquerda;
    }

    private int getAltura(No<T> no) {

        if (no != null)
            return no.getAltura();
        else
            return -1;
    }

    public int getAltura() {
        return this.altura;
    }

    public void setAltura() {

        int alturaEsquerda, alturaDireita;

        alturaEsquerda = getAltura(this.esquerda);
        alturaDireita = getAltura(this.direita);

        if (alturaEsquerda > alturaDireita)
            this.altura = alturaEsquerda + 1;
        else
            this.altura = alturaDireita + 1;
    }

    public int getFatorBalanceamento() {

        int alturaEsquerda, alturaDireita;

        alturaEsquerda = getAltura(this.esquerda);
        alturaDireita = getAltura(this.direita);

        return (alturaEsquerda - alturaDireita);
    }
}
// #endregion

// #region Lista
final class Lista<E> {

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

        if (posicao == this.tamanho)
            this.ultimo = novaCelula;

        this.tamanho++;
    }

    public void inserirFinal(E novo) {

        Celula<E> novaCelula = new Celula<>(novo);

        this.ultimo.setProximo(novaCelula);
        this.ultimo = novaCelula;

        this.tamanho++;
    }

    private E removerProxima(Celula<E> anterior) {

        Celula<E> celulaRemovida, proximaCelula;

        celulaRemovida = anterior.getProximo();

        proximaCelula = celulaRemovida.getProximo();

        anterior.setProximo(proximaCelula);
        celulaRemovida.setProximo(null);

        if (celulaRemovida == this.ultimo)
            this.ultimo = anterior;

        this.tamanho--;

        return (celulaRemovida.getItem());
    }

    public E remover(int posicao) {

        Celula<E> anterior;

        if (vazia())
            throw new IllegalStateException("Não foi possível remover o item da lista: "
                    + "a lista está vazia!");

        if ((posicao < 0) || (posicao >= this.tamanho))
            throw new IndexOutOfBoundsException("Não foi possível remover o item da lista: "
                    + "a posição informada é inválida!");

        anterior = this.primeiro;
        for (int i = 0; i < posicao; i++)
            anterior = anterior.getProximo();

        return (removerProxima(anterior));
    }

    public E remover(E elemento) {

        Celula<E> anterior;

        if (vazia())
            throw new IllegalStateException("Não foi possível remover o item da lista: "
                    + "a lista está vazia!");

        anterior = this.primeiro;
        while ((anterior.getProximo() != null) && !(anterior.getProximo().getItem().equals(elemento)))
            anterior = anterior.getProximo();

        if (anterior.getProximo() == null)
            throw new NoSuchElementException("Item não encontrado!");
        else {
            return (removerProxima(anterior));
        }
    }

    public E pesquisar(E procurado) {

        Celula<E> aux;

        aux = this.primeiro.getProximo();

        while (aux != null) {
            if (aux.getItem().equals(procurado))
                return aux.getItem();
            aux = aux.getProximo();
        }

        throw new NoSuchElementException("Item não encontrado!");
    }

    public void imprimir() {

        Celula<E> aux;

        aux = this.primeiro.getProximo();

        while (aux != null) {
            System.out.println(aux.getItem());
            aux = aux.getProximo();
        }
    }
}
// #endregion

// #region Celula
final class Celula<T> {

    private T item;
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

    public void setItem(T item) {
        this.item = item;
    }

    public Celula<T> getProximo() {
        return proximo;
    }

    public void setProximo(Celula<T> proximo) {
        this.proximo = proximo;
    }
}
// #endregion

// #region Tabela Hash
final class TabelaHash<K, V> {

    private Lista<Entrada<K, V>>[] tabelaHash;

    private int capacidade;

    @SuppressWarnings("unchecked")
    public TabelaHash(int capacidade) {

        this.capacidade = capacidade;
        this.tabelaHash = (Lista<Entrada<K, V>>[]) new Lista[this.capacidade];

        for (int i = 0; i < this.capacidade; i++)
            this.tabelaHash[i] = new Lista<>();
    }

    private int funcaoHash(K chave) {
        return Math.abs(chave.hashCode() % this.capacidade);
    }

    public int inserir(K chave, V item) {

        /// cálculo da posição da tabela hash em que o novo item deverá ser armazenado.
        int posicao = funcaoHash(chave);

        Entrada<K, V> entrada = new Entrada<>(chave, item);
        try {
            this.tabelaHash[posicao].pesquisar(entrada);
            throw new IllegalArgumentException("O item já havia sido inserido anteriormente na tabela hash!");
        } catch (NoSuchElementException excecao) {
            this.tabelaHash[posicao].inserirFinal(entrada);
            return posicao;
        }
    }

    public V pesquisar(K chave) {
        int posicao = funcaoHash(chave);
        try {
            Entrada<K, V> procurado = this.tabelaHash[posicao].pesquisar(new Entrada<>(chave, null));
            return procurado.getValor();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public V remover(K chave) {

        int posicao = funcaoHash(chave);

        Entrada<K, V> procurado = new Entrada<>(chave, null);
        procurado = this.tabelaHash[posicao].remover(procurado);
        return procurado.getValor();
    }

    public void imprimir() {

        for (int i = 0; i < this.capacidade; i++) {
            System.out.println("Posição[" + i + "]: ");
            if (this.tabelaHash[i].vazia())
                System.out.println("vazia");
            else
                this.tabelaHash[i].imprimir();
        }
    }
}
// #endregion

// #region Entrada (K, V)
final class Entrada<K, V> {

    private final K chave;
    private V valor;

    public Entrada(K chave, V item) {
        this.chave = chave;
        this.valor = item;
    }

    public K getChave() {
        return chave;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object outroObjeto) {

        Entrada<K, V> outraEntrada;

        if (this == outroObjeto)
            return true;
        else if (outroObjeto == null || !(outroObjeto.getClass() == this.getClass()))
            return false;
        else {
            outraEntrada = (Entrada<K, V>) outroObjeto;
            return (outraEntrada.getChave().equals(this.chave));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.chave);
    }

    @Override
    public String toString() {
        return (this.chave + "\n" + this.valor);
    }
}

// #endregion
// #region Eventos
class Evento implements Comparable<Evento> {
    private final ABB<Medalhista> medalhistas; // Árvore de medalhistas
    private final String evento; // Nome do evento
    private final String disciplina; // Disciplina do evento

    // Construtor
    public Evento(String evento, String disciplina) {
        this.medalhistas = new ABB<>();
        this.evento = evento;
        this.disciplina = disciplina;
    }

    // Adicionar medalhista ao evento
    public void incluirMedalhista(Medalhista m) {
        try {
            medalhistas.adicionar(m);
        } catch (RuntimeException e) {
            System.out.println("Medalhista já registrado: " + m.getName());
        }
    }

    // Imprimir medalhistas do evento
    public void imprimirMedalhistas() {
        System.out.println(evento + " - " + disciplina);
        if (medalhistas.vazia()) {
            System.out.println("Nenhum medalhista registrado.");
            return;
        }
        medalhistas.caminhamentoEmOrdemComAcao(m -> {
            System.out.println(m.toString());
            m.getMedals().forEach(System.out::println);
        });
    }

    public boolean verificaMedalhista(Medalhista m) {
        return medalhistas.pesquisar(m) != null;
    }

    // Remover um medalhista do evento
    public void removerMedalhista(Medalhista m) {
        try {
            medalhistas.remover(m);
            System.out.println("Medalhista removido: " + m.getName());
        } catch (NoSuchElementException e) {
            System.out.println("Medalhista não encontrado: " + m.getName());
        }
    }

    // Comparação para ordenação
    @Override
    public int compareTo(Evento outroEvento) {
        int disciplinaComparison = this.disciplina.compareTo(outroEvento.disciplina);
        return disciplinaComparison != 0 ? disciplinaComparison : this.evento.compareTo(outroEvento.evento);
    }

    // Retornar nome do evento
    public String getNome() {
        return evento;
    }

    // Retornar disciplina do evento
    public String getDisciplina() {
        return disciplina;
    }

    @Override
    public String toString() {
        return disciplina + " - " + evento;
    }
}
// #endregion