import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int[] selected;
    static boolean[] visited;
    static boolean[] finished;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            selected = new int[n + 1];
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];
            count = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                selected[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }

            System.out.println(n - count);
        }
    }

    private static void dfs(int current) {
        visited[current] = true;
        int next = selected[current];

        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) {
            count++;
            for (int temp = next; temp != current; temp = selected[temp]) {
                count++;
            }
        }

        finished[current] = true;
    }
}
