import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();  // 테스트 케이스의 개수
        scanner.nextLine();  // 버퍼 비우기

        for (int i = 0; i < T; i++) {
            String[] input = scanner.nextLine().split(" ");
            double value = Double.parseDouble(input[0]);
            String unit = input[1];

            switch (unit) {
                case "kg":
                    double toPounds = value * 2.2046;
                    System.out.printf("%.4f lb\n", toPounds);
                    break;
                case "lb":
                    double toKilograms = value * 0.4536;
                    System.out.printf("%.4f kg\n", toKilograms);
                    break;
                case "l":
                    double toGallons = value * 0.2642;
                    System.out.printf("%.4f g\n", toGallons);
                    break;
                case "g":
                    double toLiters = value * 3.7854;
                    System.out.printf("%.4f l\n", toLiters);
                    break;
                default:
                    System.out.println("Unknown unit");
                    break;
            }
        }

        scanner.close();
    }
}