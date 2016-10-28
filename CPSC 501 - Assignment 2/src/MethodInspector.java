import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class MethodInspector extends FieldInspector {

	public MethodInspector() {
		super();
	}

	/**
	 * Purpose: This method simply tells us all the methods available
	 * 			in the class
	 * 
	 * Detail: The method's name including other properties listed below
	 * 		   are printed
	 * 
	 * 		•	The exceptions thrown
	 *		•	The parameter types
	 *		•	The return type
	 *		•	The modifiers
     *
	 * @param obj
	 * @param ObjClass
	 * @param objectsToInspectj
	 */
	protected void inspectMethods(Object obj) {
		System.out.println("\n***** Inspecting Methods of Class *****");
		for (Method method : obj.getClass().getMethods()) {
			if(method.getDeclaringClass().equals(obj.getClass())){
				System.out.println("\nName: " + method.getName());
					// A loop is required for both exception and parameter 
					// as there can be more than one exception and parameter on a method 
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
	}//RETURN inspectMethods
	/**
	 * Purpose: This method gives us details about all the available
	 * 			constructors in a given class
	 * 
	 * Detail: The parameters and modifiers of the constructor will be
	 * 		   printed
	 * 
	 * @param obj
	 */
	protected void inspectConstructor(Object obj) {
		System.out.println("\n***** Inspecting Constructor *****\n");
		
		for (Constructor<?> construc : obj.getClass().getConstructors()) {
			System.out.println("Constructor: " + construc.getName());
			for (Parameter param : construc.getParameters()) {
				System.out.println("Parameter: " + param.getParameterizedType());
			}
			System.out.println("Modifier: " + Modifier.toString(construc.getModifiers()) + "\n");
		}
	}//RETURN inspectConstructor
}