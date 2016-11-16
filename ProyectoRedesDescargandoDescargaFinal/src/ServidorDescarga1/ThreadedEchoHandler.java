package ServidorDescarga1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/*Realiza el manejo del hilo de servidor de descarga 1 correspondiente a un usuario, contiene toda la interaccion entre el cliente y el servidor de descarga 1*/
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
	  //    while (true) {  
	    	  
	    	nombrepelicula=entrada.readLine();
	    	System.out.println(nombrepelicula);
	    	salida.println("Bienvenido al servidor de descarga numero (1), presione enter para descargar.");
					try{
				    salida.println("va a transferir");
				     Transferir("C:\\Users\\Alan\\Desktop\\Worespais\\"+nombrepelicula+".WMV");
				   // entrada.readLine();
				    } 
				catch (Exception e){
				    	System.out.println("IOException: "+ e.getMessage());
				    }
	   //   }
	    } 
	    catch (IOException e){
	    	System.out.println("PEO ACA");
	    	System.out.println("IOException: " + e.getMessage());
	    }  
	  }
	
	 public void Transferir(String RutaPelicula ) throws IOException
	   {
		// Creamos el archivo que vamos a enviar
	       File archivo = new File(RutaPelicula);
	    
	       // Obtenemos el tamaño del archivo
	       int tamañoArchivo = ( int )archivo.length();
	    
	       // Creamos el flujo de salida, este tipo de flujo nos permite 
	       // hacer la escritura de diferentes tipos de datos tales como
	       // Strings, boolean, caracteres y la familia de enteros, etc.
	       DataOutputStream dos = new DataOutputStream( ss.getOutputStream() );
	    
	       System.out.println( "Enviando Archivo: "+archivo.getName() );
	    
	       // Enviamos el nombre del archivo 
	       dos.writeUTF( archivo.getName() );
	    
	       // Enviamos el tamaño del archivo
	       dos.writeInt( tamañoArchivo );
	    
	       // Creamos flujo de entrada para realizar la lectura del archivo en bytes
	       FileInputStream fis = new FileInputStream(archivo);
	       BufferedInputStream bis = new BufferedInputStream(new FileInputStream(archivo));
	       BufferedOutputStream bos = new BufferedOutputStream(ss.getOutputStream());
	       // Creamos un array de tipo byte con el tamaño del archivo 
	       byte[] buffer = new byte[8192];
	       System.out.println( "tamaño "+archivo.length() );
	    int bytesLeidos;
	       // Leemos el archivo y lo introducimos en el array de bytes 
	  
	    
	       // Realizamos el envio de los bytes que conforman el archivo
	    int pct = 0; 

	    int bytesActuales=0;
	    while (/*bytesLeidos = bis.read(buffer)) > -1*||*/ tamañoArchivo-bytesActuales>8192 ){
	  bytesLeidos= bis.read(buffer);
	    bytesActuales+=bytesLeidos;
	    bos.write(buffer,0,bytesLeidos);
	 if (tamañoArchivo-bytesActuales<8192){
		 bytesLeidos= bis.read(buffer);
		 System.out.println("BytesActuales"+bytesActuales);
		 System.out.println("BytesPorMeter "+bytesLeidos);
		 
		 bos.write(buffer,0,bytesLeidos);
		 break;}
	    }
	       System.out.println( "Archivo Enviado: "+archivo.getName() );
	       // Cerramos socket y flujos
	   //    bis.close();
	    //   bos.close();
	    
	     }
}