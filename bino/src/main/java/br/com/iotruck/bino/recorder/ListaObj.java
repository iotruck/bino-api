package br.com.iotruck.bino.recorder;

public class ListaObj<T> {

    //Atributos
    private T[] vetor;
    private int nmrElemento;

    //Construtor
    public ListaObj(int tamanho) {
        this.vetor = (T[]) new Object[tamanho];
        this.nmrElemento = 0;
    }

    //Metodos adicionar e exibir
    public Boolean adicionar(T valor) {
        if(nmrElemento >= vetor.length) {
            return false;
        } else {
            vetor[nmrElemento++] = valor;
            return true;
        }
    }

    public void exibir() {
        for (int i =0; i < nmrElemento; i++) {
            System.out.println(vetor[i]);
        }
    }

    //Metodos de busca, por índice ou objeto
    public int buscar(T valor) {
        for (int i=0; i < nmrElemento; i++) {
            if (vetor[i].equals(valor)) {
                return i;
            }
        }
        return -1;
    }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= nmrElemento) {
            return null;
        }
        else {
            return vetor[indice];
        }
    }

    // Métodos de remover
    public boolean removePeloIndice(int indice) {
        if (indice < 0 || indice >= nmrElemento) {
            return false;
        }
        else {
            for (int i=indice; i < nmrElemento-1; i++) {
                vetor[i] = vetor[i+1];
            }
            nmrElemento--;
            return true;
        }
    }

    public boolean removeElemento(T valor) {
        return removePeloIndice(buscar(valor));
    }

    public void limpar() { nmrElemento = 0;}

    // Método de alterar
    public boolean substituiPeloIndice(int indice, T edicaoAgenciavel) {
        if (indice < 0 || indice >= nmrElemento) {
            System.out.println("índice inválido");
            return false;
        }
        else {
            vetor[indice] = edicaoAgenciavel;
            System.out.println("Agenciável editado com sucesso");
            return true;
        }
    }

    //Get NmrElemento
    public int getNmrElemento() {
        return nmrElemento;
    }
}
