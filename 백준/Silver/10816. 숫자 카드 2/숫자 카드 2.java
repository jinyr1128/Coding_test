import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> cardCounts = new HashMap<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int card = Integer.parseInt(st.nextToken());
            cardCounts.put(card, cardCounts.getOrDefault(card, 0) + 1);
        }
        
        int M = Integer.parseInt(br.readLine());
        int[] results = new int[M];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int query = Integer.parseInt(st.nextToken());
            results[i] = cardCounts.getOrDefault(query, 0);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int result : results) {
            sb.append(result).append(" ");
        }
        
        System.out.println(sb.toString().trim());
    }
}
