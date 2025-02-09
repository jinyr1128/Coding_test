import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 과자 가격 K, 개수 N, 현재 가진 돈 M 입력 받기
        int K = sc.nextInt();
        int N = sc.nextInt();
        int M = sc.nextInt();

        // 필요한 총 금액 계산
        int totalCost = K * N;

        // 부족한 금액 계산 (부족하지 않으면 0 출력)
        int neededMoney = Math.max(0, totalCost - M);

        // 결과 출력
        System.out.println(neededMoney);

        sc.close();
    }
}
