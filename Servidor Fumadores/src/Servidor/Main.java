package servidor;


import java.io.*;
import java.net.*;

public class Main {  
	  public static final int PORT = 50008;
	  public static Banco banco = new Banco();
	  public static void main(String[] args) throws IOException {
		  int i=1;
		  System.out.println("Servidor principal en funcionamiento.");
		  try {
		  ServerSocket s=new ServerSocket(PORT);
		  for (;;) {
		  Socket ss=s.accept();
		  System.out.println("Recibiendo conexion numero: "+i);
		  new ThreadedEchoHandler(ss,i,banco).start();
		  i++;
		  }
		  } catch (Exception e) { System.out.println(e); }
	  }
	}


