package Verde;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Classe principal da aplicação
 */
// #region Main
public class AplicacaoQuick {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int quantPaises = scanner.nextInt();
            scanner.nextLine();

            List<String> paisesLidos = new ArrayList<>();
            for (int i = 0; i < quantPaises; i++) {
                paisesLidos.add(scanner.nextLine().trim());
            }

            List<Pais> todosPaises = new DAO().leitura();

            List<Pais> paisesFiltrados = new ArrayList<>();
            for (String nome : paisesLidos) {
                Pais pais = findPaisByNome(todosPaises, nome);
                if (pais != null) {
                    paisesFiltrados.add(pais);
                } else {
                    System.out.println("Pais não encontrado: " + nome);
                }
            }

            QuickSort<Pais> quicksort = new QuickSort<>();
            quicksort.setComparator(null);
            Pais[] sortedPaises = quicksort.ordenar(paisesFiltrados.toArray(new Pais[0]));

            for (Pais p : sortedPaises) {
                System.out.println(p.toString());
            }

            // Criar arquivo de log
            criarArquivoLog(quicksort);
        }
    }

    private static Pais findPaisByNome(List<Pais> paises, String nome) {
        for (Pais p : paises) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    private static void criarArquivoLog(QuickSort<Pais> quickSort) {
        String matricula = "836095";
        String logFile = matricula + "_quicksort.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile))) {
            writer.printf("%s\t%.2f\t%d\t%d\n", matricula, quickSort.getTempoOrdenacao(),
                    quickSort.getComparacoes(), quickSort.getMovimentacoes());
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo de log: " + e.getMessage());
        }
    }
}
// #endregion

// #region QuickSort
class QuickSort<T extends Comparable<T>> implements IOrdenator<T> {

    private Comparator<T> comparator;
    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public T[] ordenar(T[] array) {
        comparacoes = 0;
        movimentacoes = 0;
        long startTime = System.nanoTime();

        sort(array, 0, array.length - 1);

        tempoOrdenacao = (System.nanoTime() - startTime) / 1e6;
        return array;
    }

    private void sort(T[] array, int esq, int dir) {
        if (esq < dir) {
            int part = particao(array, esq, dir);
            sort(array, esq, part - 1);
            sort(array, part + 1, dir);
        }
    }

    private int particao(T[] array, int inicio, int fim) {
        T pivot = array[fim];
        int part = inicio - 1;
        for (int i = inicio; i < fim; i++) {
            comparacoes++;
            if (comparator != null) {
                if (comparator.compare(array[i], pivot) < 0) {
                    part++;
                    swap(array, part, i);
                }
            } else {
                if (array[i].compareTo(pivot) < 0) {
                    part++;
                    swap(array, part, i);
                }
            }
        }
        part++;
        swap(array, part, fim);
        return part;
    }

    private void swap(T[] array, int i, int j) {
        movimentacoes += 3;
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public int getComparacoes() {
        return comparacoes;
    }

    @Override
    public int getMovimentacoes() {
        return movimentacoes;
    }

    @Override
    public double getTempoOrdenacao() {
        return tempoOrdenacao;
    }
}
// #endregion

// #region IOrdenator
interface IOrdenator<T> {
    void setComparator(Comparator<T> comparator);

    T[] ordenar(T[] array);

    int getComparacoes();

    int getMovimentacoes();

    double getTempoOrdenacao();
}
// #endregion

// #region Medalhista
class Medalhista {
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
        return name + ", " + gender + ". Nascimento: " + dataFormatada + ". Pais: " + country;
    }
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

    @Override
    public String toString() {
        String dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(data);
        return tipo + " - " + disciplina + " - " + evento + " - " + dataFormatada;
    }
}
// #endregion

// #region TipoMedalha
enum TipoMedalha {
    OURO,
    PRATA,
    BRONZE
}
// #endregion

// #region DAO
class DAO {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Pais> leitura() {
        List<Medalhista> medalhistas = new ArrayList<>();
        Map<String, Pais> paisMap = new HashMap<>();
        String csvFile = "/tmp/medallists.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String linha;
            boolean isFirstLine = true;
            while ((linha = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] campos = linha.split(",");
                // name,medal_type,medal_date,gender,birth_date,country,discipline,event
                // Example: EVENEPOEL
                // Remco,OURO,2024-07-27,MASCULINO,2000-01-25,Belgium,Cycling,Road,Men's
                // Individual Time Trial
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
                String evento = campos[7].trim();

                Pais pais = paisMap.getOrDefault(paisNome, new Pais(paisNome));
                paisMap.putIfAbsent(paisNome, pais);

                Medalhista medalhista = new Medalhista(nome, genero, dataNascimento, paisNome);
                medalhista.incluirMedalha(new Medalha(tipoMedalha, dataMedalha, disciplina, evento));
                pais.incluirMedalhista(medalhista);

                medalhistas.add(medalhista);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo CSV: " + e.getMessage());
        }

        return new ArrayList<>(paisMap.values());
    }
}
// #endregion

// #region Pais
class Pais implements Comparable<Pais> {
    private List<Medalhista> medalhistas = new ArrayList<>();
    private String nome;

    public Pais(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public List<Medalhista> getMedalhistas() {
        return this.medalhistas;
    }

    public void incluirMedalhista(Medalhista m) {
        this.medalhistas.add(m);
    }

    public int getMedalhaCount(TipoMedalha tipo) {
        return (int) medalhistas.stream().filter(m -> m.getMedalhaCount(tipo) > 0).count();
    }

    public int getTotalMedalhas(TipoMedalha tipo) {
        return medalhistas.stream().mapToInt(m -> m.getMedalhaCount(tipo)).sum();
    }

    @Override
    public int compareTo(Pais outroPais) {
        int goldThis = this.getTotalMedalhas(TipoMedalha.OURO);
        int goldOther = outroPais.getTotalMedalhas(TipoMedalha.OURO);
        if (goldThis != goldOther) {
            return Integer.compare(goldOther, goldThis);
        }

        int silverThis = this.getTotalMedalhas(TipoMedalha.PRATA);
        int silverOther = outroPais.getTotalMedalhas(TipoMedalha.PRATA);
        if (silverThis != silverOther) {
            return Integer.compare(silverOther, silverThis);
        }
        int bronzeThis = this.getTotalMedalhas(TipoMedalha.BRONZE);
        int bronzeOther = outroPais.getTotalMedalhas(TipoMedalha.BRONZE);
        if (bronzeThis != bronzeOther) {
            return Integer.compare(bronzeOther, bronzeThis);
        }
        return 0;
    }

    @Override
    public String toString() {
        int gold = getTotalMedalhas(TipoMedalha.OURO);
        int bronze = getTotalMedalhas(TipoMedalha.BRONZE);
        int silver = getTotalMedalhas(TipoMedalha.PRATA);

        int total = gold + silver + bronze;

        String bronzeStr = (bronze < 10) ? "0" + bronze : String.valueOf(bronze);
        String silverStr = (silver < 10) ? "0" + silver : String.valueOf(silver);
        String goldStr = (gold < 10) ? "0" + gold : String.valueOf(gold);
        String totalStr = (total < 10) ? "0" + total : String.valueOf(total);

        return nome + ": " + goldStr + " ouros " + silverStr + " pratas " + bronzeStr + " bronzes Total: "
                + totalStr + " medalhas.";
    }
}
// #endregion
