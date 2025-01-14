import java.util.*;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N, M, Q;
    static int[] x1, y1, x2, y2, lo, hi, ans;
    static int[][] H;
    static List<Integer>[] mid;
    static List<Cell> d = new ArrayList<>();
    static int[] parent;
    static List<Integer> comp = new ArrayList<>();

    static class Cell implements Comparable<Cell> {
        int height, x, y;

        Cell(int height, int x, int y) {
            this.height = height;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Cell o) {
            return Integer.compare(this.height, o.height);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        Q = sc.nextInt();

        H = new int[N + 2][M + 2];
        x1 = new int[Q];
        y1 = new int[Q];
        x2 = new int[Q];
        y2 = new int[Q];
        lo = new int[Q];
        hi = new int[Q];
        ans = new int[Q];
        mid = new ArrayList[1000000];

        for (int i = 0; i < mid.length; i++) mid[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                H[i][j] = sc.nextInt();
                comp.add(H[i][j]);
            }
        }

        Collections.sort(comp);
        comp = new ArrayList<>(new LinkedHashSet<>(comp));

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                H[i][j] = Collections.binarySearch(comp, H[i][j]);
                d.add(new Cell(H[i][j], i, j));
            }
        }

        Collections.sort(d);

        for (int i = 0; i < Q; i++) {
            x1[i] = sc.nextInt();
            y1[i] = sc.nextInt();
            x2[i] = sc.nextInt();
            y2[i] = sc.nextInt();
            hi[i] = comp.size() - 1;
        }

        while (true) {
            boolean flag = false;
            for (int i = 0; i < Q; i++) {
                if (lo[i] <= hi[i]) {
                    mid[(lo[i] + hi[i]) / 2].add(i);
                    flag = true;
                }
            }
            if (!flag) break;

            parent = new int[(N + 2) * (M + 2)];
            for (int i = 0; i < parent.length; i++) parent[i] = i;

            for (Cell cell : d) {
                int i = cell.x, j = cell.y;
                for (int k = 0; k < 4; k++) {
                    int ni = i + dx[k], nj = j + dy[k];
                    if (ni >= 1 && ni <= N && nj >= 1 && nj <= M && H[ni][nj] <= cell.height) {
                        union(f(i, j), f(ni, nj));
                    }
                }

                for (int queryIndex : mid[cell.height]) {
                    if (H[x1[queryIndex]][y1[queryIndex]] <= cell.height && H[x2[queryIndex]][y2[queryIndex]] <= cell.height &&
                        find(f(x1[queryIndex], y1[queryIndex])) == find(f(x2[queryIndex], y2[queryIndex]))) {
                        ans[queryIndex] = cell.height;
                        hi[queryIndex] = cell.height - 1;
                    } else {
                        lo[queryIndex] = cell.height + 1;
                    }
                }
            }

            for (List<Integer> list : mid) list.clear();
        }

        for (int i = 0; i < Q; i++) {
            System.out.println(comp.get(ans[i]));
        }
    }

    static int f(int x, int y) {
        return x * (M + 2) + y;
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}
