import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sangDeok = scanner.nextInt();
        int jungDeok = scanner.nextInt();
        int haDeok = scanner.nextInt();

        int cola = scanner.nextInt();
        int cider = scanner.nextInt();

        int cheapestBurger = Math.min(sangDeok, Math.min(jungDeok, haDeok));
        int cheapestDrink = Math.min(cola, cider);

        int setPrice = cheapestBurger + cheapestDrink - 50;

        System.out.println(setPrice);

        scanner.close();
    }
}
