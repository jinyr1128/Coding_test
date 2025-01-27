import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 스캐너로 입력받기
        Scanner sc = new Scanner(System.in);

        // 문자열의 총 개수 입력
        int N = sc.nextInt();
        sc.nextLine(); // 줄바꿈 처리

        // N개의 문자열 확인
        for (int i = 0; i < N; i++) {
            String password = sc.nextLine();

            // 비밀번호 길이 확인
            if (password.length() >= 6 && password.length() <= 9) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }

        // 스캐너 닫기
        sc.close();
    }
}
