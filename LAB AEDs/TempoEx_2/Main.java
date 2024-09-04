package TempoEx_2;

class Main {
    static long n = 7500000;
    static int num = 100000;
    static int tamanho;
    static long startTime;
    static long endTime;
    static int comparacoes;

    public static void main(String[] args) {
        while (n <= 2000000000) {
            tamanho = (int) n;
            int[] vetor = new int[tamanho];

            for (int i = 0; i < n; i++) {
                vetor[i] = i;
            }

            startTime = System.nanoTime();
            pesquisaSequencial(vetor, num);
            endTime = System.nanoTime();

            System.out.println("Pesquisa sequencial em n = " + n);
            System.out.println(comparacoes + " Comparacoes realizadas\n"
                    + (endTime - startTime) + " Em nanosegundos de execucao\n");
            System.out.println("----------------------------------------------------------------------");
            n *= 2;
        }

        while (n <= 2000000000) {
            comparacoes = 0;
            tamanho = (int) n;
            int[] vetor = new int[tamanho];

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
        int comparacoes = 0;
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

        int comparacoes = 0;
        int dir = (vet.length - 1), esq = 0, meio;

        while (esq <= dir) {
            meio = (esq + dir) / 2;
            if (x == vet[meio]) {
                esq = dir + 1;
                comparacoes++;
                return comparacoes;
            } else if (x > vet[meio]) {
                esq = meio + 1;
                comparacoes++;
            } else {
                dir = meio - 1;
            }
            comparacoes++;
        }
        return comparacoes;
    }
}