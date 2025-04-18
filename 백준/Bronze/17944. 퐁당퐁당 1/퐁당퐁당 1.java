import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 사람 수
        int T = sc.nextInt(); // T번째 차례

        int cycle = 4 * N - 2; // 전체 주기 길이
        int idx = (T - 1) % cycle;

        int result;
        if (idx < 2 * N) {
            result = 1 + idx; // 증가하는 구간
        } else {
            result = 4 * N - 1 - idx; // 감소하는 구간
        }

        System.out.println(result);
    }
}
