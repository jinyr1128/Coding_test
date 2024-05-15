import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // BufferedReader와 BufferedWriter를 사용하여 입력과 출력을 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            // 마지막 자리 숫자를 이용하여 주기를 계산
            int[] lastDigit = new int[10];
            int index = 0;
            int temp = a % 10;
            boolean[] seen = new boolean[10];
            
            // 주기를 찾기 위한 루프
            while (!seen[temp]) {
                seen[temp] = true;
                lastDigit[index++] = temp;
                temp = (temp * a) % 10;
            }
            
            // 실제로 필요한 주기 길이
            int cycleLength = index;
            
            // b가 주기 길이보다 클 경우를 처리
            int position = (b - 1) % cycleLength;
            int result = lastDigit[position];
            
            // 0은 10번 컴퓨터로 간주
            if (result == 0) result = 10;
            
            bw.write(result + "\n");
        }
        
        bw.flush();
        bw.close();
    }
}
