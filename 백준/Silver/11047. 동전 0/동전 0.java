import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // 동전 종류의 수
        int K = scanner.nextInt(); // 만들어야 할 금액
        int[] coins = new int[N];

        for (int i = 0; i < N; i++) {
            coins[i] = scanner.nextInt(); // 동전 가치 입력
        }

        int count = 0; // 사용된 동전의 개수

        // 큰 동전부터 사용
        for (int i = N - 1; i >= 0; i--) {
            if (K >= coins[i]) {
                count += K / coins[i]; // 현재 동전으로 만들 수 있는 최대 개수
                K %= coins[i]; // 남은 금액 업데이트
            }
        }

        System.out.println(count); // 필요한 동전의 최소 개수 출력
    }
}
