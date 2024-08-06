import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력: N 값을 입력 받음
        int N = scanner.nextInt();

        // N부터 1까지 감소하면서 각 줄에 별을 출력
        for (int i = N; i >= 1; i--) {
            // i개의 별 출력
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            // 다음 줄로 이동
            System.out.println();
        }

        // Scanner 닫기
        scanner.close();
    }
}
