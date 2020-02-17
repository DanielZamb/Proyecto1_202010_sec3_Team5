package model.data_structures;

public class Queue<T> extends ListaEncadenada implements IQueue{

    private Nodo<T> first;
    private Nodo<T> last;
    private int tamanio;

    /**
     * la vaina acá es que toca usar los nodos de la super clase al reves<br>
     * es decir, el "ultimoNodo" de la super clase es el nodo que se añadio<br>
     * primero a la Cola en terminos de tiempo.  Porque? porque la super hace un append por la izquierda.
     * @param primero primer nodo de tipo T que se añade a la cola.
     */
    public Queue(Nodo<T> primero)
    {
        super(primero);
        first = (Nodo<T>) super.getUltimoNodo();
        last = (Nodo<T>) super.getPrimerNodo();
        tamanio = super.getTamanio();
    }

    public void enqueue(Object nodo) {
        if (first==null)
        {
            super.AppendNode((Nodo<T>)nodo);
            super.setUltimoNodo((Nodo<T>)nodo);
            first = (Nodo<T>)super.getUltimoNodo();
            last = (Nodo<T>)super.getPrimerNodo();
            tamanio = super.getTamanio();
        }
        else {
            super.AppendNode((Nodo<T>)nodo);
            last = (Nodo<T>)super.getPrimerNodo();
        }
        tamanio = super.getTamanio();
    }

    public Nodo<T> dequeue ()
    {
        Nodo<T> respuesta = first;
        if(first == last){
            super.setPrimerNodo(null);
            super.setUltimoNodo(null);
            super.setPrimeroAntiguo(null);
            first = (Nodo<T>) super.getUltimoNodo();
            last = (Nodo<T>) super.getPrimerNodo();
            super.setTamanio(0);
            tamanio = super.getTamanio();
         return respuesta;
        }
        else {
        super.deleteNode(super.getTamanio()-1);
        first = (Nodo<T>) super.getUltimoNodo();
        tamanio = super.getTamanio();
        return respuesta;}
    }

    public boolean isEmpty()
    {
        boolean respuesta = false;
        tamanio = getTamanio();
        if(tamanio==0)
        {
            respuesta = true;
        }
        return respuesta;

    }

    public int size()
    {
        return tamanio;
    }

    public Nodo<T> peek()
    {
        return first;
    }

    public Nodo<T> peekLast()
    {
        return last;
    }





}
