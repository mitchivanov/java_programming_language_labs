package Java.classes;
public class MyClass {
    private final int myField;
    private String myStringField;
    public static final double PI = 3.14159;

    public MyClass() {
        this.myField = 0;
        this.myStringField = "default";
    }

    public void myMethod(int number, String text) {
        System.out.println("Number: " + number + ", Text: " + text);
    }

    public int getMyField() {
        return myField;
    }

    public String getMyStringField() {
        return myStringField;
    }

    public void setMyStringField(String myStringField) {
        this.myStringField = myStringField;
    }
}
