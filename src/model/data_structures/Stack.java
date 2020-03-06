package model.data_structures;

public class Stack<T> extends ListaEncadenada implements IStack {
    private Nodo<T> top;
    private int tamanio;

    public Stack(Nodo<T> primerNodo){
        super(primerNodo);
        top = (Nodo<T>) super.getPrimerNodo();
        tamanio = super.getTamanio();

    }
    public boolean isEmpty()
    {
        boolean respuesta = false;
        if (tamanio==0)
        {
            respuesta = true;
        }
        return respuesta;
    }

    public void push(Object nodo)
    {
        super.AppendNode((Nodo<T>)nodo);
        top = (Nodo<T>)super.getPrimerNodo();
        tamanio = super.getTamanio();

    }

    public int getSize(){
        tamanio = super.getTamanio();
        return tamanio;
    }

    public Nodo<T> peek() {
        return top;
    }

    public Nodo<T> pop()
    {   Nodo<T> rta = top;
        top = (Nodo<T>) top.getSiguiente();
        super.deleteNode(0);
        tamanio = super.getTamanio();
        return rta;
    }
    /**
     * Busca un nodo y cuenta a cuantas posiciones esta del principio de la pila
     * @param buscado
     * @return el numero de posiciones a las que se encuentra el nodo del tope de la pila
     */
    public int Buscar (Object buscado) {

        int respuesta = 0;
        Nodo<T> actual = top;
        boolean done = false;

        while (actual != null && !done)
        {
            if (actual.equals((Nodo<T>)buscado)) {
                done=true;
            }
            else respuesta++;
            actual = (Nodo<T>) actual.getSiguiente();
        }
        return respuesta;

    }
}