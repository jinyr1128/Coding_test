import java.util.*;
import java.io.*;

public class Main {
    static final long MOD = 1000000007;

    static class Matrix {
        long[][] values;
        int size;

        Matrix(int size, boolean identity) {
            this.size = size;
            values = new long[size][size];
            if (identity) {
                for (int i = 0; i < size; i++) {
                    values[i][i] = 1;
                }
            }
        }

        Matrix multiply(Matrix other) {
            Matrix result = new Matrix(this.size, false);
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    for (int k = 0; k < this.size; k++) {
                        result.values[i][j] = (result.values[i][j] + this.values[i][k] * other.values[k][j] % MOD) % MOD;
                    }
                }
            }
            return result;
        }

        static Matrix power(Matrix base, long exp) {
            int size = base.size;
            Matrix result = new Matrix(size, true);
            while (exp > 0) {
                if ((exp & 1) == 1) {
                    result = result.multiply(base);
                }
                base = base.multiply(base);
                exp >>= 1;
            }
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        long D = Long.parseLong(st.nextToken());

        List<Matrix> matrices = new ArrayList<>();
        matrices.add(new Matrix(N, true));

        for (int t = 0; t < T; t++) {
            Matrix mat = new Matrix(N, false);
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());
                mat.values[a][b] = c;
            }
            matrices.add(mat);
        }

        for (int i = 1; i <= T; i++) {
            matrices.set(i, matrices.get(i - 1).multiply(matrices.get(i)));
        }

        Matrix result = Matrix.power(matrices.get(T), D / T).multiply(matrices.get((int) (D % T)));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result.values[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}
