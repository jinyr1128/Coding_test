import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long B;
    static int MOD = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        
        int[][] A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken()) % MOD;
            }
        }

        int[][] result = matrixPow(A, B);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static int[][] matrixMultiply(int[][] A, int[][] B) {
        int[][] C = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                C[i][j] = 0;
                for (int k = 0; k < N; k++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    public static int[][] matrixPow(int[][] A, long exp) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            result[i][i] = 1; // 단위 행렬 초기화
        }

        while (exp > 0) {
            if (exp % 2 == 1) {
                result = matrixMultiply(result, A);
            }
            A = matrixMultiply(A, A);
            exp /= 2;
        }
        return result;
    }
}
