import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int maxL = 0; 
        int minR = 200000; // 문제에서 최대 범위 제한
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            maxL = Math.max(maxL, a); // 구간 시작 중 최댓값
            minR = Math.min(minR, b); // 구간 끝 중 최솟값
        }
        
        if (maxL > minR) {
            System.out.println("bad news");
        } else {
            int count = minR - maxL + 1;
            System.out.println(count + " " + maxL);
        }
    }
}
