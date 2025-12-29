import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 테스트 케이스의 수 T 입력
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            // 꼭짓점(V)과 모서리(E) 입력
            int V = sc.nextInt();
            int E = sc.nextInt();

            // 오일러의 다면체 정리 변형: F = 2 - V + E
            int F = 2 - V + E;

            System.out.println(F);
        }
        
        sc.close();
    }
}