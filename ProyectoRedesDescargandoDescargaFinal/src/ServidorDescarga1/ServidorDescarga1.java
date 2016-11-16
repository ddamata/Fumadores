package ServidorDescarga1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/*Levanta un hilo de servidor de descarga1 cada vez que recibe a un usuario.*/
public class ServidorDescarga1 {  
	  public static final int PORT = 50007;
	  public static void main(String[] args) throws IOException {
		  int i=1;
		  System.out.println("Servidor de descarga n�mero (1) en funcionamiento.");
		  try {
		  ServerSocket s=new ServerSocket(PORT);
		  for (;;) {
		  Socket ss=s.accept();
		  System.out.println("Recibiendo conexion numero: "+i);
		  new ThreadedEchoHandler(ss,i).start();
		  i++;
		  }
		  } catch (Exception e) { System.out.println(e); }
	  }
	}