import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the age of the youngest child
        int Y = sc.nextInt();
        // Read the age of the middle child
        int M = sc.nextInt();

        // Calculate the age of the oldest child
        int O = 2 * M - Y;

        // Print the result
        System.out.println(O);

        sc.close();
    }
}
