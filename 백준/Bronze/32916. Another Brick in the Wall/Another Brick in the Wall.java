import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (sc.hasNextInt()) {
            int l = sc.nextInt();
            int h = sc.nextInt();

            if (l % 2 != 0) {
                // 길이가 홀수인 경우:
                // 각 층마다 최소 1개의 1x3 벽돌이 필요함.
                // (3, 2, 2...)와 (2, 2..., 3)을 번갈아 쌓으면 됨.
                System.out.println(h);
            } else {
                // 길이가 짝수인 경우:
                // 1x3 벽돌 0개인 층(모두 2)과 2개인 층(양끝 3)을 번갈아 쌓아야 함.
                // 0개인 층부터 시작해야 최소가 됨.
                // 비용 2인 층의 개수 = h / 2
                System.out.println((h / 2) * 2);
            }
        }
        
        sc.close();
    }
}