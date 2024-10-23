import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력된 단어 읽어오기
        String word = sc.nextLine();
        
        // 모음의 개수를 저장할 변수
        int vowelCount = 0;
        
        // 단어의 각 문자를 하나씩 확인하면서 모음인지 확인
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            // 모음이 있으면 카운트를 증가
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowelCount++;
            }
        }
        
        // 모음의 개수 출력
        System.out.println(vowelCount);
        
        sc.close();
    }
}
