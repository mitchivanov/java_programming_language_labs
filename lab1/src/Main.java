public class Main {
    public static void main(String[] args) {
        String str1 = "Java";
        String str2 = "    study    ";
        System.out.println("str1: " + str1);
        System.out.println("str2: " + str2);

        System.out.println("Длина строки str1: " + str1.length());

        System.out.println("Символ в str1 по индексу 0: " + str1.charAt(0));

        System.out.println("Объединение str1 и str2: " + str1.concat(str2));

        System.out.println("str1 и str2 равны? " + str1.equalsIgnoreCase(str2));

        System.out.println("Индекс первого вхождения 'study' в str1: " + str2.indexOf("study"));

        System.out.println("Подстрока str1 со 2-го символа: " + str1.substring(2));

        System.out.println("str1 в нижнем регистре: " + str1.toLowerCase());
        System.out.println("str1 в верхнем регистре: " + str1.toUpperCase());

        System.out.println("str2 без пробелов вначале и конце: " + str2.trim());

        System.out.println("str2 с заменой слова: " + str2.replace("study", "learn"));

    }
}