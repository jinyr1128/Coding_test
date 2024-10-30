import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[] capital = new long[2 * N];
        long S = 0; // 전체 자본 합계를 저장

        // 자본 입력과 초기화
        for (int i = 0; i < N; i++) {
            capital[i] = sc.nextLong();
            capital[i + N] = capital[i]; // 순환을 위한 두 배 배열
            S += capital[i];
        }
        sc.close();

        // 누적 합 계산
        for (int i = 1; i < 2 * N; i++) {
            capital[i] += capital[i - 1];
        }

        long result = 0;
        // 최소 마법 이동 횟수 계산
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                long subSum = capital[j + i] - capital[j];
                if (subSum < 0) {
                    result += Math.ceil((double) -subSum / S);
                }
            }
        }

        System.out.println(result);
    }
}
