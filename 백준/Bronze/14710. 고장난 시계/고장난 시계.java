import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int theta1 = sc.nextInt();
        int theta2 = sc.nextInt();
        
        if ((12 * theta1) % 360 == theta2) {
            System.out.println("O");
        } else {
            System.out.println("X");
        }
        
        sc.close();
    }
}