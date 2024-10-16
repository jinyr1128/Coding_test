import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt(); // 행
        int M = sc.nextInt(); // 열
        sc.nextLine(); // 개행 문자 처리
        
        char[][] rectangle = new char[N][M];
        
        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < M; j++) {
                rectangle[i][j] = line.charAt(j);
            }
        }

        int maxSize = 1; // 최소 정사각형 크기 1x1
        
        // 각 칸을 시작점으로 하여 정사각형 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int size = 1; i + size < N && j + size < M; size++) {
                    if (rectangle[i][j] == rectangle[i][j + size] &&
                        rectangle[i][j] == rectangle[i + size][j] &&
                        rectangle[i][j] == rectangle[i + size][j + size]) {
                        maxSize = Math.max(maxSize, (size + 1) * (size + 1));
                    }
                }
            }
        }
        
        System.out.println(maxSize);
        sc.close();
    }
}
