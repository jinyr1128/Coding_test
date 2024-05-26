import java.util.Scanner;

public class Main {
    static char[][] board;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        board = new char[N][N];
        
        fillBoard(N, 0, 0, false);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    private static void fillBoard(int n, int x, int y, boolean blank) {
        if (blank) {
            for (int i = x; i < x + n; i++) {
                for (int j = y; j < y + n; j++) {
                    board[i][j] = ' ';
                }
            }
            return;
        }
        
        if (n == 1) {
            board[x][y] = '*';
            return;
        }
        
        int size = n / 3;
        int count = 0;
        for (int i = x; i < x + n; i += size) {
            for (int j = y; j < y + n; j += size) {
                count++;
                if (count == 5) {
                    fillBoard(size, i, j, true);
                } else {
                    fillBoard(size, i, j, false);
                }
            }
        }
    }
}
