
import java.util.Scanner;

public class Evergreen {
    private static String[] entradas = new String[2];

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
                entradas[0] = scanner.nextLine();
                if (entradas[0].equals("FIM")) {
                    break;
                }
                entradas[1] = scanner.nextLine();
                String nomeDesembaralhado = desembaralharNome(entradas[0], entradas[1]);
                System.out.println(nomeDesembaralhado);
            } 
        }

    private static String desembaralharNome(String parte1, String parte2) {
        StringBuilder nomeDesembaralhado = new StringBuilder();

        int length = Math.min(parte1.length(), parte2.length());

        for (int i = 0; i < length; i += 2) {
            if (i + 2 <= parte1.length()) {
                nomeDesembaralhado.append(parte1, i, i + 2);
            } else {
                nomeDesembaralhado.append(parte1.substring(i));
            }
            if (i + 2 <= parte2.length()) {
                nomeDesembaralhado.append(parte2, i, i + 2);
            } else {
                nomeDesembaralhado.append(parte2.substring(i));
            }
        }

        if (parte1.length() > length) {
            nomeDesembaralhado.append(parte1.substring(length));
        }
        if (parte2.length() > length) {
            nomeDesembaralhado.append(parte2.substring(length));
        }

        return nomeDesembaralhado.toString();
    }
}