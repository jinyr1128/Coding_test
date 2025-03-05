import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 사람의 위치 입력
        int[] p = new int[5]; // 1-based index 사용
        for (int i = 1; i <= 4; i++) {
            p[i] = sc.nextInt();
        }

        // 사과 정보 입력
        int x = sc.nextInt();
        int y = sc.nextInt();
        int r = sc.nextInt();

        // 충돌한 사람 찾기
        int ans = 0;
        for (int i = 1; i <= 4; i++) {
            if (x == p[i]) {
                ans = i;
                break; // 첫 번째로 충돌한 사람을 찾으면 종료
            }
        }

        // 결과 출력
        System.out.println(ans);
        sc.close();
    }
}
