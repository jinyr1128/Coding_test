import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int V = scanner.nextInt();

        int days = (V - B - 1) / (A - B) + 1; // V - B는 마지막 날에 올라가야 하는 전체 높이, -1은 마지막에 미끄러지지 않기 때문에 계산

        System.out.println(days);
    }
}
