import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();

            // (0, 0)인 경우: AXIS를 출력하고 반복 종료
            if (x == 0 && y == 0) {
                System.out.println("AXIS");
                break;
            }
            
            // x나 y가 0인 경우(축 위에 있는 경우)
            if (x == 0 || y == 0) {
                System.out.println("AXIS");
            } 
            // 1사분면
            else if (x > 0 && y > 0) {
                System.out.println("Q1");
            } 
            // 2사분면
            else if (x < 0 && y > 0) {
                System.out.println("Q2");
            }
            // 3사분면
            else if (x < 0 && y < 0) {
                System.out.println("Q3");
            } 
            // 4사분면
            else if (x > 0 && y < 0) {
                System.out.println("Q4");
            }
        }

        sc.close();
    }
}
