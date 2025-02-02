import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 팀명 후보 개수 입력
        int N = sc.nextInt();
        sc.nextLine(); // 개행문자 처리

        // 조건을 만족하는 팀명 찾기
        for (int i = 0; i < N; i++) {
            String teamName = sc.nextLine();

            // 조건 검사
            if (isValidTeamName(teamName)) {
                System.out.println(teamName);
                break; // 유일한 답이 보장되므로 찾으면 바로 종료
            }
        }

        sc.close();
    }

    // 팀명이 세 가지 조건을 만족하는지 검사하는 함수
    private static boolean isValidTeamName(String name) {
        int upperCount = 0, lowerCount = 0, digitCount = 0;
        boolean hasNonDigit = false;

        // 문자열 길이 조건 검사
        if (name.length() > 10) {
            return false;
        }

        // 문자열 문자 개수 검사
        for (char c : name.toCharArray()) {
            if (Character.isUpperCase(c)) {
                upperCount++;
            } else if (Character.isLowerCase(c)) {
                lowerCount++;
            } else if (Character.isDigit(c)) {
                digitCount++;
            } else if (c == '-') {
                // 하이픈은 카운트에 포함하지 않음
            } else {
                hasNonDigit = true;
            }
        }

        // 대문자가 소문자보다 많지 않아야 함
        if (upperCount > lowerCount) {
            return false;
        }

        // 숫자로만 구성되면 안 됨 (적어도 하나의 알파벳이나 하이픈 필요)
        if (digitCount == name.length()) {
            return false;
        }

        return true;
    }
}
