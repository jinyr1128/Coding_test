import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String target = sc.nextLine();              // 재수강 과목 코드
        String targetPrefix = target.substring(0, 5);

        int N = Integer.parseInt(sc.nextLine());    // 신청 가능한 과목 수
        int count = 0;

        for (int i = 0; i < N; i++) {
            String code = sc.nextLine();
            if (code.substring(0, 5).equals(targetPrefix)) {
                count++;
            }
        }

        System.out.println(count);
    }
}
