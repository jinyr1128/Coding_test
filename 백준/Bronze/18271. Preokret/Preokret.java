import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());   // 골 수 (≤ 250)

        int[] goals = new int[N];
        for (int i = 0; i < N; i++) goals[i] = Integer.parseInt(br.readLine().trim());

        /* ------------ 공통 변수 ------------ */
        int city   = 0;   // 팀 1 득점
        int opp    = 0;   // 팀 2 득점

        HashSet<Integer> tieSet = new HashSet<>(); // 동점이 된 점수(=양쪽 동일 득점)
        tieSet.add(0);                            // 0:0 출발도 동점

        int prevTeam  = -1;   // 직전 골 팀
        int blkLen    = 0;    // 현재 연속 골 길이
        int blkStart1 = 0;    // 블록 시작 시 시티 점수
        int blkStart2 = 0;    // 블록 시작 시 상대 점수
        int maxTurn   = 0;    // 최대 turnover 길이

        /* ------------ 골 순회 ------------ */
        for (int g : goals) {

            // 새 블록이 시작되면 이전 블록 평가
            if (prevTeam == -1 || prevTeam != g) {

                if (prevTeam != -1) {
                    evaluateBlock(prevTeam, blkLen,
                                   blkStart1, blkStart2, city, opp,   // 블록 전·후 점수
                                   tieSet, maxTurnHolder);            // 업데이트
                    maxTurn = maxTurnHolder[0];
                }

                // 새 블록 정보 초기화
                blkStart1 = city;
                blkStart2 = opp;
                blkLen    = 0;
                prevTeam  = g;
            }

            /* 현재 골 반영 */
            if (g == 1) city++;
            else        opp++;
            blkLen++;

            if (city == opp) tieSet.add(city);
        }

        /* 마지막 블록 평가 */
        if (prevTeam != -1) {
            evaluateBlock(prevTeam, blkLen,
                          blkStart1, blkStart2, city, opp,
                          tieSet, maxTurnHolder);
            maxTurn = maxTurnHolder[0];
        }

        /* ------------ 결과 출력 ------------ */
        StringBuilder sb = new StringBuilder();
        sb.append(city).append(' ').append(opp).append('\n');
        sb.append(tieSet.size()).append('\n');
        sb.append(maxTurn).append('\n');

        System.out.print(sb.toString());
    }

    /**
     * 현재 블록(같은 팀이 연속해서 넣은 골)의 turnover 여부를 확인하고
     * 최대 turnover 길이를 필요하면 갱신한다.
     */
    private static void evaluateBlock(int team, int len,
                                      int before1, int before2,   // 블록 시작 전 점수
                                      int after1,  int after2,    // 블록 종료 후 점수
                                      HashSet<Integer> tieSet,    // 동점 집합(사용 안 하지만 인자 맞춤)
                                      int[] maxHolder) {

        // team==1 → 시티, team==2 → 상대
        boolean wasLosing, isWinning;

        if (team == 1) {
            wasLosing = before1 < before2;
            isWinning = after1  > after2;
        } else {
            wasLosing = before2 < before1;
            isWinning = after2  > after1;
        }

        if (wasLosing && isWinning) {
            if (len > maxHolder[0]) maxHolder[0] = len;
        }
    }

    /* turnover 최대값을 array(길이1) 로 전달하기 위한 임시 저장소 */
    private static int[] maxTurnHolder = new int[]{0};
}
