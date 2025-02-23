import java.util.Scanner;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 두 수를 문자열로 입력받아 BigInteger로 변환
        BigInteger A = new BigInteger(sc.next());
        BigInteger B = new BigInteger(sc.next());

        // A + B 계산
        BigInteger result = A.add(B);

        // 결과 출력
        System.out.println(result);

        sc.close();
    }
}
