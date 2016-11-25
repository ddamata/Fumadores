package Servidor;


import java.io.*;
import java.net.*;
import java.util.Random;

public class Main {  
	  //Se crea el banco.
	  public static BancoTabaco bancoTabaco;
	  public static BancoFosforo bancoFosforo;
	  public static BancoPapel bancoPapel;
	
	  
	  public static void main(String[] args) throws IOException {
		  Random randomN  = new Random();
		  System.out.println("Servidor - Servidor principal en funcionamiento.");
			 
			 switch(randomN.nextInt(3)){
				case 0:
					bancoTabaco = new BancoTabaco(Boolean.FALSE);
					bancoFosforo = new  BancoFosforo(Boolean.TRUE);
					bancoPapel = new BancoPapel(Boolean.TRUE);
					  System.out.println("Servidor - Se ha agregado papel y fosforo al banco.");
					//Se registra el evento en la traza de tipo XML.
					//t.insertarTraza(Hora.horaActual(), "Fuamdor 0", "ha agarrado Fosforos del banco.");
				break;
				case 1:
					bancoTabaco = new BancoTabaco(Boolean.TRUE);
					bancoFosforo = new  BancoFosforo(Boolean.TRUE);
					bancoPapel = new BancoPapel(Boolean.FALSE);
					System.out.println("Servidor - Se ha agregado tabaco y fosforo al banco.");
					//Se registra el evento en la traza de tipo XML.
					//.insertarTraza(Hora.horaActual(), "Fumador 1", "ha agarrado Fosforos del banco.");
				break;
				case 2:
					bancoTabaco = new BancoTabaco(Boolean.TRUE);
					bancoFosforo = new  BancoFosforo(Boolean.FALSE);
					bancoPapel = new BancoPapel(Boolean.TRUE);
					System.out.println("Servidor - Se ha agregado papel y tabaco al banco.");
					//Se registra el evento en la traza de tipo XML.
					//t.insertarTraza(Hora.horaActual(), "Fumador", "ha agarrado Fosforos del banco");
				break;
			}
			 
		 
		  
		  int contadorUsuarios=1;
		  
    	
		  try {
	      //Se crea el socket.
		  ServerSocket s=new ServerSocket(50008);
		  //Se implementa un ciclo infinito para la escucha.
		  for (;;) {
			//Se espera por una conexi�n via Socket.
			Socket ss=s.accept();
			System.out.println("Servidor - Recibiendo conexion numero: "+contadorUsuarios);
			//Se crea un hilo para manejar al usuario conectado..
			new ThreadedEchoHandler(ss,contadorUsuarios,bancoTabaco,bancoFosforo,bancoPapel).start();
			contadorUsuarios++;
		  }
		  } catch (Exception e) { System.out.println(e); }
	  }
	}


