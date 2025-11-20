import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // N: 첫 줄의 코 수, M: 총 줄 수, K: 패턴의 길이
            int N = sc.nextInt();
            int M = sc.nextInt();
            int K = sc.nextInt();

            // 종료 조건 (0 0 0)
            if (N == 0 && M == 0 && K == 0) {
                break;
            }

            // 패턴 변화량 입력
            int[] pattern = new int[K];
            for (int i = 0; i < K; i++) {
                pattern[i] = sc.nextInt();
            }

            int currentStitches = N;
            int totalStitches = N;

            // 2번째 줄부터 M번째 줄까지 반복
            for (int row = 2; row <= M; row++) {
                // 이번 줄에 적용할 패턴의 인덱스 계산
                // row=2 -> index=0, row=3 -> index=1 ...
                int patternIndex = (row - 2) % K;
                
                // 현재 줄의 코 수 갱신
                currentStitches += pattern[patternIndex];
                
                // 총 코 수에 누적
                totalStitches += currentStitches;
            }

            System.out.println(totalStitches);
        }
        
        sc.close();
    }
}