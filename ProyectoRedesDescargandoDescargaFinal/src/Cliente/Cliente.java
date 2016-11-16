package Cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    public static void main(String[] args) {
        Socket ecoSocket = null;
        DataOutputStream salida = null;
        DataInputStream entrada = null;
        DataInputStream stdIn = new DataInputStream(System.in);
    
        String a,ip,nombrepelicula;
        int puerto;
        nombrepelicula="";
/* Establecimiento de la conexión del Socket entre el cliente y el servidor y
apertura del canal E/S sobre el socket : */
  try {
            ecoSocket = new Socket("127.0.0.1", 50006);
            salida = new DataOutputStream(ecoSocket.getOutputStream());
            entrada = new DataInputStream(ecoSocket.getInputStream());

            
           
        } catch (UnknownHostException e) {  
            System.err.println("No conozco al host: " + "192.168.1.119");
        } catch (IOException e) {
            System.err.println("Error de E/S para la conexión con: " + "192.168.1.119");
  }
/* Ahora lee desde el stream de entrada estándar una línea cada vez. El programa
escribe inmediatamente la entrada seguida por un carácter de nueva línea en el stream
de salida conectado al socket. */
   if (ecoSocket != null && salida != null && entrada != null) {
   try {
    String userInput;
    System.out.println(entrada.readLine());
    while (((userInput = stdIn.readLine()) != null)) {
     salida.writeBytes(userInput);
     salida.writeByte('\n');
     a=entrada.readLine();
     if(a.equals("DatosConexionServidorDescarga")){
       ip=entrada.readLine();
       puerto=Integer.parseInt(entrada.readLine());
       ecoSocket = new Socket(ip, puerto);
       ecoSocket.setKeepAlive(true);
       salida = new DataOutputStream(ecoSocket.getOutputStream());
         entrada = new DataInputStream(ecoSocket.getInputStream());
       
        ; 
         salida.writeBytes(userInput);
         nombrepelicula=userInput;
         salida.writeByte('\n');
       System.out.println(entrada.readLine());
     }
     if(a.equals("va a transferir")){
      System.out.println(nombrepelicula);
      FileOutputStream archivo = new FileOutputStream("C:\\Users\\Alan\\Desktop\\"+nombrepelicula+".WMV");
      BufferedOutputStream bos = new BufferedOutputStream(archivo);
      BufferedInputStream bis = new BufferedInputStream(ecoSocket.getInputStream());
      String nombreArchivo = entrada.readUTF().toString(); 
    
      int tam = entrada.readInt();
    byte[] buffer = new byte[4096];
    System.out.println( "Recibiendo archivo "+nombreArchivo );
    System.out.println( "Tamaño de archivo:  "+ tam/1024+"kb" );
    long pct = 0; 
    int bytesLeidos;
    long porcentaje = 0;
   int bytesActuales=0;
    while (tam-bytesActuales>4096 ){
    	bytesLeidos= bis.read(buffer); 
    	bytesActuales+=bytesLeidos;
    	porcentaje = bytesActuales*100;
    	porcentaje = porcentaje/tam;
    	if(porcentaje% 10 == 0 && pct != porcentaje)
         {
           System.out.println( "Descargando "+porcentaje + "%" );   
           pct= porcentaje;     
         }   	

    bos.write(buffer,0,bytesLeidos);
 if (tam-bytesActuales<4096){
	 bytesLeidos= bis.read(buffer); 
	 bos.write(buffer,0,bytesLeidos);
	 break;}
    }
    	 bos.close();
    	 
    System.out.println( "Descargando 100%" );
    System.out.println( "Archivo Recibido "+nombreArchivo );
     }
     if(!a.equals("va a transferir") && !a.equals("DatosConexionServidorDescarga"))
       System.out.println(a);
        
    }

    salida.close();
    entrada.close();
      } catch (IOException e) {
                System.err.println("E/S fallo en la conexión a: " + "192.168.1.119");
                System.err.println("" + e.getMessage());
                
   }
  }
 }
} 

