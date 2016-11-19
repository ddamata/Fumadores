package servidor;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
/*Realiza el manejo del hilo correspondiente a un usuario, contiene toda la interaccion entre el cliente y el servidor principal*/
public class ThreadedEchoHandler extends Thread{
	private Banco banco;
	private Random randomN = new Random();
	
	public ThreadedEchoHandler(Socket i, int c, Banco banco) { 
		ss=i;
		counter=c; 
		this.banco =banco;
		}
	
	public void run() {
	  
	    BufferedReader entrada = null;
	    PrintWriter salida = null;


	    try {
	    	 entrada = new BufferedReader(new InputStreamReader(ss.getInputStream()));
		      salida = new PrintWriter(new BufferedWriter(new 
			  OutputStreamWriter(ss.getOutputStream())),true);
		      String accion[] = entrada.readLine().split("x");
		      System.out.println("Servidor - "+accion[1]+" "+accion[0]+" "+ "conectado");
		      while (true) {
		    	 
		    	  
		    	  if (accion[1].equals("Fumador")){
		    
						banco.fumar(Integer.parseInt(accion[0]));
						Thread.sleep(5*1000);
						banco.dejarFumar(Integer.parseInt(accion[0]));
						Thread.sleep(5*1000);
					
		    	  }else{
		    		  int ingrediente = randomN.nextInt(3);
		    		  Thread.sleep(5*1000);
		    		  banco.nuevosIngredientes(ingrediente);
		    	  }
	      
		      }
	    } catch (IOException e) {
	      System.out.println("IOException: " + e.getMessage());
	    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
        } 
 
	}
	private Socket ss;
	private int counter;
}