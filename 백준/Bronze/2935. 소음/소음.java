import java.util.Scanner;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받기
        String A = scanner.nextLine();
        String operator = scanner.nextLine();
        String B = scanner.nextLine();

        // BigInteger로 변환
        BigInteger bigA = new BigInteger(A);
        BigInteger bigB = new BigInteger(B);
        BigInteger result = BigInteger.ZERO;

        // 연산자에 따라 덧셈 또는 곱셈 수행
        if (operator.equals("+")) {
            result = bigA.add(bigB);
        } else if (operator.equals("*")) {
            result = bigA.multiply(bigB);
        }

        // 결과 출력
        System.out.println(result);
    }
}
