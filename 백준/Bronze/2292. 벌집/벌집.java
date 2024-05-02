import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int currentMax = 1;  
        int layer = 1;      

        while (currentMax < N) {
            currentMax += 6 * layer;  
            layer++;                  
        }

        System.out.println(layer);
    }
}
