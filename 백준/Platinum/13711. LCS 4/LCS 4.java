import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        int[] A = new int[N];
        int[] B = new int[N];
        int[] position = new int[N + 1]; // 수열 B에서 각 숫자의 위치를 저장할 배열
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
            position[B[i]] = i;
        }
        
        ArrayList<Integer> lis = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int index = position[A[i]]; // A[i]가 B에서 위치하는 인덱스
            if (lis.isEmpty() || lis.get(lis.size() - 1) < index) {
                lis.add(index);
            } else {
                int pos = Collections.binarySearch(lis, index);
                if (pos < 0) {
                    pos = -pos - 1; // 위치 삽입 변환
                }
                lis.set(pos, index);
            }
        }
        
        System.out.println(lis.size());
    }
}
