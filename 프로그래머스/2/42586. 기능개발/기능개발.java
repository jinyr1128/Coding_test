import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();
        
        // 작업 완료에 필요한 날짜 계산
        for (int i = 0; i < progresses.length; i++) {
            queue.add((100 - progresses[i]) % speeds[i] == 0 ? 
                      (100 - progresses[i]) / speeds[i] : 
                      (100 - progresses[i]) / speeds[i] + 1);
        }
        
        List<Integer> answerList = new ArrayList<>();
        int prev = queue.poll();
        int num = 1;
        
        // 같이 배포되는 작업의 수 계산
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (prev >= curr) {
                num++;
            } else {
                answerList.add(num);
                num = 1;
                prev = curr;
            }
        }
        answerList.add(num);

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}
