import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long comb(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;   // 안전: n ≤ 8 → 64-bit 충분
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long total = comb(N, M);           // 분모
        long good  = 0;                    // 분자(성공 경우의 수 합)

        for (int i = K; i <= M; i++) {
            good += comb(M, i) * comb(N - M, M - i);
        }

        double prob = (double) good / total;
        System.out.println(prob);          // 기본 double 출력 → 1e-9 허용
    }
}
