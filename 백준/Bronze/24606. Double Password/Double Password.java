import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 두 개의 4자리 비밀번호 입력 받기
        String password1 = scanner.nextLine();
        String password2 = scanner.nextLine();

        // 가능한 시퀀스 개수 계산
        int result = countValidSequences(password1, password2);

        // 결과 출력
        System.out.println(result);
        scanner.close();
    }

    public static int countValidSequences(String password1, String password2) {
        int count = 0;

        // 각 자릿수에 대해 0부터 9까지 모든 조합 탐색
        for (char d1 = '0'; d1 <= '9'; d1++) {
            for (char d2 = '0'; d2 <= '9'; d2++) {
                for (char d3 = '0'; d3 <= '9'; d3++) {
                    for (char d4 = '0'; d4 <= '9'; d4++) {
                        // 현재 조합을 문자열로 생성
                        String sequence = "" + d1 + d2 + d3 + d4;

                        // 각 자릿수 조건 확인
                        if (isValid(sequence, password1, password2)) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static boolean isValid(String sequence, String password1, String password2) {
        for (int i = 0; i < 4; i++) {
            char c = sequence.charAt(i);
            if (c != password1.charAt(i) && c != password2.charAt(i)) {
                return false; // 조건에 맞지 않으면 false
            }
        }
        return true; // 모든 자릿수가 조건을 만족하면 true
    }
}
