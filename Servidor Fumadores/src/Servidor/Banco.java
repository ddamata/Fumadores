package servidor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Banco {
	private boolean fumando = false;
	private boolean hayIngredientes = false;
	private int ingredientes;
	private Traza t= new Traza();
	
	public static String horaActual(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public synchronized void nuevosIngredientes (int ingredienteNuevo) throws  InterruptedException {
		while(hayIngredientes || fumando){
			wait();
		}
		this.ingredientes = ingredienteNuevo;
		hayIngredientes = true;
		switch(ingredienteNuevo){
			case 0:
				System.out.println("Vendedor - Se ha agregado papel y fosforo al banco.");
				t.insertarTraza(horaActual(), "Vendedor", "Agrego papel y fosforo al banco.");
			break;
			case 1:
				System.out.println("Vendedor - Se ha agregado tabaco y fosforo al banco.");
				t.insertarTraza(horaActual(), "Vendedor", "Agrego tabaco y fosforo al banco.");
			break;
			case 2:
				System.out.println("Vendedor - Se ha agregado tabaco y papel al banco.");
				t.insertarTraza(horaActual(), "Vendedor", "Agrego tabaco y papel al banco.");
			break;
		}
		notifyAll();
	} 
	
	
	public synchronized void fumar (int id)  throws  InterruptedException {
		while (!hayIngredientes || fumando ||ingredientes != id){
			wait();
		}
		switch(id){
			case 0:
				System.out.println("Fumador "+id+" - Ha recogido papel y f�sforo del banco.");
				t.insertarTraza(horaActual(), "Fumador "+ Integer.toString(id), "Recogio papel y fosforo del banco.");
				System.out.println("Fumador "+id+" - Fuma.");
				t.insertarTraza(horaActual(), "Fumador "+ Integer.toString(id), "Fuma");
			break;
			case 1:
				System.out.println("Fumador "+id+" - Ha recogido tabaco y fosforo del banco.");
				t.insertarTraza(horaActual(), "Fumador "+ Integer.toString(id), "Recogio tabaco y fosforo del banco.");
				System.out.println("Fumador "+id+" - Fuma.");
				t.insertarTraza(horaActual(), "Fumador "+ Integer.toString(id), "Fuma");
			break;
			case 2:
				System.out.println("Fumador "+id+" - Ha recogido tabaco y papel del banco.");
				t.insertarTraza(horaActual(), "Fumador "+ Integer.toString(id), "Recogio tabaco y papel del banco.");
				System.out.println("Fumador "+id+" - Fuma.");
				t.insertarTraza(horaActual(), "Fumador "+ Integer.toString(id), "Fuma");
			break;
		}
		hayIngredientes = false;
		fumando= true;
	}
	
	
	public synchronized void dejarFumar (int id) {
		fumando = false;
		System.out.println("Fumador "+id+" - Termina de fumar.");
		t.insertarTraza(horaActual(), "Fumador "+ Integer.toString(id), "Dejo de fumar");
		notifyAll();
	}

}
