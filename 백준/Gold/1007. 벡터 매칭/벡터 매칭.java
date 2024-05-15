import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] points;
    static boolean[] visited;
    static double minVectorSum;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            points = new int[N][2];
            visited = new boolean[N];
            
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                points[i][0] = Integer.parseInt(st.nextToken());
                points[i][1] = Integer.parseInt(st.nextToken());
            }
            
            minVectorSum = Double.MAX_VALUE;
            findMinVectorSum(0, 0);
            System.out.printf("%.12f%n", minVectorSum);
        }
    }
    
    public static void findMinVectorSum(int idx, int count) {
        if (count == N / 2) {
            double vectorSumX = 0;
            double vectorSumY = 0;
            
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    vectorSumX += points[i][0];
                    vectorSumY += points[i][1];
                } else {
                    vectorSumX -= points[i][0];
                    vectorSumY -= points[i][1];
                }
            }
            
            double vectorSum = Math.sqrt(vectorSumX * vectorSumX + vectorSumY * vectorSumY);
            minVectorSum = Math.min(minVectorSum, vectorSum);
            return;
        }
        
        if (idx == N) {
            return;
        }
        
        visited[idx] = true;
        findMinVectorSum(idx + 1, count + 1);
        visited[idx] = false;
        findMinVectorSum(idx + 1, count);
    }
}
