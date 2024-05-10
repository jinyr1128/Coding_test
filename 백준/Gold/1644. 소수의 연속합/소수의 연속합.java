import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        // 에라토스테네스의 체를 사용하여 N까지의 소수를 구함
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primes.add(i);
                for (long j = (long) i * i; j <= N; j += i) {
                    isPrime[(int) j] = false;
                }
            }
        }

        // 투 포인터 기법으로 연속된 소수의 합이 N이 되는 경우를 찾음
        int count = 0;
        int sum = 0;
        int start = 0;
        int end = 0;
        while (true) {
            if (sum >= N) {
                sum -= primes.get(start++);
            } else if (end == primes.size()) {
                break;
            } else {
                sum += primes.get(end++);
            }
            if (sum == N) {
                count++;
            }
        }

        System.out.println(count);
    }
}
