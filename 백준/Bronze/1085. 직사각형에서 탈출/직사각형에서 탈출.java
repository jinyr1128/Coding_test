import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int x = sc.nextInt();
        int y = sc.nextInt();
        int w = sc.nextInt();
        int h = sc.nextInt();
        
        // 거리 계산
        int distanceToLeft = x;
        int distanceToRight = w - x;
        int distanceToBottom = y;
        int distanceToTop = h - y;
        
        // 최솟값 계산
        int minDistance = Math.min(Math.min(distanceToLeft, distanceToRight), 
                                   Math.min(distanceToBottom, distanceToTop));
        
        System.out.println(minDistance);
        
        sc.close();
    }
}
