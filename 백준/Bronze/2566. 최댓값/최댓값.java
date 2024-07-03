import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] grid = new int[9][9];
        int maxVal = Integer.MIN_VALUE;
        int maxRow = -1;
        int maxCol = -1;

        // 입력받기
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = sc.nextInt();
                // 최댓값과 위치 찾기
                if (grid[i][j] > maxVal) {
                    maxVal = grid[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        // 최댓값 출력
        System.out.println(maxVal);
        // 위치 출력 (1-indexed)
        System.out.println((maxRow + 1) + " " + (maxCol + 1));
    }
}
