import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 개수

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());  // 건물의 개수
            int K = Integer.parseInt(st.nextToken());  // 건설 순서 규칙의 개수

            int[] constructionTime = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                constructionTime[i] = Integer.parseInt(st.nextToken());  // 각 건물 건설에 걸리는 시간
            }

            ArrayList<Integer>[] graph = new ArrayList[N + 1];
            int[] inDegree = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                graph[X].add(Y);  // X 건물 다음에 Y 건물을 짓도록 규칙 설정
                inDegree[Y]++;
            }

            int W = Integer.parseInt(br.readLine());  // 최종적으로 완성해야 할 건물 번호

            // 위상 정렬을 위한 큐
            Queue<Integer> queue = new LinkedList<>();
            int[] earliestCompletion = new int[N + 1];

            // 초기에 진입차수가 0인 노드들 큐에 추가
            for (int i = 1; i <= N; i++) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                    earliestCompletion[i] = constructionTime[i];
                }
            }

            // 위상 정렬 수행
            while (!queue.isEmpty()) {
                int current = queue.poll();
                
                for (int next : graph[current]) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        queue.add(next);
                    }
                    // 최소 건설 완료 시간 갱신
                    earliestCompletion[next] = Math.max(earliestCompletion[next], earliestCompletion[current] + constructionTime[next]);
                }
            }

            bw.write(earliestCompletion[W] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
