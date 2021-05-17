package br.com.iotruck.bino.extensions;

public class FilaObj<T> {

    private int tamanho;
    private T[] fila;

    public FilaObj(int capacidade) {
        this.tamanho = 0;
        this.fila = (T[]) new Object[capacidade];
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(T info) {
        if (isFull()) {
            System.out.println("Fila cheia");
        } else {
            fila[tamanho++] = info;
        }
    }

    public T peek() {
        return fila[0];
    }

    public T poll() {
        T primeiro = peek();

        if (!isFull()) {
            for (int i = 0; i < tamanho; i++) {
                fila[i] = fila[i + 1];
            }
        }
        fila[tamanho - 1] = null;
        tamanho--;
        return primeiro;
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Fila vazia");
        } else {
            System.out.println("\n Elementos da fila");
            for (int i = 0; i < tamanho; i++) {
                System.out.println(fila[i]);
            }
        }
    }
}
