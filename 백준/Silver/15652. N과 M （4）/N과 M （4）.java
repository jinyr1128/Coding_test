import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        generateSequence(new ArrayList<Integer>(), 1);
        bw.flush();
        bw.close();
    }

    static void generateSequence(ArrayList<Integer> current, int start) throws IOException {
        if (current.size() == M) {
            for (int num : current) {
                bw.write(num + " ");
            }
            bw.write("\n");
            return;
        }

        for (int i = start; i <= N; i++) {
            current.add(i);
            generateSequence(current, i); // 다음 요소도 i 이상이어야 하므로 start는 i
            current.remove(current.size() - 1); // 다음 반복을 위해 추가한 요소 제거
        }
    }
}
