import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Deserializer {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException { 
		deserialize();
	}
	
	public static void deserialize(){
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("xmlfile.xml");	
		try {
	
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("Object");
		
			
			for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);
				for (Attribute attr : node.getAttributes()) {
					System.out.println("********************************");
					System.out.println("Object Attribute" + attr);
					
				}
				System.out.println("********************************");
				
				for (Element field : node.getChildren()) {	
					for (Attribute attr : field.getAttributes()) {
						System.out.println("\nValue ----------------------" + attr);
					}
					for (Content cont : field.getContent()) {
						System.out.println("Value ----------------------" + cont.getValue());
					}
				}
			}
			 
		  } catch (IOException io) {
			System.out.println(io.getMessage());
		  } catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		  }
	

	}

}
