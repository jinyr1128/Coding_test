import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();

        // Calculate coordinates for the first number
        int x1 = (num1 - 1) / 4;
        int y1 = (num1 - 1) % 4;

        // Calculate coordinates for the second number
        int x2 = (num2 - 1) / 4;
        int y2 = (num2 - 1) % 4;

        // Calculate Manhattan distance
        int distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);

        System.out.println(distance);
    }
}
