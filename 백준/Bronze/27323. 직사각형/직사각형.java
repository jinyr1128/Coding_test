import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 스캐너로 입력 받기
        Scanner sc = new Scanner(System.in);

        // A와 B 입력
        int A = sc.nextInt();
        int B = sc.nextInt();

        // 직사각형의 넓이 계산
        int area = A * B;

        // 넓이 출력 (단위는 생략)
        System.out.println(area);

        // 스캐너 닫기
        sc.close();
    }
}
