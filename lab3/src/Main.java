public class Main {
    public static void main(String[] args) {

        System.out.println("true && false: " + (true && false));
        System.out.println("true || false: " + (true || false));
        System.out.println("!true: " + !true);

        int x = 125, y = 360;
        System.out.println("Максимум из 125 и 360: " + ((x > y) ? x : y));

        System.out.println("5 & 1: " + (5 & 1));
        System.out.println("8 | 1: " + (8 | 1));
        System.out.println("7 ^ 1: " + (7 ^ 1));
        System.out.println("~4: " + (~4));

        System.out.println("5 >> 2: " + (5 >> 2));
        System.out.println("-5 >> 2: " + (-5 >> 2));
        System.out.println("5 >>> 2: " + (5 >>> 2));
        System.out.println("-5 >>> 20: " + (-5 >>> 20));
        System.out.println("138 << 3: " + (138 << 3));
    }
}