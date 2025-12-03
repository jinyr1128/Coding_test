import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 비즈니스 클래스: n1행, 행당 k1석
        int n1 = sc.nextInt();
        int k1 = sc.nextInt();

        // 이코노미 클래스: n2행, 행당 k2석
        int n2 = sc.nextInt();
        int k2 = sc.nextInt();

        // 총 좌석 수 계산
        int totalSeats = (n1 * k1) + (n2 * k2);

        // 결과 출력
        System.out.println(totalSeats);
        
        sc.close();
    }
}