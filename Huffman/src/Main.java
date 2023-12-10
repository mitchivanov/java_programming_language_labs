import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean shouldRun = true;

        while (shouldRun) {
            System.out.println("Please, enter the program's operating mode:\n" +
                    "1. Encoding\n" +
                    "2. Decoding\n" +
                    "3. Informing\n" +
                    "4. Exit");

            String operatingMode = scanner.nextLine().toLowerCase(); // Приведем введенный режим к нижнему регистру

            switch (operatingMode) {
                case "encoding":
                    Encode.encode();
                    break;
                case "decoding":
                    Decode.decode();
                    break;
                case "informing":
                    Inform.inform();
                    break;
                case "exit":
                    shouldRun = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Unknown mode. Please, enter a valid option.");
            }
        }

        scanner.close(); // Закрываем Scanner, чтобы избежать утечки ресурсов
    }
}
