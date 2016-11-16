package ServidorDescarga2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/*Levanta un hilo de servidor de descarga2 cada vez que recibe a un usuario.*/
public class ServidorDescarga2 {  
	  public static final int PORT = 50008;
	  public static void main(String[] args) throws IOException {
		  int i=1;
		  System.out.println("Servidor de descarga número (2) en funcionamiento.");
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