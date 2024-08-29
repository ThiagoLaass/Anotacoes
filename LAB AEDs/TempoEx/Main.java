package TempoEx;

public class Main {
    static Long opRealizadas = 0L;
    static Long timeLimit = 50000000000L;

    public static void main(String[] args) {
        int[] vetor = new int[250000];

        long startTime = System.nanoTime();
        codigoA(vetor.length);
        long endTime = System.nanoTime();

        if (!ultrapassou(startTime, endTime)) {
            System.out.println("Tempo de execucao em nanosegundos da funcao codigoA(): " 
                    + (endTime - startTime) + "\n"
                    + opRealizadas + " Operacoes Realizadas");
        } else
            System.out.println("Tempo expirado");

        opRealizadas = 0L;
        startTime = System.nanoTime();
        codigoB(vetor);
        endTime = System.nanoTime();

        if (!ultrapassou(startTime, endTime)) {
            System.out.println("\nTempo de execucao em nanosegundos da funcao codigoB(): " 
                    + (endTime - startTime) + "\n"
                    + opRealizadas + " Operacoes Realizadas");
        } else
            System.out.println("Tempo expirado");
    }

    public static boolean ultrapassou(long startTime, long endTime) {
        return endTime - startTime >= timeLimit;
    }

    public static int codigoA(int n) {
        int b = 0;
        for (int i = 0; i <= n; i += 2) {
            b += 3;
            opRealizadas++;
        }
        return b;
    }

    public static void codigoB(int[] vet) {
        for (int i = 0; i < vet.length; i += 2) {
            for (int j = i; j < (vet.length / 2); j++) {
                vet[i] += vet[j];
                opRealizadas++;
            }
        }
    }
}