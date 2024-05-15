import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // BufferedReader와 BufferedWriter를 사용하여 입력과 출력을 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        // 인덱스와 값을 함께 저장할 Pair 리스트
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            pairs.add(new Pair(A[i], i));
        }
        
        // 값 기준으로 정렬, 값이 같으면 인덱스 기준으로 정렬
        Collections.sort(pairs, (p1, p2) -> {
            if (p1.value == p2.value) {
                return p1.index - p2.index;
            } else {
                return p1.value - p2.value;
            }
        });
        
        // 결과 P 배열
        int[] P = new int[N];
        for (int i = 0; i < N; i++) {
            P[pairs.get(i).index] = i;
        }
        
        // 결과 출력
        for (int i = 0; i < N; i++) {
            bw.write(P[i] + " ");
        }
        bw.flush();
        bw.close();
    }
    
    // Pair 클래스 정의
    static class Pair {
        int value;
        int index;
        
        Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}

