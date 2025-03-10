import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 테스트 케이스 개수

        for (int i = 0; i < n; i++) {
            int k = sc.nextInt(); // 히스토그램 크기 입력
            System.out.println("=".repeat(k)); // k개의 '=' 출력
        }

        sc.close();
    }
}
