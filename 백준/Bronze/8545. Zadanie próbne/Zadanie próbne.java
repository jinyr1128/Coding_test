import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 스캐너로 입력받기
        Scanner sc = new Scanner(System.in);

        // 단어 입력 (3글자)
        String word = sc.next();

        // 문자열 뒤집기
        String reversedWord = new StringBuilder(word).reverse().toString();

        // 결과 출력
        System.out.println(reversedWord);

        // 스캐너 닫기
        sc.close();
    }
}
