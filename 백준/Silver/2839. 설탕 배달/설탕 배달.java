import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int bags = 0;
        
        while (N > 0) {
            if (N % 5 == 0) {  
                bags += N / 5;
                System.out.println(bags);
                return;
            } else {
                N -= 3; 
                bags++;
            }
        }
        
        if (N < 0) {  
            System.out.println(-1);
        } else {
            System.out.println(bags);
        }
    }
}
