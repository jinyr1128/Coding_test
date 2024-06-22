import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 입력받기
        BigInteger A = new BigInteger(scanner.nextLine());
        BigInteger B = new BigInteger(scanner.nextLine());
        
        // 연산 결과
        BigInteger sum = A.add(B);
        BigInteger difference = A.subtract(B);
        BigInteger product = A.multiply(B);
        
        // 결과 출력
        System.out.println(sum);
        System.out.println(difference);
        System.out.println(product);
        
        scanner.close();
    }
}
