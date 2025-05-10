import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();      // 마나 수정 개수
        int K = sc.nextInt();      // 마나 응집 수정의 순위 (1~N 중 K번째 강함)
        double P = sc.nextDouble(); // 망치 최대 세기 (실수)
        int W = sc.nextInt();      // 폭발을 유발하는 힘의 차이

        // Math.ceil은 실수 올림 처리
        int cnt = (int) Math.ceil(P / W); // 강도 P를 W씩 쪼개면 몇 번 시도해야 되는지
        System.out.println(cnt);
    }
}
