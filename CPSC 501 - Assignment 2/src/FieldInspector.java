import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Vector;

public class FieldInspector {

	public FieldInspector() {
		super();
	}
	/**
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
	    			
	    			if(!f.getType().isPrimitive()) 
	    			    objectsToInspect.addElement(f);
	    			
	    			try{	
	    				System.out.println("\nField: " + f.getName()
	    									+ "	\nType: " + f.getType().getName()  
	    									+ " = " + f.get(obj).toString() + "\nModifier: " 
	    									+ Modifier.toString(f.getModifiers()));	
	    				if (f.getType().isArray())
	    					System.out.format("--------------------------------------- Array%n"
	    							+ "Length: %s%n"
					 			    + "Component Type: %s%n",
					 			   Array.getLength(f.get(obj)), f.getType().getComponentType().getName());
	    			}
	    			catch(Exception e) {}    
				}
			if(ObjClass.getSuperclass() != null )
				if(ObjClass.getSuperclass().getDeclaredFields().length >=1)
					inspectFields(obj, ObjClass.getSuperclass() , objectsToInspect);
		    }
	}

}