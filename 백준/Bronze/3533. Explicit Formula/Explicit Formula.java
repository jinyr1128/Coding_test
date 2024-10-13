import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] x = new int[10];
        
        // Read the 10 input values
        for (int i = 0; i < 10; i++) {
            x[i] = scanner.nextInt();
        }

        // Compute the value of the function f
        int fValue = 0;
        
        // Calculate all pairs
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                fValue ^= (x[i] | x[j]);
            }
        }

        // Calculate all triplets
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                for (int k = j + 1; k < 10; k++) {
                    fValue ^= (x[i] | x[j] | x[k]);
                }
            }
        }

        // Output the result
        System.out.println(fValue);

        scanner.close();
    }
}
