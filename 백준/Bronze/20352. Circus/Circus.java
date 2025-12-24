import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 원의 넓이 입력 (범위가 크므로 double로 입력받음)
        double area = sc.nextDouble();

        // 2. 반지름(r) 계산: r = sqrt(Area / PI)
        double radius = Math.sqrt(area / Math.PI);

        // 3. 둘레(C) 계산: C = 2 * PI * r
        double circumference = 2 * Math.PI * radius;

        // 4. 결과 출력 (오차 허용 범위 내에서 출력됨)
        System.out.println(circumference);
        
        sc.close();
    }
}