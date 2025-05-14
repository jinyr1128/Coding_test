import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 빠른 입력 처리를 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 빠른 출력 처리를 위한 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            long result = (long) N * N; // 오버플로우 방지
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
    }
}
