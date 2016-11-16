package ServidorDescarga3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/*Realiza el manejo del hilo de servidor de descarga 3 correspondiente a un usuario, contiene toda la interaccion entre el cliente y el servidor de descarga 3*/

public class ThreadedEchoHandler extends Thread{
	
	private Socket ss;
	private int counter;
	public ThreadedEchoHandler(Socket i, int c) { 
		ss=i;
		counter=c; 
		}
	
	public void run() {
	  
	    BufferedReader entrada = null;
	    PrintWriter salida = null;


	    try {
	    
	      // Se bloquea hasta que recibe alguna petición de un cliente
	      // abriendo un socket para el cliente

	    	
	      //socketCliente = socketServidor.accept();
	    	
	      //System.out.println("Connexión acceptada: "+ socketCliente);
	      // Establece canal de entrada
	      entrada = new BufferedReader(new InputStreamReader(ss.getInputStream()));
	      // Establece canal de salida
	      salida = new PrintWriter(new BufferedWriter(new 
		  OutputStreamWriter(ss.getOutputStream())),true);
	      // Hace eco de lo que le proporciona el cliente, hasta que recibe "Adios"
	      String nombrepelicula;
	      while (true) {  
	    	nombrepelicula=entrada.readLine();
	    	System.out.println(nombrepelicula);
	    	salida.println("Bienvenido al servidor de descarga numero (3), presione enter para descargar.");
				Transferencia transferir = new Transferencia ("C:\\Users\\Fernando Silva\\Desktop\\"+nombrepelicula+".JPG", ss);
				try{
				    salida.println("va a transferir");
				    transferir.Transferir();
				    entrada.readLine();
				    } 
				catch (Exception e){
				    	System.out.println("IOException: "+ e.getMessage());
				    }
	      }
	    } 
	    catch (IOException e){
	    	System.out.println("PEO ACA");
	    	System.out.println("IOException: " + e.getMessage());
	    }  
	  }
}