package Trab_Pratico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Comparator;

/**
 * Classe Medalhista: representa um medalhista olímpico e sua coleção de
 * medalhas nas Olimpíadas de Paris 2024
 */
class Medalhista implements Comparable<Medalhista> {
    private static final int MAX_MEDALHAS = 8;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String country;
    private List<Medalha> medals = new ArrayList<>();

    @Override
    public int compareTo(Medalhista outroMedalhista) {
        int thisGoldCount = 0, thisSilverCount = 0, thisBronzeCount = 0;
        int otherGoldCount = 0, otherSilverCount = 0, otherBronzeCount = 0;

        // Contar as medalhas deste medalhista
        for (Medalha medal : this.getMedals()) {
            if (medal.getTipo() == TipoMedalha.OURO) {
                thisGoldCount++;
            } else if (medal.getTipo() == TipoMedalha.PRATA) {
                thisSilverCount++;
            } else if (medal.getTipo() == TipoMedalha.BRONZE) {
                thisBronzeCount++;
            }
        }

        // Contar as medalhas do outro medalhista
        for (Medalha medal : outroMedalhista.getMedals()) {
            if (medal.getTipo() == TipoMedalha.OURO) {
                otherGoldCount++;
            } else if (medal.getTipo() == TipoMedalha.PRATA) {
                otherSilverCount++;
            } else if (medal.getTipo() == TipoMedalha.BRONZE) {
                otherBronzeCount++;
            }
        }

        // Comparar primeiro o número de medalhas de ouro
        if (thisGoldCount != otherGoldCount) {
            return Integer.compare(otherGoldCount, thisGoldCount); // Ordem decrescente
        }
        // Se empatar em ouro, comparar prata
        if (thisSilverCount != otherSilverCount) {
            return Integer.compare(otherSilverCount, thisSilverCount); // Ordem decrescente
        }
        // Se empatar em prata, comparar bronze
        if (thisBronzeCount != otherBronzeCount) {
            return Integer.compare(otherBronzeCount, thisBronzeCount); // Ordem decrescente
        }
        // Se empatar em todas as medalhas, comparar pelo nome
        return this.name.compareToIgnoreCase(outroMedalhista.getName());
    }

    public Medalhista(String nome, String genero, LocalDate nascimento, String pais) {
        this.name = nome;
        this.gender = genero;
        this.birthDate = nascimento;
        this.country = pais;
    }

    public void incluirMedalha(Medalha medalha) {
        if (medals.size() < MAX_MEDALHAS) {
            this.medals.add(medalha);
        }
    }

    public String relatorioDeMedalhas(TipoMedalha tipo) {
        StringBuilder relatorio = new StringBuilder();
        boolean hasMedals = false;

        for (Medalha medalha : medals) {
            if (medalha.getTipo() == tipo) {
                if (hasMedals) {
                    relatorio.append("\n");
                }
                relatorio.append(medalha.toString());
                hasMedals = true;
            }
        }

        if (!hasMedals) {
            return "Nao possui medalha de " + tipo;
        }
        return relatorio.toString();
    }

    @Override
    public String toString() {
        String dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(birthDate);
        return name + ", " + gender + ". Nascimento: " + dataFormatada + ". Pais: " + country;
    }

    public String getName() {
        return name;
    }

    public List<Medalha> getMedals() {
        return medals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), birthDate);
    }
}

/** Enumerador para medalhas de ouro, prata e bronze */
enum TipoMedalha {
    OURO,
    PRATA,
    BRONZE
}

/**
 * Representa uma medalha obtida nos Jogos Olímpicos de Paris em 2024.
 */
class Medalha {
    private TipoMedalha metalType;
    private LocalDate medalDate;
    private String discipline;
    private String event;

    public Medalha(TipoMedalha tipo, LocalDate data, String disciplina, String evento) {
        metalType = tipo;
        medalDate = data;
        discipline = disciplina;
        event = evento;
    }

    public TipoMedalha getTipo() {
        return metalType;
    }

    @Override
    public String toString() {
        String dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(medalDate);
        return this.metalType + " - " + this.discipline + " - " + this.event + " - " + dataFormatada;
    }
}

class MedalhistasDAO {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Medalhista> leitura() {
        List<Medalhista> medalhistas = new ArrayList<>();
        String csvFile = System.getProperty("user.dir") + "/Trab_Pratico/medallists.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] fields = line.split(",");

                String nome = fields[0].trim();
                TipoMedalha tipoMedalha = TipoMedalha.valueOf(fields[1].trim().toUpperCase());
                LocalDate dataMedalha = LocalDate.parse(fields[2].trim(), formatter);
                String genero = fields[3].trim();
                LocalDate nascimento = LocalDate.parse(fields[4].trim(), formatter);
                String pais = fields[5].trim();
                String disciplina = fields[6].trim();
                String evento = fields[7].trim();

                Medalhista medalhista = new Medalhista(nome, genero, nascimento, pais);
                Medalha medalha = new Medalha(tipoMedalha, dataMedalha, disciplina, evento);

                int index = medalhistas.indexOf(medalhista);
                if (index != -1) {
                    medalhistas.get(index).incluirMedalha(medalha);
                } else {
                    medalhista.incluirMedalha(medalha);
                    medalhistas.add(medalhista);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao processar os dados: " + e.getMessage());
        }

        return medalhistas;
    }
}

public class Aplicacao {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int quantAtletas = scanner.nextInt();
            List<Medalhista> medalhistas = new MedalhistasDAO().leitura();

            // Ordenar os medalhistas
            BubbleSort<Medalhista> bubbleSort = new BubbleSort<>();
            bubbleSort.setComparator(Comparator.naturalOrder()); // Usar o compareTo da classe Medalhista
            Medalhista[] sortedMedalhistas = bubbleSort.ordenar(medalhistas.toArray(new Medalhista[0]));

            // Exibir os medalhistas ordenados de acordo com o número estipulado
            for (int i = 0; i < quantAtletas && i < sortedMedalhistas.length; i++) {
                System.out.println(sortedMedalhistas[i]);
            }

            System.out.println("Total de comparações: " + bubbleSort.getComparacoes());
            System.out.println("Total de movimentações: " + bubbleSort.getMovimentacoes());
            System.out.println("Tempo de ordenação: " + bubbleSort.getTempoOrdenacao() + "ms");
        }
    }

    private static Medalhista findMedalhistaByName(List<Medalhista> medalhistas, String name) {
        for (Medalhista m : medalhistas) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

    interface IOrdenator<T> {
        T[] ordenar(T[] array);

        void setComparator(Comparator<T> comparator);

        int getComparacoes();

        int getMovimentacoes();

        double getTempoOrdenacao();
    }

    static class BubbleSort<T> implements IOrdenator<T> {

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

            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - 1 - i; j++) {
                    comparacoes++;
                    if (comparator.compare(array[j], array[j + 1]) > 0) {
                        T temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        movimentacoes++;
                    }
                }
            }

            long endTime = System.nanoTime();
            tempoOrdenacao = (endTime - startTime) / 1e6; // Convertido para milissegundos
            return array;
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
}
