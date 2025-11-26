import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // n: 총 문제 수, k: 푼 문제 수
        long n = sc.nextLong();
        long k = sc.nextLong();

        // d: 전체 평균 난이도, s: 푼 문제 평균 난이도
        long d = sc.nextLong();
        long s = sc.nextLong();

        sc.close();

        // 1. 전체 난이도의 총합
        long totalSum = n * d;

        // 2. 이미 푼 문제들의 난이도 총합
        long solvedSum = k * s;

        // 3. 남은 문제들의 난이도 총합
        long remainingSum = totalSum - solvedSum;

        // 4. 남은 문제의 개수
        long remainingCount = n - k;

        // 5. 남은 문제들의 평균 계산
        double result = (double) remainingSum / remainingCount;

        // 6. 유효성 검사 (평균은 0 이상 100 이하여야 함)
        if (result >= 0 && result <= 100) {
            // 소수점 9자리까지 넉넉하게 출력 (오차 범위 10^-6 만족을 위해)
            System.out.printf("%.9f", result);
        } else {
            System.out.println("impossible");
        }
    }
}