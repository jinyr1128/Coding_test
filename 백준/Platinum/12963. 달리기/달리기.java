import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_007;
    static int n, m;
    static int[] parent;
    static long[] powerOfThree;
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 처리
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] a = new int[m];
        int[] b = new int[m];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken()) + 1;
            b[i] = Integer.parseInt(st.nextToken()) + 1;
        }

        // 3의 거듭제곱 계산
        powerOfThree = new long[m + 1];
        powerOfThree[0] = 1;
        for (int i = 1; i <= m; i++) {
            powerOfThree[i] = (powerOfThree[i - 1] * 3) % MOD;
        }

        // Union-Find 초기화
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // 역순으로 간선 처리
        for (int i = m - 1; i >= 0; i--) {
            union(a[i], b[i], i);
        }

        // 결과 출력
        System.out.println(answer);
    }

    // Find 함수: 경로 압축(Path Compression) 적용
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    // Union 함수: 간선 연결 및 결과 갱신
    static void union(int u, int v, int weightIndex) {
        int rootU = find(u);
        int rootV = find(v);

        // 이미 같은 집합이면 처리할 필요 없음
        if (rootU == rootV) {
            return;
        }

        // 작은 번호를 부모로 설정
        if (rootU > rootV) {
            int temp = rootU;
            rootU = rootV;
            rootV = temp;
        }

        // 1번과 N번이 연결되는 경우
        if (rootU == 1 && rootV == n) {
            answer = (answer + powerOfThree[weightIndex]) % MOD;
        } else if (rootU == 1) {
            parent[rootV] = rootU;
        } else {
            parent[rootU] = rootV;
        }
    }
}
