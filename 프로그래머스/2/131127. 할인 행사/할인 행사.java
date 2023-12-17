import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        HashMap<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        for (int i = 0; i <= discount.length - 10; i++) {
            HashMap<String, Integer> discountMap = new HashMap<>();
            for (int j = i; j < i + 10; j++) {
                discountMap.put(discount[j], discountMap.getOrDefault(discount[j], 0) + 1);
            }
            if (isMatch(wantMap, discountMap)) {
                answer++;
            }
        }
        return answer;
    }

    private boolean isMatch(HashMap<String, Integer> want, HashMap<String, Integer> discount) {
        for (String key : want.keySet()) {
            if (want.get(key) > discount.getOrDefault(key, 0)) {
                return false;
            }
        }
        return true;
    }
}
