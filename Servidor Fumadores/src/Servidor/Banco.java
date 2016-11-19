package servidor;

public class Banco {
	private boolean fumando = false;
	private boolean hayIngredientes = false;
	private int ingredientes;
	
	public synchronized void nuevosIngredientes (int ingredienteNuevo) throws  InterruptedException {
		while(hayIngredientes || fumando){
			wait();
		}
		this.ingredientes = ingredienteNuevo;
		hayIngredientes = true;
		switch(ingredienteNuevo){
			case 0:
				System.out.println("Se ha agregado el ingrediente 1 y 2.");
			break;
			case 1:
				System.out.println("Se ha agregado el ingrediente 0 y 2.");
			break;
			case 2:
				System.out.println("Se ha agregado el ingrediente 0 y 1.");
			break;
		}
		notifyAll();
	} 
	
	
	public synchronized void fumar (int id)  throws  InterruptedException {
		while (!hayIngredientes || fumando ||ingredientes != id){
			wait();
		}
		System.out.println("Fumador "+id+" empieza a fumar.");
		hayIngredientes = false;
		fumando= true;
	}
	
	
	public synchronized void dejarFumar (int id) {
		fumando = false;
		System.out.println("Fumador "+id+" dejo de fumar.");
		notifyAll();
	}

}
