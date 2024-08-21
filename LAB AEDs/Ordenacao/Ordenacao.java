import java.util.Scanner;


class Ordenacao {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String entrada;
                entrada = scanner.nextLine();
                if (entrada.equals("FIM")) {
                    System.exit(0);
                }
                String vet[] = entrada.split(";");
                int[] vint = new int[vet.length];
                for (int i = 0; i < vet.length; i++) {
                    vint[i] = Integer.parseInt(vet[i]);
                }

                // for(int i = 0; i< vint.length; i++){
                //     System.out.print(vint[i] + ' ');
                // }
                
                for(int i =0 ; i < vint.length; i++){
                    System.out.print(vint[i] + ' ');
                }
                System.out.println(comparator(vint));
            }
        }
    }

    public static int comparator(int[] vint) {
        int oprealizadas = 0;
        for(int i = 0; i < vint.length - 1; i++){
            int menor = i;
            for(int j = i + 1; j <vint.length;  j++){
                if(vint[menor] > vint[i]){
                    menor = j;
                }
                oprealizadas++;
            }
            swap(menor, i, vint);
        }
        return oprealizadas;
    }

    public static void swap(int menor, int i, int[] vint){
        int aux = vint[menor];
        vint[menor] = vint[i];
        vint[i] = aux;
    }
}
