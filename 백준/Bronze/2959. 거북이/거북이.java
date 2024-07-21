import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] steps = new int[4];
        for (int i = 0; i < 4; i++) {
            steps[i] = scanner.nextInt();
        }

        int maxArea = 0;
        maxArea = Math.max(maxArea, calculateArea(steps[0], steps[1], steps[2], steps[3]));
        maxArea = Math.max(maxArea, calculateArea(steps[0], steps[1], steps[3], steps[2]));
        maxArea = Math.max(maxArea, calculateArea(steps[0], steps[2], steps[1], steps[3]));
        maxArea = Math.max(maxArea, calculateArea(steps[0], steps[2], steps[3], steps[1]));
        maxArea = Math.max(maxArea, calculateArea(steps[0], steps[3], steps[1], steps[2]));
        maxArea = Math.max(maxArea, calculateArea(steps[0], steps[3], steps[2], steps[1]));
        
        maxArea = Math.max(maxArea, calculateArea(steps[1], steps[0], steps[2], steps[3]));
        maxArea = Math.max(maxArea, calculateArea(steps[1], steps[0], steps[3], steps[2]));
        maxArea = Math.max(maxArea, calculateArea(steps[1], steps[2], steps[0], steps[3]));
        maxArea = Math.max(maxArea, calculateArea(steps[1], steps[2], steps[3], steps[0]));
        maxArea = Math.max(maxArea, calculateArea(steps[1], steps[3], steps[0], steps[2]));
        maxArea = Math.max(maxArea, calculateArea(steps[1], steps[3], steps[2], steps[0]));
        
        maxArea = Math.max(maxArea, calculateArea(steps[2], steps[0], steps[1], steps[3]));
        maxArea = Math.max(maxArea, calculateArea(steps[2], steps[0], steps[3], steps[1]));
        maxArea = Math.max(maxArea, calculateArea(steps[2], steps[1], steps[0], steps[3]));
        maxArea = Math.max(maxArea, calculateArea(steps[2], steps[1], steps[3], steps[0]));
        maxArea = Math.max(maxArea, calculateArea(steps[2], steps[3], steps[0], steps[1]));
        maxArea = Math.max(maxArea, calculateArea(steps[2], steps[3], steps[1], steps[0]));
        
        maxArea = Math.max(maxArea, calculateArea(steps[3], steps[0], steps[1], steps[2]));
        maxArea = Math.max(maxArea, calculateArea(steps[3], steps[0], steps[2], steps[1]));
        maxArea = Math.max(maxArea, calculateArea(steps[3], steps[1], steps[0], steps[2]));
        maxArea = Math.max(maxArea, calculateArea(steps[3], steps[1], steps[2], steps[0]));
        maxArea = Math.max(maxArea, calculateArea(steps[3], steps[2], steps[0], steps[1]));
        maxArea = Math.max(maxArea, calculateArea(steps[3], steps[2], steps[1], steps[0]));

        System.out.println(maxArea);
    }

    private static int calculateArea(int a, int b, int c, int d) {
        return Math.min(a, c) * Math.min(b, d);
    }
}
