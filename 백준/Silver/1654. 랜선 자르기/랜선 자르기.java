import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();
        int[] lengths = new int[K];
        
        long max = 0;
        for (int i = 0; i < K; i++) {
            lengths[i] = sc.nextInt();
            if (lengths[i] > max) {
                max = lengths[i]; 
            }
        }

        long low = 1;
        long high = max;
        long answer = 0;

        while (low <= high) {
            long mid = (low + high) / 2;
            long count = 0;

            for (int i = 0; i < K; i++) {
                count += lengths[i] / mid;
            }

            if (count >= N) {
                answer = mid;
                low = mid + 1;
            } else { 
                high = mid - 1;
            }
        }

        System.out.println(answer); 
        sc.close();
    }
}
