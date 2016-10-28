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
		Vector objectsToInspect = new Vector();
		
		if(obj != null){
			Class ObjClass = obj.getClass();
			
		
			System.out.println("inside inspector: " + obj + " (recursive = "+recursive+")");
			
			//inspect the current class
				inspectClass(obj, ObjClass,objectsToInspect);
				inspectMethods(obj, ObjClass,objectsToInspect);
				inspectConstructor(obj, ObjClass,objectsToInspect);
				inspectFields(obj, ObjClass,objectsToInspect);
				
				if(recursive)
					inspectFieldClasses( obj, ObjClass, objectsToInspect, recursive);   
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
    	System.out.println("\n****************** Inspecting Declaring Class ******************\n");
    	System.out.println("Class Name: " + obj.getClass().getName());
    	System.out.println("\n****************** Inspecting Immediate Superclass ******************\n");
    	System.out.println("Immediate Super Class: " + obj.getClass().getSuperclass().getName());
    	System.out.println("\n****************** Inspecting Interfaces ******************\n");
    
    	for ( Class interF : obj.getClass().getInterfaces()) {
    		System.out.println("Interface: " + interF.getName());
		}
    }
    /**
     * 
     * @param obj
     * @param ObjClass
     * @param objectsToInspectj
     */
    private void inspectMethods(Object obj,Class ObjClass,Vector objectsToInspectj){
    	System.out.println("\n****************** Inspecting Methods of Class ******************");
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
    	System.out.println("\n****************** Inspecting Constructor ******************");
    	
    	for (Constructor<?> construc : obj.getClass().getConstructors()) {
			System.out.println("Constructor: " + construc.getName());
			for (Parameter param : construc.getParameters()) {
				System.out.println("Parameter " + param.getParameterizedType());
			}
			System.out.println("Modifier: " + Modifier.toString(construc.getModifiers()));
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
		    System.out.println("---- Inspecting Field Classes ----");
		
		Enumeration e = objectsToInspect.elements();
		while(e.hasMoreElements()){
			Field f = (Field) e.nextElement();
			System.out.println("Inspecting Field: " + f.getName() );
			
			try{
				System.out.println("******************");
				inspect(f.get(obj) , recursive);
				System.out.println("******************");
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
    	System.out.println("\n****************** Inspecting Fields ******************");
    	if(ObjClass.getDeclaredFields().length >= 1){
    		for (Field f : ObjClass.getDeclaredFields()) {
    			f.setAccessible(true);
    			
    			if(!f.getType().isPrimitive()) 
    			    objectsToInspect.addElement(f);
    			
    			try{
    				if (f.getType().isArray())
    					System.out.format("--------------------------------------- Array%n"
    							+ "Length: %s%n"
				 			    + "Component Type: %s%n",
				 			   Array.getLength(f.get(obj)), f.getType().getComponentType().getName());
    					
    					
    				System.out.println("\nType: " + f.getType().getTypeName() + "\nField: " + f.getName() 
    									+ " = " + f.get(obj).getClass().getTypeName() + "\nModifier: " 
    									+ Modifier.toString(f.getModifiers()) + "\n");	
    			}
    			catch(Exception e) {}    
			}
		if(ObjClass.getSuperclass() != null )
			if(ObjClass.getSuperclass().getDeclaredFields().length >=1)
				inspectFields(obj, ObjClass.getSuperclass() , objectsToInspect);
	    }
    }
}
