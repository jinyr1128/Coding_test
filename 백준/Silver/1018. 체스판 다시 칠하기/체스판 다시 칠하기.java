import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        char[][] board = new char[N][M];
        sc.nextLine();

        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        int minChanges = Integer.MAX_VALUE;

        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                minChanges = Math.min(minChanges, checkBoard(board, i, j));
            }
        }

        System.out.println(minChanges);
        sc.close();
    }

    private static int checkBoard(char[][] board, int x, int y) {
        int changesWhite = 0;
        int changesBlack = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    if (board[x + i][y + j] != 'W') changesWhite++;
                    if (board[x + i][y + j] != 'B') changesBlack++;
                } else {
                    if (board[x + i][y + j] != 'B') changesWhite++;
                    if (board[x + i][y + j] != 'W') changesBlack++;
                }
            }
        }

        return Math.min(changesWhite, changesBlack);
    }
}
