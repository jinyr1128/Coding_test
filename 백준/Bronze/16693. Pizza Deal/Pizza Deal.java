import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A1 = sc.nextInt(); // 슬라이스 면적
        int P1 = sc.nextInt(); // 슬라이스 가격

        int R1 = sc.nextInt(); // 원형 피자 반지름
        int P2 = sc.nextInt(); // 원형 피자 가격

        // 슬라이스 피자 면적당 가치
        double sliceValue = (double) A1 / P1;

        // 원형 피자 면적당 가치 (π는 생략하고 R²로만 비교 가능)
        double wholeValue = (Math.PI * R1 * R1) / P2;

        if (wholeValue > sliceValue) {
            System.out.println("Whole pizza");
        } else {
            System.out.println("Slice of pizza");
        }
    }
}
