package vendedor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) {
	
		// TODO Auto-generated method stub
				Socket ecoSocket = null;
		        DataOutputStream salida = null;
		        DataInputStream entrada = null;
		        DataInputStream stdIn = new DataInputStream(System.in);    
		        
		     
		        try {
		            ecoSocket = new Socket("192.168.1.111", 50008);
		            salida = new DataOutputStream(ecoSocket.getOutputStream());
		            entrada = new DataInputStream(ecoSocket.getInputStream());

		            
		           
		        } catch (UnknownHostException e) {  
		            System.err.println("No conozco al host: " + "192.168.1.119");
		        } catch (IOException e) {
		            System.err.println("Error de E/S para la conexión con: " + "192.168.1.119");
		        }catch (NullPointerException e) {
		            System.err.println("Error de E/S para la conexión con: " + "192.168.1.119");
		        }
		        
		        if (ecoSocket != null && salida != null && entrada != null) {
		        	try {
		        		   
		        		salida.writeBytes("0xVendedor");
		        		salida.close();
		        		
		        	}catch (IOException e) {
		            System.err.println("E/S fallo en la conexión a: " + "192.168.1.119");
		            System.err.println("" + e.getMessage());
		            
		        	}
		        }

	}	
}
