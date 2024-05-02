import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int M = sc.nextInt(); 

        if (M < 45) {
            M = 60 + M - 45;  
            H = (H == 0) ? 23 : H - 1;  
        } else {
            M -= 45; 
        }

        System.out.println(H + " " + M);
    }
}
