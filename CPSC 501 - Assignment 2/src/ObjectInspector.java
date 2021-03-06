/*==========================================================================
File: ObjectInspector.java
Purpose:Demo Object inspector for the Asst2TestDriver

Location: University of Calgary, Alberta, Canada
Created By: Jordan Kidney
Created on:  Oct 23, 2005
Last Updated: Oct 23, 2005

***********************************************************************
If you are going to reproduce this code in any way for your asignment 
rember to include my name at the top of the file toindicate where you
got the original code from
***********************************************************************


========================================================================*/

import java.util.*;
import java.lang.reflect.*;

public class ObjectInspector extends ClassInspector {
	public ObjectInspector() {}
    /**
     * Purpose: This is the method that is called at runtime for
     * 			reflection. 
     * 
     * Detail: This method with the help of helper functions allows
     * 			the classes and objects to be inspected by finding 
     * 			details about their parameters, return types, methods
     * 			and many more
     * 
     * @param obj
     * @param recursive
     */
    public void inspect(Object obj, boolean recursive){
		if(obj != null){
			Vector objectsToInspect = new Vector();
			Class ObjClass = obj.getClass();
			
			System.out.println("inside inspector: " + obj.getClass().getTypeName() + " (recursive = "+recursive+")");
		
			if(obj.getClass().isArray()){		// This checks if the non-primitive types are an array and inspects them
				for(int i = 0; i < Array.getLength(obj); i++){
					inspect(Array.get(obj, i), recursive);
				}
			}
			else{
			//inspect the current class
				inspectDeclerationClass(obj);
				inspectImmediateSuperClass(obj);
				inspectInterface(obj);
				inspectMethods(obj);
				inspectConstructor(obj);
				inspectFields(obj, ObjClass,objectsToInspect);
				
				if(recursive)
					inspectFieldClasses( obj, ObjClass, objectsToInspect, recursive);  
			}
		}
		else{
			System.out.println("NULL");
		}
    }//RETURN inspect
    /**
	 * Purpose: This method allows Objects and Classes to be inspected further
	 * 
	 * Details: This method is executed if the recursive boolean is set to true
	 * 			and of course if there is non-primitive types the needs to be 
	 * 			inspected. 
	 * 
	 * @param obj
	 * @param ObjClass
	 * @param objectsToInspect
	 * @param recursive
	 */
	protected void inspectFieldClasses(Object obj, Class ObjClass, Vector objectsToInspect, boolean recursive) {
		if(objectsToInspect.size() > 0 )
		    System.out.println("\n\n================================================"
		    		+ " Inspecting Field Classes/Objects "
		    		+ "================================================\n\n");
		
		Enumeration e = objectsToInspect.elements(); //this vector keeps all the non-primitive data 
		while(e.hasMoreElements()){					// the needs to be further inspected
			Field f = (Field) e.nextElement();
			System.out.println("***************************************************");
			System.out.println("************ Inspecting Field: " + f.getName() + " ***************" );
			
			try{
				System.out.println("***************************************************\n");
				inspect(f.get(obj) , recursive);
				System.out.println("\n***************************************************");
			}
			catch(Exception exp) { exp.printStackTrace(); }
		}
	}//RETURN inspectFieldClasses
}
