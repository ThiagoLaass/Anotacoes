package Recursivdade;
import java.util.*;

class ContaOcorrencias {
    
        public static void main(String[] args) {
            
            try(Scanner scanner = new Scanner(System.in)) {
                do {
                    String string = scanner.nextLine();
                    String[] s = string.split(";");
                    if (string.equals("FIM")) {
                        System.exit(0);
                    }
                    int[] a = new int[s.length];
                    for (int i = 0; i < s.length; i++) {
                        a[i] = Integer.parseInt(s[i]);
                    }
                    int x = Integer.parseInt(scanner.nextLine());
                    System.out.println(conta_ocorrencias(a, a.length, x, 0));
                } while (true);
            } finally {
            }
        }
    
        public static int conta_ocorrencias(int[] a, int n, int x, int i) {
            if (i == n) {
                return 0;
            }
            if (a[i] == x) {
                return 1 + conta_ocorrencias(a, n, x, i + 1);
            }
            return conta_ocorrencias(a, n, x, i + 1);
        }

}