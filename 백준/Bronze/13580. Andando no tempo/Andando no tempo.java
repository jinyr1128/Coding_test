import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] credit = new int[3];
        credit[0] = sc.nextInt();
        credit[1] = sc.nextInt();
        credit[2] = sc.nextInt();

        boolean possible = false;

        // 1. 하나만 사용하는 경우
        for (int i = 0; i < 3; i++) {
            if (credit[i] == 0) {
                possible = true;
            }
        }

        // 2. 두 개 사용하는 경우
        for (int i = 0; i < 3; i++) {
            for (int j = i+1; j < 3; j++) {
                int a = credit[i];
                int b = credit[j];
                if (a + b == 0 || a - b == 0 || -a + b == 0 || -a - b == 0) {
                    possible = true;
                }
            }
        }

        // 3. 세 개 다 사용하는 경우
        int a = credit[0], b = credit[1], c = credit[2];
        int[] signs = {-1, 1};
        for (int sa : signs) {
            for (int sb : signs) {
                for (int scn : signs) {
                    if (sa * a + sb * b + scn * c == 0) {
                        possible = true;
                    }
                }
            }
        }

        System.out.println(possible ? "S" : "N");
    }
}
