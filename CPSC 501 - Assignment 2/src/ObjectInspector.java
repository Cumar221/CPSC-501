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

public class ObjectInspector{
    
	public ObjectInspector() { }
    /**
     * 
     * @param obj
     * @param recursive
     */
    public void inspect(Object obj, boolean recursive){
		if(obj != null){
			Vector objectsToInspect = new Vector();
			Class ObjClass = obj.getClass();
			
	
			System.out.println("inside inspector: " + obj + " (recursive = "+recursive+")");
		
			if(obj.getClass().isArray()){
				for(int i = 0; i < Array.getLength(obj); i++){
					inspect(Array.get(obj, i), recursive);
				}
			}
			else{
			//inspect the current class
				inspectClass(obj, ObjClass,objectsToInspect);
				inspectMethods(obj, ObjClass,objectsToInspect);
				inspectConstructor(obj, ObjClass,objectsToInspect);
				inspectFields(obj, ObjClass,objectsToInspect);
				
				if(recursive)
					inspectFieldClasses( obj, ObjClass, objectsToInspect, recursive);  
			}
		}
		else{
			System.out.println("NULL");
		}
    }
    /**
     * 
     * @param obj
     * @param ObjClass
     * @param objectsToInspectj
     */
    private void inspectClass(Object obj,Class ObjClass,Vector objectsToInspectj){
    	System.out.println("\n***** Inspecting Declaring Class *****\n");
    	if(!obj.getClass().getName().isEmpty())
    		System.out.println("Class Name: " + obj.getClass().getName());
    	else
    		System.out.println("NULL");
    	
    	System.out.println("\n***** Inspecting Immediate Superclass ******\n");
    	if(!obj.getClass().getSuperclass().getName().isEmpty())
    		System.out.println("Immediate Super Class: " + obj.getClass().getSuperclass().getName());
    	else
    		System.out.println("NULL");
    	
    	System.out.println("\n***** Inspecting Interfaces *****\n");
    	if(obj.getClass().getInterfaces().length >= 1){
	    	for ( Class interF : obj.getClass().getInterfaces()) {
	    		System.out.println("Interface: " + interF.getName());
			}
    	}
    	else
    		System.out.println("NULL");
    }
    /**
     * 
     * @param obj
     * @param ObjClass
     * @param objectsToInspectj
     */
    private void inspectMethods(Object obj,Class ObjClass,Vector objectsToInspectj){
    	System.out.println("\n***** Inspecting Methods of Class *****");
    	for (Method method : obj.getClass().getMethods()) {
    		if(method.getDeclaringClass().equals(obj.getClass())){
    			System.out.println("\nName: " + method.getName());
    				for (Class excepThrown : method.getExceptionTypes()) {
						System.out.println("Exception Throws: " + excepThrown.getName());
					}
    				for (Parameter param : method.getParameters()) {
    					System.out.println("Parameter: " + param.getParameterizedType());
					}
    				System.out.println("Return Type: " + method.getReturnType().getName());
    				System.out.println("Modifier: " + Modifier.toString(method.getModifiers()));
    		}
		}
    }
    private void inspectConstructor(Object obj,Class ObjClass,Vector objectsToInspectj){
    	System.out.println("\n***** Inspecting Constructor *****\n");
    	
    	for (Constructor<?> construc : obj.getClass().getConstructors()) {
			System.out.println("Constructor: " + construc.getName());
			for (Parameter param : construc.getParameters()) {
				System.out.println("Parameter: " + param.getParameterizedType());
			}
			System.out.println("Modifier: " + Modifier.toString(construc.getModifiers()) + "\n");
		}
    }
    /**
     * 
     * @param obj
     * @param ObjClass
     * @param objectsToInspect
     * @param recursive
     */
    private void inspectFieldClasses(Object obj,Class ObjClass,Vector objectsToInspect,boolean recursive){
		if(objectsToInspect.size() > 0 )
		    System.out.println("\n\n================================================"
		    		+ " Inspecting Field Classes "
		    		+ "================================================\n\n");
		
		Enumeration e = objectsToInspect.elements();
		while(e.hasMoreElements()){
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
    }
    /**
     * 
     * @param obj
     * @param ObjClass
     * @param objectsToInspect
     */
    private void inspectFields(Object obj,Class ObjClass,Vector objectsToInspect){
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
