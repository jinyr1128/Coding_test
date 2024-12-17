import java.util.*;

public class Main {
    static int H, W;
    static int[][] board;
    static int[][][][] D; // 메모이제이션 배열 (4차원 Grundy 값)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        H = sc.nextInt(); // 보드의 높이
        W = sc.nextInt(); // 보드의 너비

        board = new int[H + 1][W + 1]; // 1-based 인덱싱
        D = new int[H + 2][H + 2][W + 2][W + 2]; // Grundy 메모이제이션

        for (int i = 1; i <= H; i++) {
            String row = sc.next();
            for (int j = 1; j <= W; j++) {
                board[i][j] = (row.charAt(j - 1) == 'X') ? 1 : 0; // 'X'는 마킹된 칸
            }
        }

        for (int[][][] d1 : D) {
            for (int[][] d2 : d1) {
                for (int[] d3 : d2) {
                    Arrays.fill(d3, -1); // Grundy 메모이제이션 초기화
                }
            }
        }

        if (Grundy(1, H, 1, W) != 0) {
            System.out.println("First");
        } else {
            System.out.println("Second");
        }
    }

    // Grundy 값 계산
    static int Grundy(int r1, int r2, int c1, int c2) {
        if (r1 < 1 || c1 < 1 || r2 > H || c2 > W || r1 > r2 || c1 > c2) return 0;

        if (D[r1][r2][c1][c2] != -1) return D[r1][r2][c1][c2];

        Set<Integer> grundySet = new HashSet<>();

        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                if (board[i][j] == 1) continue; // 이미 마킹된 칸은 건너뛴다.

                // 네 방향으로 나누어 Grundy 값을 XOR
                int g1 = Grundy(r1, i - 1, c1, j - 1);
                int g2 = Grundy(r1, i - 1, j + 1, c2);
                int g3 = Grundy(i + 1, r2, c1, j - 1);
                int g4 = Grundy(i + 1, r2, j + 1, c2);

                grundySet.add(g1 ^ g2 ^ g3 ^ g4);
            }
        }

        int mex = 0;
        while (grundySet.contains(mex)) mex++; // Grundy 수의 최솟값 (Mex)

        return D[r1][r2][c1][c2] = mex;
    }
}
