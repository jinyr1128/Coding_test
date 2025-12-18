import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int R = sc.nextInt();
            if (R == 0) break; // 입력 종료 조건

            int[] M = new int[R]; // Mark의 몬스터들
            int[] L = new int[R]; // Leti의 몬스터들

            // Mark 입력 및 기본 점수 합산
            int scoreM = 0;
            for (int i = 0; i < R; i++) {
                M[i] = sc.nextInt();
                scoreM += M[i];
            }

            // Leti 입력 및 기본 점수 합산
            int scoreL = 0;
            for (int i = 0; i < R; i++) {
                L[i] = sc.nextInt();
                scoreL += L[i];
            }

            // 보너스 점수 계산 (연속 3번 같은 숫자)
            // 3번째 라운드(인덱스 2)부터 확인 가능
            for (int i = 2; i < R; i++) {
                boolean markBonus = (M[i] == M[i - 1] && M[i] == M[i - 2]);
                boolean letiBonus = (L[i] == L[i - 1] && L[i] == L[i - 2]);

                if (markBonus && letiBonus) {
                    // 동시에 달성한 경우: 아무도 점수를 못 받고, 보너스 기회는 끝남
                    break;
                } else if (markBonus) {
                    // Mark가 먼저 달성: Mark +30점, 기회 끝
                    scoreM += 30;
                    break;
                } else if (letiBonus) {
                    // Leti가 먼저 달성: Leti +30점, 기회 끝
                    scoreL += 30;
                    break;
                }
            }

            // 승자 출력
            if (scoreM > scoreL) {
                System.out.println("M");
            } else if (scoreL > scoreM) {
                System.out.println("L");
            } else {
                System.out.println("T");
            }
        }
        
        sc.close();
    }
}