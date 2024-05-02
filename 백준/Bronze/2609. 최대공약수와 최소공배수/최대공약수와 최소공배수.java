import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int a = sc.nextInt();
        int b = sc.nextInt();
        
        int gcd = gcd(a, b);
        int lcm = (a * b) / gcd; 
        
        System.out.println(gcd); 
        System.out.println(lcm); 
    }
    
    private static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
