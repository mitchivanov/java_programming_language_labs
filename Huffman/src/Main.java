import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean shouldRun = true;

        while (shouldRun) {
            System.out.println("""
                    Пожалуйста, введите режим работы программы:
                    1. Кодирование
                    2. Декодирование
                    3. Информация
                    4. Выход""");

            String operatingMode = scanner.nextLine().toLowerCase(); // Приведем введенный режим к нижнему регистру

            switch (operatingMode) {
                case "кодирование", "1" -> Encode.encode();
                case "декодирование", "2" -> Decode.decode();
                case "информация", "3" -> Inform.inform();
                case "выход", "4" -> {
                    shouldRun = false;
                    System.out.println("До свидания!");
                }
                default -> System.out.println("Неизвестный режим. Пожалуйста, введите действительный вариант.");
            }
        }

        scanner.close(); // Закрываем Scanner, чтобы избежать утечки ресурсов
    }
}
