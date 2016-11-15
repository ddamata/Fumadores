package Fumadores;

public class main {

	public static void main(String[] args) {
		
		Banco banco = new Banco();
		
		Vendedor vendedor = new Vendedor(banco);
		
		Fumadores[] fumador = new Fumadores[3];
		
		for ( int i = 0 ; i<fumador.length; i++ ){
			
			fumador[i] = new Fumadores (i,banco);
			
		}
		
		vendedor.start();
		
		for( int i = 0 ; i < fumador.length; i++){
			
			fumador[i].start();
		}

	}

}
