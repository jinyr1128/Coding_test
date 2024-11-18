import java.util.*;

public class Main {

    static List<Integer>[] tasks;
    static int[] work;
    static boolean[] visited;
    static int N, M, K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 직원 수
        M = sc.nextInt(); // 작업 수
        K = sc.nextInt(); // 일을 2개까지 할 수 있는 직원 수

        tasks = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tasks[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            int c = sc.nextInt();
            for (int j = 0; j < c; j++) {
                tasks[i].add(sc.nextInt());
            }
        }

        work = new int[M + 1];
        visited = new boolean[M + 1];

        int count = 0;

        // 첫 번째 매칭 시도
        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited, false);
            if (dfs(i)) {
                count++;
            }
        }

        // 두 번째 매칭 시도 (최대 K번 추가 매칭 가능)
        for (int i = 1; i <= N && K > 0; i++) {
            Arrays.fill(visited, false);
            if (dfs(i)) {
                count++;
                K--;
            }
        }

        System.out.println(count);
    }

    // DFS를 통해 매칭 시도
    static boolean dfs(int x) {
        for (int task : tasks[x]) {
            if (visited[task]) {
                continue;
            }
            visited[task] = true;

            // 해당 작업이 비어있거나, 현재 작업을 담당하는 직원이 다른 작업으로 이동 가능하면 매칭
            if (work[task] == 0 || dfs(work[task])) {
                work[task] = x;
                return true;
            }
        }
        return false;
    }
}
