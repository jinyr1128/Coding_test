import java.math.BigInteger;

public class Main {
    
    // 최대공약수 GCD 계산
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }

    // 최소공배수 LCM 계산
    public static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(gcd(a, b));
    }

    // 10의 몇 배수인지 알아내는 함수
    public static int countTrailingZeros(BigInteger number) {
        int count = 0;
        while (number.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
            count++;
            number = number.divide(BigInteger.TEN);
        }
        return count;
    }

    public static void main(String[] args) {
        // 입력을 받기
        java.util.Scanner sc = new java.util.Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        
        // LCM 계산: 큰 범위의 숫자들을 다루기 위해 BigInteger 사용
        BigInteger lcm = BigInteger.valueOf(1);
        
        // a부터 b까지의 모든 숫자에 대해 LCM을 구한다
        for (long i = a; i <= b; i++) {
            lcm = lcm(lcm, BigInteger.valueOf(i));
        }
        
        // LCM의 끝에 있는 0의 개수
        int zeros = countTrailingZeros(lcm);
        
        // 결과 출력
        System.out.println(zeros);
    }
}
