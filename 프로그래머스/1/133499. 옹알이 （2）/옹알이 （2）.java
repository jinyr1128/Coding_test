class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        String[] possible = {"aya", "ye", "woo", "ma"};
        String[] impossible = {"ayaaya", "yeye", "woowoo", "mama"};
        
        
        for (String element : babbling) {
            for (String check : impossible) {
                element = element.replace(check, "X");
            }
            
            for (String check : possible) {
                element = element.replace(check, "O");
            }
            
            int check = 0;
            for (int i = 0; i < element.length(); i++) {
                if (element.charAt(i) != 'O') {
                    check++;
                    break;
                }
            }
            
            if (check == 0) answer++;
        }
        
        return answer;
    }
}
