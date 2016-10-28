import java.util.Enumeration;

public class ClassInspector extends MethodInspector {

	public ClassInspector() {
		super();
	}
	/**
	 * Purpose: This method simply let us know what interface 
	 * 			if any a particular class is using
	 * 
	 * Details: The interface name will be only printed out
	 *  
	 * @param obj
	 * @param ObjClass
	 * @param objectsToInspectj
	 */
	protected void inspectInterface(Object obj) {
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
	 * Purpose: This method gives up some info on the declared class
	 * 
	 * Detail: Just prints out the name of the class
	 * 
	 * @param obj
	 */
	protected void inspectDeclerationClass(Object obj) {
		System.out.println("\n***** Inspecting Declaring Class *****\n");
		if(!obj.getClass().getName().isEmpty())
			System.out.println("Class Name: " + obj.getClass().getName());
		else
			System.out.println("NULL");
	}
	/**
	 * Purpose: This method simply provides info on any immediate 
	 * 			super classes a partiular class may be using
	 * 
	 * Details: Just prints out the names of the super classes used
	 * 
	 * @param obj
	 */
	protected void inspectImmediateSuperClass(Object obj) {
		System.out.println("\n***** Inspecting Immediate Superclass ******\n");
		if(!obj.getClass().getSuperclass().getName().isEmpty())
			System.out.println("Immediate Super Class: " + obj.getClass().getSuperclass().getName());
		else
			System.out.println("NULL");
	}
}