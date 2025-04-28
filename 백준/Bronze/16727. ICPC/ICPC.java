import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int p1 = sc.nextInt(); // 퍼시폴리스 홈 경기 득점
        int s1 = sc.nextInt(); // 에스테그랄 어웨이 경기 득점
        int s2 = sc.nextInt(); // 에스테그랄 홈 경기 득점
        int p2 = sc.nextInt(); // 퍼시폴리스 어웨이 경기 득점

        int persepolisTotal = p1 + p2;
        int esteghlalTotal = s1 + s2;

        if (persepolisTotal > esteghlalTotal) {
            System.out.println("Persepolis");
        } else if (persepolisTotal < esteghlalTotal) {
            System.out.println("Esteghlal");
        } else {
            // 합산 점수 같으면 어웨이 골 비교
            if (p2 > s1) {
                System.out.println("Persepolis");
            } else if (p2 < s1) {
                System.out.println("Esteghlal");
            } else {
                System.out.println("Penalty");
            }
        }
    }
}
