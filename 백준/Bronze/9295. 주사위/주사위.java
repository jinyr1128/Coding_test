import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 테스트 케이스의 개수 입력
        int T = sc.nextInt();

        // 각 테스트 케이스 처리
        for (int i = 1; i <= T; i++) {
            // 주사위를 두 번 던진 결과 입력
            int dice1 = sc.nextInt();
            int dice2 = sc.nextInt();

            // 두 주사위의 합 출력
            int sum = dice1 + dice2;
            System.out.println("Case " + i + ": " + sum);
        }

        sc.close();
    }
}
