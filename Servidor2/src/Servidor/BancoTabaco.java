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
		System.out.println("Vendedor - Se ha agregado  Tabaco al banco.");
		//Se registra el evento en la traza de tipo XML.
		
		}
		
	} 

	public synchronized boolean RecogerIngredientes (int id) throws  InterruptedException {
		if (! hayIngredientes ){
		
			return false;
		}else {
		
		
		switch(id){
		case 0:
			System.out.println("Fuamdor (Tabaco) -  ha agarrado Tabaco del banco.");
			//Se registra el evento en la traza de tipo XML.
			//Traza.insertarTraza(Hora.horaActual(), "Fuamdor 0", "ha agarrado Tabaco del banco.");
			
		break;
		case 1:
			System.out.println("Fumador (Papel)- ha agarrado Tabaco del banco.");
			//Se registra el evento en la traza de tipo XML.
			//Traza.insertarTraza(Hora.horaActual(), "Fumador 1", "ha agarrado Tabaco del banco.");
			
		break;
		case 2:
			System.out.println("Fumador (Fosforo)- ha agarrado Tabaco del banco.");
			//Se registra el evento en la traza de tipo XML.
			//Traza.insertarTraza(Hora.horaActual(), "Fumador", "ha agarrado Tabaco del banco");
			
		break;
		
		}
		hayIngredientes = Boolean.FALSE;
		return true;
	}
	
	}
}

	

