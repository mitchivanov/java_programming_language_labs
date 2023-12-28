// Иванов Михаил, 5030102/10101
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Encode {

    // Метод для чтения содержимого файла и создания строки ввода
    public static String createInputString(String filePath) {
        StringBuilder inputString = new StringBuilder();
        try {
            Path fileFullPath = Paths.get(filePath);
            FileReader fileReader = new FileReader(fileFullPath.toFile());
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    inputString.append(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return inputString.toString();
    }

    // Метод для записи символов и их частот в словарь
    public static Map<Character, Integer> writeSymbols(String str) {
        Map<Character, Integer> dictionary = new HashMap<>();
        for (char sym : str.toCharArray()) {
            dictionary.put(sym, dictionary.getOrDefault(sym, 0) + 1);
        }
        return dictionary;
    }

    // Метод для создания списка узлов на основе словаря символов и их частот
    public static List<Node> createNodes(Map<Character, Integer> dictionary) {
        List<Node> nodeList = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : dictionary.entrySet()) {
            nodeList.add(new Node(entry.getValue(), entry.getKey()));
        }
        return nodeList;
    }

    // Метод для построения кода Хаффмана и создания таблицы кодов
    public static Map<Character, String> huffmanTree(List<Node> nodeList) {
        int notSeen = nodeList.size();
        while (notSeen != 1) {
            int max = 0;
            for (int i = 0; i < nodeList.size(); i++) {
                if (nodeList.get(max).getProbability() <= nodeList.get(i).getProbability()) {
                    max = i;
                }
            }
            int min1 = max;
            int min2 = max;

            for (int i = 0; i < nodeList.size(); i++) {
                if (nodeList.get(i).getProbability() <= nodeList.get(min1).getProbability()
                        && nodeList.get(i).isMerged()) {
                    min2 = min1;
                    min1 = i;
                } else if (nodeList.get(i).getProbability() <= nodeList.get(min2).getProbability()
                        && nodeList.get(i).isMerged()) {
                    min2 = i;
                }
            }
            nodeList.add(new Node(nodeList.get(min1).getProbability() + nodeList.get(min2).getProbability(), '.'));
            nodeList.get(nodeList.size() - 1).setChildren(min2, min1);
            nodeList.get(min1).setParent(nodeList.size() - 1);
            nodeList.get(min2).setParent(nodeList.size() - 1);
            nodeList.get(min1).merging();
            nodeList.get(min2).merging();
            notSeen--;
        }
        Map<Character, String> codeTable = new HashMap<>();
        nodeList.get(nodeList.size() - 1).setCode("");
        for (int i = nodeList.size() - 2; i >= 0; i--) {
            if (nodeList.get(nodeList.get(i).getParent()).getLeft() == i) {
                nodeList.get(i).setCode(nodeList.get(nodeList.get(i).getParent()).getCode() + "0");
            }
            if (nodeList.get(nodeList.get(i).getParent()).getRight() == i) {
                nodeList.get(i).setCode(nodeList.get(nodeList.get(i).getParent()).getCode() + "1");
            }
            if (nodeList.get(i).getSymbol() != '.') {
                codeTable.put(nodeList.get(i).getSymbol(), nodeList.get(i).getCode());
            }
        }
        return codeTable;
    }

    // Метод для записи закодированной строки в файл
    public static void writeInFile(String encodedString, String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(encodedString);
            System.out.println("Закодированная строка записана в файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    // Метод для записи таблицы кодов в файл
    public static void writeCodeTableInFile(Map<Character, String> codeTable, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<Character, String> entry : codeTable.entrySet()) {
                String line = entry.getKey() + " : " + entry.getValue();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Таблица кодов записана в файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка при записи таблицы кодов в файл: " + e.getMessage());
        }
    }

    // Метод для обработки кодирования и записи результатов в файлы
    public static String encode_process(String initial, Map<Character, String> codeTable, String encodedFilePath, String codeTableFilePath) {
        StringBuilder readyFor = new StringBuilder();
        for (char letter : initial.toCharArray()) {
            readyFor.append(codeTable.get(letter));
        }
        writeInFile(readyFor.toString(), encodedFilePath);
        writeCodeTableInFile(codeTable, codeTableFilePath);
        return readyFor.toString();
    }

    // Метод для начала процесса кодирования
    public static void encode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Пожалуйста, введите путь к файлу для кодирования: ");
        String filePath = scanner.nextLine();

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.err.println("Неверный путь к файлу. Пожалуйста, введите действительный путь к файлу.");
            return;
        }

        String inputString = createInputString(filePath);
        Map<Character, Integer> symbolDictionary = writeSymbols(inputString);
        List<Node> nodeList = createNodes(symbolDictionary);

        System.out.println("Пожалуйста, введите путь для закодированного файла: ");
        String encodedFilePath = scanner.nextLine();

        System.out.println("Пожалуйста, введите путь для файла с таблицей кодов: ");
        String codeTableFilePath = scanner.nextLine();

        Map<Character, String> codeTable = huffmanTree(nodeList);
        System.out.println(encode_process(inputString, codeTable, encodedFilePath, codeTableFilePath) + "\n");
    }
}
