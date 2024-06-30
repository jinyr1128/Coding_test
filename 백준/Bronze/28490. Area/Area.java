import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 프레임의 수 입력 받기
        int n = scanner.nextInt();
        
        int maxArea = 0;

        // 각 프레임의 높이와 너비를 입력받고, 면적 계산 및 최대 면적 갱신
        for (int i = 0; i < n; i++) {
            int height = scanner.nextInt();
            int width = scanner.nextInt();
            int area = height * width;
            if (area > maxArea) {
                maxArea = area;
            }
        }

        // 최대 면적 출력
        System.out.println(maxArea);

        scanner.close();
    }
}
