import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // 참여하는 사람 수

        int maxPrize = 0; // 가장 많은 상금

        for (int i = 0; i < N; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();

            int prize = calculatePrize(a, b, c);

            if (prize > maxPrize) {
                maxPrize = prize;
            }
        }

        System.out.println(maxPrize);
        scanner.close();
    }

    private static int calculatePrize(int a, int b, int c) {
        if (a == b && b == c) {
            return 10000 + a * 1000;
        } else if (a == b || a == c) {
            return 1000 + a * 100;
        } else if (b == c) {
            return 1000 + b * 100;
        } else {
            return Math.max(a, Math.max(b, c)) * 100;
        }
    }
}
