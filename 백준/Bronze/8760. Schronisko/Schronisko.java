import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int Z = sc.nextInt(); // 테스트 케이스 수

        for (int t = 0; t < Z; t++) {
            int W = sc.nextInt(); // 행
            int K = sc.nextInt(); // 열

            int area = W * K;
            int maxTourists = area / 2;

            System.out.println(maxTourists);
        }

        sc.close();
    }
}
