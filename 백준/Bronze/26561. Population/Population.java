import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 테스트 케이스 수

        for (int i = 0; i < n; i++) {
            long p = sc.nextLong(); // 초기 인구
            long t = sc.nextLong(); // 시간 (초 단위)

            long born = t / 4;       // 4초마다 한 명 태어남
            long died = t / 7;       // 7초마다 한 명 죽음

            long result = p + born - died;

            System.out.println(result);
        }

        sc.close();
    }
}
