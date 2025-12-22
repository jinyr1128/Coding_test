import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. 라운드 수 N 입력
        int N = Integer.parseInt(br.readLine());
        
        int[] A = new int[N]; // 에이스 팀의 수
        int[] B = new int[N]; // 다니엘 팀의 수
        
        // 2. 에이스 팀의 가위바위보 정보 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        // 3. 다니엘 팀의 가위바위보 정보 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        
        int maxStreak = 0;     // 최대 연승 횟수
        int currentStreak = 0; // 현재 연승 횟수
        int winner = 0;        // 현재 승자 (1: 에이스, 2: 다니엘)
        
        // 4. 게임 시뮬레이션
        for (int i = 0; i < N; i++) {
            int roundWinner = 0; // 이번 판의 실제 승자 (규칙 적용 후)
            
            if (A[i] == B[i]) {
                // 비긴 경우: 도전자가 승리 (현재 승자의 반대편)
                // 첫 판(i=0)은 비기는 경우가 없다고 했으므로, i>0 일 때만 발생
                if (winner == 1) roundWinner = 2;
                else roundWinner = 1;
            } else if (checkAWin(A[i], B[i])) {
                // A가 이긴 경우
                roundWinner = 1;
            } else {
                // B가 이긴 경우
                roundWinner = 2;
            }
            
            // 첫 판 처리
            if (i == 0) {
                winner = roundWinner;
                currentStreak = 1;
            } else {
                // 기존 승자와 이번 판 승자가 같은지 확인
                if (winner == roundWinner) {
                    currentStreak++;
                } else {
                    // 승자가 바뀌면 연승 초기화 및 승자 교체
                    winner = roundWinner;
                    currentStreak = 1;
                }
            }
            
            // 최대 연승 갱신
            maxStreak = Math.max(maxStreak, currentStreak);
        }
        
        System.out.println(maxStreak);
    }
    
    // A가 이기면 true, 지면 false를 반환하는 함수 (비기는 경우는 제외하고 호출됨)
    // 1:가위, 2:바위, 3:보
    public static boolean checkAWin(int a, int b) {
        if (a == 1 && b == 3) return true; // 가위 vs 보
        if (a == 2 && b == 1) return true; // 바위 vs 가위
        if (a == 3 && b == 2) return true; // 보 vs 바위
        return false;
    }
}