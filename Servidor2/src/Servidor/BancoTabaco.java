package Servidor;



public class BancoTabaco {
	private boolean hayIngredientes;

	
	BancoTabaco(boolean ingrediente ){
		
		hayIngredientes = ingrediente;

	}

	public synchronized void nuevosIngredientes () throws  InterruptedException {
		
		if (!hayIngredientes){
		//Se agrega Tabaco al banco.
		hayIngredientes = Boolean.TRUE;
		//imprimo por pantalla la accion 
		System.out.println("Vendedor - Se ha agregado tabaco al banco.");
		//Se registra el evento en la traza de tipo XML.
		
		}
		
	} 

	public synchronized boolean RecogerIngredientes (int id) throws  InterruptedException {
		if (! hayIngredientes ){
			return false;
		}else {
		
		
		switch(id){
		case 0:
			System.out.println("Fumador(Tabaco) -  ha agarrado tabaco del banco.");
			//Se registra el evento en la traza de tipo XML.
			Traza.insertarTraza(Hora.horaActual(), "Fumador 0", "Agarro tabaco del banco.");
			
		break;
		case 1:
			System.out.println("Fumador(Papel) - ha agarrado tabaco del banco.");
			//Se registra el evento en la traza de tipo XML.
			Traza.insertarTraza(Hora.horaActual(), "Fumador 1", "Agarro tabaco del banco.");
			
		break;
		case 2:
			System.out.println("Fumador(Fosforo) - ha agarrado tabaco del banco.");
			//Se registra el evento en la traza de tipo XML.
			Traza.insertarTraza(Hora.horaActual(), "Fumador 2", "Agarro tabaco del banco");
			
		break;
		
		}
		hayIngredientes = Boolean.FALSE;
		return true;
	}
	
	}
}

	

