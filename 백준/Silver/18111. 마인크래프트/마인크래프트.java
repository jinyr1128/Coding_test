import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[][] ground = new int[N][M];
        int maxH = 0;  

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ground[i][j] = Integer.parseInt(st.nextToken());
                if (ground[i][j] > maxH) {
                    maxH = ground[i][j];  
                }
            }
        }

        int minTime = Integer.MAX_VALUE;
        int resultHeight = -1;

        for (int h = 0; h <= 256; h++) {
            int removeBlocks = 0;
            int addBlocks = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int diff = ground[i][j] - h;
                    if (diff > 0) {
                        removeBlocks += diff; 
                    } else if (diff < 0) {
                        addBlocks -= diff; 
                    }
                }
            }

            if (removeBlocks + B >= addBlocks) {
                int time = removeBlocks * 2 + addBlocks;
                if (time < minTime) {
                    minTime = time;
                    resultHeight = h;
                } else if (time == minTime && h > resultHeight) {
                    resultHeight = h;
                }
            }
        }

        System.out.println(minTime + " " + resultHeight);
    }
}
