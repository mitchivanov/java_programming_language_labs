import java.io.*;
import java.util.*;

public class Decode {

    public static Map<Character, String> readCodeFile(String fileName) {
        Map<Character, String> codeTable = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" : ");
                if (parts.length == 2) {
                    char symbol = parts[0].charAt(0);
                    String code = parts[1];
                    codeTable.put(symbol, code);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла кодов: " + e.getMessage());
        }

        return codeTable;
    }

    public static void decode_process(String fileToWrite, Map<Character, String> codeTable) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите путь к закодированному файлу:");
            String encodedFilePath = scanner.nextLine();


            try (BufferedReader br = new BufferedReader(new FileReader(encodedFilePath));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(fileToWrite))) {

                StringBuilder encodedMessage = new StringBuilder();
                int c;

                // Чтение закодированного сообщения из файла
                while ((c = br.read()) != -1) {
                    encodedMessage.append((char) c);
                }

                // Декодирование сообщения
                StringBuilder currentCode = new StringBuilder();
                StringBuilder decodedMessage = new StringBuilder();

                for (char bit : encodedMessage.toString().toCharArray()) {
                    currentCode.append(bit);

                    // Проверяем, есть ли текущий код в таблице кодов
                    for (Map.Entry<Character, String> entry : codeTable.entrySet()) {
                        if (entry.getValue().contentEquals(currentCode)) {
                            // Найден соответствующий символ, добавляем его к декодированному сообщению
                            decodedMessage.append(entry.getKey());
                            // Сбрасываем текущий код
                            currentCode.setLength(0);
                            break;
                        }
                    }
                }

                // Запись раскодированного сообщения в файл
                bw.write(decodedMessage.toString());
                System.out.println("Сообщение успешно декодировано и записано в файл: " + fileToWrite);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при декодировании: " + e.getMessage());
        }
    }

    public static void decode() {
        System.out.println("Введите путь к файлу, в который будет записано декодированное сообщение:");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();

        System.out.println("Введите путь к файлу с таблицей кодов:");
        String codeTablePath = scanner.nextLine();
        Map<Character, String> codeTable = readCodeFile(codeTablePath);

        if (codeTable.isEmpty()) {
            System.out.println("Таблица кодов пуста или не удалось прочитать.");
            return;
        }

        decode_process(filePath, codeTable);
    }

    public static void main(String[] args) {
        decode();
    }
}
