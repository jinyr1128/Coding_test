import java.util.Scanner;

public class Main {
    static int findZOrder(int N, int r, int c) {
        int size = 1 << N; 
        int count = 0;

        while (size > 1) {
            size /= 2;
            int half = size * size;

            if (r < size && c < size) { 
            } else if (r < size && c >= size) { 
                count += half;
                c -= size;
            } else if (r >= size && c < size) { 
                count += 2 * half;
                r -= size;
            } else { 
                count += 3 * half;
                r -= size;
                c -= size;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.close();

        System.out.println(findZOrder(N, r, c));
    }
}
