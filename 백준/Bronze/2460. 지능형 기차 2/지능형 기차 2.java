import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int currentPassengers = 0;
        int maxPassengers = 0;
        
        for (int i = 0; i < 10; i++) {
            int out = scanner.nextInt();
            int in = scanner.nextInt();
            
            currentPassengers -= out;
            currentPassengers += in;
            
            if (currentPassengers > maxPassengers) {
                maxPassengers = currentPassengers;
            }
        }
        
        System.out.println(maxPassengers);
        scanner.close();
    }
}
