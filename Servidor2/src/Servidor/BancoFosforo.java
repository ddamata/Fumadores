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
		System.out.println("Vendedor - Se ha agregado fosforo al banco.");
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
			System.out.println("Fumador(Tabaco) -  Agarro fosforo del banco.");
			//Se registra el evento en la traza de tipo XML.
			Traza.insertarTraza(Hora.horaActual(), "Fumador(Tabaco)", "Agarro fosforo del banco.");
		break;
		case 1:
			System.out.println("Fumador(Papel) - Agarro fosforo del banco.");
			//Se registra el evento en la traza de tipo XML.
			Traza.insertarTraza(Hora.horaActual(), "Fumador(Papel)", "Agarro fosforo del banco.");
		break;
		case 2:
			System.out.println("Fumador(Fosforo) - Agarro fosforo del banco.");
			//Se registra el evento en la traza de tipo XML.
			Traza.insertarTraza(Hora.horaActual(), "Fumador(Fosforo)", "Agarro fosforo del banco");
		break;
	}
		hayIngredientes = Boolean.FALSE;
		return true;
	}
	}
	
}
