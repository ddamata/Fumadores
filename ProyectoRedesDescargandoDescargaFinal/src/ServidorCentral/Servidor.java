package ServidorCentral;
import java.io.*;
import java.net.*;
/*Levanta un hilo de servidor central cada vez que recibe a un usuario.*/
public class Servidor {  
  public static final int PORT = 50006;
  public static void main(String[] args) throws IOException {
	  int i=1;
	  System.out.println("Servidor principal en funcionamiento.");
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