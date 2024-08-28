package Recursivdade;
import java.util.Scanner;

public class AlgebraBooleana {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            do {
                String string = scanner.nextLine();
                if (string.equals("FIM")) {
                    System.exit(0);
                }

                String[] partes = string.split(" ");
                int n = Integer.parseInt(partes[0]);
                boolean[] entradas = new boolean[n];
                for (int i = 0; i < n; i++) {
                    entradas[i] = partes[i + 1].equals("1"); 
                }
                String expressao = string.substring(2*n + 2); 

                int resultado = avaliarExpressao(expressao, entradas) ? 1 : 0;
                System.out.println(resultado);

            } while (true);
        } finally {
            scanner.close();
        }
    }

    public static boolean avaliarExpressao(String expressao, boolean[] entradas) {
        expressao = expressao.replaceAll("\\s+", "");

        if (expressao.matches("[AB]\\d+")) { 
            int indice = Integer.parseInt(expressao.substring(1)) - 1;
            return entradas[indice];
        } else if (expressao.startsWith("not")) {
            return !avaliarExpressao(expressao.substring(3), entradas);
        } else if (expressao.startsWith("and")) {
            String[] operandos = expressao.substring(3).split(",");
            return avaliarExpressao(operandos[0], entradas) && avaliarExpressao(operandos[1], entradas);
        } else if (expressao.startsWith("or")) {
            String[] operandos = expressao.substring(2).split(",");
            return avaliarExpressao(operandos[0], entradas) || avaliarExpressao(operandos[1], entradas);
        } 
        return false; 
    }
}