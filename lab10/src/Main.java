public class Main {
    public static void main(String[] args) {
        try {
            Triple<String> triple1 = new Triple<>("Misha", "Alex", "Tom");
            System.out.println("Triple<>(\"Misha\", \"Alex\", \"Tom\")");
            System.out.println("Min: " + triple1.min());
            System.out.println("Max: " + triple1.max());
            System.out.println("Mean: " + triple1.mean());
        } catch (RuntimeException e) {
            System.out.println("Exeption: " + e.getMessage());
        }

        try {
            Triple<Integer> triple1 = new Triple<>(3, 7, 5);
            System.out.println("Triple<>(3, 7, 5)");
            System.out.println("Min: " + triple1.min());
            System.out.println("Max: " + triple1.max());
            System.out.println("Mean: " + triple1.mean());
        } catch (RuntimeException e) {
            System.out.println("Exeption: " + e.getMessage());
        }

        try {
            Triple<Double> triple2 = new Triple<>(1.5, 2.7, 3.9);
            System.out.println("Triple<>(1.5, 2.7, 3.9)");
            System.out.println("Min: " + triple2.min());
            System.out.println("Max: " + triple2.max());
            System.out.println("Mean: " + triple2.mean());
        } catch (RuntimeException e) {
            System.out.println("Exeption: " + e.getMessage());
        }
    }
}