import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 입력을 받기 위한 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);
        String caesarWord = sc.nextLine();
        sc.close();

        // 결과를 저장할 StringBuilder 객체 생성
        StringBuilder originalWord = new StringBuilder();

        // 입력받은 단어의 각 문자를 순회
        for (int i = 0; i < caesarWord.length(); i++) {
            char ch = caesarWord.charAt(i);
            char originalChar;

            // 'D' ~ 'Z'인 경우, 3을 빼서 복호화
            if (ch >= 'D') {
                originalChar = (char) (ch - 3);
            } 
            // 'A', 'B', 'C'인 경우, 순환하여 복호화 (23을 더함)
            else {
                originalChar = (char) (ch + 23);
            }
            
            // 복호화된 문자를 결과에 추가
            originalWord.append(originalChar);
        }

        // 최종 결과 출력
        System.out.println(originalWord.toString());
    }
}