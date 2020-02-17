package model.data_structures;

public class Nodo<T> {
    private Nodo<T> siguiente;
    private T info;

    public Nodo(Nodo<T> siguiente, T features){
        this.siguiente = siguiente;
        this.info = features;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public T getInfo() {
        return info;
    }


    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

    public void setInfo(T feature) {
        this.info = feature;
    }
}
