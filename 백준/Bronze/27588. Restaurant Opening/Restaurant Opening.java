import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. 도시 크기 입력
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int[][] grid = new int[n][m];
        
        // 2. 인구 수 정보 입력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        
        // 최솟값을 찾기 위해 초기값을 큰 수로 설정 (long 사용 안전)
        long minTotalCost = Long.MAX_VALUE;
        
        // 3. 모든 격자를 식당 후보지(r, c)로 가정하고 완전 탐색
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                
                long currentCost = 0;
                
                // 후보지(r, c)에 대해 전체 인구의 이동 거리 비용 계산
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        // 해당 격자의 인구 수
                        int population = grid[i][j];
                        
                        if (population > 0) {
                            // 맨해튼 거리 계산: |r - i| + |c - j|
                            int distance = Math.abs(r - i) + Math.abs(c - j);
                            currentCost += population * distance;
                        }
                    }
                }
                
                // 최솟값 갱신
                if (currentCost < minTotalCost) {
                    minTotalCost = currentCost;
                }
            }
        }
        
        // 4. 결과 출력
        System.out.println(minTotalCost);
        
        sc.close();
    }
}