import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.close();

        // 가로 자르는 횟수를 a, 세로 자르는 횟수를 b라 할 때
        // N = a + b
        // 최대 조각 수는 (a + 1) * (b + 1)로 이루어집니다.

        int maxPieces = (N / 2 + 1) * ((N - N / 2) + 1);
        System.out.println(maxPieces);
    }
}
