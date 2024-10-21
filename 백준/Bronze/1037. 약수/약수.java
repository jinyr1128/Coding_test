import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 진짜 약수의 개수 입력
        int numOfDivisors = sc.nextInt();
        
        // 진짜 약수들 입력
        int[] divisors = new int[numOfDivisors];
        for (int i = 0; i < numOfDivisors; i++) {
            divisors[i] = sc.nextInt();
        }

        // 최소값과 최대값 구하기
        int minDivisor = Arrays.stream(divisors).min().getAsInt();
        int maxDivisor = Arrays.stream(divisors).max().getAsInt();

        // N은 최소 약수와 최대 약수의 곱
        int N = minDivisor * maxDivisor;

        // 결과 출력
        System.out.println(N);

        sc.close();
    }
}
