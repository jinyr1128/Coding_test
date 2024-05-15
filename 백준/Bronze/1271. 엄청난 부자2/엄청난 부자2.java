import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력받기
        BigInteger n = new BigInteger(scanner.next());
        BigInteger m = new BigInteger(scanner.next());

        // 몫 구하기
        BigInteger quotient = n.divide(m);
        // 나머지 구하기
        BigInteger remainder = n.remainder(m);

        // 결과 출력
        System.out.println(quotient);
        System.out.println(remainder);
    }
}
