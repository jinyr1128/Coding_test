import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();  // 테스트 케이스 수

        for (int i = 0; i < t; i++) {
            int k = sc.nextInt();  // 승리 조건 점수
            int x = sc.nextInt();  // 첫 번째 팀 점수
            int y = sc.nextInt();  // 두 번째 팀 점수

            int result;

            if (x >= y) {
                // x가 승리하려면 y보다 2점 이상 차이나고, 점수가 k 이상이어야 함
                int targetScore = Math.max(k, y + 2);
                result = targetScore - x;
            } else {
                // y가 승리하려면 x보다 2점 이상 차이나고, 점수가 k 이상이어야 함
                int targetScore = Math.max(k, x + 2);
                result = targetScore - y;
            }

            System.out.println(result);
        }

        sc.close();
    }
}
