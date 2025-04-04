import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            double x = scanner.nextDouble();

            if (x == 0.0) break;

            // procession size: 1 man + wives + sacks + cats + kittens
            double total = 1 + x + Math.pow(x, 2) + Math.pow(x, 3) + Math.pow(x, 4);

            // 소수점 2자리까지 출력
            System.out.printf("%.2f%n", total);
        }

        scanner.close();
    }
}
