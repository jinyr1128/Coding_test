import java.io.*;

public class Main {
    static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        solve(0, 0);
    }

    static void solve(int row, int col) {
        if (col == 9) {
            solve(row + 1, 0);
            return;
        }

        if (row == 9) {
            printBoard();
            System.exit(0);
        }

        if (board[row][col] == 0) {
            for (int num = 1; num <= 9; num++) {
                if (check(row, col, num)) {
                    board[row][col] = num;
                    solve(row, col + 1);
                    board[row][col] = 0;
                }
            }
        } else {
            solve(row, col + 1);
        }
    }

    static boolean check(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}
