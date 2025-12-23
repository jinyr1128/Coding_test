import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 블록의 개수
        int K = Integer.parseInt(st.nextToken()); // 색깔의 개수

        // 각 색깔별로 '숫자'들을 저장할 리스트 배열
        ArrayList<Integer>[] numbersByColor = new ArrayList[K + 1];
        // 각 색깔별로 '위치(인덱스)'들을 저장할 리스트 배열
        ArrayList<Integer>[] indicesByColor = new ArrayList[K + 1];

        // 리스트 초기화
        for (int i = 1; i <= K; i++) {
            numbersByColor[i] = new ArrayList<>();
            indicesByColor[i] = new ArrayList<>();
        }

        // 입력 처리
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());

            numbersByColor[color].add(num);
            indicesByColor[color].add(i); // 현재 블록의 위치는 i
        }

        // 각 색깔별로 검증
        for (int c = 1; c <= K; c++) {
            // 해당 색깔의 블록이 가지고 있는 숫자들을 오름차순 정렬
            Collections.sort(numbersByColor[c]);
            
            // 해당 색깔의 블록들이 위치한 인덱스들은 입력 순서(1~N)대로 넣었으므로 이미 정렬되어 있음.
            
            // 두 리스트 비교
            // numbersByColor[c] : 이 색깔이 가진 숫자들 (예: {1, 3})
            // indicesByColor[c] : 이 색깔이 위치한 자리들 (예: {1, 3})
            // 이 둘이 같아야만 해당 위치에 해당 숫자를 배치할 수 있음
            ArrayList<Integer> nums = numbersByColor[c];
            ArrayList<Integer> idxs = indicesByColor[c];

            // 사이즈는 입력 로직상 무조건 같으므로 내용물 비교
            for (int j = 0; j < nums.size(); j++) {
                if (!nums.get(j).equals(idxs.get(j))) {
                    System.out.println("N");
                    return;
                }
            }
        }

        // 모든 검증을 통과하면 정렬 가능
        System.out.println("Y");
    }
}