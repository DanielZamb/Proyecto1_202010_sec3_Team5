package model.logic;

import model.data_structures.*;
import java.util.List;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo<T,S extends Comparable<S>> {
	/**
	 * Atributos del modelo del mundo
	 */
	private IArregloDinamico<S> arregloD;
	private IStack<T> stackComparendos;
	private IQueue<T> colaComparendos;
	private IListaEncadenada<T> listaComparendos;
	private Nodo<T> nodoComparendo;
	private int tamanio; 
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		arregloD = null;
		tamanio = 0;
	}
	
	public Modelo(List<T> listaFeatures)
	{
		cargarComparendos(listaFeatures);
	}
	public void cargarComparendos(List<T> listaFeatures){
		try {
			Nodo<Features> primero = new Nodo<Features>(null, (Features) listaFeatures.get(0));
			Nodo<Features> ultimo = new Nodo<Features>(null,(Features)listaFeatures.get(listaFeatures.size()-1));
			listaFeatures.remove(0);
			colaComparendos = (IQueue<T>)new Queue<T>((Nodo<T>) primero);
			stackComparendos =(IStack<T>)new Stack<T>((Nodo<T>) primero);
			listaComparendos = (IListaEncadenada<T>)new ListaEncadenada<T>((Nodo<T>)primero);
			listaFeatures.forEach(feature -> {
				nodoComparendo = new Nodo<T>(null,(T) feature);
				colaComparendos.enqueue((T)nodoComparendo);
				stackComparendos.push((T)nodoComparendo);
				listaComparendos.AppendNode(nodoComparendo);
			});
			tamanio = stackComparendos.getSize();
		}catch (Exception e){e.printStackTrace();}
	}
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */

}
