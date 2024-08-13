import java.util.*;

class ContaOcorrencias {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String string = scanner.nextLine();
            int x = scanner.nextInt();

            if(string.equals("FIM")){
                System.exit(0);
            }

            String[] vetors = string.split(";");

            int[] vetor = new int[vetors.length];
            for (int i = 0; i < vetors.length; i++) {
                vetor[i] = Integer.parseInt(vetors[i]);
            }
            int contador = contaOcorrencias(vetor, x, vetor.length - 1, 0);
            System.out.println(contador);
        }
    }

    public static int contaOcorrencias(int[] vetor, int x, int index, int contador){
        if(index < 0){
            return contador;
        }
        else if(vetor[index] == x){
            return contaOcorrencias(vetor, x, index - 1, contador++);
        }
        return 0;
    }
}