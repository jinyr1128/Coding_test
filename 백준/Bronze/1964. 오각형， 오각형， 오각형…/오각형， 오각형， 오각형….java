import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        sc.close();

        // 3n^2 + 5n + 2 계산 시 long 범위 내에서 처리
        long val = 3 * N * N + 5 * N + 2;

        // 항상 짝수이므로 2로 나눈 결과는 정수
        val /= 2;

        // 45678로 나눈 나머지
        long answer = val % 45678;

        System.out.println(answer);
    }
}
