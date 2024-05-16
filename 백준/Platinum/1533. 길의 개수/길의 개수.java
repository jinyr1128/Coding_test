import java.util.Scanner;

public class Main {
    static final int MOD = 1000003;
    static long N, S, E, T;
    static long[][] A;
    static long[][] ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = sc.nextInt();
        E = sc.nextInt();
        T = sc.nextInt();
        sc.nextLine();  // consume the remaining newline

        A = new long[(int) (5 * N + 1)][(int) (5 * N + 1)];
        ans = new long[(int) (5 * N + 1)][(int) (5 * N + 1)];

        for (int i = 1; i <= N; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < s.length(); j++) {
                int val = s.charAt(j) - '0';
                if (val >= 1) {
                    A[i * 5][(j + 1) * 5 - (val - 1)] = 1;
                }
            }
            for (int j = 1; j <= 4; j++) {
                A[(i - 1) * 5 + j][(i - 1) * 5 + j + 1] = 1;
            }
        }

        for (int i = 1; i <= 5 * N; i++) {
            ans[i][i] = 1;
        }

        mpow();

        System.out.println(ans[(int) (5 * S)][(int) (5 * E)]);
    }

    static long[][] matrixMultiply(long[][] a, long[][] b) {
        long[][] res = new long[a.length][a[0].length];
        for (int i = 1; i < res.length; i++) {
            for (int j = 1; j < res[i].length; j++) {
                for (int k = 1; k < a[i].length; k++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return res;
    }

    static void mpow() {
        while (T > 0) {
            if (T % 2 == 1) {
                ans = matrixMultiply(ans, A);
            }
            T /= 2;
            A = matrixMultiply(A, A);
        }
    }
}
