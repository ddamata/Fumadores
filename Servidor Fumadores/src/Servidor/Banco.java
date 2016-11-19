package servidor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Banco {
	private boolean fumando;
	private boolean hayIngredientes;
	private int ingredientes;
	private Traza t;
	
	Banco(){
		fumando = false;
		hayIngredientes = true;
		//Se asigna inicialmente al banco papel y fosforo.
		ingredientes=0;
		t= new Traza();
	}
	
	//M�todo que devuelve la hora actual del servidor.
	public static String horaActual(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	//M�todo que agrega ingredientes al banco.
	public synchronized void nuevosIngredientes (int ingredienteNuevo) throws  InterruptedException {
		//Bucle infinito que detiene al vendedor de poner mas ingredientes en el banco si algun fumador est� fumando o el banco ya tiene ingredientes.
		while(hayIngredientes || fumando){
			wait();
		}
		//Se asigna el n�mero de ingrediente aleatorio seleccionado por el vendedor al banco.
		ingredientes = ingredienteNuevo;
		//Se especifica que el banco se encuentra lleno.
		hayIngredientes = true;
		//El n�mero aleatorio indica que ingrediente NO va a ser a�adido al banco.
		switch(ingredienteNuevo){
			case 0:
				System.out.println("Vendedor - Se ha agregado papel y fosforo al banco.");
				//Se registra el evento en la traza de tipo XML.
				t.insertarTraza(horaActual(), "Vendedor", "Agrego papel y fosforo al banco.");
			break;
			case 1:
				System.out.println("Vendedor - Se ha agregado tabaco y fosforo al banco.");
				//Se registra el evento en la traza de tipo XML.
				t.insertarTraza(horaActual(), "Vendedor", "Agrego tabaco y fosforo al banco.");
			break;
			case 2:
				System.out.println("Vendedor - Se ha agregado tabaco y papel al banco.");
				//Se registra el evento en la traza de tipo XML.
				t.insertarTraza(horaActual(), "Vendedor", "Agrego tabaco y papel al banco.");
			break;
		}
		//Se notifica a todos los fumadores que se han puesto ingredientes nuevos en el banco.
		notifyAll();
	} 
	
	//Metodo que permite fumar a los fumadores.
	public synchronized void fumar (int idUsuario)  throws  InterruptedException {
		//Bucle infinito que detiene a los fumadores de fumar si no hay ingredientes en el banco, si no existe otro fumador fumando o si los ingredientes en el banco no corresponden a su necesidad.
		while (!hayIngredientes || fumando || ingredientes != idUsuario){
			wait();
		}
		
		switch(idUsuario){
			case 0:
				System.out.println("Fumador(Tabaco) - Ha recogido papel y f�sforo del banco.");
				//Se registra el evento en la traza de tipo XML.
				t.insertarTraza(horaActual(), "Fumador(Tabaco)","Recogio papel y fosforo del banco.");
				System.out.println("Fumador(Tabaco) - Fuma.");
				//Se registra el evento en la traza de tipo XML.
				t.insertarTraza(horaActual(), "Fumador(Tabaco)","Fuma");
			break;
			case 1:
				System.out.println("Fumador(Papel) - Ha recogido tabaco y fosforo del banco.");
				//Se registra el evento en la traza de tipo XML.
				t.insertarTraza(horaActual(), "Fumador(Papel)", "Recogio tabaco y fosforo del banco.");
				System.out.println("Fumador(Papel) - Fuma.");
				//Se registra el evento en la traza de tipo XML.
				t.insertarTraza(horaActual(), "Fumador(Papel)","Fuma");
			break;
			case 2:
				System.out.println("Fumador(Fosforo) - Ha recogido tabaco y papel del banco.");
				//Se registra el evento en la traza de tipo XML.
				t.insertarTraza(horaActual(), "Fumador(Fosforo)","Recogio tabaco y papel del banco.");
				System.out.println("Fumador(Fosforo) - Fuma.");
				//Se registra el evento en la traza de tipo XML.
				t.insertarTraza(horaActual(), "Fumador(Fosforo)","Fuma");
			break;
		}
		//Se indica que el banco qued� vac�o.
		hayIngredientes = false;
		//Se indica que existe un fumador fumando.
		fumando= true;
	}
	
	
	public synchronized void dejarFumar (int idUsuario) {
		//Se indica que el fumador ha dejado de fumar.
		fumando = false;
		switch(idUsuario){
		case 0:
			System.out.println("Fumador(Tabaco) - Termina de fumar.");
			//Se registra el evento en la traza de tipo XML.
			t.insertarTraza(horaActual(), "Fumador(Tabaco)","Dejo de fumar");
			break;
		case 1:
			System.out.println("Fumador(Papel) - Termina de fumar.");
			//Se registra el evento en la traza de tipo XML.
			t.insertarTraza(horaActual(), "Fumador(Papel)","Dejo de fumar");
			break;
		case 2: 
			System.out.println("Fumador(Fosforo) - Termina de fumar.");
			//Se registra el evento en la traza de tipo XML.
			t.insertarTraza(horaActual(), "Fumador(Fosforo)","Dejo de fumar");
			break;
		}
		//Se notifica a los dem�s fumadores y al vendedor que el fumador ha dejado de fumar.
		notifyAll();
	}

}
