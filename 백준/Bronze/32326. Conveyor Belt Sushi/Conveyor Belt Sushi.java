import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();  // Red plates
        int g = sc.nextInt();  // Green plates
        int b = sc.nextInt();  // Blue plates

        int cost = r * 3 + g * 4 + b * 5;
        System.out.println(cost);
    }
}
