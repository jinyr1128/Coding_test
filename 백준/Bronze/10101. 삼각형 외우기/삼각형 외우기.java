import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 세 각 입력받기
        int angle1 = sc.nextInt();
        int angle2 = sc.nextInt();
        int angle3 = sc.nextInt();

        // 세 각의 합이 180이 아닌 경우
        if (angle1 + angle2 + angle3 != 180) {
            System.out.println("Error");
        }
        // 세 각이 모두 60인 경우
        else if (angle1 == 60 && angle2 == 60 && angle3 == 60) {
            System.out.println("Equilateral");
        }
        // 두 각이 같은 경우
        else if (angle1 == angle2 || angle2 == angle3 || angle1 == angle3) {
            System.out.println("Isosceles");
        }
        // 모든 각이 다른 경우
        else {
            System.out.println("Scalene");
        }

        sc.close();
    }
}
