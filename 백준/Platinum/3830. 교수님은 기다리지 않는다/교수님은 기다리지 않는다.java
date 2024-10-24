import java.util.*;

public class Main {
    static final int MAX = 100010;
    static int[] parent = new int[MAX];
    static long[] diff = new long[MAX];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            if (n == 0 && m == 0) {
                break;
            }
            
            init(n);
            
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < m; i++) {
                char cmd = scanner.next().charAt(0);
                if (cmd == '!') {
                    int a = scanner.nextInt();
                    int b = scanner.nextInt();
                    long w = scanner.nextLong();
                    union(a, b, w);
                } else if (cmd == '?') {
                    int a = scanner.nextInt();
                    int b = scanner.nextInt();
                    if (find(a) == find(b)) {
                        result.append(diff[b] - diff[a]).append("\n");
                    } else {
                        result.append("UNKNOWN\n");
                    }
                }
            }
            System.out.print(result);
        }
        scanner.close();
    }

    // 초기화 메서드
    static void init(int n) {
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            diff[i] = 0;
        }
    }

    // find 연산 메서드 (경로 압축 최적화 적용)
    static int find(int x) {
        if (parent[x] != x) {
            int originalParent = parent[x];
            parent[x] = find(parent[x]);
            diff[x] += diff[originalParent]; // 부모 노드로부터 루트까지의 거리 추가
        }
        return parent[x];
    }

    // union 연산 메서드
    static void union(int a, int b, long w) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
            diff[rootB] = diff[a] - diff[b] + w; // 두 노드의 관계 업데이트
        }
    }
}
