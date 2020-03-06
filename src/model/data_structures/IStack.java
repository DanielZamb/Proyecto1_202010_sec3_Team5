package model.data_structures;

public interface IStack<T> {
		 public boolean isEmpty();

	    public void push(T nodo);

	    public int getSize();

	    public T peek();

	    public T pop();
	   
	    public int Buscar (T buscado);
}
