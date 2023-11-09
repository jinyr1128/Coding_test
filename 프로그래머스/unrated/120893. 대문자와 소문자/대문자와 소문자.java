class Solution {
    public String solution(String my_string) {
        StringBuilder answer = new StringBuilder(); 

        for (char ch : my_string.toCharArray()) { 
            if (Character.isLowerCase(ch)) { 
                answer.append(Character.toUpperCase(ch));
            } else { 
                answer.append(Character.toLowerCase(ch));
            }
        }

        return answer.toString(); 
    }
}
