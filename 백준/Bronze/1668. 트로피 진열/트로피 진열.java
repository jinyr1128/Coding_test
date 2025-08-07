import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int[] h = new int[N];
        for (int i = 0; i < N; i++) {
            h[i] = Integer.parseInt(br.readLine().trim());
        }

        // 왼쪽에서 본 개수
        int leftVisible = 0;
        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            if (h[i] > maxHeight) {
                leftVisible++;
                maxHeight = h[i];
            }
        }

        // 오른쪽에서 본 개수
        int rightVisible = 0;
        maxHeight = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (h[i] > maxHeight) {
                rightVisible++;
                maxHeight = h[i];
            }
        }

        System.out.println(leftVisible);
        System.out.println(rightVisible);
    }
}
