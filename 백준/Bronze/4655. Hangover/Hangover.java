import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            double c = scanner.nextDouble();
            if (c == 0.00) break;

            double overhang = 0.0;
            int cards = 0;

            // 카드 수를 1부터 시작해서 필요한 만큼 더해준다.
            while (overhang < c) {
                cards++;
                overhang += 1.0 / (cards + 1);  // 카드 추가
            }

            System.out.println(cards + " card(s)");
        }

        scanner.close();
    }
}
