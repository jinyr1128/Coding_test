import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 문제 개수
        int excludedCount = 0;

        for (int i = 0; i < n; i++) {
            int d = sc.nextInt(); // 난이도
            if (d % 2 != 0) {     // 홀수이면 제외
                excludedCount++;
            }
        }

        System.out.println(excludedCount); // 제외된 문제 개수 출력
    }
}
