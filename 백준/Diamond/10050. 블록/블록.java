import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        solve(N, 1);
    }

    public static void solve(int n, int i) {
        if (n == 3) {
            System.out.println((i + 1) + " to " + (i - 2));
            System.out.println((i + 4) + " to " + (i + 1));
            System.out.println((i + 2) + " to " + (i - 4));
            return;
        }
        if (n == 4) {
            System.out.println((i + 5) + " to " + (i - 2));
            System.out.println((i + 2) + " to " + (i + 5));
            System.out.println((i - 1) + " to " + (i + 2));
            System.out.println((i + 6) + " to " + (i - 1));
            return;
        }
        if (n == 5) {
            System.out.println((i + 7) + " to " + (i - 2));
            System.out.println((i + 2) + " to " + (i + 7));
            System.out.println((i + 5) + " to " + (i + 2));
            System.out.println((i - 1) + " to " + (i + 5));
            System.out.println((i + 8) + " to " + (i - 1));
            return;
        }
        if (n == 6) {
            System.out.println((i + 9) + " to " + (i - 2));
            System.out.println((i + 6) + " to " + (i + 9));
            System.out.println((i + 1) + " to " + (i + 6));
            System.out.println((i + 5) + " to " + (i + 1));
            System.out.println((i - 1) + " to " + (i + 5));
            System.out.println((i + 10) + " to " + (i - 1));
            return;
        }
        if (n == 7) {
            System.out.println((i + 11) + " to " + (i - 2));
            System.out.println((i + 4) + " to " + (i + 11));
            System.out.println((i + 7) + " to " + (i + 4));
            System.out.println((i + 2) + " to " + (i + 7));
            System.out.println((i + 8) + " to " + (i + 2));
            System.out.println((i - 1) + " to " + (i + 8));
            System.out.println((i + 12) + " to " + (i - 1));
            return;
        }
        // General recursive case
        System.out.println((i + n * 2 - 3) + " to " + (i - 2));
        System.out.println((i + 2) + " to " + (i + n * 2 - 3));
        solve(n - 4, i + 4); // Recursive call to solve the inner section
        System.out.println((i - 1) + " to " + (i + n * 2 - 6));
        System.out.println((i + (n - 1) * 2) + " to " + (i - 1));
    }
}
