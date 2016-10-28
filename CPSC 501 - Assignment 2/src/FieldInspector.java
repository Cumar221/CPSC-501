import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Vector;

public class FieldInspector {

	public FieldInspector() {
		super();
	}
	/**
	 * Purpose: This method finds and provides the details of each field
	 * 			declared in a given class.
	 * 
	 * Detail: The name of the class including its type and 
	 * 				modifier is printed
	 *  
	 * @param obj
	 * @param ObjClass
	 * @param objectsToInspect
	 */
	protected void inspectFields(Object obj, Class ObjClass, Vector objectsToInspect) {
	    	System.out.println("\n***** Inspecting Fields *****");
	    	if(ObjClass.getDeclaredFields().length >= 1){
	    		for (Field f : ObjClass.getDeclaredFields()) {
	    			f.setAccessible(true);
	    			
	    			if(!f.getType().isPrimitive()) //This is setting up the recursive part if need
	    			    objectsToInspect.addElement(f);//It's adding b/c we may need to extract detail 
	    												// of class or object
	    			try{	
	    				print(f, obj);
	    			}
	    			catch(Exception e) {}    
				}
			if(ObjClass.getSuperclass() != null )
				if(ObjClass.getSuperclass().getDeclaredFields().length >=1)
					inspectFields(obj, ObjClass.getSuperclass() , objectsToInspect);
		    }
	}//RETURN inspectFields
	/**
	 * Purpose: A helper function to inspectFields that prints out
	 * 			the information of each field in a class
	 * 
	 * @param f
	 * @param obj
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void print(Field f, Object obj) throws IllegalArgumentException, IllegalAccessException{
		System.out.println("\nField: " + f.getName()
							+ "	\nType: " + f.getType().getName()  
							+ " = " + f.get(obj).toString() + "\nModifier: " 
							+ Modifier.toString(f.getModifiers()));	
		if (f.getType().isArray())
					System.out.format("--------------------------------------- Array%n"
									+ "Length: %s%n"
									+ "Component Type: %s%n",
									Array.getLength(f.get(obj)), f.getType().getComponentType().getName());
	}//RETURN print
}