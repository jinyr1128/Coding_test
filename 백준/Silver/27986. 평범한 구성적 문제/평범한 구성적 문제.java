import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 첫 번째 줄 입력: N, M
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // K의 최댓값은 가장 짧은 구간의 길이와 같습니다.
        // 초기값을 N으로 설정 (구간의 최대 길이는 N이므로)
        int minLength = N;
        
        // M개의 구간 정보를 입력받으며 최소 길이를 갱신
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            
            int length = R - L + 1;
            if (length < minLength) {
                minLength = length;
            }
        }
        
        int K = minLength;
        
        // 결과 수열 생성 및 출력
        // 1, 2, ..., K, 1, 2, ... 패턴으로 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            // i는 0부터 시작하므로 (i % K) + 1을 하면 1 ~ K 사이의 값이 반복됨
            sb.append((i % K) + 1).append(" ");
        }
        
        System.out.println(sb.toString().trim());
    }
}