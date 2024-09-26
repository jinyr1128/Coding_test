import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final int MAX = 16;
    static final int INF = 987654321;
    static int N, P, answer = INF;
    static int[][] map = new int[MAX][MAX];
    static int[] cost = new int[1 << MAX];
    static String state;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        state = sc.next();
        P = sc.nextInt();
        sc.close();

        solve();
    }

    // 상태에 대한 비트 카운트
    static int bitCount(int bit) {
        int count = 0;
        while (bit != 0) {
            count += (bit & 1);
            bit >>= 1;
        }
        return count;
    }

    // DFS를 이용하여 최소 비용 계산
    static int dfs(int currentState) {
        if (bitCount(currentState) >= P) return 0;
        if (cost[currentState] != -1) return cost[currentState];

        cost[currentState] = INF;
        for (int i = 0; i < N; i++) {
            if ((currentState & (1 << i)) == 0) continue; // i번째 발전소가 꺼져있으면 스킵
            for (int j = 0; j < N; j++) {
                if ((currentState & (1 << j)) == 0) { // j번째 발전소가 꺼져있을 때
                    int nextState = currentState | (1 << j); // j번째 발전소를 켜는 상태
                    cost[currentState] = Math.min(cost[currentState], map[i][j] + dfs(nextState));
                }
            }
        }
        return cost[currentState];
    }

    // 문제 해결을 위한 초기화 및 실행
    static void solve() {
        int bitState = 0;
        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) == 'Y') bitState |= (1 << i);
        }

        Arrays.fill(cost, -1);
        answer = dfs(bitState);

        if (answer == INF) System.out.println(-1);
        else System.out.println(answer);
    }
}
