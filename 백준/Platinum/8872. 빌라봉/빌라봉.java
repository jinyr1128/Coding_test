import java.io.*;
import java.util.*;

public class Main {
    static int N, M, L, ans = Integer.MIN_VALUE;
    static List<List<int[]>> adj = new ArrayList<>();
    static boolean[] isVisited;
    static List<Integer> currentTree = new ArrayList<>();
    static List<Integer> radiusList = new ArrayList<>();
    static int v1, dist1, v2, dist2;
    static Map<Integer, int[]> parent = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            adj.get(A).add(new int[]{B, T});
            adj.get(B).add(new int[]{A, T});
        }

        isVisited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!isVisited[i]) {
                findRadius(i);
            }
        }

        Collections.sort(radiusList, Collections.reverseOrder());

        if (radiusList.size() >= 2 && radiusList.get(0) + radiusList.get(1) + L >= ans) {
            ans = radiusList.get(0) + radiusList.get(1) + L;
        }
        if (radiusList.size() >= 3 && radiusList.get(1) + radiusList.get(2) + 2 * L >= ans) {
            ans = radiusList.get(1) + radiusList.get(2) + 2 * L;
        }

        System.out.println(ans);
    }

    static void findRadius(int root) {
        currentTree.clear();
        dist1 = Integer.MIN_VALUE;
        dist2 = Integer.MIN_VALUE;

        dfs1(root, 0);

        for (int node : currentTree) {
            isVisited[node] = false;
        }

        dfs2(v1, 0);

        int radius = Integer.MAX_VALUE;
        int curDist = 0;
        int v = v2;

        if (dist2 > ans) {
            ans = dist2;
        }

        if (dist2 == 0) {
            radiusList.add(0);
            return;
        }

        while (v != v1) {
            radius = Math.min(radius, Math.max(curDist, dist2 - curDist));
            curDist += parent.get(v)[1];
            v = parent.get(v)[0];
        }

        radiusList.add(radius);
    }

    static void dfs1(int cur, int dist) {
        currentTree.add(cur);
        isVisited[cur] = true;

        if (dist > dist1) {
            v1 = cur;
            dist1 = dist;
        }

        for (int[] next : adj.get(cur)) {
            if (isVisited[next[0]]) continue;
            dfs1(next[0], dist + next[1]);
        }
    }

    static void dfs2(int cur, int dist) {
        currentTree.add(cur);
        isVisited[cur] = true;

        if (dist > dist2) {
            v2 = cur;
            dist2 = dist;
        }

        for (int[] next : adj.get(cur)) {
            if (isVisited[next[0]]) continue;
            parent.put(next[0], new int[]{cur, next[1]});
            dfs2(next[0], dist + next[1]);
        }
    }
}
