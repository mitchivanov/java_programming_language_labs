import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Encode {

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
            System.err.println("Error reading file: " + e.getMessage());
        }
        return inputString.toString();
    }

    public static Map<Character, Integer> writeSymbols(String str) {
        Map<Character, Integer> dictionary = new HashMap<>();
        for (char sym : str.toCharArray()) {
            dictionary.put(sym, dictionary.getOrDefault(sym, 0) + 1);
        }
        return dictionary;
    }

    public static List<Node> createNodes(Map<Character, Integer> dictionary) {
        List<Node> nodeList = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : dictionary.entrySet()) {
            nodeList.add(new Node(entry.getValue(), entry.getKey()));
        }
        return nodeList;
    }

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
            if (nodeList.get(min2).getProbability() < nodeList.get(min1).getProbability()
                    && !nodeList.get(min2).isMerged()) {
                int tmp = min1;
                min1 = min2;
                min2 = tmp;
            }
            for (int i = 0; i < nodeList.size(); i++) {
                if (nodeList.get(i).getProbability() <= nodeList.get(min1).getProbability()
                        && !nodeList.get(i).isMerged()) {
                    min2 = min1;
                    min1 = i;
                } else if (nodeList.get(i).getProbability() <= nodeList.get(min2).getProbability()
                        && !nodeList.get(i).isMerged()) {
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

    public static void writeInFile(String encodedString, String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(encodedString);
            System.out.println("Encoded string has been written to the file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void writeCodeTableInFile(Map<Character, String> codeTable, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<Character, String> entry : codeTable.entrySet()) {
                String line = entry.getKey() + " : " + entry.getValue();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Code table has been written to the file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing code table to file: " + e.getMessage());
        }
    }

    public static String encode_process(String initial, Map<Character, String> codeTable, String encodedFilePath, String codeTableFilePath) {
        StringBuilder readyFor = new StringBuilder();
        for (char letter : initial.toCharArray()) {
            readyFor.append(codeTable.get(letter));
        }
        writeInFile(readyFor.toString(), encodedFilePath);
        writeCodeTableInFile(codeTable, codeTableFilePath);
        return readyFor.toString();
    }

    public static void encode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the path of file you want to encode: ");
        String filePath = scanner.nextLine();

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.err.println("Invalid file path. Please enter a valid file path.");
            return;
        }

        String inputString = createInputString(filePath);
        Map<Character, Integer> symbolDictionary = writeSymbols(inputString);
        List<Node> nodeList = createNodes(symbolDictionary);

        System.out.println("Please enter the path for the encoded file: ");
        String encodedFilePath = scanner.nextLine();

        System.out.println("Please enter the path for the code table file: ");
        String codeTableFilePath = scanner.nextLine();

        Map<Character, String> codeTable = huffmanTree(nodeList);
        System.out.println(encode_process(inputString, codeTable, encodedFilePath, codeTableFilePath) + "\n");
    }
}
