import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /* 1. ‘같은 그룹’ 제약 읽기 */
        int X = Integer.parseInt(br.readLine().trim());
        String[][] mustTogether = new String[X][2];
        for (int i = 0; i < X; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            mustTogether[i][0] = st.nextToken();
            mustTogether[i][1] = st.nextToken();
        }

        /* 2. ‘다른 그룹’ 제약 읽기 */
        int Y = Integer.parseInt(br.readLine().trim());
        String[][] mustApart = new String[Y][2];
        for (int i = 0; i < Y; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            mustApart[i][0] = st.nextToken();
            mustApart[i][1] = st.nextToken();
        }

        /* 3. 그룹 정보 읽어서 “이름 → 그룹ID” 매핑 */
        int G = Integer.parseInt(br.readLine().trim());
        HashMap<String, Integer> groupOf = new HashMap<>(3 * G * 2); // 여유 있는 초기 용량
        for (int g = 0; g < G; g++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int k = 0; k < 3; k++) {
                groupOf.put(st.nextToken(), g);   // 같은 g 값 = 같은 그룹
            }
        }

        /* 4. 제약 위반 카운트 */
        int violations = 0;

        // 4-1. 반드시 같은 그룹이어야 하는데 다른 그룹?
        for (int i = 0; i < X; i++) {
            if (!groupOf.get(mustTogether[i][0]).equals(groupOf.get(mustTogether[i][1]))) {
                violations++;
            }
        }

        // 4-2. 반드시 다른 그룹이어야 하는데 같은 그룹?
        for (int i = 0; i < Y; i++) {
            if (groupOf.get(mustApart[i][0]).equals(groupOf.get(mustApart[i][1]))) {
                violations++;
            }
        }

        /* 5. 출력 */
        System.out.println(violations);
    }
}
