import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        while (T-- > 0) {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int r1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            int r2 = scanner.nextInt();

            System.out.println(findLocation(x1, y1, r1, x2, y2, r2));
        }
    }

    private static int findLocation(int x1, int y1, int r1, int x2, int y2, int r2) {
        int distancePow = (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1);
        if(x1 == x2 && y1 == y2 && r1 == r2) {
            return -1;
        }
        else if(distancePow > (r1+r2)*(r1+r2)) {
            return 0;
        }
        else if(distancePow < (r1-r2)*(r1-r2)) {
            return 0;
        }
        else if(distancePow == (r1-r2)*(r1-r2)) {
            return 1;
        }
        else if(distancePow == (r1+r2)*(r1+r2)) {
            return 1;
        }
        else {
            return 2;
        }
    }
}
