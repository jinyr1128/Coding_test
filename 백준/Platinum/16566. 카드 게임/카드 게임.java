import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    int m, k;
    int[] redCards;
    int[] cheolsuCards;
    int[] parent;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken(); // N 값을 무시
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        redCards = new int[m];
        cheolsuCards = new int[k];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            redCards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(redCards);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            cheolsuCards[i] = Integer.parseInt(st.nextToken());
        }
        
        parent = new int[m + 1];
        for (int i = 0; i <= m; i++) {
            parent[i] = i;
        }
    }

    int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    void union(int a, int b) {
        parent[find(a)] = find(b);
    }

    int binarySearch(int card) {
        int left = 0;
        int right = m;
        while (left < right) {
            int mid = (left + right) / 2;
            if (redCards[mid] > card) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    void solve() throws IOException {
        input();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int card : cheolsuCards) {
            int position = binarySearch(card);
            position = find(position);
            bw.write(redCards[position] + "\n");
            union(position, position + 1);
        }
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solve();
    }
}


