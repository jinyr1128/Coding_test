import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.close();
        
        // 첫 번째 줄부터 N번째 줄까지
        for (int i = 0; i < N; i++) {
            // 왼쪽 공백 출력
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            // 별 출력
            for (int j = 0; j < (2 * (N - i) - 1); j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // N+1번째 줄부터 2N-1번째 줄까지
        for (int i = N - 2; i >= 0; i--) {
            // 왼쪽 공백 출력
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            // 별 출력
            for (int j = 0; j < (2 * (N - i) - 1); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
