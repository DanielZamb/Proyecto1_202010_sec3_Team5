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
				case 4:
					view.printMessage("--------------------------------------\nDigite el codigo de la infraccion a buscar:");
					dato = lector.next();
					view.printMessage("Loading...");
					view.printMessage("Comparendo encontrado en los datos: \n"+modelo.getPrimerComparendoInfrac(dato));
					break;
				case 5:
					view.printMessage("--------------------------------------\nDigite el codigo de la infraccion a buscar:");
					dato = lector.next();
					view.printMessage("Loading...");
					Features[] arr = modelo.getListaComparendosInfrac(dato);
					for (int i = 0;i<arr.length;i++){
						view.printMessage(arr[i].getProperties().toString());
					}
					view.printMessage("tamanio del arreglo ordenado :"+modelo.getTamanio());
					break;
				case 6:
					view.printMessage("--------------------------------------");
					view.printMessage("Loading...");
					Object[] res = modelo.compareNumInfraccionesTipoSevicio();
					ArregloDinamico<String> infracList = (ArregloDinamico<String>)res[0];
					ArregloDinamico<Integer> numC = (ArregloDinamico<Integer>) res[1];
					view.printMessage("| INFRACCION | PARTICULAR | PUBLICO |");
					int i=0;
					int j =0;
					for (;i<infracList.darTamano()&&j<numC.darTamano();){
						if (infracList.darElemento(i)!= null){
						view.printMessage("| "+infracList.darElemento(i)+" | "+numC.darElemento(j)+" | "+numC.darElemento(j+1)+" |");
						i++;
						j+=2;
						}
					}
					break;

				}

		}
		
	}	
}
