import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();  // 25개의 대문자 입력
        boolean[] alphabet = new boolean[26];  // A ~ Z 체크용

        // 입력된 문자 체크
        for (char c : input.toCharArray()) {
            alphabet[c - 'A'] = true;
        }

        // A부터 Z까지 하나씩 검사해서 빠진 것 찾기
        for (int i = 0; i < 26; i++) {
            if (!alphabet[i]) {
                System.out.println((char) ('A' + i));
                break;
            }
        }

        sc.close();
    }
}
