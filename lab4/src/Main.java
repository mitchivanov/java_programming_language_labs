import java.util.Scanner;

public class Main {
    enum Numbers {
        one, two, three, four, five
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число: ");
        int num = scanner.nextInt();
        switch (num) {
            case 1 -> System.out.println("Один");
            case 2 -> System.out.println("Два");
            default -> System.out.println("Другое число: " + num);
        }

        System.out.println("Введите букву a или b или что-то другое: ");
        char letter = scanner.next().charAt(0);
        scanner.nextLine();
        switch (letter) {
            case 'a' -> System.out.println("Буква A");
            case 'b' -> System.out.println("Буква B");
            default -> System.out.println("Другая буква: " + letter);
        }

        System.out.println("Введите строку hello bye или Misha или что-то другое: ");
        String in = scanner.nextLine();
        switch (in) {
            case "hello" -> System.out.println("Привет");
            case "bye" -> System.out.println("Пока");
            case "Misha" -> System.out.println("Привет Миша");
            default -> System.out.println("Другая строка" + in);
        }

        Numbers n = Numbers.one;
        switch (n) {
            case one -> System.out.println("one");
            case two -> System.out.println("two");
            case three -> System.out.println("three");
            case four -> System.out.println("four");
            case five -> System.out.println("five");
            default -> System.out.println("nothing");
        }
    }
}