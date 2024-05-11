import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] sequence;
    static int[] prev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sequence = new int[n];
        prev = new int[n];
        Arrays.fill(prev, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> lis = new ArrayList<>();
        int[] pos = new int[n]; // pos[i]는 sequence[i]가 lis에서 위치한 인덱스

        for (int i = 0; i < n; i++) {
            if (lis.isEmpty() || sequence[i] > lis.get(lis.size() - 1)) {
                pos[i] = lis.size();
                lis.add(sequence[i]);
            } else {
                int index = Collections.binarySearch(lis, sequence[i]);
                if (index < 0) index = -(index + 1);
                lis.set(index, sequence[i]);
                pos[i] = index;
                if (index > 0) prev[i] = pos[i-1];
            }
        }

        // 출력
        System.out.println(lis.size());

        // LIS 수열 복원
        int index = lis.size() - 1;
        int[] result = new int[lis.size()];
        for (int i = n - 1; i >= 0; i--) {
            if (pos[i] == index) {
                result[index] = sequence[i];
                index--;
            }
            if (index < 0) break;
        }
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
