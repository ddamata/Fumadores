package servidor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Traza {

	public static void insertarTraza(String hora,String actor, String accion){
	      
	    Document document=null;
	      Element root=null;
	      File xmlFile = new File("Traza.xml");
	      if(xmlFile.exists()) {
	          FileInputStream fis;
	      try {
	        fis = new FileInputStream(xmlFile);
	            SAXBuilder sb = new SAXBuilder();
	            document = sb.build(fis);
	            root = document.getRootElement();
	            fis.close();
	      } catch (JDOMException | IOException e) {
	        e.printStackTrace();
	      }
	      } else {
	          document = new Document();
	          root = new Element("Traza");
	      }
	      
	      Element nodohecho = new Element("Hecho");    
	        Element nodohora = new Element("Hora");
	      Element nodoactor = new Element("Actor");
	        Element nodoaccion = new Element("Accion");
	        
	        nodohora.setText(hora);
	        nodoactor.setText(actor);
	        nodoaccion.setText(accion);
	        
	        nodohecho.addContent(nodohora);
	        nodohecho.addContent(nodoactor);
	        nodohecho.addContent(nodoaccion);
	        root.addContent(nodohecho);
	        document.setContent(root);
	        
	        
	        XMLOutputter xmlOutput = new XMLOutputter();
	        xmlOutput.setFormat(Format.getPrettyFormat());
	        
	        try {
	      xmlOutput.output(document, new FileWriter("Traza.xml"));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	        
	  }	
	
	
}
