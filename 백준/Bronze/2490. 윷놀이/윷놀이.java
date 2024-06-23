import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 세 줄의 입력을 처리하기 위해 반복문을 사용합니다.
        for (int i = 0; i < 3; i++) {
            // 네 개의 윷 상태를 입력받아 배열에 저장합니다.
            int[] yut = new int[4];
            for (int j = 0; j < 4; j++) {
                yut[j] = scanner.nextInt();
            }

            // 윷 상태를 더하여 결과를 결정합니다.
            int sum = yut[0] + yut[1] + yut[2] + yut[3];

            // 합계에 따라 결과를 출력합니다.
            switch (sum) {
                case 0:
                    System.out.println("D"); // 윷
                    break;
                case 1:
                    System.out.println("C"); // 걸
                    break;
                case 2:
                    System.out.println("B"); // 개
                    break;
                case 3:
                    System.out.println("A"); // 도
                    break;
                case 4:
                    System.out.println("E"); // 모
                    break;
            }
        }

        scanner.close();
    }
}
