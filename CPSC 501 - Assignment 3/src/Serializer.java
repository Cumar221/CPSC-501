import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Vector;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Serializer {
	public static Document serialize(Object obj){
		Vector objectsToInspect = new Vector<Object>();
		IdentityHashMap identityHashMap = new IdentityHashMap<>();
		
		String id = Integer.toString(identityHashMap.size()); 
		identityHashMap.put(obj, id); 

		Element root = new Element("serialized");
		Document doc = new Document(root);
		 
		try {
			doc = serializeHelper(doc,obj,objectsToInspect, identityHashMap);
			inspectFieldClasses(doc, obj, objectsToInspect, identityHashMap);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	public static Document serializeHelper(Document doc, Object obj, Vector objectsToInspect, IdentityHashMap identityHashMap) 
			throws IllegalArgumentException, IllegalAccessException{
		try {
			
			String id = String.valueOf(identityHashMap.get(obj));
			
			Element object = new Element("Object");
			object.setAttribute(new Attribute("id", id));
			object.setAttribute( new Attribute("class", obj.getClass().getName()));
			
			
			Element value =  null;
			Element field = null;
			Element reference = null;
			
			ArrayList<Element> fieldArryElem = new ArrayList<>();
			ArrayList<Element> objArryElem = new ArrayList<>();
			ArrayList<Element> valueArryElem = new ArrayList<>();
			ArrayList<Element> refArryElem = new ArrayList<>();
			ArrayList<Element> temp = new ArrayList<>();
			objArryElem.add(object);
			
			
			if(obj.getClass().isArray()){
				String fieldVal;
				
				object = new Element("Object");
				object.setAttribute(new Attribute("id", id));
				object.setAttribute( new Attribute("class", obj.getClass().getName()));
				object.setAttribute( new Attribute("length", String.valueOf(Array.getLength(obj))));
				
				for(int i = 0; i < Array.getLength(obj); i++){
					if(!Array.get(obj, i).getClass().getName().contains("java.lang")){
						System.out.println("Found");
						
						id = Integer.toString(identityHashMap.size());
						field = new Element("Field");
						identityHashMap.put(Array.get(obj,i), id);
						//objectsToInspect.addElement(Array.get(obj, i));
						
						reference = new Element("Reference");
						reference.addContent(id);
					
						refArryElem.add(reference);
						temp = refArryElem;
					}
					else{
						fieldVal = String.valueOf(Array.get(obj, i));
						value = new Element("Value");
						value.addContent(fieldVal);
						valueArryElem.add(value);
						temp = valueArryElem;
					
					}
				}
				object.setContent(temp);
			}
			for (Field f : obj.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				String fieldName = f.getName();
				String fieldVal = f.get(obj).toString();
				String fieldDecClass = f.getDeclaringClass().getName();
			
				
                
				if (!(f.getType().isPrimitive() || f.get(obj).getClass().getName().contains("java.lang"))){
					field = new Element("Field");
					id = Integer.toString(identityHashMap.size());
					identityHashMap.put(f.get(obj), id);
					objectsToInspect.addElement(f);
					
					reference = new Element("Reference");
					reference.addContent(id);
					
					field.setContent(reference);
					fieldArryElem.add(field);
				}
				else{
					field = new Element("Field");
					field.setAttribute(new Attribute("name" ,fieldName));
					field.setAttribute(new Attribute("declaringClass" , fieldDecClass));
				
					
					if (obj.getClass().isArray()){
						field.setAttribute( new Attribute("length", String.valueOf(Array.getLength(f.get(obj)))));
						for(int i = 0; i < Array.getLength(f.get(obj)); i++){
							fieldVal = String.valueOf(Array.get(f.get(obj), i));
							value = new Element("Value");
							value.addContent(fieldVal);
							valueArryElem.add(value);
						}
						
						field.setContent(valueArryElem);
					}
					
					else{
						value = new Element("Value");
						value.addContent(fieldVal);
						
						field.setContent(value);
						fieldArryElem.add(field);
					}
				}
				object.setContent(fieldArryElem);
			}
			
			doc.getRootElement().addContent(object);
			
			
			// new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("xmlFile.xml"));

			System.out.println("File Saved!");
			
		  } catch (IOException io) {
			System.out.println(io.getMessage());
		  }
		return doc;
	}
	public static void inspectFieldClasses(Document doc, Object obj, Vector objectsToInspect, 
			IdentityHashMap identityHashMap) {
		if(objectsToInspect.size() > 0 ){
		
		Enumeration e = objectsToInspect.elements(); //this vector keeps all the non-primitive data 
		while(e.hasMoreElements()){	// the needs to be further inspected
			//Object o = e.nextElement();
			
			Object test = e.nextElement();
			System.out.println("FFFFF" + test.getClass().getSimpleName());
			Field f = (Field) test;
			
			try{
				serializeHelper(doc, f.get(obj), objectsToInspect, identityHashMap);
			}
			catch(Exception exp) { exp.printStackTrace(); }
		}
	}
	}//RETUR
}

