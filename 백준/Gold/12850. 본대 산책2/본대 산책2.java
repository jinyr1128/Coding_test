import java.util.Scanner;

class Main {
    static final int MOD = 1000000007;
    static final int SIZE = 8;

    static class Matrix {
        long[][] array;

        Matrix() {
            array = new long[SIZE][SIZE];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Matrix base = new Matrix();
        base.array = new long[][] {
            {0, 1, 1, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 1, 0, 0},
            {0, 0, 1, 1, 0, 1, 1, 0},
            {0, 0, 0, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 1, 0}
        };

        long D = scanner.nextLong();
        scanner.close();

        base = matrixPowerModular(base, D);

        System.out.println(base.array[0][0]);
    }

    static Matrix matrixMultiplyModular(Matrix A, Matrix B) {
        Matrix result = new Matrix();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result.array[i][j] = 0;
                for (int k = 0; k < SIZE; k++) {
                    result.array[i][j] = (result.array[i][j] + A.array[i][k] * B.array[k][j]) % MOD;
                }
            }
        }
        return result;
    }

    static Matrix matrixPowerModular(Matrix base, long power) {
        Matrix result = new Matrix();
        for (int i = 0; i < SIZE; i++) {
            result.array[i][i] = 1;
        }
        while (power > 0) {
            if (power % 2 == 1) {
                result = matrixMultiplyModular(result, base);
            }
            base = matrixMultiplyModular(base, base);
            power /= 2;
        }
        return result;
    }
}
