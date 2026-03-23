import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); // 결과를 한 번에 출력하기 위해 사용
        
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            
            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue; // 빈 줄 방어
            
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            // N과 M이 모두 0이면 입력 종료
            if (N == 0 && M == 0) {
                break;
            }
            
            // 선수들의 점수를 저장할 배열 (선수 번호 1 ~ 10000)
            int[] counts = new int[10001];
            int maxScore = 0;
            
            // N주 동안의 랭킹 정보 입력
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int player = Integer.parseInt(st.nextToken());
                    counts[player]++; // 해당 선수의 포인트 1 증가
                    
                    // 1등의 점수(최고점) 갱신
                    if (counts[player] > maxScore) {
                        maxScore = counts[player];
                    }
                }
            }
            
            // 2등의 점수 찾기
            int secondMaxScore = 0;
            for (int i = 1; i <= 10000; i++) {
                // 1등 점수보다는 낮고, 현재 저장된 2등 점수보다 높은 점수를 찾음
                if (counts[i] < maxScore && counts[i] > secondMaxScore) {
                    secondMaxScore = counts[i];
                }
            }
            
            // 2등 점수를 가진 선수들의 번호를 오름차순으로 추가
            for (int i = 1; i <= 10000; i++) {
                if (counts[i] == secondMaxScore) {
                    sb.append(i).append(" ");
                }
            }
            sb.append("\n"); // 한 테스트 케이스가 끝나면 줄바꿈
        }
        
        // 최종 결과 출력
        System.out.print(sb.toString());
    }
}