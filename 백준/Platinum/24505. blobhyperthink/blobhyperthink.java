import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MOD = 1_000_000_007;
    static final int MAX_N = 100000;
    static int[][] fenwickTree = new int[12][MAX_N + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 수열 길이 및 수열 입력
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 각 수열 값에 대해 쌍의 개수 계산
        for (int i = 1; i <= n; i++) {
            int x = arr[i];
            update(1, x, 1);
            for (int k = 2; k <= 11; k++) {
                int val = query(k - 1, x - 1);
                update(k, x, val);
            }
        }

        // 최종 결과 출력
        System.out.println(query(11, MAX_N));
    }

    // 펜윅 트리 업데이트
    static void update(int idx, int pos, int val) {
        while (pos <= MAX_N) {
            fenwickTree[idx][pos] = (fenwickTree[idx][pos] + val) % MOD;
            pos += pos & -pos;
        }
    }

    // 펜윅 트리 쿼리
    static int query(int idx, int pos) {
        int res = 0;
        while (pos > 0) {
            res = (res + fenwickTree[idx][pos]) % MOD;
            pos -= pos & -pos;
        }
        return res;
    }
}
