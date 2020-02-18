package model.logic;

import model.data_structures.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo<T> {// ,S extends Comparable<S>
	/**
	 * Atributos del modelo del mundo
	 */
	// private IArregloDinamico<S> arregloD;
	private IStack<T> stackComparendos;
	private IQueue<T> colaComparendos;
	private IListaEncadenada<T> listaComparendos;
	private Nodo<T> nodoComparendo;
	private int tamanio;
	private Features mayorObj;
	private Double[] MinMax;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo() {
		// arregloD = null;
		tamanio = 0;
		mayorObj = null;
	}

	public Modelo(List<T> listaFeatures) {
		cargarComparendos(listaFeatures);
		objConMayorOBJID();
		MiniMax();
	}

	public void cargarComparendos(List<T> listaFeatures) {
		try {
			long startTime = System.nanoTime();
			Nodo<Features> primero = new Nodo<Features>(null, (Features) listaFeatures.get(0));
			Nodo<Features> ultimo = new Nodo<Features>(null, (Features) listaFeatures.get(listaFeatures.size() - 1));
			listaFeatures.remove(0);
			colaComparendos = (IQueue<T>) new Queue<T>((Nodo<T>) primero);
			stackComparendos = (IStack<T>) new Stack<T>((Nodo<T>) primero);
			listaComparendos = (IListaEncadenada<T>) new ListaEncadenada<T>((Nodo<T>) primero);
			listaFeatures.forEach(feature -> {
				nodoComparendo = new Nodo<T>(null, (T) feature);
				colaComparendos.enqueue((T) nodoComparendo);
				stackComparendos.push((T) nodoComparendo);
				listaComparendos.AppendNode(nodoComparendo);
			});
			long endTime = System.nanoTime();
			long elapsedTime = endTime - startTime;
			double convertET = (double) elapsedTime / 1000000000;
			tamanio = stackComparendos.getSize();
			System.out.println("tamaño de la queue: " + colaComparendos.size());
			System.out.println("tamaño del stack: " + stackComparendos.getSize());
			System.out.println("tamaño de la lista: " + listaComparendos.getTamanio());
			System.out.println(
					"Datos cargados en estructuras -----// \n\tTime elapsed loading data: " + convertET + " seconds");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void objConMayorOBJID() {
		Features obj = null;
		Nodo<Features> evaluado = (Nodo<Features>) listaComparendos.getPrimerNodo();
		int mayor = 0;
		while (evaluado != null) {
			int comp = evaluado.getInfo().getProperties().getOBJECTID();
			if (comp > mayor) {
				obj = evaluado.getInfo();
				mayor = comp;
			}
			evaluado = (Nodo<Features>) evaluado.getSiguiente();
		}
		mayorObj = obj;
	}

	public void MiniMax() {
		Nodo<Features> evaluado = (Nodo<Features>) listaComparendos.getPrimerNodo();
		double HiLat = 0;
		double HiLong = 0;
		double LoLat = 0;
		double LoLong = 0;
		while (evaluado != null) {
			double compLat = evaluado.getInfo().getGeometry().DarCoordenadas().get(0);
			double compLong = evaluado.getInfo().getGeometry().DarCoordenadas().get(1);
			if (compLat > HiLat) HiLat = compLat; 
			else if (compLat < LoLat) LoLat=compLat;
			if (compLong > HiLong) HiLong = compLong;
			else if (compLong < LoLong ) LoLong = compLong; 
			evaluado = (Nodo<Features>) evaluado.getSiguiente();
		}
		Double[] coor = { HiLat, HiLong, LoLat, LoLong };
		MinMax = coor;
	}
	public String getPrimerComparendoInfrac(String pInfraccion) {
		return null;
	}
	public String getListaComparendosInfrac(String pInfraccion, String pFecha) {
		return null;
	}
	public void compareNumInfraccionesTipoSevicio(String pInfraccion) {}
	public void mostrarComparendosLocalidadFecha(String Localidad, String startDate,String endDate) {}
	public void consultarNumInfraccionesMasComparendosPorTiempo(int Num,String startDate,String endDate ) {}
	public void generarHistograma() {}
	public void primerComparendoPorLocalidad(String localidad) {}
	public void getComparendosPorFechaOrdenados(String pFecha) {}
	public void compareComparendosPorCodigoFecha(String pCodigo, String pFecha) {}
	public Features getMayorOBJ() {
		return mayorObj;
	}

	public Double[] getMinMax() {
		return MinMax;
	}
}
