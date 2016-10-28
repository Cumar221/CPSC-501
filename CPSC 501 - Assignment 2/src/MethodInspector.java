import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class MethodInspector extends FieldInspector {

	public MethodInspector() {
		super();
	}

	/**
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

	protected void inspectConstructor(Object obj) {
		System.out.println("\n***** Inspecting Constructor *****\n");
		
		for (Constructor<?> construc : obj.getClass().getConstructors()) {
			System.out.println("Constructor: " + construc.getName());
			for (Parameter param : construc.getParameters()) {
				System.out.println("Parameter: " + param.getParameterizedType());
			}
			System.out.println("Modifier: " + Modifier.toString(construc.getModifiers()) + "\n");
		}
	}

}