package Servidor;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
/*Realiza el manejo del hilo correspondiente a un usuario, contiene toda la interaccion entre el cliente y el servidor principal*/
public class ThreadedEchoHandler extends Thread{
	//private Banco banco;
	private BancoTabaco bancoTabaco;
	 private BancoFosforo bancoFosforo;
	private BancoPapel bancoPapel;
	private Random randomN = new Random();
	private boolean ingredientes[] = {false ,false};
	private Traza t =new Traza();
	
	public ThreadedEchoHandler(Socket i, int c, BancoTabaco bancoTabaco, BancoFosforo bancoFosforo, BancoPapel bancoPapel) { 
		ss=i;
		counter=c; 
		this.bancoTabaco = bancoTabaco;
		this.bancoFosforo = bancoFosforo;
		this.bancoPapel = bancoPapel;
		
		}
	
	public void run() {
	  
	    BufferedReader entrada = null;
	    PrintWriter salida = null;


	    try {
	    	//Se crean los canales de entrada y salida del socket.
	    	 entrada = new BufferedReader(new InputStreamReader(ss.getInputStream()));
		      salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(ss.getOutputStream())),true);
		    //Se recibe un mensaje y se manipula para determinar el n�mero de usuario y tipo del mismo.
		      String accion[] = entrada.readLine().split("x");
		      if(Integer.parseInt(accion[0]) == 0){
				
					 System.out.println("Servidor - Fumador(Tabaco) conectado.");
					 //Bucle infinito que determina la acci�n del usuario dependiendo de su tipo.
				      while (true) {
				    	 
					    	if (ingredientes[0] && ingredientes[1] ){
					    		System.out.println("Fumador(Tabaco) - Comienza a fumar.");
					    		Traza.insertarTraza(Hora.horaActual(), "Fumador(Tabaco)", "Comienza a fumar.");
					    		Thread.sleep(5*1000);
					    		ingredientes[0] = false;
					    		ingredientes[1] = false;
					    		System.out.println("Fumador(Tabaco) - Termina de fumar.");
					    		//Se registra el evento en la traza de tipo XML.
					    		salida.println(Hora.horaActual()+"xFumador(Tabaco)x Finaliza de fumar.");
			 		    		Traza.insertarTraza(Hora.horaActual(), "Fumador(Tabaco)", "Termina de fumar.");
					    	}else{
					    		if (!ingredientes[0]){
					    			ingredientes[0] = bancoFosforo.RecogerIngredientes(Integer.parseInt(accion[0]));;
					    			if(ingredientes[0]){
					    				salida.println(Hora.horaActual()+"xFumador(Tabaco)x Agarro fosforo del banco.");
					    			}
					    			
					    		}
					    		if (!ingredientes[1]){
					    			ingredientes[1] = bancoPapel.RecogerIngredientes(Integer.parseInt(accion[0]));
					    		 
					    			if(ingredientes[1]){
					    				salida.println(Hora.horaActual()+"xFumador(Tabaco)x Agarro papel del banco.");
					    		}
					    		
					    	}
					    	
				      }
		      }}
			 if(Integer.parseInt(accion[0]) == 1 && accion[1].equals("Fumador")){
				
					System.out.println("Servidor - Fumador(Papel) conectado.");
					//Bucle infinito que determina la acci�n del usuario dependiendo de su tipo.
				      while (true) {
				    	 
					    	if (ingredientes[0] && ingredientes[1] ){
					    		System.out.println("Fumador(Papel) - Comienza a fumar.");
					    		Traza.insertarTraza(Hora.horaActual(), "Fumador(Papel)", "Comienza a fumar.");
					    		Thread.sleep(5*1000);
					    		ingredientes[0] = false;
					    		ingredientes[1] = false;
					    		System.out.println("Fumador(Papel) - Termina de fumar.");
					    		salida.println(Hora.horaActual()+"xFumador(Papel)x Termina de fumar.");
					    		//Se registra el evento en la traza de tipo XML.
			 		    		Traza.insertarTraza(Hora.horaActual(), "Fumador(Papel)", "Termina de fumar.");
					    	}else{
					    		if (!ingredientes[0]){
					    			ingredientes[0] = bancoFosforo.RecogerIngredientes(Integer.parseInt(accion[0]));;
					    			if(ingredientes[0]){
					    				salida.println(Hora.horaActual()+"xFumador(Papel)x Agarro fosforo del banco.");
					    			}
					    			
					    		}
					    		if (!ingredientes[1]){
					    			ingredientes[1] = bancoTabaco.RecogerIngredientes(Integer.parseInt(accion[0]));
					    			
					    			if(ingredientes[1]){
					    				salida.println(Hora.horaActual()+"xFumador(Papel)x Agarro tabaco del banco.");
					    		}
					    		
					    	}
					    	
				      }
			 }}
			if(Integer.parseInt(accion[0]) == 2 && accion[1].equals("Fumador")){
				
					System.out.println("Servidor - Fumador(Fosforo) conectado.");
					//Bucle infinito que determina la acci�n del usuario dependiendo de su tipo.
				      while (true) {
				    	 
					    	if (ingredientes[0] && ingredientes[1] ){
					    		System.out.println("Fumador(Fosforo) - Comienza a fumar.");
					    		salida.println(Hora.horaActual()+"xFumador (Fosforo)x Fumando.");
					    		Traza.insertarTraza(Hora.horaActual(), "Fumador(Fosforo)", "Comienza a fumar.");
					    		Thread.sleep(5*1000);
					    		ingredientes[0] = false;
					    		ingredientes[1] = false;
					    		System.out.println("Fumador(Fosforo) - Termina de fumar.");
					    		//Se registra el evento en la traza de tipo XML.
					    		salida.println(Hora.horaActual()+"xFumador(Fosforo)x Deja de fumar.");
			 		    		Traza.insertarTraza(Hora.horaActual(), "Fumador(Fosforo)", "Termina de fumar.");
					    	}else{
					    		if (!ingredientes[0]){
					    			ingredientes[0] = bancoTabaco.RecogerIngredientes(Integer.parseInt(accion[0]));;
					    			if(ingredientes[0]){
					    				salida.println(Hora.horaActual()+"xFumador(Fosforo)x Agarro tabaco del banco.");
					    			}
					    			
					    		}
					    		if (!ingredientes[1]){
					    			ingredientes[1] = bancoPapel.RecogerIngredientes(Integer.parseInt(accion[0]));
					    			if(ingredientes[1]){
					    			salida.println(Hora.horaActual()+"xFumador(Fosforo)x Agarro papel del banco.");
					    		}
					    		
					    	}
					    	
				      }
			 }}
			if(Integer.parseInt(accion[0]) == 3 && accion[1].equals("Vendedor")){
				
					System.out.println("Servidor - Vendedor conectado.");
					while (true){
					for (int i=0 ;i < 2; i++){
						
						int control = randomN.nextInt(3);
						switch ( control ){
							case 0:
								bancoTabaco.nuevosIngredientes();
								break;
							case 1:
								bancoPapel.nuevosIngredientes();
								break;
							case 2: 
								bancoFosforo.nuevosIngredientes();
								break;
							
						}
						
					}
					Thread.sleep(3*1000);
					
					}
					
			 }
			
		      
	    } catch (IOException e) {
	      System.out.println("IOException: " + e.getMessage());
	    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
        } 
 
	}
	private Socket ss;
	private int counter;
}