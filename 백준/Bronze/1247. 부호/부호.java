import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 세 개의 테스트 셋을 처리
        for (int t = 0; t < 3; t++) {
            // 첫 줄에 N이 주어짐
            int N = sc.nextInt();

            // BigInteger를 사용하여 합을 계산
            BigInteger sum = BigInteger.ZERO;

            // N개의 정수를 입력받아 합을 계산
            for (int i = 0; i < N; i++) {
                BigInteger num = sc.nextBigInteger();
                sum = sum.add(num);
            }

            // 합의 부호를 출력
            if (sum.equals(BigInteger.ZERO)) {
                System.out.println("0");
            } else if (sum.compareTo(BigInteger.ZERO) > 0) {
                System.out.println("+");
            } else {
                System.out.println("-");
            }
        }

        sc.close();
    }
}
