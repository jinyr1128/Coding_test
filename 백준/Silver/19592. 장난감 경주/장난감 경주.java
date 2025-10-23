import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 참가자 수
            long X = Long.parseLong(st.nextToken());  // 트랙 길이
            int Y = Integer.parseInt(st.nextToken());   // 부스터 한계치

            long[] V = new long[N]; // 속도 배열
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                V[i] = Long.parseLong(st.nextToken());
            }

            long myNormalSpeed = V[N - 1]; // 나의 기본 속도
            long maxOpponentSpeed = 0;   // 가장 빠른 상대방의 속도

            // 1. 가장 빠른 상대방 속도 찾기
            for (int i = 0; i < N - 1; i++) {
                maxOpponentSpeed = Math.max(maxOpponentSpeed, V[i]);
            }

            // 2-1. Case 1: 부스터 불필요
            // 내 기본 속도가 상대 최고 속도보다 빠르면 단독 우승
            // (X / myNormalSpeed) < (X / maxOpponentSpeed)  =>  myNormalSpeed > maxOpponentSpeed
            if (myNormalSpeed > maxOpponentSpeed) {
                System.out.println(0);
                continue;
            }

            // 상대방의 최소 완주 시간 (기준 시간)
            double minOpponentTime = (double) X / maxOpponentSpeed;
            // Epsilon: 부동소수점 비교 시 '같다'를 피하기 위한 아주 작은 값
            // (단독 우승 = 'strictly less')
            double EPSILON = 1e-9; 

            // 2-2. Case 2: 최대 부스터(Z=Y)로도 불가능
            // 나의 최대 부스트 사용 시 걸리는 시간
            double myMaxBoostTime = 1.0 + (double) (X - Y) / myNormalSpeed;

            // 내 시간이 상대 시간보다 '크거나 같으면' (빠르지 않으면) -1
            if (myMaxBoostTime >= minOpponentTime - EPSILON) {
                System.out.println(-1);
                continue;
            }

            // 2-3. Case 3: 이진 탐색으로 최소 Z 찾기
            int low = 1;
            int high = Y;
            int minZ = Y; // Y는 이미 우승 가능함을 확인했으므로 최댓값으로 설정

            while (low <= high) {
                int midZ = low + (high - low) / 2;
                
                // Z = midZ 일 때 나의 완주 시간
                double myCurrentTime = 1.0 + (double) (X - midZ) / myNormalSpeed;

                // 단독 우승이 가능한 경우 (내 시간이 더 빠름)
                if (myCurrentTime < minOpponentTime - EPSILON) {
                    minZ = midZ;      // 이 Z값을 저장하고 더 작은 Z를 시도
                    high = midZ - 1;
                } else {
                    // 단독 우승이 불가능한 경우 (Z가 부족함)
                    low = midZ + 1;
                }
            }
            System.out.println(minZ);
        }
    }
}