import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 크기 n
        int n = sc.nextInt();

        // 수행 횟수 출력
        System.out.println(n);

        // 최고차항의 차수 출력 (O(n) → 차수는 1)
        System.out.println(1);

        sc.close();
    }
}
