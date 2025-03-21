import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int antenna = sc.nextInt();
        int eyes = sc.nextInt();

        // TroyMartian
        if (antenna >= 3 && eyes <= 4) {
            System.out.println("TroyMartian");
        }

        // VladSaturnian
        if (antenna <= 6 && eyes >= 2) {
            System.out.println("VladSaturnian");
        }

        // GraemeMercurian
        if (antenna <= 2 && eyes <= 3) {
            System.out.println("GraemeMercurian");
        }

        sc.close();
    }
}
