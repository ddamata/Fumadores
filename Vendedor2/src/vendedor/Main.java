package vendedor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
		            System.err.println("Error de E/S para la conexión con: " + "192.168.1.119");
		        }catch (NullPointerException e) {
		            System.err.println("Error de E/S para la conexión con: " + "192.168.1.119");
		        }
		        
		        if (ecoSocket != null && salida != null && entrada != null) {
		        	try {
		        		//Se envia al servidor el id de usuario junto con el tipo de usuario separado por una X.
		        		salida.println("3xVendedor");
		        		//Se itera infinitamente para la escucha de mensajes por parte del servidor con respecto a los hechos ocurridos a ser plasmados en la traza.
						while(true){
							//Se comprueba que el canal de comunicacion de entrada contiene informacion.
							if(entrada.readLine()!=null){
								//System.out.println(entrada.readLine());
								//Se trata la informacion recibida para extraer la hora de la accion, actor y mensaje.
								String mensaje[]= entrada.readLine().split("-");
								//Se inserta el hecho en la traza
								traza.insertarTraza(mensaje[0], mensaje[1], mensaje[2]);
							}
						}
		        	}catch (IOException e) {
			            System.err.println("E/S fallo en la conexion a: " + "192.168.1.119");
			            System.err.println("" + e.getMessage());      
		        	}
		        	salida.close();
		        }

	}	
}
