package TempoEx_2;

class Main {
    static long n = 7500000;
    static int num = -1;
    static int tamanho;
    static long startTime;
    static long endTime;
    static int comparacoes;
    static long limit = 3840000000L;

    public static void main(String[] args) {

        // while (n <= limit) {
        //     tamanho = (int) n;
        //     int[] vetor = new int[tamanho];
        //     comparacoes = 0;

        //     for (int i = 0; i < n; i++) {
        //         vetor[i] = i;
        //     }

        //     startTime = System.nanoTime();
        //     pesquisaSequencial(vetor, num);
        //     endTime = System.nanoTime();

        //     System.out.println("Pesquisa sequencial em n = " + n);
        //     System.out.println(comparacoes + " Comparacoes realizadas\n"
        //             + (endTime - startTime) + " Em nanosegundos de execucao\n");
        //     System.out.println("----------------------------------------------------------------------");
        //     n *= 2;
        // }

        comparacoes = 0;
        while (n <= limit) {
            tamanho = (int) n;
            int[] vetor = new int[tamanho];
            comparacoes = 0;

            for (int i = 0; i < n; i++) {
                vetor[i] = i;
            }

            startTime = System.nanoTime();
            comparacoes = pesquisaBinaria(vetor, num);
            endTime = System.nanoTime();

            System.out.println("Pesquisa binaria em n = " + n);
            System.out.println(comparacoes + " Comparacoes realizadas\n"
                    + (endTime - startTime) + " Em nanosegundos de execucao\n");
            n *= 2;
        }

    }

    public static int pesquisaSequencial(int[] vetor, int num) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == num) {
                comparacoes++;
                return comparacoes;
            }
            comparacoes++;
        }
        return comparacoes;
    }

    public static int pesquisaBinaria(int[] vet, int x) {
        int dir = (vet.length - 1), esq = 0, meio;
        while (esq <= dir) {
            comparacoes++;
            meio = (esq + dir) / 2;
            if (x == vet[meio]) {
                esq = dir + 1;
                return comparacoes;
            } else if (x > vet[meio]) {
                esq = meio + 1;
            } else {
                dir = meio - 1;
            }
        }
        return comparacoes;
    }
}