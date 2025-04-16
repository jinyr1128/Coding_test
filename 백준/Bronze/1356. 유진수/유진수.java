import java.util.Scanner;

public class Main {
    // 문자열 숫자의 각 자릿수를 곱하는 함수
    private static int product(String s) {
        int result = 1;
        for (int i = 0; i < s.length(); i++) {
            result *= s.charAt(i) - '0';
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();

        boolean isEugene = false;

        for (int i = 1; i < n.length(); i++) {
            String left = n.substring(0, i);
            String right = n.substring(i);

            int leftProduct = product(left);
            int rightProduct = product(right);

            if (leftProduct == rightProduct) {
                isEugene = true;
                break;
            }
        }

        System.out.println(isEugene ? "YES" : "NO");
    }
}
