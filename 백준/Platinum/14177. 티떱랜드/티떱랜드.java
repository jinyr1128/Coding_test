import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, K;
    static int[][] U;
    static int[][] D1, D2, D, P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        U = new int[N + 1][N + 1];
        D1 = new int[N + 1][N + 1];
        D2 = new int[N + 1][N + 1];
        D = new int[K + 1][N + 1];
        P = new int[K + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                U[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        preprocess();

        for (int i = 1; i <= N; i++) {
            D[1][i] = D2[1][i];
        }

        for (int t = 2; t <= K; t++) {
            solve(t, t, N, t - 1, N - 1);
        }

        System.out.println(D[K][N]);
    }

    static void preprocess() {
        for (int i = 1; i <= N; i++) {
            D1[i][1] = U[i][1];
            for (int j = 2; j <= N; j++) {
                D1[i][j] = D1[i][j - 1] + U[i][j];
            }
        }

        for (int i = 1; i <= N; i++) {
            D2[i][i] = 0;
            for (int j = i + 1; j <= N; j++) {
                D2[i][j] = D2[i][j - 1] + (D1[j][j] - D1[j][i - 1]);
            }
        }
    }

    static void solve(int t, int st, int en, int Pmn, int Pmx) {
        if (st > en) return;

        int mid = (st + en) / 2;
        D[t][mid] = INF;

        for (int sep = Pmn; sep <= Math.min(Pmx, mid - 1); sep++) {
            int cost = D[t - 1][sep] + D2[sep + 1][mid];
            if (cost < D[t][mid]) {
                D[t][mid] = cost;
                P[t][mid] = sep;
            }
        }

        solve(t, st, mid - 1, Pmn, P[t][mid]);
        solve(t, mid + 1, en, P[t][mid], Pmx);
    }
}
