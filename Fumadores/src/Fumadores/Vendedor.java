package Fumadores;
import java.util.*;

public class Vendedor extends Thread {
	
	private Random randomN = new Random();
	
	private Banco banco;
	
	public Vendedor(Banco banco) {
		this.banco = banco;
	}
	
	public void run (){
		while (true){
			int ingrediente = randomN.nextInt(3);
			try{
				
				Thread.sleep(randomN.nextInt(300));
				
				banco.nuevosIngredientes(ingrediente);
				
			}catch(InterruptedException e){
				
				e.printStackTrace();
			}
		}
	}

}
