package model.data_structures;

public interface IListaEncadenada<T> {
	public void Iterador(String ControlS, Nodo<T> primeroPersonal) throws Exception;

	public Nodo<T> consultarActual();

	public void setActual(Nodo<T> actual);

	public int getTamanio();

	public Nodo<T> getPrimerNodo();

	public Nodo<T> getUltimoNodo();

	public void setPrimerNodo(Nodo<T> primerNodo);

	public void setTamanio(int tamanio);

	public void setPrimeroAntiguo(Nodo<T> primeroAntiguo);

	public void setUltimoNodo(Nodo<T> ultimoNodo);

	public Nodo<T> getPrimeroAntiguo();

	@Deprecated
	public void darTamanioLista();

	public void AppendNode(Nodo<T> nuevo);

	public void appendNodeInPos(Nodo<T> nuevo, int pos);

	public Nodo<T> ConsultarNodo(int pos);

	public void deleteNode(int pos);
}
