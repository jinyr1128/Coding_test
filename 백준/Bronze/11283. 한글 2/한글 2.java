import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 한글 문자 입력받기
        char ch = sc.next().charAt(0);

        // "가"의 유니코드 값: 44032 (U+AC00)
        int order = (ch - '가') + 1;

        // 결과 출력
        System.out.println(order);

        sc.close();
    }
}
