import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String line = br.readLine();
        if (line == null) return;
        int N = Integer.parseInt(line.trim());

        // 메모리와 시간 최적화를 위해 x좌표와 길이를 하나의 long 배열에 압축
        long[] dominoes = new long[N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long l = Long.parseLong(st.nextToken());
            
            // a를 상위 32비트에, l을 하위 32비트에 저장
            dominoes[i] = (a << 32) | l;
        }

        // x 좌표를 기준으로 오름차순 자동 정렬
        Arrays.sort(dominoes);

        // 첫 번째 도미노는 무조건 밀어야 하므로 1부터 시작
        int pushes = 1;

        for (int i = 0; i < N - 1; i++) {
            // 비트 연산으로 x좌표와 길이 복원
            long currentA = dominoes[i] >>> 32;
            long currentL = dominoes[i] & 0xFFFFFFFFL;
            long nextA = dominoes[i + 1] >>> 32;

            // 현재 도미노가 쓰러져서 바로 다음 도미노에 닿지 못하는 경우
            if (currentA + currentL < nextA) {
                pushes++; // 연쇄 반응이 끊겼으므로 새로 밀어야 함
            }
        }

        System.out.println(pushes);
    }
}