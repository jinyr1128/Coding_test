import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static List<int[]> homes = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    static int minDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 1) {
                    homes.add(new int[]{i, j});
                } else if (value == 2) {
                    chickens.add(new int[]{i, j});
                }
            }
        }

        // Generate all combinations of chicken stores up to M
        boolean[] visited = new boolean[chickens.size()];
        for (int i = 1; i <= M; i++) {
            combineChickens(visited, 0, 0, i);
        }

        System.out.println(minDistance);
    }

    static void combineChickens(boolean[] visited, int start, int depth, int r) {
        if (depth == r) {
            minDistance = Math.min(minDistance, calculateCityChickenDistance(visited));
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            visited[i] = true;
            combineChickens(visited, i + 1, depth + 1, r);
            visited[i] = false;
        }
    }

    static int calculateCityChickenDistance(boolean[] visited) {
        int cityDistance = 0;

        for (int[] home : homes) {
            int minDist = Integer.MAX_VALUE;
            for (int i = 0; i < chickens.size(); i++) {
                if (visited[i]) {
                    int[] chicken = chickens.get(i);
                    int dist = Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
                    minDist = Math.min(minDist, dist);
                }
            }
            cityDistance += minDist;
        }

        return cityDistance;
    }
}
