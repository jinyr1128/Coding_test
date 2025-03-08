import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테스트 케이스 개수

        for (int t = 1; t <= T; t++) {
            int maxWeight = 0;

            // 5개의 정수 입력받아 최대값 찾기
            for (int i = 0; i < 5; i++) {
                int weight = sc.nextInt();
                if (weight > maxWeight) {
                    maxWeight = weight;
                }
            }

            // 출력 형식 맞추기
            System.out.println("Case #" + t + ": " + maxWeight);
        }

        sc.close();
    }
}
