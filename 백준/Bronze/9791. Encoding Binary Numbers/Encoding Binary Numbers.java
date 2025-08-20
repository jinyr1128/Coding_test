import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = br.readLine()) != null) {
            if (line.equals("0")) break; // 종료 조건

            StringBuilder encoded = new StringBuilder();
            char prev = line.charAt(0);
            int count = 1;
            for (int i = 1; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == prev) {
                    count++;
                } else {
                    // 이전 run 기록
                    encoded.append(count).append(prev);
                    prev = c;
                    count = 1;
                }
            }
            // 마지막 run 기록
            encoded.append(count).append(prev);

            sb.append(encoded).append('\n');
        }

        System.out.print(sb.toString());
    }
}
