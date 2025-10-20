import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 입력 받기
        double l = sc.nextDouble(); // 길이
        double w = sc.nextDouble(); // 너비
        int n = sc.nextInt();     // 크레인 수
        double r = sc.nextDouble(); // 도달 거리

        // 거리 비교를 위해 r을 제곱 (sqrt 연산 방지)
        double rSq = r * r;

        // 2. 4개의 벽면 중앙 좌표 정의
        // wall[0] = 왼쪽, wall[1] = 오른쪽, wall[2] = 아래, wall[3] = 위
        double[][] walls = {
            {-l / 2.0, 0},     // 0 (binary 0001)
            {l / 2.0, 0},      // 1 (binary 0010)
            {0, -w / 2.0},   // 2 (binary 0100)
            {0, w / 2.0}     // 3 (binary 1000)
        };

        // 3. 전처리: 각 크레인이 덮을 수 있는 벽면을 비트마스크로 저장
        int[] craneCoverage = new int[n];
        for (int i = 0; i < n; i++) {
            double cx = sc.nextDouble();
            double cy = sc.nextDouble();
            int mask = 0;

            // 4개의 벽면을 모두 확인
            for (int j = 0; j < 4; j++) {
                double dx = cx - walls[j][0];
                double dy = cy - walls[j][1];
                double distSq = dx * dx + dy * dy;

                // 거리가 r 이하이면 (제곱 거리가 rSq 이하이면)
                if (distSq <= rSq) {
                    mask |= (1 << j); // j번째 비트를 1로 설정
                }
            }
            craneCoverage[i] = mask;
        }
        sc.close();

        // 4. 탐색: 최소 크레인 수 찾기
        int targetMask = 15; // 1111 (모든 벽면)

        // 크레인 1개로 가능한지 확인
        for (int i = 0; i < n; i++) {
            if (craneCoverage[i] == targetMask) {
                System.out.println(1);
                return;
            }
        }

        // 크레인 2개로 가능한지 확인
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((craneCoverage[i] | craneCoverage[j]) == targetMask) {
                    System.out.println(2);
                    return;
                }
            }
        }

        // 크레인 3개로 가능한지 확인
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if ((craneCoverage[i] | craneCoverage[j] | craneCoverage[k]) == targetMask) {
                        System.out.println(3);
                        return;
                    }
                }
            }
        }

        // 크레인 4개로 가능한지 확인
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int m = k + 1; m < n; m++) {
                        if ((craneCoverage[i] | craneCoverage[j] | craneCoverage[k] | craneCoverage[m]) == targetMask) {
                            System.out.println(4);
                            return;
                        }
                    }
                }
            }
        }
        
        // 4개로도 불가능하면 "Impossible"
        System.out.println("Impossible");
    }
}