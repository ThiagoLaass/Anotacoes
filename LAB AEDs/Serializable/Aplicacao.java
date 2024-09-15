package Serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe Medalhista: representa um medalhista olímpico e sua coleção de
 * medalhas nas Olimpíadas de Paris 2024
 */
class Medalhista {
    /** Para criar o vetor com no máximo 8 medalhas */
    private static final int MAX_MEDALHAS = 8;
    /** Nome do medalhista */
    private String name;
    /** Gênero do medalhista */
    private String gender;
    /** Data de nascimento do medalhista */
    private LocalDate birthDate;
    /** País do medalhista */
    private String country;
    /** Coleção de medalhas do medalhista */
    private List<Medalha> medals;
    /** Contador de medalhas e índice para controlar o vetor de medalhas */
    private int medalCount;

    /**
     * Cria um medalhista olímpico. Nenhum dado precisa ser validado.
     * 
     * @param nome       Nome do medalhista no formato "SOBRENONE nome"
     * @param genero     Gênero do medalhista
     * @param nascimento Data de nascimento do medalhista
     * @param pais       País do medalhista (conforme dados originais, em inglês)
     */
    public Medalhista(String nome, String genero, LocalDate nascimento, String pais) {
        this.name = nome;
        this.gender = genero;
        this.birthDate = nascimento;
        this.country = pais;
    }

    /**
     * Inclui uma medalha na coleção do medalhista. Retorna a quantidade atual de
     * medalhas do atleta.
     * 
     * @param medalha A medalha a ser armazenada.
     * @return A quantidade total de medalhas do atleta após a inclusão.
     */
    public void incluirMedalha(Medalha medalha) {
        medals.add(medalha);
        medalCount++;
    }

    /**
     * Total de medalhas do atleta. É um número maior ou igual a 0.
     * 
     * @return Inteiro com o total de medalhas do atleta (>=0)
     */
    public int totalMedalhas() {
        return medalCount;
    }

    /**
     * Retorna um relatório das medalhas do atleta conforme o tipo solicitado pelo
     * parâmetro. Veja no
     * enunciado da atividade o formato correto deste relatório. Em caso de não
     * possuir medalhas
     * deste tipo, a resposta deve ser "Nao possui medalha de TIPO".
     * 
     * @param tipo Tipo da medalha para o relatório
     * @return Uma string, multilinhas, com o relatório de medalhas daquele tipo. Em
     *         caso de não possuir
     *         medalhas deste tipo, a resposta deve ser "Nao possui medalha de
     *         TIPO".
     */
    public String relatorioDeMedalhas(TipoMedalha tipo) {
        // TODO lógica para gerar uma string conforme documentado acima
        return "";
    }

    /**
     * Retorna o nome do país do medalhista (conforme arquivo original em inglês.)
     * 
     * @return String contendo o nome do país do medalhista (conforme arquivo
     *         original em inglês, iniciais em maiúsculas.)
     */
    public String getPais() {
        return country;
    }

    /**
     * Retorna uma cópia da data de nascimento do medalhista.
     * 
     * @return LocalDate com a data de nascimento do medalhista.
     */
    public LocalDate getNascimento() {
        return LocalDate.from(birthDate);
    }

    /**
     * Deve retornar os dados pessoais do medalhista, sem as medalhas, conforme
     * especificado no enunciado
     * da atividade.
     * 
     * @return String de uma linha, com os dados do medalhista, sem dados da
     *         medalha.
     */
    public String toString() {
        String dataFormatada = DateTimeFormatter.ofPattern("dd/MM/YYYY").format(getNascimento()); // formata a data em // DD/MM/AAAA
        return this.name + ", " + this.gender + ". Nascimento: " + dataFormatada + ". Pais: " + getPais();
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
    /** Tipo/cor da medalha conforme o enumerador */
    private TipoMedalha metalType;
    /** Data de obtenção da medalha */
    private LocalDate medalDate;
    /** Disciplina da medalha, conforme arquivo de dados */
    private String discipline;
    /** Evento da medalha, conforme arquivo de dados */
    private String event;

    /** Cria uma medalha com os dados do parâmetro. Nenhum dado é validado */
    public Medalha(TipoMedalha tipo, LocalDate data, String disciplina, String evento) {
        metalType = tipo;
        medalDate = data;
        discipline = disciplina;
        event = evento;
    }

    /**
     * Retorna o tipo de medalha, conforme o enumerador
     * 
     * @return TipoMedalha (enumerador) com o tipo/cor desta medalha
     */
    public TipoMedalha getTipo() {
        return metalType;
    }

    /**
     * Retorna uma string com o "relatório" da medalha de acordo com o especificado
     * no enunciado do problema.
     * Contém uma linha que já formata a data da medalha no formato brasileiro. O
     * restante deve ser implementado.
     */
    public String toString() {
        String dataFormatada = DateTimeFormatter.ofPattern("dd/MM/YYYY").format(medalDate); // formata a data em // DD/MM/AAAA
        return this.metalType + " - " + this.discipline + " - " + this.event + " - " + dataFormatada;
    }
}

class MedalhistasDAO implements Serializable {
    @SuppressWarnings("unchecked")
    public HashMap<Medalhista, Medalha> leitura() {
        HashMap<Medalhista, Medalha> data = new HashMap<>();
        try {
            File arq = new File(Paths.get(System.getProperty("user.dir"), "tmp", "medalhistas.csv").toString());
            FileInputStream fi = new FileInputStream(arq.getAbsolutePath());
            ObjectInputStream oi = new ObjectInputStream(fi);
            data = (HashMap<Medalhista, Medalha>) oi.readObject();
            oi.close();
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return data;
        }
    }
}

public class Aplicacao implements Serializable {

    private static MedalhistasDAO medalhistasDAO = new MedalhistasDAO();

    public static void main(String[] args) {
        HashMap<Medalhista, Medalha> data = medalhistasDAO.leitura();
    }
}