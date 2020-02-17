package model.data_structures;

public interface IQueue<T> {
	public void enqueue(T nodo);

	public T dequeue();

	public boolean isEmpty();

	public int size();

	public T peek();

	public T peekLast();

}
