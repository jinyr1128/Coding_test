import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 4명의 스킬 레벨 입력
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        int D = sc.nextInt();

        // 가능한 팀 조합의 차이 계산
        int diff1 = Math.abs((A + D) - (B + C));
        int diff2 = Math.abs((A + C) - (B + D));
        int diff3 = Math.abs((A + B) - (C + D));

        // 최소 차이 출력
        System.out.println(Math.min(diff1, Math.min(diff2, diff3)));

        sc.close();
    }
}
