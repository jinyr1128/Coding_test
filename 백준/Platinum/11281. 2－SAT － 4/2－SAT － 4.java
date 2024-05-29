import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 200020;
    static int n, m;
    static int[] mySCC = new int[MAX];
    static List<Integer>[] vt = new ArrayList[MAX];
    static List<Integer>[] rvt = new ArrayList[MAX];
    static List<Integer>[] scc = new ArrayList[MAX];
    static boolean[] visited = new boolean[MAX];
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < MAX; i++) {
            vt[i] = new ArrayList<>();
            rvt[i] = new ArrayList<>();
            scc[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (x < 0) x = n - x;
            if (y < 0) y = n - y;
            rvt[x].add(f(y));
            rvt[y].add(f(x));
            vt[f(y)].add(x);
            vt[f(x)].add(y);
        }

        for (int i = 1; i <= 2 * n; i++) {
            if (!visited[i]) dfs(i);
        }

        Arrays.fill(visited, false);
        int r = 0;
        while (!stack.isEmpty()) {
            int t = stack.pop();
            if (visited[t]) continue;
            dfss(t, r++);
        }

        boolean isPossible = true;
        for (int i = 1; i <= n; i++) {
            if (mySCC[i] == mySCC[n + i]) {
                isPossible = false;
                break;
            }
        }

        System.out.println(isPossible ? 1 : 0);
        if (isPossible) {
            for (int i = 1; i <= n; i++) {
                System.out.print((mySCC[i] > mySCC[n + i] ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

    static void dfs(int x) {
        visited[x] = true;
        for (int next : vt[x]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
        stack.push(x);
    }

    static void dfss(int x, int y) {
        mySCC[x] = y;
        scc[y].add(x);
        visited[x] = true;
        for (int next : rvt[x]) {
            if (!visited[next]) {
                dfss(next, y);
            }
        }
    }

    static int f(int x) {
        if (x > n) return x - n;
        return x + n;
    }
}
