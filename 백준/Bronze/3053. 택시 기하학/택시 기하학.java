import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 반지름 R 입력 받기
        int R = scanner.nextInt();

        // 유클리드 기하학에서의 원의 넓이 계산
        double euclideanArea = Math.PI * R * R;

        // 택시 기하학에서의 원의 넓이 계산
        double taxicabArea = 2.0 * R * R;

        // 결과 출력
        System.out.printf("%.6f\n", euclideanArea);
        System.out.printf("%.6f\n", taxicabArea);

        // Scanner 닫기
        scanner.close();
    }
}
