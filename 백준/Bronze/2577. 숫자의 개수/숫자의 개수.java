import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        int product = A * B * C;
        int[] count = new int[10];  // 0부터 9까지 숫자가 몇 번 사용되었는지 카운트할 배열

        while (product > 0) {
            count[product % 10]++;  // 마지막 자리 숫자 카운트
            product /= 10;  // 한 자리 줄임
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(count[i]);
        }
    }
}
