import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받기
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = scanner.nextInt();

        // 수로 계산한 결과
        int numericResult = A + B - C;

        // 문자열로 계산한 결과
        String strA = String.valueOf(A);
        String strB = String.valueOf(B);
        String strC = String.valueOf(C);
        int stringResult = Integer.parseInt(strA + strB) - Integer.parseInt(strC);

        // 결과 출력
        System.out.println(numericResult);
        System.out.println(stringResult);

        scanner.close();
    }
}
