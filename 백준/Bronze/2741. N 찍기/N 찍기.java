import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();  // 자연수 N 입력 받기

        for (int i = 1; i <= N; i++) {
            System.out.println(i);  // 1부터 N까지 차례대로 출력
        }
    }
}
