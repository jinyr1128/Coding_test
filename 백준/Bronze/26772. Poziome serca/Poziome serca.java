import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] heart = {
            " @@@   @@@ ",
            "@   @ @   @",
            "@    @    @",
            "@         @",
            " @       @ ",
            "  @     @  ",
            "   @   @   ",
            "    @ @    ",
            "     @     "
        };

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for (int i = 0; i < 9; i++) { // 9줄
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < N; j++) {
                sb.append(heart[i]);
                if (j != N - 1) {
                    sb.append(" "); // 하트 사이 공백
                }
            }

            System.out.println(sb);
        }
    }
}
