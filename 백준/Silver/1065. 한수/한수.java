import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(countHansu(N));
    }

    // 한수를 세는 함수
    public static int countHansu(int N) {
        if (N < 100) {
            return N; // 1부터 99까지는 모두 한수
        }

        int count = 99; // 1~99까지는 모두 한수이므로 초기값 99
        for (int i = 100; i <= N; i++) {
            if (isHansu(i)) {
                count++;
            }
        }
        return count;
    }

    // 한수인지 판별하는 함수
    public static boolean isHansu(int num) {
        String str = String.valueOf(num);
        int diff = str.charAt(1) - str.charAt(0);
        for (int i = 1; i < str.length() - 1; i++) {
            if (str.charAt(i + 1) - str.charAt(i) != diff) {
                return false;
            }
        }
        return true;
    }
}