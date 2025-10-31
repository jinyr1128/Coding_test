import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. N(달걀 수)과 M(고객 수) 입력
        int N = sc.nextInt();
        int M = sc.nextInt();

        // 2. M명의 고객이 제시한 가격을 배열에 저장
        int[] prices = new int[M];
        for (int i = 0; i < M; i++) {
            prices[i] = sc.nextInt();
        }

        // 3. 가격 배열 오름차순 정렬
        Arrays.sort(prices);

        long maxProfit = 0; // 최대 수익 (수익은 int 범위를 넘을 수 있으므로 long)
        int bestPrice = 0; // 최대 수익일 때의 가격

        // 4. M개의 모든 가격을 판매 가격 후보로 검사
        for (int i = 0; i < M; i++) {
            int currentPrice = prices[i]; // 현재 후보 가격
            
            // 5. 구매 가능 고객 수 계산
            int buyers = M - i;
            
            // 6. 실제 판매량 계산 (달걀 수 N을 넘을 수 없음)
            int sales = Math.min(N, buyers);
            
            // 7. 현재 가격으로의 수익 계산 (long으로 형변환 주의)
            long currentProfit = (long)currentPrice * sales;
            
            // 8. 최대 수익 갱신
            // (profit > maxProfit) 조건만으로 "가장 낮은 가격" 룰이 자동 만족됨
            // (이미 오름차순으로 보고 있으므로)
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
                bestPrice = currentPrice;
            }
        }

        // 9. 결과 출력
        System.out.println(bestPrice + " " + maxProfit);
        sc.close();
    }
}