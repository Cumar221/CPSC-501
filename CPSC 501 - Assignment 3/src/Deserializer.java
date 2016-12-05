import java.io.File;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Deserializer {
	public static void deserialize(Document document){
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("xmlfile.xml");	
		
	
			//document = (Document) builder.build(xmlFile);
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
			 
		  
	

	}

}
