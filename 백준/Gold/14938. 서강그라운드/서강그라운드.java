import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int[] items = new int[n + 1];
        int[][] dist = new int[n + 1][n + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }
        
        // 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], 10001); // 거리가 l의 최대값을 넘지 않는다는 보장이 있으므로 충분히 큰 수로 초기화
            dist[i][i] = 0;
        }
        
        // 길 정보 입력받아 처리
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            dist[a][b] = l;
            dist[b][a] = l;
        }
        
        // 플로이드-워셜 알고리즘으로 모든 지점 간 최단 거리 계산
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        // 각 지점에서 출발했을 때 수집 가능한 최대 아이템 계산
        int maxItems = 0;
        for (int i = 1; i <= n; i++) {
            int currentItemSum = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] <= m) {
                    currentItemSum += items[j];
                }
            }
            maxItems = Math.max(maxItems, currentItemSum);
        }
        
        System.out.println(maxItems);
    }
}
