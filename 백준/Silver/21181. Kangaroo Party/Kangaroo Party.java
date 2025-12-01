import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 캥거루 수 N 입력
        int n = sc.nextInt();
        
        // 집 위치 저장 배열
        int[] houses = new int[n];
        for (int i = 0; i < n; i++) {
            houses[i] = sc.nextInt();
        }
        sc.close();

        // 최솟값을 저장할 변수 (초기값은 아주 큰 수로 설정)
        long minTotalEnergy = Long.MAX_VALUE;

        // 2. 모든 가능한 두 파티 장소의 쌍(i, j)을 탐색
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                
                long currentTotalEnergy = 0;

                // 3. 모든 캥거루(k)에 대해 더 가까운 파티 장소로 이동할 때의 에너지 계산
                for (int k = 0; k < n; k++) {
                    // k번째 캥거루가 i번 집으로 갈 때의 에너지
                    long distToI = (long) (houses[k] - houses[i]) * (houses[k] - houses[i]);
                    
                    // k번째 캥거루가 j번 집으로 갈 때의 에너지
                    long distToJ = (long) (houses[k] - houses[j]) * (houses[k] - houses[j]);

                    // 둘 중 더 적은 에너지를 합산
                    currentTotalEnergy += Math.min(distToI, distToJ);
                }

                // 4. 최소 에너지 갱신
                if (currentTotalEnergy < minTotalEnergy) {
                    minTotalEnergy = currentTotalEnergy;
                }
            }
        }

        // 5. 결과 출력
        System.out.println(minTotalEnergy);
    }
}