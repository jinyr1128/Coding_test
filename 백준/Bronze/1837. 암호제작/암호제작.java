import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String P = scanner.next();
        int K = scanner.nextInt();

        BigInteger bigP = new BigInteger(P);

        for (int i = 2; i < K; i++) {
            BigInteger divisor = BigInteger.valueOf(i);
            if (bigP.mod(divisor).equals(BigInteger.ZERO)) {
                System.out.println("BAD " + i);
                return;
            }
        }

        System.out.println("GOOD");
    }
}
