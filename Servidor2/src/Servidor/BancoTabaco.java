package Servidor;



public class BancoTabaco {
	private boolean hayIngredientes;
	private Traza t;
	
	BancoTabaco(boolean ingrediente ){
		
		hayIngredientes = ingrediente;
		//Se asigna inicialmente al banco papel y fosforo.
		t= new Traza();
	}

	public synchronized void nuevosIngredientes () throws  InterruptedException {
		//Bucle infinito que detiene al vendedor de poner mas ingredientes en el banco si algun fumador est� fumando o el banco ya tiene ingredientes.
		/*while(hayIngredientes){
			wait();
		}*/
		if (!hayIngredientes){
		//Se agrega Tabaco al banco.
		hayIngredientes = true;
		//imprimo por pantalla la accion 
		System.out.println("Vendedor - Se ha agregado  Tabaco al banco.");
		//Se registra el evento en la traza de tipo XML.
		t.insertarTraza( Hora.horaActual(), "Vendedor", "Se ha agregado  Tabaco al banco.");
		//Se notifica a todos los fumadores que se han puesto ingredientes nuevos en el banco.
		notifyAll();}
	} 

	public synchronized boolean RecogerIngredientes (int id) throws  InterruptedException {
		while (! hayIngredientes ){
			//wait();
			return false;
		}
		
		
		switch(id){
		case 0:
			System.out.println("Fuamdor 0 -  ha agarrado Tabaco del banco.");
			//Se registra el evento en la traza de tipo XML.
			t.insertarTraza(Hora.horaActual(), "Fuamdor 0", "ha agarrado Tabaco del banco.");
			
		break;
		case 1:
			System.out.println("Fumador 1- ha agarrado Tabaco del banco.");
			//Se registra el evento en la traza de tipo XML.
			t.insertarTraza(Hora.horaActual(), "Fumador 1", "ha agarrado Tabaco del banco.");
			
		break;
		case 2:
			System.out.println("Fumador 2- ha agarrado Tabaco del banco.");
			//Se registra el evento en la traza de tipo XML.
			t.insertarTraza(Hora.horaActual(), "Fumador", "ha agarrado Tabaco del banco");
			
		break;
		
		}
		hayIngredientes = false;
		return true;
	}
	
	
}

	

