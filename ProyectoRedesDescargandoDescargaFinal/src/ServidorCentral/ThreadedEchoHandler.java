package ServidorCentral;

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
/*Realiza el manejo del hilo correspondiente a un usuario, contiene toda la interaccion entre el cliente y el servidor principal*/
public class ThreadedEchoHandler extends Thread{
	
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
	      String decision,usuario,contrasena;
	      while (true) {
	    	decision="";
	    	salida.println("Introduzca uno(1) para loguearse, dos(2) para registrarse, tres(3) para ver las peliculas disponibles y cuatro(4) para ver la cantidad de descargas.");
	    	decision=entrada.readLine();
	    	if(decision.equals("1")){
		    	salida.println("Indique su Usuario:");
		    	usuario=entrada.readLine();
		    	salida.println("Indique su contrasena");
		    	contrasena=entrada.readLine();
		    	if(ManejoArchivo.login(usuario,contrasena)){
		    		System.out.println("Un miembro se ha logueado satisfactoriamente. "+ss);
			    	salida.println("Se ha logueado satisfactoriamente, ingrese enter para continuar.");
			    	decision=entrada.readLine();
			    	salida.println("Introduzca el nombre de la pelicula a descargar.");
			    	decision=entrada.readLine();
			    	if(ManejoArchivo.BusquedaPelicula(1,decision)){
			    		ManejoArchivo.sumarDescarga(1);
				    	salida.println("DatosConexionServidorDescarga");
				    	salida.println("127.0.0.1");
				    	salida.println(50007);
				    	System.out.println("Un usuario ha sido enviado al servidor de descarga (1). "+ss);
				    	interrupt();
			    	}if(ManejoArchivo.BusquedaPelicula(2,decision)){
			    		ManejoArchivo.sumarDescarga(2);
				    	salida.println("DatosConexionServidorDescarga");
				    	salida.println("190.203.252.18");
				    	salida.println(50008);
				    	System.out.println("Un usuario ha sido enviado al servidor de descarga (2). "+ss);
				    	interrupt();
			    	}
			    	if(ManejoArchivo.BusquedaPelicula(3,decision)){
			    		ManejoArchivo.sumarDescarga(3);
				    	salida.println("DatosConexionServidorDescarga");
				    	salida.println("190.203.252.18");
				    	salida.println(50009);
				    	System.out.println("Un usuario ha sido enviado al servidor de descarga (3). "+ss);
				    	interrupt();
			    	}	    	
		    	}
	    	}
	    	if(decision.equals("2")){
		    	salida.println("Indique su Usuario:");
		    	usuario=entrada.readLine();
		    	salida.println("Indique su contrasena");
		    	contrasena=entrada.readLine();
	    		File fichero = new File("ArchivoPlano.txt");
	    		ManejoArchivo.GuardarCliente(fichero,usuario,contrasena);
	    		System.out.println("Un miembro se ha registrado satisfactoriamente."+ss);
	    		salida.println("Se ha registrado satisfactoriamente, ingrese enter para continuar.");
	    		decision=entrada.readLine();
	    	}
	    	if(decision.equals("3")){
          String Listado;
	    		ArrayList<String> peliculas = new ArrayList<String>();
	    		peliculas=ManejoArchivo.BusquedaPeliculasDisponiblesPorServidor(1);
           Listado="Servidor 1: "; 
           System.out.println(peliculas.size());
	    		for(int i=0;i<=peliculas.size()-1;i++){
            Listado=Listado+peliculas.get(i)+" - " ; 
	    		}
	    		Listado=Listado+" ";
	    		peliculas=ManejoArchivo.BusquedaPeliculasDisponiblesPorServidor(2);
           Listado+="Servidor 2: "; 
	    		for(int i=0;i<=peliculas.size()-1;i++){
	    			Listado=Listado+peliculas.get(i)+" - ";
  
	    		}
	    		Listado=Listado+" ";
	    		peliculas=ManejoArchivo.BusquedaPeliculasDisponiblesPorServidor(3);
           Listado+="Servidor 3: "; 
	    		for(int i=0;i<=peliculas.size()-1;i++){
            Listado=Listado+peliculas.get(i)+" - ";

	    		}
	    		salida.println(Listado+". Ingrese enter para continuar");
	    		decision=entrada.readLine();
	    	}
	    	if(decision.equals("4")){
	    		String cantidades;
	    		cantidades="Servidor 1: ";
	    		cantidades=cantidades+Integer.toString(ManejoArchivo.buscarCantidadDescargas(1))+". ";
	    		cantidades=cantidades+"Servidor 2: ";
	    	    cantidades=cantidades+Integer.toString(ManejoArchivo.buscarCantidadDescargas(2))+". ";
	    		cantidades=cantidades+"Servidor 3: ";
	    	    cantidades=cantidades+Integer.toString(ManejoArchivo.buscarCantidadDescargas(3))+". ";
	    	    salida.println(cantidades+" Ingrese enter para continuar");
	    		decision=entrada.readLine();
	    	}
	    	
	      }

	    } catch (IOException e) {
	    	System.out.println("PEO ACA");
	      System.out.println("IOException: " + e.getMessage());
	    }  
	  
	

	    
	}
	private Socket ss;
	private int counter;
}