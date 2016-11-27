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
		System.out.println("Vendedor - Agrego tabaco al banco.");
		//Se registra el evento en la traza de tipo XML.
		Traza.insertarTraza( Hora.horaActual(), "Vendedor", "Agrego tabaco al banco.");
		//Se notifica a todos los fumadores que se han puesto ingredientes nuevos en el banco.
		
		}
		
	} 

	public synchronized boolean RecogerIngredientes (int id) throws  InterruptedException {
		if (! hayIngredientes ){
			return false;
		}else {
		
		
		switch(id){
		case 0:
			System.out.println("Fumador(Tabaco) -  Agarro tabaco del banco.");
			//Se registra el evento en la traza de tipo XML.
			Traza.insertarTraza(Hora.horaActual(), "Fumador(Tabaco)", "Agarro tabaco del banco.");
			
		break;
		case 1:
			System.out.println("Fumador(Papel) - Agarro tabaco del banco.");
			//Se registra el evento en la traza de tipo XML.
			Traza.insertarTraza(Hora.horaActual(), "Fumador(Papel)", "Agarro tabaco del banco.");
			
		break;
		case 2:
			System.out.println("Fumador(Fosforo) - Agarro tabaco del banco.");
			//Se registra el evento en la traza de tipo XML.
			Traza.insertarTraza(Hora.horaActual(), "Fumador(Fosforo)", "Agarro tabaco del banco");
			
		break;
		
		}
		hayIngredientes = Boolean.FALSE;
		return true;
	}
	
	}
}

	

