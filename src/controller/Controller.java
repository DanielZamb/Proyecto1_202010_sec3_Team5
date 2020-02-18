package controller;

import model.*;

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
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
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
				view.printMessage("--------- \n Cargar comparendos a estructuras : \n-Queue\n-Stack\n-LinkedList ");
				view.printMessage("Loading...");
				try {
					Gson gson = new Gson();
					String json = "./data/comparendos_dei_2018_BIG.geojson";
					BufferedReader br;
					br = new BufferedReader(new FileReader(json));
					Comparendos comparendos = gson.fromJson(br, Comparendos.class);
					Modelo<Features> mdl = new Modelo(comparendos.darListaFeatures());
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
				}
		}
		
	}	
}
