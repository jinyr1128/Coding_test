import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sum = 0;
        for (int i = 0; i < 5; i++) {
            int num = sc.nextInt();
            sum += num * num;  // 각 숫자를 제곱하여 합계에 더함
        }

        int verificationNumber = sum % 10;  // 합계를 10으로 나눈 나머지를 검증수로 함
        System.out.println(verificationNumber);
    }
}
