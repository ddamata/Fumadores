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
	    	//Se crean los canales de entrada y salida del socket.
	    	 entrada = new BufferedReader(new InputStreamReader(ss.getInputStream()));
		      salida = new PrintWriter(new BufferedWriter(new 
			  OutputStreamWriter(ss.getOutputStream())),true);
		    //Se recibe un mensaje y se manipula para determinar el número de usuario y tipo del mismo.
		      String accion[] = entrada.readLine().split("x");
		      if(Integer.parseInt(accion[0])==0)
		      System.out.println("Servidor - Fumador(Tabaco) conectado.");
		      if(Integer.parseInt(accion[0])==1)
		      System.out.println("Servidor - Fumador(Papel) conectado.");
		      if(Integer.parseInt(accion[0])==2)
		      System.out.println("Servidor - Fumador(Fosforo) conectado.");
		    //Bucle infinito que determina la acción del usuario dependiendo de su tipo.
		      while (true) {
		    	 
		    	
		    	  if (accion[1].equals("Fumador")){
		    		  	//Se indica que el fumador intentará fumar, enviando su id de fumador.
						banco.fumar(Integer.parseInt(accion[0]));
						//El fumador tardará cinco(5) segundos fumando.
						Thread.sleep(5*1000);
						//El fumador indicará que dejará de fumar, enviando su id de fumador.
						banco.dejarFumar(Integer.parseInt(accion[0]));
						//El fumador esperará cinco(5) segundos para volver a intentar fumar.
						Thread.sleep(5*1000);
					
		    	  }else{
		    		  //Se obtiene un número al azar entre [0,3) correspondiente a un número de ingrediente que NO será colocado en el banco.
		    		  int ingrediente = randomN.nextInt(3);
		    		//El vendedor esperará cinco(5) segundos para colocar los ingredientes en el banco.
		    		  Thread.sleep(5*1000);
		    		//El vendedor añade los ingredientes en el banco, enviando el ingrediente que no se colocará.
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