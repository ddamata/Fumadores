package Fumadores;

import java.util.Random;

public class Fumadores extends Thread {
	
	private Random randomN = new Random();
	
	private int id;
	private Banco banco;
	

	public Fumadores(int id, Banco banco) {
		this.id = id;
		this.banco = banco;
	}

	public void run() {
		while (true){
			try {
				banco.fumar(id);
				Thread.sleep(randomN.nextInt(300));
				banco.dejarFumar(id);
				Thread.sleep(randomN.nextInt(300));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
	}

}
