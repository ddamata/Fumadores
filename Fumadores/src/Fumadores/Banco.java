package Fumadores;

public class Banco {
	
	private boolean fumando = false;
	private boolean hayIngredientes = false;
	private int ingredientes;

	
	//ingredientes  1- papel 2-tabaco 3-fosfores
	
	public synchronized void nuevosIngredientes (int ingredienteNuevo) throws  InterruptedException {
		while(hayIngredientes || fumando){
			wait();
		}
		this.ingredientes = ingredienteNuevo;
		hayIngredientes = true;
		if (ingredienteNuevo == 0){
		System.out.println("se a agregado el ingredeinete 1 y 2 ");
		}else{
			if (ingredienteNuevo == 1){
				System.out.println("se a agregado el ingredeinete 0 y 2 ");
				}else{
					System.out.println("se a agregado el ingredeinete 0 y 1 ");
				}
		}
		notifyAll();
	} 
	
	
	public synchronized void fumar (int id)  throws  InterruptedException {
		while (!hayIngredientes || fumando ||ingredientes != id){
			wait();
		}
		System.out.println("Fumador "+id+" empieza a fumar");
		hayIngredientes = false;
		fumando= true;
	}
	
	
	public synchronized void dejarFumar (int id) {
		fumando = false;
		System.out.println("Fumador "+id+" dejo de fumar");
		notifyAll();
	}

}
