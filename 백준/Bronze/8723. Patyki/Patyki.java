import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] sticks = new int[3];
        sticks[0] = sc.nextInt();
        sticks[1] = sc.nextInt();
        sticks[2] = sc.nextInt();

        Arrays.sort(sticks);  // 가장 큰 값이 맨 뒤로 오도록 정렬

        int a = sticks[0], b = sticks[1], c = sticks[2];

        // 삼각형 성립 여부 확인
        if (a + b <= c) {
            System.out.println(0);
            return;
        }

        // 정삼각형 여부 확인
        if (a == b && b == c) {
            System.out.println(2);
            return;
        }

        // 직각삼각형 여부 확인
        if (a * a + b * b == c * c) {
            System.out.println(1);
            return;
        }

        // 아무 조건도 해당 안 됨
        System.out.println(0);
    }
}
