class Node {
    private int parent;
    private int left;
    private int right;
    private final int probability;
    private final char symbol;
    private String code;
    private boolean merged;

    // Конструктор для создания узла с заданной вероятностью и символом
    public Node(int probability, char symbol) {
        this.parent = -1;  // Индекс родителя (-1, чтобы указать отсутствие родителя)
        this.right = -1;   // Индекс правого потомка (-1, чтобы указать отсутствие потомка)
        this.left = -1;    // Индекс левого потомка (-1, чтобы указать отсутствие потомка)
        this.probability = probability;  // Вероятность (частота)
        this.symbol = symbol;  // Символ (если это листовой узел)
        this.code = "";  // Код Хаффмена для этого узла
        this.merged = false;  // Флаг объединения (используется при построении дерева)
    }

    // Устанавливает левого и правого потомка
    public void setChildren(int left, int right) {
        this.left = left;
        this.right = right;
    }

    // Устанавливает родителя
    public void setParent(int parent) {
        this.parent = parent;
    }

    // Помечает узел как объединенный (используется при построении дерева)
    public void merging() {
        this.merged = true;
    }

    // Получает символ узла (если это листовой узел)
    public char getSymbol() {
        return this.symbol;
    }

    // Получает индекс правого потомка
    public int getRight() {
        return this.right;
    }

    // Получает индекс левого потомка
    public int getLeft() {
        return this.left;
    }

    // Получает вероятность (частоту) узла
    public int getProbability() {
        return this.probability;
    }

    // Получает индекс родителя
    public int getParent() {
        return this.parent;
    }

    // Получает код Хаффмена для узла
    public String getCode() {
        return this.code;
    }

    // Устанавливает код Хаффмена для узла
    public void setCode(String code) {
        this.code = code;
    }

    // Проверяет, объединен ли узел
    public boolean isMerged() {
        return !this.merged;
    }
}
