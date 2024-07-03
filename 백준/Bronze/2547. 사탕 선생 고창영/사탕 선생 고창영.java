import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine(); // 첫 번째 줄의 개행 문자 처리
        
        for (int i = 0; i < t; i++) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // 테스트 케이스 사이의 빈 줄 처리
            }
            
            int n = scanner.nextInt();
            BigInteger sum = BigInteger.ZERO;
            
            for (int j = 0; j < n; j++) {
                sum = sum.add(scanner.nextBigInteger());
            }
            
            if (sum.remainder(BigInteger.valueOf(n)).equals(BigInteger.ZERO)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
