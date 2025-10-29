import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 입력 받기
        int N = sc.nextInt();
        int[] heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = sc.nextInt();
        }
        sc.close();

        int maxUphillSize = 0; // 가장 큰 오르막길 크기
        int currentStartHeight = heights[0]; // 현재 오르막길의 시작 높이

        // 2. 수열 순회 (1번 인덱스부터 N-1까지)
        for (int i = 1; i < N; i++) {
            if (heights[i] > heights[i - 1]) {
                // 오르막길이 계속되는 경우: 아무것도 하지 않음
            } else {
                // 오르막길이 끊긴 경우
                // (heights[i-1]이 직전 오르막길의 최고점이었음)
                int currentUphillSize = heights[i - 1] - currentStartHeight;
                
                // 최댓값 갱신
                maxUphillSize = Math.max(maxUphillSize, currentUphillSize);
                
                // 현재 위치에서 다음 오르막길 시작
                currentStartHeight = heights[i];
            }
        }

        // 3. 마지막까지 오르막길이 이어진 경우를 처리
        // (예: 1 2 3 4 5)
        int lastUphillSize = heights[N - 1] - currentStartHeight;
        maxUphillSize = Math.max(maxUphillSize, lastUphillSize);

        // 결과 출력
        System.out.println(maxUphillSize);
    }
}