package servidor;


import java.io.*;
import java.net.*;

public class Main {  
	  //Se crea el banco.
	  public static Banco banco = new Banco();
	  public static void main(String[] args) throws IOException {
		  int contadorUsuarios=1;
		  System.out.println("Servidor - Servidor principal en funcionamiento.");
    	  System.out.println("Servidor - Se ha agregado papel y fosforo al banco.");
		  try {
	      //Se crea el socket.
		  ServerSocket s=new ServerSocket(50008);
		  //Se implementa un ciclo infinito para la escucha.
		  for (;;) {
			//Se espera por una conexi�n via Socket.
			Socket ss=s.accept();
			System.out.println("Servidor - Recibiendo conexion numero: "+contadorUsuarios);
			//Se crea un hilo para manejar al usuario conectado..
			new ThreadedEchoHandler(ss,contadorUsuarios,banco).start();
			contadorUsuarios++;
		  }
		  } catch (Exception e) { System.out.println(e); }
	  }
	}


