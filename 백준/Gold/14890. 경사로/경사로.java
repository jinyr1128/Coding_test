import java.util.Scanner;

public class Main {
    static int n, l;
    static int[][] road1;
    static int[][] road2;
    static int result = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();

        road1 = new int[n][n];
        road2 = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                road1[i][j] = sc.nextInt();
                road2[j][i] = road1[i][j];
            }
        }

        passCount(road1);
        passCount(road2);

        System.out.println(result);
    }

    static void passCount(int[][] road) {
        for (int i = 0; i < n; i++) {
            boolean[] slope = new boolean[n];
            boolean possible = true;

            for (int h = 0; h < n - 1; h++) {
                if (Math.abs(road[i][h] - road[i][h + 1]) > 1) {
                    possible = false;
                    break;
                }

                if (road[i][h] == road[i][h + 1] + 1) {
                    int curHeight = road[i][h + 1];
                    for (int k = h + 2; k < h + 1 + l; k++) {
                        if (k >= n || road[i][k] != curHeight) {
                            possible = false;
                            break;
                        }
                    }
                    if (possible) {
                        for (int k = h + 1; k < h + 1 + l; k++) {
                            slope[k] = true;
                        }
                    } else {
                        break;
                    }
                }

                if (road[i][h] == road[i][h + 1] - 1) {
                    int curHeight = road[i][h];
                    for (int k = h; k > h - l; k--) {
                        if (k < 0 || road[i][k] != curHeight || slope[k]) {
                            possible = false;
                            break;
                        }
                    }
                    if (possible) {
                        for (int k = h; k > h - l; k--) {
                            slope[k] = true;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (possible) {
                result++;
            }
        }
    }
}
