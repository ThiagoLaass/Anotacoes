package Verde;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Consumer;

public class AplicacaoABB {
    public static void main(String[] args) {
        String str;
        DAO dao = new DAO();
        Scanner scanner = new Scanner(System.in);
        List<Evento> eventos = new ArrayList<>();
        ABB<Medalhista> arvore = dao.leitura(eventos);
        Collections.sort(eventos);
        ListaDuplamenteEncadeada<Medalhista> listaRecortada;
        try {
            while (true) {

                str = scanner.nextLine();
                if (str.equals("FIM")) {
                    break;
                }

                if (str.startsWith("PESQUISAR")) {
                    str = str.replace("PESQUISAR -", "").trim();
                    String[] entradas = str.split(" - ");
                    String esporte = entradas[0].trim();
                    String evento = entradas[1].trim();
                    String nome = entradas[2].trim();
                    Medalhista medalhista = findMedalhistaInArvoreByNome(nome, arvore);
                    boolean encontrou = false;

                    if (medalhista != null) {
                        for (Medalha m : medalhista.getMedals()) {
                            if (m.getEvento().equals(evento) && m.getDisciplina().equals(esporte)) {
                                System.out.println(medalhista.getName() + " foi medalhista no evento " + evento
                                        + " do esporte " + esporte);
                                encontrou = true;
                                break;
                            }
                        }
                        if (!encontrou) {
                            System.out.println(medalhista.getName() + " nao foi medalhista no evento " + evento
                                    + " do esporte " + esporte);
                        }
                    } else {
                        System.out.println("Medalhista " + nome + " nao encontrado.");
                    }
                    System.out.println();
                } else if (str.startsWith("IMPRIMIR")) {
                    str = str.replace("IMPRIMIR -", "").trim();
                    String[] entradas = str.split(" - ");
                    String disciplina = entradas[0].trim();
                    String evento = entradas[1].trim();
                    System.out.println("Medalhistas " + disciplina + " - " + evento);
                    for (Evento e : eventos) {
                        if (e.getNome().trim().equalsIgnoreCase(evento) &&
                                e.getDisciplina().trim().equalsIgnoreCase(disciplina)) {
                            e.imprimirMedalhistas();
                        }
                    }
                    System.out.println();
                } else if (str.startsWith("RECORTAR")) {
                    str = str.replace("RECORTAR -", "").trim();
                    String[] entradas = str.split(" - ");
                    String esporte = entradas[0].trim();
                    String evento = entradas[1].trim();
                    String deOnde = entradas[2].trim();
                    String ateOnde = entradas[3].trim();
                    Medalhista medalhistaDe = findMedalhistaInArvoreByNome(deOnde, arvore);
                    Medalhista medalhistaAte = findMedalhistaInArvoreByNome(ateOnde, arvore);
                    listaRecortada = arvore.recortar(medalhistaDe, medalhistaAte);
                    System.out.print(listaRecortada.toString(TipoMedalha.BRONZE));
                    System.out.print(listaRecortada.toString(TipoMedalha.PRATA));
                    System.out.print(listaRecortada.toString(TipoMedalha.OURO));
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
//#endregion

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
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ABB<Medalhista> leitura(List<Evento> eventos) {
        String csvFile = "/tmp/medallists.csv";
        Map<String, Medalhista> medalhistaMap = new HashMap<>();
        ABB<Medalhista> arvoreMedalhistas = new ABB<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String linha;
            boolean isFirstLine = true;
    
            while ((linha = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
    
                String[] campos = linha.split(",");
                if (campos.length < 8) {
                    continue;
                }
    
                String nome = campos[0].trim();
                TipoMedalha tipoMedalha = TipoMedalha.valueOf(campos[1].trim().toUpperCase());
                LocalDate dataMedalha = LocalDate.parse(campos[2].trim(), formatter);
                String genero = campos[3].trim();
                LocalDate dataNascimento = LocalDate.parse(campos[4].trim(), formatter);
                String paisNome = campos[5].trim();
                String disciplina = campos[6].trim();
                String eventoNome = campos[7].trim();
    
                Medalhista medalhista = medalhistaMap.getOrDefault(nome.toLowerCase(), null);
                if (medalhista == null) {
                    medalhista = new Medalhista(nome, genero, dataNascimento, paisNome);
                    medalhistaMap.put(nome.toLowerCase(), medalhista);
                    arvoreMedalhistas.adicionar(medalhista);
                }
    
                medalhista.incluirMedalha(new Medalha(tipoMedalha, dataMedalha, disciplina, eventoNome));
    
                Evento evento = eventos.stream()
                        .filter(e -> e.getNome().equals(eventoNome) && e.getDisciplina().equals(disciplina))
                        .findFirst()
                        .orElse(null);
    
                if (evento == null) {
                    evento = new Evento(new ABB<>(), eventoNome, 0, disciplina);
                    eventos.add(evento);
                }
    
                evento.incluirMedalhista(medalhista);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo CSV: " + e.getMessage());
        }
    
        return arvoreMedalhistas;
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
        return name + ", " + gender.toUpperCase() + ". Nascimento: " + dataFormatada + ". Pais: " + country+".";
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
            return null;

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
            System.out.println(raizArvore.getItem().toString());
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

    public int tamanho(E item) {
        No<E> raizEncontrada = this.pesquisar(item);
        return tamanho(item, raizEncontrada);
    }

    private int tamanho(E item, No<E> raiz) {
        if (raiz == null) {
            return 0;
        } else {
            return 1 + tamanho(item, raiz.getDireita()) + tamanho(item, raiz.getEsquerda());
        }
    }

    public ListaDuplamenteEncadeada<E> recortar(E deOnde, E ateOnde) {
        ListaDuplamenteEncadeada<E> lista = new ListaDuplamenteEncadeada<>();
        recortar(this.raiz, deOnde, ateOnde, lista);
        return lista;
    }

    private void recortar(No<E> no, E deOnde, E ateOnde, ListaDuplamenteEncadeada<E> lista) {
        if (no == null) {
            return;
        }

        int comparacaoInicio = deOnde.compareTo(no.getItem());
        int comparacaoFim = ateOnde.compareTo(no.getItem());

        if (comparacaoInicio < 0) {
            recortar(no.getEsquerda(), deOnde, ateOnde, lista);
        }

        if (comparacaoInicio <= 0 && comparacaoFim >= 0) {
            lista.inserirFinal(no.getItem());
        }

        if (comparacaoFim > 0) {
            recortar(no.getDireita(), deOnde, ateOnde, lista);
        }
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
final class ListaDuplamenteEncadeada<E extends Comparable<E>> {

    private Celula<E> primeiro;
    private Celula<E> ultimo;
    private int tamanho;

    public ListaDuplamenteEncadeada() {

        Celula<E> sentinela = new Celula<>();

        this.primeiro = this.ultimo = sentinela;
        this.tamanho = 0;
    }

    public Celula<E> getPrimeiro() {
        return this.primeiro;
    }

    public void inserirOrdenado(E item) {

        Celula<E> novaCelula = new Celula<>(item);

        Celula<E> atual = this.primeiro.getProximo();
        Celula<E> anterior = this.primeiro;

        while (atual != null && atual.getItem().compareTo(item) < 0) {
            anterior = atual;
            atual = atual.getProximo();
        }

        anterior.setProximo(novaCelula);
        novaCelula.setProximo(atual);

        if (atual == null)
            this.ultimo = novaCelula;

        this.tamanho++;
    }

    public boolean vazia() {

        return (this.primeiro == this.ultimo);
    }

    public void inserirFinal(E novo) {

        Celula<E> novaCelula = new Celula<>(novo, this.ultimo, null);

        this.ultimo.setProximo(novaCelula);
        this.ultimo = novaCelula;

        this.tamanho++;

    }

    public E removerFinal() {

        Celula<E> removida, penultima;

        if (vazia())
            throw new IllegalStateException("Não foi possível remover o último item da lista: "
                    + "a lista está vazia!");

        removida = this.ultimo;

        penultima = this.ultimo.getAnterior();
        penultima.setProximo(null);

        removida.setAnterior(null);

        this.ultimo = penultima;

        this.tamanho--;

        return (removida.getItem());
    }

    public void sort() {
        if (this.tamanho > 1) {
            Celula<E> atual = this.primeiro.getProximo();
            Celula<E> proximo = atual.getProximo();
            Celula<E> anterior = this.primeiro;
            Celula<E> temp = new Celula<>();
            boolean trocou = false;
            for (int i = 0; i < this.tamanho; i++) {
                while (proximo != null) {
                    if (atual.getItem().compareTo(proximo.getItem()) > 0) {
                        temp.setProximo(proximo.getProximo());
                        proximo.setProximo(atual);
                        anterior.setProximo(proximo);
                        trocou = true;
                    }
                    anterior = atual;
                    atual = atual.getProximo();
                    proximo = atual.getProximo();
                }
                if (!trocou)
                    break;
                atual = this.primeiro.getProximo();
                proximo = atual.getProximo();
                anterior = this.primeiro;
                trocou = false;
            }
        }
    }

    public String toString(TipoMedalha tipoMedalha) {
        StringBuilder sb = new StringBuilder();
        Celula<E> atual = this.primeiro.getProximo();
        while (atual != null) {
            sb.append(atual.getItem().toString()).append("\n");
            atual = atual.getProximo();
        }
        return sb.toString();
    }
}
// #endregion

// #region Celula
final class Celula<T> {

    private final T item;
    private Celula<T> anterior;
    private Celula<T> proximo;

    public Celula() {
        this.item = null;
        setAnterior(null);
        setProximo(null);
    }

    public Celula(T item) {
        this.item = item;
        setAnterior(null);
        setProximo(null);
    }

    public Celula(T item, Celula<T> anterior, Celula<T> proximo) {
        this.item = item;
        this.anterior = anterior;
        this.proximo = proximo;
    }

    public T getItem() {
        return item;
    }

    public Celula<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(Celula<T> anterior) {
        this.anterior = anterior;
    }

    public Celula<T> getProximo() {
        return proximo;
    }

    public void setProximo(Celula<T> proximo) {
        this.proximo = proximo;
    }
}
// #endregion

// #region Eventos
class Evento implements Comparable<Evento> {
    private final ABB<Medalhista> medalhistas;
    private final String evento;
    private int quantMedalistas;
    private final String disciplina;

    public Evento(ABB<Medalhista> medalhistas, String evento, int quantMedalistas, String disciplina) {
        this.medalhistas = medalhistas;
        this.evento = evento;
        this.quantMedalistas = quantMedalistas;
        this.disciplina = disciplina;
    }

    public void incluirMedalhista(Medalhista m) {
        medalhistas.adicionar(m);
    }

    public String getDisciplina() {
        return disciplina;
    }

    @Override
    public int compareTo(Evento outroEvento) {
        int sportComparison = this.disciplina.compareTo(outroEvento.getDisciplina());
        if (sportComparison != 0) {
            return sportComparison;
        }
        return this.evento.compareTo(outroEvento.evento);
    }

    public void imprimirMedalhistas() {
        if (medalhistas == null || medalhistas.vazia()) {
            System.out.println("Nenhum medalhista registrado no evento: " + evento + " - " + disciplina);
            return;
        }
        medalhistas.caminhamentoEmOrdemComAcao((medalhista) -> {
            System.out.println(medalhista.toString());
            for (Medalha medalha : medalhista.getMedals()) {
                System.out.println(medalha.toString());
            }
        });
    }

    public String getNome() {
        return evento;
    }

    public boolean verificaMedalhista(Medalhista m) {
        Medalhista pesquisado = medalhistas.pesquisar(m).getItem();
        return pesquisado != null;
    }
}
// #endregion