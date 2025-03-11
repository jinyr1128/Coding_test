import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력 받기
        String input = sc.nextLine();
        StringBuilder result = new StringBuilder();

        // 문자 하나씩 변환
        for (char c : input.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append(Character.toLowerCase(c)); // 대문자 → 소문자
            } else {
                result.append(Character.toUpperCase(c)); // 소문자 → 대문자
            }
        }

        // 변환된 문자열 출력
        System.out.println(result.toString());
        sc.close();
    }
}
