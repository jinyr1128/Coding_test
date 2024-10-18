import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String octal = sc.nextLine();  // 8진수 입력
        sc.close();

        // 8진수를 10진수로 변환
        String binary = new StringBuilder(new java.math.BigInteger(octal, 8).toString(2)).toString();

        System.out.println(binary);
    }
}