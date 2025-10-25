import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        
        // N의 최대값이 100,000이므로 미리 배열을 선언
        int[] count = new int[100001];
        int[] prefixPairs = new int[100001];

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            
            // 0으로 초기화 (N+1까지만)
            Arrays.fill(count, 0, N + 1, 0);
            Arrays.fill(prefixPairs, 0, N + 1, 0);

            // 1. 높이별 개수 세기
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int height = Integer.parseInt(st.nextToken());
                count[height]++;
            }

            // 2. 2마리 이상인 높이에 대한 누적 합 배열 생성
            for (int h = 1; h <= N; h++) {
                prefixPairs[h] = prefixPairs[h - 1];
                if (count[h] >= 2) {
                    prefixPairs[h]++;
                }
            }

            // 3. 모든 h를 peak로 가정하고 최대 K 찾기
            int maxK = 0;
            for (int h = 1; h <= N; h++) {
                // h를 peak로 사용 가능해야 함 (최소 1마리)
                if (count[h] >= 1) {
                    // k = h보다 작은 높이 중 2마리 이상인 것의 개수
                    int k = prefixPairs[h - 1];
                    int currentK = 2 * k + 1;
                    maxK = Math.max(maxK, currentK);
                }
            }
            
            pw.println(maxK);
        }
        
        pw.flush();
        pw.close();
        br.close();
    }
}