import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] grid;
    static int maxPerfectSquare = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        grid = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < M; j++) {
                grid[i][j] = line.charAt(j) - '0';
            }
        }

        findMaxPerfectSquare();
        System.out.println(maxPerfectSquare);
    }

    private static void findMaxPerfectSquare() {
        for (int row_start = 0; row_start < N; row_start++) {
            for (int col_start = 0; col_start < M; col_start++) {
                for (int row_diff = -N; row_diff < N; row_diff++) {
                    for (int col_diff = -M; col_diff < M; col_diff++) {
                        if (row_diff == 0 && col_diff == 0) {
                            continue;
                        }
                        checkSequence(row_start, col_start, row_diff, col_diff);
                    }
                }
            }
        }
    }

    private static void checkSequence(int row_start, int col_start, int row_diff, int col_diff) {
        StringBuilder sb = new StringBuilder();
        int row = row_start;
        int col = col_start;

        while (row >= 0 && row < N && col >= 0 && col < M) {
            sb.append(grid[row][col]);
            int number = Integer.parseInt(sb.toString());
            if (isPerfectSquare(number)) {
                maxPerfectSquare = Math.max(maxPerfectSquare, number);
            }
            row += row_diff;
            col += col_diff;
        }
    }

    private static boolean isPerfectSquare(int number) {
        if (number < 0) {
            return false;
        }
        int sqrt = (int) Math.sqrt(number);
        return sqrt * sqrt == number;
    }
}
