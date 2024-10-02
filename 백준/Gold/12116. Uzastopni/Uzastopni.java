import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();  // N을 입력받음

        // j는 연속된 수의 개수를 의미함, j*(j+1)/2 <= N 인 범위에서 실행
        for (long j = 2; j * j <= N * 2; j++) {
            long temp = N - (j * (j + 1)) / 2;  // N에서 j개의 수의 합을 뺀 값 계산

            // temp % j == 0 이면 N을 j개의 연속된 수의 합으로 표현 가능
            if (temp % j == 0) {
                long start = temp / j + 1;  // 연속된 수의 첫 번째 값
                long end = start + j - 1;   // 연속된 수의 마지막 값
                System.out.println(start + " " + end);  // 출력
            }
        }
    }
}
