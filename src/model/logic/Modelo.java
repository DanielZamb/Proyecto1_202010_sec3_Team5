package model.logic;

import model.data_structures.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Definicion del modelo del mundo
 */
public class Modelo<T, S extends Comparable<S>> {
    /**
     * Atributos del modelo del mundo
     */
    private IArregloDinamico<S> arregloD;
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
            Features item1 = (Features) listaFeatures.get(0);
            listaFeatures.remove(0);
            arregloD = (IArregloDinamico<S>) new ArregloDinamico<S>(listaFeatures.size());
            arregloD.agregar((S) item1);
            colaComparendos = (IQueue<T>) new Queue<T>((Nodo<T>) primero);
            stackComparendos = (IStack<T>) new Stack<T>((Nodo<T>) primero);
            listaComparendos = (IListaEncadenada<T>) new ListaEncadenada<T>((Nodo<T>) primero);
            listaFeatures.forEach(feature -> {
                nodoComparendo = new Nodo<T>(null, (T) feature);
                arregloD.agregar((S) feature);
                colaComparendos.enqueue((T) nodoComparendo);
                stackComparendos.push((T) nodoComparendo);
                listaComparendos.AppendNode(nodoComparendo);
            });
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            double convertET = (double) elapsedTime / 1000000000;
            System.out.println("tama�o de la queue: " + colaComparendos.size());
            System.out.println("tama�o del stack: " + stackComparendos.getSize());
            System.out.println("tama�o de la lista: " + listaComparendos.getTamanio());
            System.out.println("tamanio del arreglo: " + arregloD.darTamano());
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
            else if (compLat < LoLat) LoLat = compLat;
            if (compLong > HiLong) HiLong = compLong;
            else if (compLong < LoLong) LoLong = compLong;
            evaluado = (Nodo<Features>) evaluado.getSiguiente();
        }
        Double[] coor = {HiLat, HiLong, LoLat, LoLong};
        MinMax = coor;
    }

    public String getPrimerComparendoInfrac(String pInfraccion) {
        Nodo<Features> buscado = null;
        Nodo<Features> iterador = (Nodo<Features>) listaComparendos.getPrimerNodo();
        Nodo<Features> ultimo = (Nodo<Features>) listaComparendos.getUltimoNodo();
        Boolean comp1 = iterador.getInfo().getProperties().getINFRACCION().equalsIgnoreCase(pInfraccion);
        Boolean comp2 = ultimo.getInfo().getProperties().getINFRACCION().equalsIgnoreCase(pInfraccion);
        Boolean cent = false;
        if (comp1) return iterador.getInfo().getProperties().toString();
        if (comp2) return ultimo.getInfo().getProperties().toString();
        iterador = iterador.getSiguiente();
        while (iterador.getSiguiente() != null && !cent) {
            if (iterador.getInfo().getProperties().getINFRACCION().equalsIgnoreCase(pInfraccion)) {
                buscado = iterador;
                cent = true;
            }
            iterador = iterador.getSiguiente();
        }
        if (buscado != null) return buscado.getInfo().getProperties().toString();
        else return "No existen comparendos con tal codigo de infraccion.";
    }

    public Features[] getListaComparendosInfrac(String pInfraccion) {
        Nodo<Features> buscado = null;
        Nodo<Features> iter = (Nodo<Features>) listaComparendos.getPrimerNodo();
        ArregloDinamico copia = new ArregloDinamico<Features>(300);
        while (iter != null) {
            if (iter.getInfo().getProperties().getINFRACCION().equalsIgnoreCase(pInfraccion)) {
                copia.agregar(iter.getInfo());
                tamanio++;
            }
            iter = iter.getSiguiente();
        }
        Features[] ordenado = new Features[copia.darTamano()];
        for (int i = 0; i < copia.darTamano(); i++) {
            ordenado[i] = (Features) copia.darElemento(i);
        }
        Merge.sort(ordenado);
        return ordenado;
    }

    public Object[] compareNumInfraccionesTipoSevicio() {
        String[] infracList = new String[300];
        int[] res = new int[600];
        int publico, particular;
        int j = 0;
        int k = 0;
        String infra = "";
        Boolean esta = false;
        Nodo<Features> iter = (Nodo<Features>) listaComparendos.getPrimerNodo();
        while (iter != null) {
            infra = iter.getInfo().getProperties().getINFRACCION();
            if (infracList[0] != null) {
            	esta = false;
                for (int x=0;x<infracList.length&&!esta;x++) {
					if (infracList[x] == null) break;
                	esta = infracList[x].equalsIgnoreCase(infra);

                }
            }
            if (esta) {
                iter = iter.getSiguiente();
            } else {
                infracList[j] = infra;
                publico = 0;
                particular = 0;
                Features[] lista = getListaComparendosInfrac(infra);
                for (int i = 0; i < lista.length; i++) {
                    String tipo = lista[i].getProperties().getTIPO_SERVI();
					if (tipo.equalsIgnoreCase("público")) publico++;
                    if (tipo.equalsIgnoreCase("particular")) particular++;

                }
                res[k] = particular;
                res[k + 1] = publico;
                j++;
                k += 2;
            }
        }
        Object[] comparacion = new Object[2];
        comparacion[0] = infracList;
        comparacion[1] = res;

        return comparacion;
    }

    public void mostrarComparendosLocalidadFecha(String Localidad, String startDate, String endDate) {
    }

    public void consultarNumInfraccionesMasComparendosPorTiempo(int Num, String startDate, String endDate) {
    }

    public void generarHistograma() {
    }

    public void primerComparendoPorLocalidad(String localidad) {
    }

    public void getComparendosPorFechaOrdenados(String pFecha) {
    }

    public void compareComparendosPorCodigoFecha(String pCodigo, String pFecha) {
    }

    public Features getMayorOBJ() {
        return mayorObj;
    }

    public Double[] getMinMax() {
        return MinMax;
    }

    public int getTamanio() {
        return tamanio;
    }

    public Comparable[] copiarComparendos() {
        Features[] nuevo = new Features[arregloD.darTamano()];
        for (int i = 0; i < arregloD.darTamano(); i++) {
            nuevo[i] = (Features) arregloD.darElemento(i);
        }
        return nuevo;
    }
}
