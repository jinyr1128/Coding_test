class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int zeroCount = 0;
        int matchCount = 0;
        for (int lotto : lottos) {
            if (lotto == 0) {
                zeroCount++;
            } else {
                for (int win_num : win_nums) {
                    if (lotto == win_num) {
                        matchCount++;
                    }
                }
            }
        }

        int[] rank = {6, 6, 5, 4, 3, 2, 1}; // 맞춘 개수에 따른 순위
        int maxRank = rank[matchCount + zeroCount]; // 최고 순위
        int minRank = rank[matchCount]; // 최저 순위

        return new int[]{maxRank, minRank};
    }
}
