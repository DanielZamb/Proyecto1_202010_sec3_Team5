package controller;

import model.data_structures.*;
import model.logic.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.*;

import model.logic.Comparendos;
import model.logic.Features;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() throws IOException 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();
			int option = lector.nextInt();
			switch(option){
			case 1:
				view.printMessage("--------- \n Cargar comparendos a estructuras : \n-Queue\n-Stack\n-LinkedList\n-ArrayList ");
				view.printMessage("Loading...");
				try {
					Gson gson = new Gson();
					String json = "./data/comparendos_dei_2018_BIG.geojson";
					BufferedReader br;
					br = new BufferedReader(new FileReader(json));
					Comparendos comparendos = gson.fromJson(br, Comparendos.class);
					Modelo<Features,Features> mdl = new Modelo(comparendos.darListaFeatures());
					modelo = mdl;
					br.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				view.printMessage("Datos Cargados y estructuras creadas.");
				view.printMessage("Comparendo conMayor OBJECTID: " + modelo.getMayorOBJ().toString() + "\n---------");
				view.printMessage("Coordenadas de Rectangulo MiniMax :\n");
				view.printMessage("[");
				for (Double i:modelo.getMinMax()) {
					view.printMessage(""+i);
					view.printMessage(",");
				}
				view.printMessage("]");
				break;

			case 2:
				view.printMessage("--------------------------------------\nDigite la localidad a buscar:");
				dato= lector.next();
				view.printMessage("Loading...");
				view.printMessage("Comparendo encontrado en los datos: \n"+modelo.primerComparendoPorLocalidad(dato));
				break;

			case 3:
				view.printMessage("----------------------------------------\nDigite la fecha_hora a buscar");
				dato = lector.next();
				view.printMessage("Loading...");
				view.printMessage(modelo.comparendosPorFecha_hora(dato));
				break;
				
			case 4:
				
				view.printMessage("------------------------------------------\nDigite las dos fechas a comparar ");
				dato = lector.next();
				dato.split(",");
				String fecha1 =
				String fecha2 = 		
			
				Object[]  devolver = modelo.();
				ArregloDinamico<String> listaInfracciones = (ArregloDinamico<String>)devolver[0];
				ArregloDinamico<Integer> number = (ArregloDinamico<Integer>) devolver[1];
				view.printMessage("| INFRACCION | PARTICULAR | PUBLICO |");
				int i=0;
				int j =0;
				for (;i<listaInfracciones.darTamano()&&j<number.darTamano();){
					if (listaInfracciones.darElemento(i)!= null){
						view.printMessage("| "+listaInfracciones.darElemento(i)+" | "+number.darElemento(j)+" | "+number.darElemento(j+1)+" |");
						i++;
						j+=2;
					}
				}
				break;

				

			case 5:
				view.printMessage("--------------------------------------\nDigite el codigo de la infraccion a buscar:");
				dato = lector.next();
				view.printMessage("Loading...");
				view.printMessage("Comparendo encontrado en los datos: \n"+modelo.getPrimerComparendoInfrac(dato));
				break;
			case 6:
				view.printMessage("--------------------------------------\nDigite el codigo de la infraccion a buscar:");
				dato = lector.next();
				view.printMessage("Loading...");
				Features[] arr = modelo.getListaComparendosInfrac(dato);
				for (int i = 0;i<arr.length;i++){
					view.printMessage(arr[i].getProperties().toString());
				}
				view.printMessage("tamanio del arreglo ordenado :"+modelo.getTamanio());
				break;
				case 7:
					String local="",sDate="",eDate="";
					view.printMessage("Digite localidad a analizar: \n");
					local=lector.next();
					view.printMessage("Digite fecha incial: \n");
					sDate = lector.next();
					view.printMessage("Digite fecha final: \n");
					eDate = lector.next();
					modelo.mostrarComparendosLocalidadFecha(local,sDate,eDate);
					view.printMessage("Loading...");
					view.printMessage("Comparación de comparendos en "+local+" desde "+sDate+" hasta "+eDate);
					view.printMessage("INFRACCION | #COMPARENDOS");
					Object[] res_ = modelo.mostrarComparendosLocalidadFecha(local,sDate,eDate);
					ArregloDinamico<String> infracList_ = (ArregloDinamico<String>)res_[0];
					ArregloDinamico<Integer> numC_ = (ArregloDinamico<Integer>) res_[1];
					for (int k =0;k<infracList_.darTamano();k++){
						view.printMessage(infracList_.darElemento(k)+" | "+ numC_.darElemento(k));
					}
					break;
				case 8:
					int N=0;
					String sDate_="",eDate_="";
					view.printMessage("Digite numero de comparendos a analizar: \n");
					N =lector.nextInt();
					view.printMessage("Digite fecha incial: \n");
					sDate_ = lector.next();
					view.printMessage("Digite fecha final: \n");
					eDate_ = lector.next();
					view.printMessage("Loading...");
					Object[] res__ = modelo.NInfraccionesMasComparendosPorTiempo(N,sDate_,eDate_);
					view.printMessage("Ranking de las "+N+" mayores infracciones desde "+sDate_+" hasta "+eDate_);
					view.printMessage("INFRACCION | #COMPARENDOS");
					String[] infracList__ = (String[]) res__[0];
					Integer[] numC__ = (Integer[])res__[1];
					for (int k =0;k<infracList__.length;k++){
						view.printMessage(infracList__[k]+" | "+ numC__[k]);
					}
					break;
				case 9:
					view.printMessage("Loading...");
					Object[] res___  = modelo.generarHistograma();
					ArregloDinamico<String> localilades = (ArregloDinamico<String>) res___[0];
					ArregloDinamico<Integer> numL = (ArregloDinamico<Integer>) res___[1];
					view.printMessage("Aproximación del número de comparendos por localidad.");
					for(int l=0;l<localilades.darTamano();l++){
						Boolean longt = ((String)localilades.darElemento(l)).length()<16;
						String msg = (String) localilades.darElemento(l);
						if (longt){
							msg = String.format("%-16s",localilades.darElemento(l));
						}
						int ast = (int)numL.darElemento(l)/50;
						if (((int)numL.darElemento(l)%50)>1) ast = ast + 1;
						String msgA = "";
						if (ast == 0) msgA ="sinComparendos";
						String format = "%"+Integer.toString(ast)+"*";
						msgA =String.format(format,"");
						view.printMessage(msg+"|"+msgA);
					}
					break;

			}



		}

	}	
}
