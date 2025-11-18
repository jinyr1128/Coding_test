import java.util.Scanner;

public class Main {

    // 조합(nCr)을 구하는 메소드
    public static long combinations(int n, int r) {
        if (r < 0 || r > n) return 0;
        if (r == 0 || r == n) return 1;
        if (r > n / 2) r = n - r;
        
        long res = 1;
        for (int i = 1; i <= r; i++) {
            // 오버플로우 방지를 위해 곱하고 나누기를 순차적으로 수행
            res = res * (n - i + 1) / i;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (sc.hasNextInt()) {
            int T = sc.nextInt(); // 테스트 케이스 수
            
            while (T-- > 0) {
                int N = sc.nextInt();  // 전체 유권자 수
                int V1 = sc.nextInt(); // 내 후보 득표수
                int V2 = sc.nextInt(); // 상대 후보 득표수
                int W = sc.nextInt();  // 승리 선언 기준 확률 (%)

                int remaining = N - (V1 + V2); // 남은 표
                int threshold = (N / 2) + 1;   // 과반수 기준
                int needed = threshold - V1;   // 내가 추가로 필요한 표

                // 1. 물리적으로 승리가 불가능한 경우 (남은 표를 다 받아도 부족)
                if (needed > remaining) {
                    System.out.println("RECOUNT!");
                    continue;
                }

                // 2. 이미 승리가 확정된 경우 (필요한 표가 0 이하)
                // W < 100 이므로 100% 확률은 무조건 샴페인입니다.
                if (needed <= 0) {
                    System.out.println("GET A CRATE OF CHAMPAGNE FROM THE BASEMENT!");
                    continue;
                }

                // 3. 확률 계산
                // 전체 경우의 수 = 2 ^ remaining
                long totalOutcomes = 1L << remaining;
                
                // 이기는 경우의 수 = needed 개 이상의 표를 받는 경우들의 합
                long favorableOutcomes = 0;
                for (int i = needed; i <= remaining; i++) {
                    favorableOutcomes += combinations(remaining, i);
                }

                // 확률 비교: (favorable / total) * 100 > W
                // 정수 연산으로 변환: favorable * 100 > W * total
                // long 범위를 넘지 않으므로 안전합니다.
                if (favorableOutcomes * 100 > (long) W * totalOutcomes) {
                    System.out.println("GET A CRATE OF CHAMPAGNE FROM THE BASEMENT!");
                } else {
                    // 확률이 0보다는 크지만 W 이하인 경우
                    // (확률 0인 경우는 위에서 needed > remaining으로 걸러짐)
                    System.out.println("PATIENCE, EVERYONE!");
                }
            }
        }
        sc.close();
    }
}