import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Classe Medalhista: representa um medalhista olímpico e sua coleção de
 * medalhas nas Olimpíadas de Paris 2024
 */
class Medalhista {
    private static final int MAX_MEDALHAS = 8;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String country;
    private List<Medalha> medals = new ArrayList<>();

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medalhista that = (Medalhista) o;
        return name.equalsIgnoreCase(that.name) && birthDate.equals(that.birthDate);
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
        String csvFile = "/tmp/medallists.csv";

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
    private static final MedalhistasDAO medalhistasDAO = new MedalhistasDAO();

    public static void main(String[] args) {
        List<Medalhista> medalhistas = medalhistasDAO.leitura();

        try (Scanner inputScanner = new Scanner(System.in)) {
            while (inputScanner.hasNextLine()) {
                String linha = inputScanner.nextLine();
                if (linha.equals("FIM")) {
                    System.exit(0);
                }
                String[] partes = linha.split(",");
                String nome = partes[0];
                TipoMedalha tipoMedalha = TipoMedalha.valueOf(partes[1].toUpperCase());

                Medalhista medalhista = findMedalhistaByName(medalhistas, nome);
                if (medalhista != null) {
                    System.out.println(medalhista);
                    System.out.println(medalhista.relatorioDeMedalhas(tipoMedalha));
                }
                System.out.println();
            }
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
}
