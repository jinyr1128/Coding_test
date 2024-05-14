import java.util.*;

public class Main {
    static final int SIZE = 10;
    static int[][] grid = new int[SIZE][SIZE];
    static int[][] copyGrid;
    static int minSwitches = Integer.MAX_VALUE;

    static int[] dx = {0, 0, 0, 1, -1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < SIZE; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = (line.charAt(j) == 'O') ? 1 : 0;
            }
        }
        scanner.close();

        for (int i = 0; i < (1 << SIZE); i++) {
            copyGrid = new int[SIZE][SIZE];
            for (int x = 0; x < SIZE; x++) {
                System.arraycopy(grid[x], 0, copyGrid[x], 0, SIZE);
            }
            int switches = 0;
            for (int j = 0; j < SIZE; j++) {
                if ((i & (1 << j)) != 0) {
                    flip(0, j);
                    switches++;
                }
            }
            for (int x = 1; x < SIZE; x++) {
                for (int y = 0; y < SIZE; y++) {
                    if (copyGrid[x - 1][y] == 1) {
                        flip(x, y);
                        switches++;
                    }
                }
            }

            boolean allOff = true;
            for (int j = 0; j < SIZE; j++) {
                if (copyGrid[SIZE - 1][j] == 1) {
                    allOff = false;
                    break;
                }
            }

            if (allOff) {
                minSwitches = Math.min(minSwitches, switches);
            }
        }

        System.out.println(minSwitches == Integer.MAX_VALUE ? -1 : minSwitches);
    }

    static void flip(int x, int y) {
        for (int i = 0; i < 5; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE) {
                copyGrid[nx][ny] ^= 1;
            }
        }
    }
}
