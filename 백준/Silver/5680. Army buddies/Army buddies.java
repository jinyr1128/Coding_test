import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입출력 속도를 위해 BufferedReader와 StringBuilder 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            
            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) break;
            
            int S = Integer.parseInt(st.nextToken()); // 병사 수
            int B = Integer.parseInt(st.nextToken()); // 손실 보고 수
            
            // 종료 조건
            if (S == 0 && B == 0) break;
            
            // 왼쪽, 오른쪽 전우를 저장할 배열 (1번부터 S번까지 사용)
            int[] left = new int[S + 2];
            int[] right = new int[S + 2];
            
            // 초기 연결 설정
            for (int i = 1; i <= S; i++) {
                left[i] = i - 1;   // i번 병사의 왼쪽은 i-1
                right[i] = i + 1;  // i번 병사의 오른쪽은 i+1
            }
            // 경계 조건 설정 (0은 '없음'을 의미)
            left[1] = 0; 
            right[S] = 0; 
            
            for (int i = 0; i < B; i++) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken()); // 제거 범위 시작
                int r = Integer.parseInt(st.nextToken()); // 제거 범위 끝
                
                // 제거되는 범위의 바로 왼쪽과 오른쪽 전우 찾기
                // l이 죽기 직전에 알고 있던 왼쪽 전우가 새로운 왼쪽 전우가 됨
                int newLeft = left[l];
                // r이 죽기 직전에 알고 있던 오른쪽 전우가 새로운 오른쪽 전우가 됨
                int newRight = right[r];
                
                // 출력 결과 생성
                if (newLeft == 0) sb.append("* ");
                else sb.append(newLeft).append(" ");
                
                if (newRight == 0) sb.append("*\n");
                else sb.append(newRight).append("\n");
                
                // 죽은 병사들을 건너뛰고 양쪽 생존자들을 서로 연결
                // 왼쪽 생존자의 오른쪽을 오른쪽 생존자로 갱신
                if (newLeft != 0) {
                    right[newLeft] = newRight;
                }
                
                // 오른쪽 생존자의 왼쪽을 왼쪽 생존자로 갱신
                if (newRight != 0) {
                    left[newRight] = newLeft;
                }
            }
            // 각 테스트 케이스 끝에 구분자 출력
            sb.append("-\n");
        }
        
        System.out.print(sb);
    }
}