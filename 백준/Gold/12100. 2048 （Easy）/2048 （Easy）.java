import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    private static int N;
    private static int[][] board;
    private static int maxBlock = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] > maxBlock) {
                    maxBlock = board[i][j];
                }
            }
        }
        
        dfs(board, 0);
        System.out.println(maxBlock);
    }

    private static void dfs(int[][] board, int depth) {
        if (depth == 5) {
            maxBlock = Math.max(maxBlock, getMaxBlock(board));
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int[][] newBoard = new int[N][N];
            for (int i = 0; i < N; i++) {
                newBoard[i] = Arrays.copyOf(board[i], N);
            }
            move(newBoard, dir);
            dfs(newBoard, depth + 1);
        }
    }

    private static int getMaxBlock(int[][] board) {
        int max = 0;
        for (int[] row : board) {
            for (int value : row) {
                max = Math.max(max, value);
            }
        }
        return max;
    }

    private static void move(int[][] board, int dir) {
        switch (dir) {
            case 0: // Up
                for (int j = 0; j < N; j++) {
                    int[] temp = new int[N];
                    int index = 0;
                    boolean merged = false;
                    for (int i = 0; i < N; i++) {
                        if (board[i][j] != 0) {
                            if (index > 0 && temp[index - 1] == board[i][j] && !merged) {
                                temp[index - 1] *= 2;
                                merged = true;
                            } else {
                                temp[index++] = board[i][j];
                                merged = false;
                            }
                        }
                    }
                    for (int i = 0; i < N; i++) {
                        board[i][j] = temp[i];
                    }
                }
                break;
            case 1: // Down
                for (int j = 0; j < N; j++) {
                    int[] temp = new int[N];
                    int index = 0;
                    boolean merged = false;
                    for (int i = N - 1; i >= 0; i--) {
                        if (board[i][j] != 0) {
                            if (index > 0 && temp[index - 1] == board[i][j] && !merged) {
                                temp[index - 1] *= 2;
                                merged = true;
                            } else {
                                temp[index++] = board[i][j];
                                merged = false;
                            }
                        }
                    }
                    for (int i = 0; i < N; i++) {
                        board[N - 1 - i][j] = temp[i];
                    }
                }
                break;
            case 2: // Left
                for (int i = 0; i < N; i++) {
                    int[] temp = new int[N];
                    int index = 0;
                    boolean merged = false;
                    for (int j = 0; j < N; j++) {
                        if (board[i][j] != 0) {
                            if (index > 0 && temp[index - 1] == board[i][j] && !merged) {
                                temp[index - 1] *= 2;
                                merged = true;
                            } else {
                                temp[index++] = board[i][j];
                                merged = false;
                            }
                        }
                    }
                    for (int j = 0; j < N; j++) {
                        board[i][j] = temp[j];
                    }
                }
                break;
            case 3: // Right
                for (int i = 0; i < N; i++) {
                    int[] temp = new int[N];
                    int index = 0;
                    boolean merged = false;
                    for (int j = N - 1; j >= 0; j--) {
                        if (board[i][j] != 0) {
                            if (index > 0 && temp[index - 1] == board[i][j] && !merged) {
                                temp[index - 1] *= 2;
                                merged = true;
                            } else {
                                temp[index++] = board[i][j];
                                merged = false;
                            }
                        }
                    }
                    for (int j = 0; j < N; j++) {
                        board[i][N - 1 - j] = temp[j];
                    }
                }
                break;
        }
    }
}
