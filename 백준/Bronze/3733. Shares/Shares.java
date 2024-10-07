import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력이 끝날 때까지 반복
        while (sc.hasNextInt()) {
            int N = sc.nextInt();
            int S = sc.nextInt();
            
            // 최대 주식 수 x는 S / (N + 1)로 계산
            int x = S / (N + 1);
            
            // 결과 출력
            System.out.println(x);
        }

        sc.close();
    }
}
