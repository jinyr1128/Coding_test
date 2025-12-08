import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    // 1000 미만의 범위이므로 크기를 1001로 잡음
    static boolean[] isPrime = new boolean[1001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. 에라토스테네스의 체로 소수 판별
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        
        for (int i = 2; i * i <= 1000; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= 1000; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        // 소수들을 리스트에 별도로 저장 (반복문 돌리기 편하게)
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 1000; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        // 2. 테스트 케이스 처리
        int T = sc.nextInt();
        while (T-- > 0) {
            int K = sc.nextInt();
            solve(K, primes);
        }
        
        sc.close();
    }

    private static void solve(int K, List<Integer> primes) {
        // 첫 번째 소수 p1 선택
        for (int i = 0; i < primes.size(); i++) {
            int p1 = primes.get(i);
            if (p1 > K) break; // K보다 크면 중단

            // 두 번째 소수 p2 선택 (p1 이상인 것부터 시작)
            for (int j = i; j < primes.size(); j++) {
                int p2 = primes.get(j);
                if (p1 + p2 > K) break; // 합이 K를 넘으면 중단

                // 세 번째 수 p3 계산
                int p3 = K - p1 - p2;

                // p3가 p2보다 작으면 오름차순이 깨지므로 중단 (이미 더 작은 조합은 앞선 루프에서 체크됨)
                if (p3 < p2) break;

                // p3가 소수인지 확인
                if (isPrime[p3]) {
                    // 정답 출력 (p1 <= p2 <= p3 순서가 보장됨)
                    System.out.println(p1 + " " + p2 + " " + p3);
                    return; // 하나만 출력하면 되므로 함수 종료
                }
            }
        }
        
        // 불가능한 경우 (이론상 홀수 K >= 7 이면 발생하지 않음)
        System.out.println(0);
    }
}