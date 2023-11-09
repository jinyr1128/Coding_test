public class Solution {
    public int solution(int[] array) {
        int[] count = new int[1000];

        for (int num : array) {
            count[num]++;
        }

        int maxFrequency = 0;
        int mode = -1;
        int modeCount = 0;

        for (int i = 0; i < count.length; i++) {
            if (count[i] > maxFrequency) {
                maxFrequency = count[i];
                mode = i;
                modeCount = 1; 
            } else if (count[i] == maxFrequency) {
                modeCount++; 
            }
        }

        if (modeCount > 1) {
            return -1;
        }

        return mode;
    }
}
