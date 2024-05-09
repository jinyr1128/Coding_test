import java.io.*;

public class Main {
    static int N;
    static int[] col;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        col = new int[N];  // 퀸의 열 위치 저장

        placeQueen(0);
        System.out.println(answer);
    }

    private static void placeQueen(int row) {
        if (row == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            col[row] = i;
            if (isValid(row)) {
                placeQueen(row + 1);
            }
        }
    }

    private static boolean isValid(int row) {
        for (int i = 0; i < row; i++) {
            if (col[i] == col[row] || Math.abs(col[row] - col[i]) == row - i) {
                return false;
            }
        }
        return true;
    }
}
