import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int n = scanner.nextInt(); // 삼각형의 크기

            if (n == 0) break; // 입력 종료

            for (int i = 1; i <= n; i++) {
                // 줄마다 i개의 '*' 출력
                for (int j = 0; j < i; j++) {
                    System.out.print("*");
                }
                System.out.println(); // 줄바꿈
            }
        }

        scanner.close();
    }
}
