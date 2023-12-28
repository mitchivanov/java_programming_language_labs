// Иванов Михаил, 5030102/10101
import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class Inform {

    private static int countEntries(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
            return lineCount;
        }
    }

    private static int countSymbols(String filePath) {
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.read() != -1) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    private static void printTree(Map<Character, String> codeTable) {
        System.out.println("Дерево кодирования:");
        for (Map.Entry<Character, String> entry : codeTable.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static void inform() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к файлу, с которым вы хотите сравнить закодированный текст:");
        String pathToDecoded = scanner.nextLine();

        System.out.println("Введите путь к файлу с таблицей кодов:");
        String codeTablePath = scanner.nextLine();

        System.out.println("Введите путь к закодированному файлу:");
        String encodedFilePath = scanner.nextLine();

        try {
            int symbolAmountDecoded = countSymbols(pathToDecoded);
            int codeTableSize = countEntries(codeTablePath);

            int infoVolumePerSymbol = (int) Math.ceil(Math.log(codeTableSize) / Math.log(2));
            int infoVolumeDecoded = infoVolumePerSymbol * symbolAmountDecoded;

            int symbolAmountEncoded = countSymbols(encodedFilePath);

            double compressionDegree = (double) infoVolumeDecoded / symbolAmountEncoded;

            System.out.println("Размер декодированного файла: " + infoVolumeDecoded + " бит");
            System.out.println("Размер закодированного файла: " + symbolAmountEncoded + " бит");
            System.out.println("Степень сжатия: " + compressionDegree + "\n");

            // Вывод дерева кодирования
            Map<Character, String> codeTable = Decode.readCodeFile(codeTablePath);
            printTree(codeTable);

        } catch (IOException e) {
            System.out.println("Невозможно выполнить подсчет: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        inform();
    }
}
