package model.data_structures;

import java.util.Iterator;

public class IteratorLista<T> {
    private String iteratorCS;
    private ListaEncadenada<T> lst;
    private Nodo<T> primero;


    public IteratorLista(ListaEncadenada<T> lst, String iteratorCS, Nodo<T> primero) throws Exception {
        this.lst = lst;
        this.iteratorCS = iteratorCS;
        this.primero = primero;
        Iterator(this.primero);
    }
    public void setIteratorCS(String iteratorCS) {
        this.iteratorCS = iteratorCS;
    }
    public void Iterator(Nodo<T> primero)throws Exception{
        if (lst.getTamanio() > 1){
            if (iteratorCS.equals("iniciar")){
                lst.setPrimerNodo(primero);
                lst.setActual(lst.getPrimerNodo());
            } else if (iteratorCS.equals("finalizar") ){
                lst.setPrimerNodo(lst.getPrimeroAntiguo());
                lst.setActual(null);
            } else {
                throw new Exception("digite un secuencia de control valida \"iniciar\"/\"finalizar\"");
            }
        }
        else {
            throw new Exception("La lista solo contiene 1 nodo");
        }
    }
    public void IteratorNext()throws Exception{
        if (lst.consultarActual() != null){
            lst.setActual((Nodo<T>) lst.consultarActual().getSiguiente());
        }
        else {
            throw new Exception("debes incializar el iterador");
        }
    }
    public void IteratorPrevious()throws Exception{
        if (lst.consultarActual() != null){
            Nodo<T> evaluado = lst.getPrimerNodo();
            boolean cent = false;
            while (evaluado != null && !cent){
                if (evaluado.getSiguiente().equals(lst.consultarActual())) {
                    lst.setActual(evaluado);
                    cent = true;
                }
                else evaluado = (Nodo<T>) evaluado.getSiguiente();
            }
        }
        else {
            throw new Exception("debes incializar el iterador");
        }
    }
}
