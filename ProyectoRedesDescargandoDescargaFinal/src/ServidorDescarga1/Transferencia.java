package ServidorDescarga1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
/*Contiene el m�todo de descarga de parte del servidor de descarga 2*/
public class Transferencia {	
	
	  String nombredearchivo = "";
	  int puerto = 0;
	  Socket sockett;
		
   public Transferencia( String nombrearchivo, Socket socket )
   {
        nombredearchivo = nombrearchivo;
        sockett = socket;
   }
   /*Realiza la transferencia del archivo entre el hilo del servidor de descarga 1 y el cliente.*/
   public void Transferir( ) throws IOException
   {
	// Creamos el archivo que vamos a enviar
       File archivo = new File(nombredearchivo);
    
       // Obtenemos el tama�o del archivo
       int tama�oArchivo = ( int )archivo.length();
    
       // Creamos el flujo de salida, este tipo de flujo nos permite 
       // hacer la escritura de diferentes tipos de datos tales como
       // Strings, boolean, caracteres y la familia de enteros, etc.
       DataOutputStream dos = new DataOutputStream( sockett.getOutputStream() );
    
       System.out.println( "Enviando Archivo: "+archivo.getName() );
    
       // Enviamos el nombre del archivo 
       dos.writeUTF( archivo.getName() );
    
       // Enviamos el tama�o del archivo
       dos.writeInt( tama�oArchivo );
    
       // Creamos flujo de entrada para realizar la lectura del archivo en bytes
       FileInputStream fis = new FileInputStream(archivo);
       BufferedInputStream bis = new BufferedInputStream(new FileInputStream(archivo));
       BufferedOutputStream bos = new BufferedOutputStream(sockett.getOutputStream());
       // Creamos un array de tipo byte con el tama�o del archivo 
       byte[] buffer = new byte[8192];
    int bytesLeidos;
       // Leemos el archivo y lo introducimos en el array de bytes 
  
    
       // Realizamos el envio de los bytes que conforman el archivo
       while ((bytesLeidos = bis.read(buffer)) != -1){
    	   System.out.println(bytesLeidos);
    	   bos.write(buffer,0,bytesLeidos);
    	   }
       System.out.println( "Archivo Enviado: "+archivo.getName() );
       // Cerramos socket y flujos
       bis.close();
       bos.close();
    
     }
}