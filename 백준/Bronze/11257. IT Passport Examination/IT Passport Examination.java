import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 수험자 수
        sc.nextLine(); // 개행 문자 처리

        for (int i = 0; i < N; i++) {
            // 수험자 번호와 점수 입력받기
            String id = sc.next();
            int s = sc.nextInt();
            int m = sc.nextInt();
            int t = sc.nextInt();

            // 총점 계산
            int total = s + m + t;

            // 합격 기준 체크
            boolean pass = (total >= 55) && (s >= 11) && (m >= 8) && (t >= 12);

            // 출력
            System.out.println(id + " " + total + " " + (pass ? "PASS" : "FAIL"));
        }

        sc.close();
    }
}
