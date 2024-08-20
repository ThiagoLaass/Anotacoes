package Recursivdade;
import java.util.*;

class Palindromo {

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            do {
                String string = scanner.nextLine();
                String[] s = string.split("");
                if (string.equals("FIM")) {
                    System.exit(0);
                }
                if (is_palindromo(string, 0, s.length - 1)) {
                    System.out.println("SIM");
                } else {
                    System.out.println("NAO");
                }
            } while (true);
        } finally {
        }
    }

    public static boolean is_palindromo(String s, int i, int j) {
        if (i >= j) {
            return true;
        }
        if (s.charAt(i) != s.charAt(j)) {
            return false;
        }
        return is_palindromo(s, i + 1, j - 1);
    }

}