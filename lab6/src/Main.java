class Stack<T> {
    private Object[] stackArray;
    private int top;

    public Stack(int size) {
        stackArray = new Object[size];
        top = -1;
    }

    public Stack() {
        this(10);
    }

    public void push(T value) {
        ensureCapacity();
        stackArray[++top] = value;
    }

    public T pop() {
        if (top == -1) {
            System.out.println("Stack empty");
            return null;
        } else {
            T value = (T) stackArray[top];
            stackArray[top--] = null;
            return value;
        }
    }

    public T peek() {
        if (top == -1) {
            System.out.println("Stack empty");
            return null;
        } else {
            return (T) stackArray[top];
        }
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public int size() {
        return top + 1;
    }

    private void ensureCapacity() {
        if (top == stackArray.length - 1) {
            int newCapacity = stackArray.length * 2;
            stackArray = java.util.Arrays.copyOf(stackArray, newCapacity);
        }
    }

    public void printAll() {
        if (top == -1) {
            System.out.println("Stack empty");
        } else {
            System.out.print("Stack elements: ");
            for (int i = 0; i <= top; i++) {
                System.out.print(stackArray[i] + " ");
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(5);

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        System.out.println("Stack size: " + (stack.size()));

        System.out.println("Top element: " + stack.peek());

        System.out.print("Stack elements: ");
        while (!stack.isEmpty()) {
            int value = stack.pop();
            System.out.print(value + " ");
        }


        Stack<String> stack1 = new Stack<>();

        stack1.push("ab");
        stack1.push("bc");
        stack1.push("rt");
        stack1.push("zx");
        stack1.push("pq");
        System.out.println("\nStack size: " + (stack1.size()));

        System.out.println("Top element: " + stack1.peek());

        stack1.printAll();

    }
}