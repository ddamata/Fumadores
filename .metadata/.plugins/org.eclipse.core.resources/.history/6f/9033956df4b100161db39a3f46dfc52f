package Servidor;


import java.io.*;
import java.net.*;
import java.util.Random;

public class Main {  
	  //Se crea el banco.
	  public static Banco banco = new Banco();
	  public static BancoTabaco bancoTabaco;
	  public static BancoFosforo bancoFosforo;
	  public static BancoPapel bancoPapel;
	  private static Random randomN ;//= new Random();
	  
	  
	  
	  public static void main(String[] args) throws IOException {
		  randomN = new Random();
		 for (int i = 0 ; i < 2 ; i++ )
		 {
			 int opcion = randomN.nextInt(3);
			 if (opcion == 1){
				 bancoTabaco = new BancoTabaco(false);
				 bancoPapel = new BancoPapel(false);
				 bancoFosforo = new BancoFosforo(false);
			 }
			 
		 }
		  
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


