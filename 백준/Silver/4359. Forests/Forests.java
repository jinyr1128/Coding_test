import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        while (first != null && first.trim().isEmpty()) first = br.readLine();
        if (first == null) return;

        StringTokenizer st = new StringTokenizer(first);
        int P = Integer.parseInt(st.nextToken()); // 사람 수 (예제 기준)
        int T = Integer.parseInt(st.nextToken()); // 나무 수

        // 사람마다 들은 나무 집합(BitSet) 준비
        BitSet[] heard = new BitSet[P + 1];
        for (int i = 1; i <= P; i++) heard[i] = new BitSet(T);

        // 이후 줄: "i j" 쌍들 EOF까지
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;
            int i = Integer.parseInt(st.nextToken());
            if (!st.hasMoreTokens()) continue;
            int j = Integer.parseInt(st.nextToken());

            // 범위 체크 (문제 조건 만족 시 정상)
            if (1 <= i && i <= P && 1 <= j && j <= T) {
                heard[i].set(j - 1); // BitSet은 0-based이므로 j-1
            }
        }

        // 서로 다른 의견(집합) 개수 세기
        HashSet<BitSet> unique = new HashSet<>();
        for (int i = 1; i <= P; i++) {
            unique.add(heard[i]);
        }

        System.out.println(unique.size());
    }
}
