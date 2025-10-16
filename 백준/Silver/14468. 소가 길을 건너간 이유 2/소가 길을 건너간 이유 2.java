import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        sc.close();

        // 1. 각 소(A-Z)의 두 위치를 저장할 배열
        // positions[0]은 'A'의 위치, positions[1]은 'B'의 위치 등
        int[][] positions = new int[26][2];
        
        // 배열을 -1로 초기화하여 첫 등장인지 확인하는 용도로 사용
        for (int[] row : positions) {
            Arrays.fill(row, -1);
        }

        // 문자열을 순회하며 각 소의 위치 저장
        for (int i = 0; i < path.length(); i++) {
            char cowChar = path.charAt(i);
            int cowIndex = cowChar - 'A'; // 'A'는 0, 'B'는 1, ...
            
            if (positions[cowIndex][0] == -1) {
                positions[cowIndex][0] = i; // 첫 번째 등장 위치
            } else {
                positions[cowIndex][1] = i; // 두 번째 등장 위치
            }
        }

        int crossingCount = 0;

        // 2. 모든 가능한 소들의 쌍(i, j)을 비교 (i < j)
        for (int i = 0; i < 26; i++) {
            for (int j = i + 1; j < 26; j++) {
                // i번째 소의 두 지점
                int cow1_pos1 = positions[i][0];
                int cow1_pos2 = positions[i][1];
                
                // j번째 소의 두 지점
                int cow2_pos1 = positions[j][0];
                int cow2_pos2 = positions[j][1];

                // 3. 교차 조건 확인
                // j번째 소의 첫 지점이 i번째 소의 두 지점 사이에 있는지?
                boolean firstPointInside = cow2_pos1 > cow1_pos1 && cow2_pos1 < cow1_pos2;
                // j번째 소의 두 번째 지점이 i번째 소의 두 지점 사이에 있는지?
                boolean secondPointInside = cow2_pos2 > cow1_pos1 && cow2_pos2 < cow1_pos2;
                
                // 두 지점 중 하나만 사이에 있을 경우에만 경로가 교차함 (XOR 조건)
                if (firstPointInside != secondPointInside) {
                    crossingCount++;
                }
            }
        }

        System.out.println(crossingCount);
    }
}