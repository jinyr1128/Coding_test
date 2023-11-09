import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        // 그리움 점수를 저장할 맵을 생성합니다.
        Map<String, Integer> yearningMap = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            // 각 사람의 이름과 그리움 점수를 매핑하여 저장합니다.
            yearningMap.put(name[i], yearning[i]);
        }
        
        // 각 사진의 추억 점수를 저장할 배열을 생성합니다.
        int[] scores = new int[photo.length];
        
        for (int i = 0; i < photo.length; i++) {
            for (String person : photo[i]) {
                // 만약 사진 속 인물의 이름이 그리움 점수 맵에 있다면,
                if (yearningMap.containsKey(person)) {
                    // 해당 인물의 그리움 점수를 추억 점수에 더해줍니다.
                    scores[i] += yearningMap.get(person);
                }
            }
        }
        
        // 계산된 추억 점수가 담긴 배열을 반환합니다.
        return scores;
    }
}
