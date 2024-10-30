import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_LOG = 16;
    static int n;
    static int[] energy;
    static List<List<Pair>> graph;
    static Pair[][] table;
    static int[] level;

    static class Pair {
        int first, second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());

        energy = new int[n + 1];
        graph = new ArrayList<>(n + 1);
        table = new Pair[MAX_LOG][n + 1];
        level = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            energy[i] = Integer.parseInt(br.readLine().trim());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Pair(b, c));
            graph.get(b).add(new Pair(a, c));
        }

        solution();
    }

    static void solution() {
        setGraphSparseAndLevel();
        setTable();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int left = 0;
            int right = level[i];

            while (left <= right) {
                int mid = (left + right) / 2;
                Pair p = moveAndGetPair(i, mid);

                if (p.second <= energy[i]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            sb.append(moveAndGetPair(i, right).first).append("\n");
        }

        System.out.print(sb);
    }

    static void setGraphSparseAndLevel() {
        level[1] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        int curLevel = 1;

        while (!queue.isEmpty()) {
            Queue<Integer> nextQueue = new LinkedList<>();

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                List<Integer> toErase = new ArrayList<>();

                for (int i = 0; i < graph.get(cur).size(); i++) {
                    int next = graph.get(cur).get(i).first;

                    if (level[next] == 0 && next != 1) {
                        nextQueue.add(next);
                        level[next] = curLevel;
                        toErase.add(i);
                    }
                }

                for (int i = toErase.size() - 1; i >= 0; i--) {
                    graph.get(cur).remove((int) toErase.get(i));
                }
            }

            queue = nextQueue;
            curLevel++;
        }

        graph.get(1).add(new Pair(1, 0));
    }

    static void setTable() {
        for (int i = 1; i <= n; i++) {
            table[0][i] = graph.get(i).get(0);
        }

        for (int k = 1; k < MAX_LOG; k++) {
            for (int i = 1; i <= n; i++) {
                int midNode = table[k - 1][i].first;
                int midCost = table[k - 1][i].second;

                table[k][i] = new Pair(
                    table[k - 1][midNode].first,
                    table[k - 1][midNode].second + midCost
                );
            }
        }
    }

    static Pair moveAndGetPair(int node, int k) {
        int curNode = node;
        int curCost = 0;

        for (int i = MAX_LOG - 1; i >= 0; i--) {
            if ((k & (1 << i)) != 0) {
                curCost += table[i][curNode].second;
                curNode = table[i][curNode].first;
            }
        }

        return new Pair(curNode, curCost);
    }
}
