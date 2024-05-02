import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt(); 
        
        int result3 = A * (B % 10);       
        int result4 = A * (B / 10 % 10);   
        int result5 = A * (B / 100);       
        int result6 = A * B;               

        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
        System.out.println(result6);
        
        sc.close(); 
    }
}
