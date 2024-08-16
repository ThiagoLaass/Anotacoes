import java.util.Scanner;

// Crie um método recursivo que receba uma string contendo uma expressão booleana e os valores de suas entradas e retorne um número inteiro indicando se a expressão é verdadeira ou falsa.
// Cada string da entrada padrão é composta por um número inteiro n,que indica o número de entradas da expressão booleana corrente.Em seguida,a string contém n valores binários(um para cada entrada)e a expressão booleana.
// Na saída padrão,para cada linha de entrada,escreva uma linha de saída com 1 ou 0,indicando se a expressão corrente é verdadeira ou falsa.
// entrada                    saida
// 2 0 0 and(not(A) , not(B))  1
// 2 0 1 and(not(A) , not(B))  0

public class AlgebraBooleana {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            do {
                String string = scanner.nextLine();
                String[] s = string.split(" ");
                if (string.equals("FIM")) {
                    System.exit(0);
                }
                int n = Integer.parseInt(s[0]);
                int[] a = new int[n];
                for (int i = 0; i < n; i++) {
                    a[i] = Integer.parseInt(s[i + 1]);
                }
                System.out.println(algebra_booleana(a, n, n - 1));
            } while (true);
        } finally {
            scanner.close();
        }
    }

    public static int algebra_booleana(int[] a, int n, int i) {
        if (i == 0) {
            return a[0];
        }
        if (a[i] == 0) {
            return algebra_booleana(a, n, i - 1);
        }
        if (a[i] == 1) {
            return algebra_booleana(a, n, i - 1);
        }
        if (a[i] == 2) {
            return algebra_booleana(a, n, i - 1) & algebra_booleana(a, n, i - 2);
        }
        if (a[i] == 3) {
            return algebra_booleana(a, n, i - 1) | algebra_booleana(a, n, i - 2);
        }
        if (a[i] == 4) {
            return algebra_booleana(a, n, i - 1) ^ algebra_booleana(a, n, i - 2);
        }
        if (a[i] == 5) {
            return algebra_booleana(a, n, i - 1) ^ algebra_booleana(a, n, i - 2);
        }
        return 0;
    }
   
}
