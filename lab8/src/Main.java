import java.lang.reflect.*;
import Java.classes.MyClass;

public class Main {
    public static void printClassInfo(Class<?> cls) {
        System.out.println("Class name: " + cls.getName());
        System.out.println("Package name: " + cls.getPackage().getName());

        Field[] fields = cls.getDeclaredFields();
        System.out.println("Fields:");
        for (Field field : fields) {
            System.out.println("  " + field.getType().getName() + " " + field.getName());
        }

        Constructor<?>[] constructors = cls.getDeclaredConstructors();
        System.out.println("Constructors:");
        for (Constructor<?> constructor : constructors) {
            System.out.print("  " + constructor.getName() + "(");
            Parameter[] parameters = constructor.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                System.out.print(parameters[i].getType().getName() + " " + parameters[i].getName());
                if (i < parameters.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");
        }

        Method[] methods = cls.getDeclaredMethods();
        System.out.println("Methods:");
        for (Method method : methods) {
            System.out.print("  " + method.getReturnType().getName() + " " + method.getName() + "(");
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                System.out.print(parameters[i].getType().getName() + " " + parameters[i].getName());
                if (i < parameters.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");
        }
    }

    public static void main(String[] args) {
        printClassInfo(MyClass.class);
    }
}