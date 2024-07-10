import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 세븐25의 삼각 김밥 가격 정보
        double sevenPricePerGram = scanner.nextDouble();
        double sevenWeight = scanner.nextDouble();

        // 1000그램의 세븐25 삼각 김밥 가격 계산
        double minPrice = (sevenPricePerGram / sevenWeight) * 1000;

        // 다른 편의점의 개수
        int n = scanner.nextInt();

        // 다른 편의점의 삼각 김밥 가격 정보
        for (int i = 0; i < n; i++) {
            double pricePerGram = scanner.nextDouble();
            double weight = scanner.nextDouble();
            double price = (pricePerGram / weight) * 1000;

            // 최저 가격 갱신
            if (price < minPrice) {
                minPrice = price;
            }
        }

        // 최저 가격 출력
        System.out.printf("%.2f", minPrice);

        scanner.close();
    }
}
