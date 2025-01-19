import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();  // 한 줄 입력 받기

            // 입력이 "***"이면 프로그램 종료
            if (input.equals("***")) {
                break;
            }

            // 문자열을 뒤집어서 출력
            String reversed = new StringBuilder(input).reverse().toString();
            System.out.println(reversed);
        }

        sc.close();
    }
}
