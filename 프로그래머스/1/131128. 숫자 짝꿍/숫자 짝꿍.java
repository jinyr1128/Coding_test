import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        int[] count_X = new int[10];
        int[] count_Y = new int[10];

        // X, Y의 각 자리 숫자의 빈도를 센다
        for (char x : X.toCharArray()) {
            count_X[Character.getNumericValue(x)]++;
        }
        for (char y : Y.toCharArray()) {
            count_Y[Character.getNumericValue(y)]++;
        }

        // 공통 숫자를 이용해 만들 수 있는 가장 큰 숫자를 찾는다
        StringBuilder result = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            int common = Math.min(count_X[i], count_Y[i]);
            for (int j = 0; j < common; j++) {
                result.append(i);
            }
        }

        // 결과가 없거나 0만 있는 경우 처리
        if (result.length() == 0) {
            return "-1";
        } else if (result.charAt(0) == '0') {
            return "0";
        }

        return result.toString();
    }
}
