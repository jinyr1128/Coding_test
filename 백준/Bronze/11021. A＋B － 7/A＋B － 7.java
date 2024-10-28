import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt(); // 테스트 케이스 개수 입력

        for (int i = 1; i <= T; i++) {
            int A = scanner.nextInt();
            int B = scanner.nextInt();
            int sum = A + B; // A와 B의 합 계산

            // 출력 형식에 맞게 출력
            System.out.println("Case #" + i + ": " + sum);
        }

        scanner.close();
    }
}
