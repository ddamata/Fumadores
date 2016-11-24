package Servidor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hora {
	
	public static String horaActual(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

}
