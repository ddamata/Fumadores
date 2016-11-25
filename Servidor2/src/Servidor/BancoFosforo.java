package Servidor;


public class BancoFosforo {
	private boolean hayIngredientes;

	
	BancoFosforo( boolean ingrediente ){
		
		hayIngredientes = ingrediente;
		
		
	}
	
	public synchronized void nuevosIngredientes () throws  InterruptedException {

		if (!hayIngredientes){
		//Se agrega fosforos al banco.
		hayIngredientes = Boolean.TRUE;
		//imprimo por pantalla la accion 
		System.out.println("Vendedor - Se ha agregado  fosforo al banco.");
		//Se registra el evento en la traza de tipo XML.
		//Traza.insertarTraza( Hora.horaActual(), "Vendedor", "Se ha agregado  fosforo al banco.");
		}
	} 

	public synchronized boolean RecogerIngredientes (int id) throws  InterruptedException {
		if (! hayIngredientes ){
		
			return false;
		}else {
		hayIngredientes =Boolean.FALSE;
		
		switch(id){
		case 0:
			System.out.println("Fuamdor (Tabaco) -  ha agarrado Fosforos del banco.");
			//Se registra el evento en la traza de tipo XML.
			//Traza.insertarTraza(Hora.horaActual(), "Fuamdor 0", "ha agarrado Fosforos del banco.");
		break;
		case 1:
			System.out.println("Fumador (Papel)- ha agarrado Fosforos del banco.");
			//Se registra el evento en la traza de tipo XML.
			//Traza.insertarTraza(Hora.horaActual(), "Fumador 1", "ha agarrado Fosforos del banco.");
		break;
		case 2:
			System.out.println("Fumador (Fosforo)- ha agarrado Fosforos del banco.");
			//Se registra el evento en la traza de tipo XML.
			//Traza.insertarTraza(Hora.horaActual(), "Fumador", "ha agarrado Fosforos del banco");
		break;
	}
		hayIngredientes = Boolean.FALSE;
		return true;
	}
	}
	
}
