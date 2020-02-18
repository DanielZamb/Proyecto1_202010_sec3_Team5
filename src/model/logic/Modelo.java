package model.logic;

import model.data_structures.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo<T> {//,S extends Comparable<S>
	/**
	 * Atributos del modelo del mundo
	 */
	//private IArregloDinamico<S> arregloD;
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
		//arregloD = null;
		tamanio = 0;
	}
	
	public Modelo(List<T> listaFeatures)
	{
		cargarComparendos(listaFeatures);
	}
	public void cargarComparendos(List<T> listaFeatures){
		try {
			long startTime = System.nanoTime();
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
			long endTime = System.nanoTime();
			long elapsedTime = endTime - startTime;
			double convertET = (double) elapsedTime/1000000000 ;
			tamanio = stackComparendos.getSize();
			System.out.println("tamaño de la queue: "+colaComparendos.size());
			System.out.println("tamaño del stack: "+stackComparendos.getSize());
			System.out.println("tamaño de la lista: "+listaComparendos.getTamanio());
			System.out.println("Datos cargados en estructuras ----- \n\tTime elapsed loading data: "+ convertET +"seconds");
		}catch (Exception e){e.printStackTrace();}
	}
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */

}
