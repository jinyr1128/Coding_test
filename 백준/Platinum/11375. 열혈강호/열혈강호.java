import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer>[] employeeTasks;
    static int[] assignedTasks;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄 입력: 직원 수와 작업 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 각 직원이 수행할 수 있는 작업 리스트 초기화
        employeeTasks = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            employeeTasks[i] = new ArrayList<>();
        }

        // 직원이 가능한 작업 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int numTasks = Integer.parseInt(st.nextToken());
            for (int j = 0; j < numTasks; j++) {
                int task = Integer.parseInt(st.nextToken());
                employeeTasks[i].add(task);
            }
        }

        assignedTasks = new int[M + 1]; // 작업이 할당된 직원 저장 배열
        int maxTasks = 0;

        // 각 직원에 대해 DFS로 최대 매칭 찾기
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            if (dfs(i)) {
                maxTasks++;
            }
        }

        // 결과 출력
        System.out.println(maxTasks);
    }

    // DFS를 사용한 이분 매칭
    static boolean dfs(int employee) {
        visited[employee] = true;
        for (int task : employeeTasks[employee]) {
            // 작업이 비어있거나, 할당된 직원이 다른 작업을 할 수 있는 경우
            if (assignedTasks[task] == 0 || (!visited[assignedTasks[task]] && dfs(assignedTasks[task]))) {
                assignedTasks[task] = employee;
                return true;
            }
        }
        return false;
    }
}
