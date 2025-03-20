import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 4개의 수 입력
        int a1 = sc.nextInt();
        int a2 = sc.nextInt();
        int a3 = sc.nextInt();
        int a4 = sc.nextInt();

        // 조건 판별
        if (a1 < a2 && a2 < a3 && a3 < a4) {
            System.out.println("Fish Rising");
        } else if (a1 > a2 && a2 > a3 && a3 > a4) {
            System.out.println("Fish Diving");
        } else if (a1 == a2 && a2 == a3 && a3 == a4) {
            System.out.println("Fish At Constant Depth");
        } else {
            System.out.println("No Fish");
        }

        sc.close();
    }
}
