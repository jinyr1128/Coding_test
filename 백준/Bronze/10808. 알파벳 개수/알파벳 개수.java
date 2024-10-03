import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        
        // 알파벳 소문자의 개수를 세기 위한 배열 (a-z까지 26개의 배열)
        int[] alphabetCount = new int[26];
        
        // 각 문자에 대해 알파벳 카운트를 증가시킴
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            alphabetCount[ch - 'a']++;  // 'a'의 아스키 코드 값을 빼서 a=0, b=1, ... z=25로 맞춤
        }
        
        // 결과 출력
        for (int count : alphabetCount) {
            System.out.print(count + " ");
        }
    }
}
