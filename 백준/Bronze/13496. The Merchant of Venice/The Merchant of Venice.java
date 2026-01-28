import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 첫 번째 줄: 데이터 세트의 개수 K
        int K = sc.nextInt();

        for (int k = 1; k <= K; k++) {
            // 각 데이터 세트의 첫 줄: 배의 수 n, 속도 s, 남은 날짜 d
            int n = sc.nextInt();
            int s = sc.nextInt();
            int d = sc.nextInt();

            // 마감일 내에 이동할 수 있는 최대 거리
            int maxDistance = s * d;
            int totalValue = 0;

            // n개의 배 정보 입력 및 처리
            for (int i = 0; i < n; i++) {
                int distance = sc.nextInt(); // 배의 거리
                int value = sc.nextInt();    // 화물의 가치

                // 배의 거리가 최대 이동 거리 이하라면 도착 가능
                if (distance <= maxDistance) {
                    totalValue += value;
                }
            }

            // 출력 형식에 맞춰 출력
            System.out.println("Data Set " + k + ":");
            System.out.println(totalValue);
            System.out.println(); // 각 데이터 세트 뒤에 빈 줄 출력
        }
        
        sc.close();
    }
}