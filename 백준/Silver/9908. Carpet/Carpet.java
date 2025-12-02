import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 격자판 크기 입력
        int R = sc.nextInt(); // 행 (Rows)
        int C = sc.nextInt(); // 열 (Columns)

        // 2. 기둥 정보 입력
        // 1-based index를 사용하기 위해 크기를 +1로 설정
        boolean[][] isPillar = new boolean[R + 1][C + 1];
        int N = sc.nextInt(); // 기둥의 개수

        for (int i = 0; i < N; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            isPillar[r][c] = true; // 기둥이 있는 곳은 true
        }

        int maxArea = 0;

        // 3. 모든 가능한 왼쪽 위 모서리 (r1, c1) 탐색
        for (int r1 = 1; r1 <= R; r1++) {
            for (int c1 = 1; c1 <= C; c1++) {
                // 시작점이 기둥이면 건너뜀
                if (isPillar[r1][c1]) continue;

                // 현재 직사각형의 최대 가능 너비를 초기화 (일단 끝까지 가능하다고 가정)
                int currentWidth = C - c1 + 1;

                // 4. 아래쪽 행(r2)으로 확장하며 직사각형 만들기
                for (int r2 = r1; r2 <= R; r2++) {
                    // r2행에서 c1부터 시작해서 연속된 빈칸의 길이 측정
                    int rowWidth = 0;
                    for (int k = c1; k <= C; k++) {
                        if (isPillar[r2][k]) break; // 기둥을 만나면 중단
                        rowWidth++;
                    }

                    // 직사각형의 너비는 지금까지 확인한 행들 중 가장 짧은 너비로 제한됨
                    currentWidth = Math.min(currentWidth, rowWidth);

                    // 너비가 0이 되면 더 이상 아래로 확장 불가능
                    if (currentWidth == 0) break;

                    // 현재 직사각형의 높이와 넓이 계산
                    int height = r2 - r1 + 1;
                    int area = currentWidth * height;

                    // 최대 넓이 갱신
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        // 5. 결과 출력
        System.out.println(maxArea);
        
        sc.close();
    }
}