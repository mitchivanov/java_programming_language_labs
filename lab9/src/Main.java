import java.lang.reflect.*;
public class Main {
    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("Java.classes.MyClass");
            Constructor<?> constructor = cls.getConstructor();
            Object myClassObject = constructor.newInstance();

            Method method = cls.getMethod("myMethod", int.class, String.class);
            method.invoke(myClassObject, 10, "Misha");

        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        } catch (NoSuchMethodException e) {
            System.err.println("Method not found: " + e.getMessage());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.err.println("Error with creating: " + e.getMessage());
        }
    }
}