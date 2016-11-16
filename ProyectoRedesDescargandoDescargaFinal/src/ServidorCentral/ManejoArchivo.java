package ServidorCentral;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ManejoArchivo {
	
	

	/*Dado un fichero, un nombre y una contraseña, los almacena en un txt en una línea con separadores y un numero de usuario correspondiente */
    public static void GuardarCliente(File fichero,String NombreUsuario, String Clave){ 
        String pass;
        int numeroUsuario;
    	try {  
                //Si no Existe el fichero lo crea  
                 if(!fichero.exists()){  
                     fichero.createNewFile();  
                 }  
                /*Abre un Flujo de escritura,sobre el fichero con codificacion utf-8.  
                 *AdemÃ¡s  en el pedazo de sentencia "FileOutputStream(Ffichero,true)", 
                 *true es por si existe el fichero seguir aÃ±adiendo texto y no borrar lo que tenia*/  
                BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero,true), "utf-8"));  
                /*Escribe en el fichero la cadena que recibe la funciÃ³n.  
                 *el string "\r\n" significa salto de linea*/ 
                numeroUsuario = AcomodarInicialArchivo();
                Fescribe.write(numeroUsuario+"/"+NombreUsuario+"/"+Clave+"\r\n");  
                //Cierra el flujo de escritura  
                Fescribe.close();  
             } catch (Exception ex) {  
                //Captura un posible error le imprime en pantalla   
                System.out.println(ex.getMessage()); 
             }

    }
    /*Extrae el último número de usuario registrado en el txt.*/
    public static int AcomodarInicialArchivo (){
		int i;
	    char x;
		i = 0;
	     try{
	            // Abrimos el archivo
	            FileInputStream fstream = new FileInputStream("ArchivoPlano.txt");
	            // Creamos el objeto de entrada
	            DataInputStream entrada = new DataInputStream(fstream);
	            // Creamos el Buffer de Lectura
	            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
	            String strLinea;
	            // Leer el archivo linea por linea
	            while ((strLinea = buffer.readLine()) != null)   {
	                // Imprimimos la línea por pantalla
	                //System.out.println (strLinea);
	                x = strLinea.charAt(0);
	                i = Character.getNumericValue(x) + 1;
	            }
	           
               
	            // Cerramos el archivo
	            entrada.close();
	            fstream.close();
	            buffer.close();
	        }catch (Exception e){ //Catch de excepciones
	            System.err.println("Ocurrio un error: " + e.getMessage());
	        }
			
			return i;
	    }
    /*Dado un usuario y una contrasena, realiza un recorrido linea por linea buscando match entre usuario y contraseña.*/
	 public static Boolean login (String nombreusuario,String contrasena) throws java.io.IOException {
		 String strLinea, s2, nombre,pass; 
		 // Cargamos el buffer con el contenido del archivo
		 BufferedReader br = new BufferedReader (new FileReader ("ArchivoPlano.txt")); // Leemos la primera linea
		 FileInputStream fstream = new FileInputStream("ArchivoPlano.txt");
		 DataInputStream entrada = new DataInputStream(fstream);
		 BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
		 while ((strLinea = buffer.readLine()) != null)   {
			 StringTokenizer st = new StringTokenizer (strLinea,"/");
			 int i=1;
       while (st.hasMoreTokens()) { 
				 s2 = st.nextToken(); 
         if (s2.equals(nombreusuario))
         {
        		nombre=s2;
          	if(st.nextToken().equals(contrasena)) return true; 
        }
			 i++;
       }
		 
     }
		 br.close();
		 fstream.close();
		 entrada.close();
		 buffer.close();
		 return false;
	}
	 /*Dado un numero de servidor y el nombre de una pelicula, recorre el archivo txt correspondiente al servidor buscando el nombre de la pelicula*/
	 public static boolean BusquedaPelicula (int numero, String nombrepelicula) throws java.io.IOException {
		 File fichero = new File("Peliculas"+numero+".txt");
		 String strLinea; 		 
		 boolean comprobar = false;
		if(fichero.exists()){  
		 BufferedReader br = new BufferedReader (new FileReader ("Peliculas"+numero+".txt")); // Leemos la primera linea
		 FileInputStream fstream = new FileInputStream("Peliculas"+numero+".txt");
		 DataInputStream entrada = new DataInputStream(fstream);
		 BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
		  
		 while ((strLinea = buffer.readLine()) != null)   { 
				 if (nombrepelicula.equals(strLinea)){
					 comprobar = true;
				 }
			   }
		 br.close();
		 fstream.close();
		 entrada.close();
		 buffer.close();
		}
		return comprobar;
		}
	 /*Dado un numero de servidor recorre el archivo txt correspondiente y devuelve todas las peliculas plasmadas.*/
	 public static ArrayList<String> BusquedaPeliculasDisponiblesPorServidor(int numeroSv) throws java.io.IOException{
		 ArrayList<String> peliculas = new ArrayList<String>();
	 String strLinea; 	
	 File fichero = new File("Peliculas"+numeroSv+".txt");
		if(fichero.exists()){  
		
			 BufferedReader br = new BufferedReader (new FileReader ("Peliculas"+numeroSv+".txt")); // Leemos la primera linea
			 FileInputStream fstream = new FileInputStream("Peliculas"+numeroSv+".txt");
			 DataInputStream entrada = new DataInputStream(fstream);
			 BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
			  
			 while ((strLinea = buffer.readLine()) != null)   { 
				 System.out.println(strLinea);
				 peliculas.add(strLinea);
			 }
			 br.close();
			 fstream.close();
			 entrada.close();
			 buffer.close();
	 
		}
		return peliculas;
		
	 }
	 /*Dado un numero de servidor recorre el archivo correspondiente, suma una unidad a la cantidad de descargas plasmadas y actualiza el archivo.*/
	 public static void sumarDescarga(int numeroSv) throws java.io.IOException{
	 String strLinea;
	 int cantidad;
	 cantidad=0;
	 File fichero = new File("NumeroDescargas"+numeroSv+".txt");
		if(fichero.exists()){  
		
			 BufferedReader br = new BufferedReader (new FileReader ("NumeroDescargas"+numeroSv+".txt")); // Leemos la primera linea
			 FileInputStream fstream = new FileInputStream("NumeroDescargas"+numeroSv+".txt");
			 DataInputStream entrada = new DataInputStream(fstream);
			 BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
			  
			 while ((strLinea = buffer.readLine()) != null)   { 
				 cantidad=Integer.parseInt(strLinea);
			 }
			 br.close();
			 fstream.close();
			 entrada.close();
			 buffer.close();
		}
			BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero,true), "utf-8"));   
		  
			 Fescribe.write(cantidad+1+"\r\n");  
             Fescribe.close(); 
	 
	 }
	 /*Dado un numero de servidor recorre el archivo correspondiente al numero de descargas y devuelve la cantidad de descargas.*/
	 public static int buscarCantidadDescargas(int numeroSv) throws java.io.IOException{
		 String strLinea;
		 int cantidad;
		 cantidad=0;
		 File fichero = new File("NumeroDescargas"+numeroSv+".txt");
			if(fichero.exists()){  
			
				 BufferedReader br = new BufferedReader (new FileReader ("NumeroDescargas"+numeroSv+".txt")); // Leemos la primera linea
				 FileInputStream fstream = new FileInputStream("NumeroDescargas"+numeroSv+".txt");
				 DataInputStream entrada = new DataInputStream(fstream);
				 BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
				  
				 while ((strLinea = buffer.readLine()) != null)   { 
					 cantidad=Integer.parseInt(strLinea);
				 }
				 br.close();
				 fstream.close();
				 entrada.close();
				 buffer.close();
			}
		 return cantidad;
		 }
	 
	 
	 
	
}
