import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        if (n == 1 || n == 2) {
            System.out.println(1);
            return;
        }

        // f(1)과 f(2)를 초기화
        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ONE;
        BigInteger fib = BigInteger.ZERO;

        // 피보나치 수열 계산
        for (int i = 3; i <= n; i++) {
            fib = a.add(b);  // fib = a + b
            a = b;           // 이전 값을 다음으로 이동
            b = fib;         // 현재 값을 다음으로 이동
        }

        System.out.println(fib);
    }
}
