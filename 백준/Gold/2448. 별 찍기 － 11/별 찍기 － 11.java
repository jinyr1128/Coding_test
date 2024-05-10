import java.io.*;

public class Main {
    static char[][] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        stars = new char[N][2 * N - 1];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                stars[i][j] = ' ';
            }
        }
        
        drawStar(0, N - 1, N);
        
        StringBuilder sb = new StringBuilder();
        for (char[] line : stars) {
            sb.append(line).append('\n');
        }
        System.out.print(sb.toString());
    }
    
    static void drawStar(int row, int col, int n) {
        if (n == 3) {
            stars[row][col] = '*';
            stars[row + 1][col - 1] = stars[row + 1][col + 1] = '*';
            for (int i = 0; i < 5; i++) {
                stars[row + 2][col - 2 + i] = '*';
            }
            return;
        }
        
        int nextSize = n / 2;
        drawStar(row, col, nextSize);
        drawStar(row + nextSize, col - nextSize, nextSize);
        drawStar(row + nextSize, col + nextSize, nextSize);
    }
}
