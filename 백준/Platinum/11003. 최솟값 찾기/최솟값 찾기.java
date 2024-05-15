import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // BufferedReader와 BufferedWriter를 사용하여 입력과 출력을 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        
        // 덱을 사용하여 슬라이딩 윈도우의 최솟값을 효율적으로 관리
        Deque<int[]> deque = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            
            // 현재 숫자보다 큰 숫자들은 덱에서 제거
            while (!deque.isEmpty() && deque.peekLast()[0] > num) {
                deque.pollLast();
            }
            
            // 현재 숫자를 덱의 끝에 추가
            deque.offer(new int[]{num, i});
            
            // 슬라이딩 윈도우 범위를 벗어난 인덱스를 덱에서 제거
            if (deque.peek()[1] < i - (l - 1)) {
                deque.poll();
            }
            
            // 덱의 첫 번째 원소는 현재 윈도우의 최솟값
            bw.write(deque.peek()[0] + " ");
        }
        
        bw.flush();
        bw.close();
    }
}
