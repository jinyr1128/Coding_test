import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();  // 원하는 막대의 길이
        int count = 0;  // 붙인 막대의 개수를 저장할 변수
        int stick = 64;  // 처음 가지고 있는 막대의 길이

        // 주어진 길이 Xcm를 만들 때까지 반복
        while (X > 0) {
            if (stick > X) {
                stick /= 2;  // 막대를 절반으로 자름
            } else {
                X -= stick;  // 막대를 붙임
                count++;  // 막대 개수를 증가
            }
        }

        // 결과 출력
        System.out.println(count);

        sc.close();
    }
}
