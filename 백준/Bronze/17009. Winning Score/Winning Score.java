import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Apples 점수
        int a3 = scanner.nextInt(); // 3-point
        int a2 = scanner.nextInt(); // 2-point
        int a1 = scanner.nextInt(); // 1-point

        // Bananas 점수
        int b3 = scanner.nextInt();
        int b2 = scanner.nextInt();
        int b1 = scanner.nextInt();

        int appleScore = a3 * 3 + a2 * 2 + a1;
        int bananaScore = b3 * 3 + b2 * 2 + b1;

        if (appleScore > bananaScore) {
            System.out.println("A");
        } else if (bananaScore > appleScore) {
            System.out.println("B");
        } else {
            System.out.println("T");
        }
    }
}
