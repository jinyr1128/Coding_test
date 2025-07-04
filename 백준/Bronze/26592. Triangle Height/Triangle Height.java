import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine()); // 테스트 케이스 수

        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().split(" ");
            double area = Double.parseDouble(parts[0]);
            double base = Double.parseDouble(parts[1]);

            double height = (2 * area) / base;

            System.out.printf("The height of the triangle is %.2f units\n", height);
        }

        sc.close();
    }
}
