import java.util.*;

public class Main {
    static class UnionFind {
        int[] parent;
        int[] left;
        int[] right;

        UnionFind(int n) {
            parent = new int[n];
            left = new int[n];
            right = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                left[i] = i;
                right[i] = i;
            }
        }

        int find(int x) {
            if (x == parent[x]) return x;
            return parent[x] = find(parent[x]);
        }

        void union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b) return;
            parent[b] = a;
            left[a] = Math.min(left[a], left[b]);
            right[a] = Math.max(right[a], right[b]);
        }

        int[] range(int i) {
            int root = find(i);
            return new int[]{left[root], right[root]};
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테스트 케이스 개수

        for (int t = 1; t <= T; t++) {
            int n = sc.nextInt();
            String s = sc.next();

            UnionFind uf = new UnionFind(n);

            // 인접한 '0'들 끼리 union
            for (int i = 1; i < n; i++) {
                if (s.charAt(i - 1) == '0' && s.charAt(i) == '0') {
                    uf.union(i - 1, i);
                }
            }

            long total = 0;

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '1') continue;

                int[] range = uf.range(i);
                int l = range[0];
                int r = range[1];

                int dist = Integer.MAX_VALUE;

                // 왼쪽 쓰레기통 탐색
                if (l > 0 && s.charAt(l - 1) == '1') {
                    dist = Math.min(dist, i - (l - 1));
                }

                // 오른쪽 쓰레기통 탐색
                if (r + 1 < n && s.charAt(r + 1) == '1') {
                    dist = Math.min(dist, (r + 1) - i);
                }

                total += dist;
            }

            System.out.println("Case #" + t + ": " + total);
        }
    }
}
