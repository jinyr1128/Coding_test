import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 부피 V 입력 (최대 1,000,000)
        long V = sc.nextLong();
        
        // 최솟값을 저장할 변수 초기화 (가장 극단적인 경우인 1x1xV로 초기화)
        long minArea = Long.MAX_VALUE;
        
        // 1. 첫 번째 변(i) 탐색: 1부터 세제곱근 V까지
        // i * i * i <= V 조건은 i가 가장 작은 변이라는 가정 하에 성립
        for (long i = 1; i * i * i <= V; i++) {
            if (V % i == 0) { // i가 V의 약수인 경우
                long remain = V / i;
                
                // 2. 두 번째 변(j) 탐색: i부터 제곱근 remain까지
                // j * j <= remain 조건은 j가 k보다 작거나 같다는 가정 하에 성립
                for (long j = i; j * j <= remain; j++) {
                    if (remain % j == 0) { // j가 remain의 약수인 경우
                        long k = remain / j; // 세 번째 변 k 결정
                        
                        // 겉넓이 계산: 2(ij + jk + ki)
                        long currentArea = 2 * (i * j + j * k + k * i);
                        
                        if (currentArea < minArea) {
                            minArea = currentArea;
                        }
                    }
                }
            }
        }
        
        System.out.println(minArea);
        
        sc.close();
    }
}