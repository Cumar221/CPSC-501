import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;


public class TestJunit {
	ClassA classA =  new ClassA();
	ClassA classA_2 =  new ClassA(12);
	ClassD classD =  new ClassD();
	ClassD classD_2 =  new ClassD(32);
	ClassB[] classBArray = new ClassB[12];
	ClassB[][] classB2DArray = new ClassB[12][12];
			

   @Test
   public void declaringClassName()  {
	 assertEquals(classA.getClass().getName().toString(), "ClassA");
	 assertEquals(classA_2.getClass().getName().toString(), "ClassA");
	 assertEquals(classD.getClass().getName().toString(), "ClassD");
	 assertEquals(classD_2.getClass().getName().toString(), "ClassD");
	 assertEquals(classBArray.getClass().getTypeName(), "ClassB[]");
	 assertEquals(classB2DArray.getClass().getTypeName(), "ClassB[][]");
  }
   @Test
   public void immediateSuperClass(){
	   assertEquals(classA.getClass().getSuperclass().getName(), "java.lang.Object");
	   assertEquals(classA_2.getClass().getSuperclass().getName(), "java.lang.Object");
	   assertEquals(classD.getClass().getSuperclass().getName(), "java.lang.Object");
	   assertEquals(classD_2.getClass().getSuperclass().getName(), "java.lang.Object");
	   assertEquals(classBArray.getClass().getSuperclass().getName(), "java.lang.Object");
	   assertEquals(classB2DArray.getClass().getSuperclass().getName(), "java.lang.Object");
   }
   @Test
   public void interfaceImplemented(){
	   assertEquals(classA.getClass().getInterfaces()[0].getName(), "java.io.Serializable");
	   assertEquals(classA.getClass().getInterfaces()[1].getName(), "java.lang.Runnable");
	   assertEquals(classA_2.getClass().getInterfaces()[0].getName(), "java.io.Serializable");
	   assertEquals(classA_2.getClass().getInterfaces()[1].getName(), "java.lang.Runnable");
	   assertEquals(classD.getClass().getInterfaces().length, 0);
	   assertEquals(classD_2.getClass().getInterfaces().length, 0);
	   assertEquals(classBArray.getClass().getInterfaces()[0].getName(), "java.lang.Cloneable");
	   assertEquals(classBArray.getClass().getInterfaces()[1].getName(), "java.io.Serializable");
	   assertEquals(classB2DArray.getClass().getInterfaces()[0].getName(), "java.lang.Cloneable");
	   assertEquals(classB2DArray.getClass().getInterfaces()[1].getName(), "java.io.Serializable");
   }
   @Test
   public void classMethods(){
	   assertEquals(classA.getClass().getDeclaredMethods()[0].getName(), "run");
	   assertEquals(classA.getClass().getDeclaredMethods()[0].getExceptionTypes().length, 0);
	   assertEquals(classA.getClass().getDeclaredMethods()[0].getParameters().length, 0);
	   assertEquals(classA.getClass().getDeclaredMethods()[0].getReturnType().getName(), "void");
	   assertEquals(Modifier.toString(classA.getClass().getDeclaredMethods()[0].getModifiers()), "public");
	   
	   assertEquals(classA.getClass().getDeclaredMethods()[1].getName(), "toString");
	   assertEquals(classA.getClass().getDeclaredMethods()[1].getExceptionTypes().length, 0);
	   assertEquals(classA.getClass().getDeclaredMethods()[1].getParameters().length, 0);
	   assertEquals(classA.getClass().getDeclaredMethods()[1].getReturnType().getName(), "java.lang.String");
	   assertEquals(Modifier.toString(classA.getClass().getDeclaredMethods()[1].getModifiers()), "public");
	   
	   
	   assertEquals(classA.getClass().getDeclaredMethods()[2].getName(), "setVal");
	   assertEquals(classA.getClass().getDeclaredMethods()[2].getExceptionTypes()[0].getName(), "java.lang.Exception");
	   assertEquals(classA.getClass().getDeclaredMethods()[2].getParameters()[0].getParameterizedType().getTypeName(), "int");
	   assertEquals(classA.getClass().getDeclaredMethods()[2].getReturnType().getName(), "void");
	   assertEquals(Modifier.toString(classA.getClass().getDeclaredMethods()[2].getModifiers()), "public");
   }
   @Test
   public void classConstructor(){
	   assertEquals(classA.getClass().getConstructors()[0].getName(), "ClassA");
	   assertEquals(classA.getClass().getTypeParameters().length, 0);
	   assertEquals(Modifier.toString(classA.getClass().getModifiers()), "public final");
	   
	   assertEquals(classA_2.getClass().getConstructors()[0].getName(), "ClassA");
	   assertEquals(classA_2.getClass().getTypeParameters().length, 0);
	   assertEquals(Modifier.toString(classA_2.getClass().getModifiers()), "public final");
	   
	   assertEquals(classD.getClass().getConstructors()[0].getName(), "ClassD");
	   assertEquals(classD.getClass().getTypeParameters().length, 0);
	   assertEquals(Modifier.toString(classD.getClass().getModifiers()), "public");
	   
	   assertEquals(classD_2.getClass().getConstructors()[0].getName(), "ClassD");
	   assertEquals(classD_2.getClass().getTypeParameters().length, 0);
	   assertEquals(Modifier.toString(classD_2.getClass().getModifiers()), "public");
	   
	   assertEquals(classBArray.getClass().getConstructors().length, 0);
	   
	   
	   assertEquals(classB2DArray.getClass().getConstructors().length, 0); 
   }
   @Test
   public void classFields(){
	   assertEquals(classA.getClass().getDeclaredFields().length, 3);
	   
	   assertEquals(classA.getClass().getDeclaredFields()[0].getName(), "val");
	   assertEquals(classA.getClass().getDeclaredFields()[0].getType().getName(), "int");
	   assertEquals(Modifier.toString(classA.getClass().getDeclaredFields()[0].getModifiers()), "private");
	   
	   assertEquals(classA.getClass().getDeclaredFields()[1].getName(), "val2");
	   assertEquals(classA.getClass().getDeclaredFields()[1].getType().getName(), "double");
	   assertEquals(Modifier.toString(classA.getClass().getDeclaredFields()[1].getModifiers()), "private");
	   
	   assertEquals(classA.getClass().getDeclaredFields()[2].getName(), "val3");
	   assertEquals(classA.getClass().getDeclaredFields()[2].getType().getName(), "boolean");
	   assertEquals(Modifier.toString(classA.getClass().getDeclaredFields()[2].getModifiers()), "private");
	   
	   assertEquals(classD.getClass().getDeclaredFields().length, 4);
	   
	   assertEquals(classD.getClass().getDeclaredFields()[0].getName(), "val");
	   assertEquals(classD.getClass().getDeclaredFields()[0].getType().getName(), "ClassA");
	   assertEquals(Modifier.toString(classD.getClass().getDeclaredFields()[0].getModifiers()), "private");
	   
	   assertEquals(classD.getClass().getDeclaredFields()[1].getName(), "val2");
	   assertEquals(classD.getClass().getDeclaredFields()[1].getType().getName(), "ClassA");
	   assertEquals(Modifier.toString(classD.getClass().getDeclaredFields()[1].getModifiers()), "private static");
	   
	   assertEquals(classD.getClass().getDeclaredFields()[2].getName(), "val3");
	   assertEquals(classD.getClass().getDeclaredFields()[2].getType().getName(), "int"); 
	   assertEquals(Modifier.toString(classD.getClass().getDeclaredFields()[2].getModifiers()), "private");
	   
	   assertEquals(classD.getClass().getDeclaredFields()[3].getName(), "vallarray");
	   assertEquals(classD.getClass().getDeclaredFields()[3].getType().getTypeName(), "ClassA[]");
	   assertEquals(Modifier.toString(classD.getClass().getDeclaredFields()[3].getModifiers()), "private");
   }
   @Test
   public void fieldArray(){ 
	   assertEquals(classBArray.getClass().isArray(), true);
	   assertEquals(Array.getLength(classBArray), 12);
	   assertEquals(Array.get(classBArray, 0), null);
	   assertEquals(Array.get(classBArray, 1), null);
	   assertEquals(Array.get(classBArray, 2), null);
	   assertEquals(Array.get(classBArray, 3), null);
	   assertEquals(Array.get(classBArray, 4), null);
	   assertEquals(Array.get(classBArray, 5), null);
	   assertEquals(Array.get(classBArray, 6), null);
	   assertEquals(Array.get(classBArray, 7), null);
	   assertEquals(Array.get(classBArray, 8), null);
	   assertEquals(Array.get(classBArray, 9), null);
	   assertEquals(Array.get(classBArray, 10), null);
	   assertEquals(Array.get(classBArray, 11), null);
   }
   @Test
   public void field2DArray(){
	   System.out.println(Array.getLength(classB2DArray));
	   
	   assertEquals(classB2DArray.getClass().isArray(), true);
	   assertEquals(Array.getLength(classB2DArray), 12);
	   assertEquals(Array.getLength(Array.get(classB2DArray,0)), 12);
	   
	   for (int x = 0; x < 12; x++ ){
		   for(int i = 0; i < 12; i++){
			  assertEquals(classB2DArray[x][i], null);
		   }
	   }
   }
}