import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            
            if (a == 0 && b == 0 && c == 0) {
                break;
            }
            
            int[] sides = {a, b, c};
            java.util.Arrays.sort(sides);
            
            a = sides[0];
            b = sides[1];
            c = sides[2];
            
            if (c * c == a * a + b * b) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
        
        sc.close();
    }
}
