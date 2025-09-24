import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 입력을 받기 위한 Scanner 객체 생성
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();

        int lastVowelIndex = -1;

        // 문자열의 뒤에서부터 시작하여 마지막 모음('a', 'e', 'i', 'o', 'u')의 위치를 찾습니다.
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                lastVowelIndex = i;
                // 마지막 모음을 찾았으므로 반복을 중단합니다.
                break; 
            }
        }

        // 원본 문자열의 시작부터 마지막 모음까지 잘라냅니다.
        // substring(beginIndex, endIndex)에서 endIndex는 포함되지 않으므로 +1을 해줍니다.
        String prefix = s.substring(0, lastVowelIndex + 1);

        // 잘라낸 부분 문자열(prefix)에 "ntry"를 붙여 결과를 만듭니다.
        String result = prefix + "ntry";

        // 최종 결과를 출력합니다.
        System.out.println(result);
    }
}