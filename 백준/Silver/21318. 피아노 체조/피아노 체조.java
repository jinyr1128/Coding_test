import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        
        // 빠른 입출력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. N 입력
        int N = Integer.parseInt(br.readLine());

        // 2. N개의 악보 난이도 입력 (1-based 인덱싱을 위해 N+1 크기)
        int[] difficulties = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            difficulties[i] = Integer.parseInt(st.nextToken());
        }

        // 3. 누적 합 배열 (mistakeSum) 생성
        // mistakeSum[i] = 1번부터 i번 악보까지 연주할 때 발생하는 총 실수 횟수
        int[] mistakeSum = new int[N + 1];
        mistakeSum[0] = 0;
        
        for (int i = 1; i < N; i++) {
            if (difficulties[i] > difficulties[i + 1]) {
                // i번째에서 실수가 발생하면, 누적 합 + 1
                mistakeSum[i] = mistakeSum[i - 1] + 1;
            } else {
                // 실수가 없으면, 이전 누적 합을 그대로 가져옴
                mistakeSum[i] = mistakeSum[i - 1];
            }
        }
        // N번째는 (i+1)이 없으므로 N-1번째의 누적 합을 그대로 가짐
        if (N > 0) {
            mistakeSum[N] = mistakeSum[N - 1];
        }

        // 4. Q개의 질문 처리
        int Q = Integer.parseInt(br.readLine());
        
        // 빠른 출력을 위한 StringBuilder
        StringBuilder sb = new StringBuilder();
        
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // x부터 y까지의 실수 횟수 = (1부터 y-1까지의 실수) - (1부터 x-1까지의 실수)
            int result = mistakeSum[y - 1] - mistakeSum[x - 1];
            
            sb.append(result).append('\n');
        }

        // 5. 정답 출력
        System.out.print(sb.toString());
    }
}