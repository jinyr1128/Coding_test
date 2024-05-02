import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();  // 연도 입력 받기

        // 윤년 판별 로직
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            System.out.println(1);  // 윤년일 경우 1 출력
        } else {
            System.out.println(0);  // 윤년이 아닐 경우 0 출력
        }
    }
}
