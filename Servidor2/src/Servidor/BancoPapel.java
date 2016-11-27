package Servidor;


public class BancoPapel {
	private boolean hayIngredientes;
	
	
	BancoPapel(boolean ingrediente ){
		
		hayIngredientes = ingrediente;
		
	}
	
	public synchronized void nuevosIngredientes () throws  InterruptedException {
		
		if (!hayIngredientes){
		//Se agrega Papel al banco.
		hayIngredientes = Boolean.TRUE;
		//imprimo por pantalla la accion 
		System.out.println("Vendedor - Se ha agregado papel al banco.");
		//Se registra el evento en la traza de tipo XML.
		//Traza.insertarTraza( Hora.horaActual(), "Vendedor", "Se ha agregado  Papel al banco.");
		//Se notifica a todos los fumadores que se han puesto ingredientes nuevos en el banco.
		
		}
	} 

	public synchronized boolean RecogerIngredientes (int id) throws  InterruptedException {
		if (! hayIngredientes ){
		
			return false;
		}else{
		hayIngredientes = false;
		
		switch(id){
		case 0:
			System.out.println("Fumador(Tabaco) -  Agarro papel del banco.");
			//Se registra el evento en la traza de tipo XML.
			Traza.insertarTraza(Hora.horaActual(), "Fumador(Tabaco)", "Agarro papel del banco.");
		break;
		case 1:
			System.out.println("Fumador(Papel) - Agarro papel del banco.");
			//Se registra el evento en la traza de tipo XML.
			Traza.insertarTraza(Hora.horaActual(), "Fumador(Papel)", "Agarro papel del banco.");
		break;
		case 2:
			System.out.println("Fumador(Fosforo) - Agarro papel del banco.");
			//Se registra el evento en la traza de tipo XML.
			Traza.insertarTraza(Hora.horaActual(), "Fumador(Fosforo)", "Agarro papel del banco");
		break;
	}
		hayIngredientes = Boolean.FALSE;
		return true;
	}
	}
}



