import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 테스트 케이스 개수

        while (n-- > 0) {
            int l1 = sc.nextInt(), a1 = sc.nextInt();
            int l2 = sc.nextInt(), a2 = sc.nextInt();
            int lt = sc.nextInt(), at = sc.nextInt();

            int solutionCount = 0;
            int xSolution = 0, ySolution = 0;

            // 가능한 x와 y 찾기 (1부터 lt/l1 범위 내에서 탐색)
            for (int x = 1; x * l1 <= lt && x * a1 <= at; x++) {
                if ((lt - l1 * x) % l2 == 0 && (at - a1 * x) % a2 == 0) {
                    int y = (lt - l1 * x) / l2;
                    if (y > 0 && (at == a1 * x + a2 * y)) { // y가 양수여야 함
                        if (solutionCount == 0) {
                            xSolution = x;
                            ySolution = y;
                            solutionCount++;
                        } else {
                            solutionCount = 2; // 두 개 이상의 해가 존재
                            break;
                        }
                    }
                }
            }

            // 결과 출력
            if (solutionCount == 1) {
                System.out.println(xSolution + " " + ySolution);
            } else {
                System.out.println("?");
            }
        }

        sc.close();
    }
}
