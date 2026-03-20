import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String line = br.readLine();
        if (line == null) return;
        
        // 1. 날짜의 수 N 입력
        int n = Integer.parseInt(line.trim());
        
        // 2. 우주 쓰레기 데이터 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int minJunk = Integer.MAX_VALUE; // 최솟값을 찾기 위해 가장 큰 값으로 초기화
        int bestDay = -1; // 발사하기 가장 좋은 날짜(인덱스)
        
        for (int i = 0; i < n; i++) {
            int currentJunk = Integer.parseInt(st.nextToken());
            
            // 현재 쓰레기 양이 지금까지 찾은 최솟값보다 엄격히 작을 때만 갱신
            // (같을 때는 갱신하지 않아야 가장 빠른 날짜가 유지됨)
            if (currentJunk < minJunk) {
                minJunk = currentJunk;
                bestDay = i; // i는 0부터 시작하므로 문제의 요구사항과 일치
            }
        }
        
        // 3. 결과 출력
        System.out.println(bestDay);
    }
}