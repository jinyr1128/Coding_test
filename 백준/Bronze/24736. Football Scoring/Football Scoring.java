import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 원정 팀(Visiting Team) 점수 계산
        int t1 = sc.nextInt(); // Touchdown (6점)
        int f1 = sc.nextInt(); // Field Goal (3점)
        int s1 = sc.nextInt(); // Safety (2점)
        int p1 = sc.nextInt(); // Point after (1점)
        int c1 = sc.nextInt(); // Two-point conversion (2점)

        int score1 = (t1 * 6) + (f1 * 3) + (s1 * 2) + (p1 * 1) + (c1 * 2);

        // 2. 홈 팀(Home Team) 점수 계산
        int t2 = sc.nextInt();
        int f2 = sc.nextInt();
        int s2 = sc.nextInt();
        int p2 = sc.nextInt();
        int c2 = sc.nextInt();

        int score2 = (t2 * 6) + (f2 * 3) + (s2 * 2) + (p2 * 1) + (c2 * 2);

        // 3. 결과 출력
        System.out.println(score1 + " " + score2);
        
        sc.close();
    }
}