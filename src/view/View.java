package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1.Cargar Datos");
			System.out.println("PARTE 2 --------------------------------------");
			System.out.println("4.Econtrar primer comparendo en los datos por infraccion.");
			System.out.println("5.Lista de comparendos en el archivo por infraccion.");
			System.out.println("6.Tabla de comparacion de todos los comparendos en el archivo por codigo de infraccion.");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
		}
}
