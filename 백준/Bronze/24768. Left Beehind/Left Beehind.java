import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 0 0 이 입력될 때까지 무한 반복
        while (true) {
            int sweetJars = sc.nextInt();
            int sourJars = sc.nextInt();

            // 1. 종료 조건
            if (sweetJars == 0 && sourJars == 0) {
                break;
            }

            // 2. 가장 우선순위가 높은 조건 (총 13개)
            if (sweetJars + sourJars == 13) {
                System.out.println("Never speak again.");
            }
            // 3. 달콤한 꿀이 더 많을 때
            else if (sweetJars > sourJars) {
                System.out.println("To the convention.");
            }
            // 4. 신 꿀이 더 많을 때
            else if (sourJars > sweetJars) {
                System.out.println("Left beehind.");
            }
            // 5. 수가 같을 때
            else { // sweetJars == sourJars
                System.out.println("Undecided.");
            }
        }
        
        sc.close();
    }
}