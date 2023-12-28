class MyPerson {
    private final String name;
    private final int age;
    private final String address;
    private final String occupation;
    private final String gender;

    public MyPerson(String name, int age, String address, String occupation, String gender) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.occupation = occupation;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", occupation='" + occupation + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

class SwapUtil {
    public static <T> void swap(T obj1, T obj2) {
        try {
            java.lang.reflect.Field[] fields = obj1.getClass().getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                field.setAccessible(true);
                Object temp = field.get(obj1);
                field.set(obj1, field.get(obj2));
                field.set(obj2, temp);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MyPerson person1 = new MyPerson("Misha", 20, "Saint Petersburg", "Student", "Male");
        MyPerson person2 = new MyPerson("Lena", 25, "Moscow", "Teacher", "Female");

        System.out.println("Before:");
        System.out.println("Person 1: " + person1);
        System.out.println("Person 2: " + person2);


        SwapUtil.swap(person1, person2);

        System.out.println("After:");
        System.out.println("Person 1: " + person1);
        System.out.println("Person 2: " + person2);
    }
}

