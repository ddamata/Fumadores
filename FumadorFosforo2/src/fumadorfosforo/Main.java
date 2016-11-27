package fumadorfosforo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket ecoSocket = null;
	    BufferedReader entrada = null;
	    PrintWriter salida = null;
        DataInputStream stdIn = new DataInputStream(System.in);    
        Traza traza= new Traza();
       
       
        try {
        	//Se crea el socket con la direcci�n IP y puerto del servidor.
            ecoSocket = new Socket("127.0.0.1", 50008);
            //Se crean los canales de entrada y salida del socket.
            entrada = new BufferedReader(new InputStreamReader(ecoSocket.getInputStream()));
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(ecoSocket.getOutputStream())),true);

            
           
        } catch (UnknownHostException e) {  
            System.err.println("No conozco al host: " + "192.168.1.119");
        } catch (IOException e) {
            System.err.println("Error de E/S para la conexion con: " + "192.168.1.119");
        }catch (NullPointerException e) {
            System.err.println("Error de E/S para la conexion con: " + "192.168.1.119");
        }
        
        if (ecoSocket != null && salida != null && entrada != null) {
        	try {
        		   
        		//Se envia al servidor el id de usuario junto con el tipo de usuario separado por una X.
        		salida.println("2xFumador");
				while(true){
					if(entrada.readLine()!=null)
						System.out.println(entrada.readLine());
						String mensaje[]= entrada.readLine().split("-");
						traza.insertarTraza(mensaje[0], mensaje[1], mensaje[2]);
				}	
        	}catch (IOException e) {
        		System.err.println("E/S fallo en la conexion a: " + "192.168.1.119");
        		System.err.println("" + e.getMessage());  
        	}
        	salida.close();
        
        }    
	
   }
	

}
