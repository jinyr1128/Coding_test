public class Solution {
    public String solution(String[] survey, int[] choices) {
        // 각 성격 유형 점수를 저장할 배열 초기화
        int[] scores = {3, 2, 1, 0, 1, 2, 3};
        int[] typeScores = new int[8];

        // 성격 유형 검사 점수 계산
        for (int i = 0; i < survey.length; i++) {
            char disagreeType = survey[i].charAt(0);
            char agreeType = survey[i].charAt(1);
            if (choices[i] <= 3) {
                typeScores[getIndex(disagreeType)] += scores[choices[i] - 1];
            } else {
                typeScores[getIndex(agreeType)] += scores[choices[i] - 1];
            }
        }

        // 성격 유형 검사 결과 문자열 초기화
        StringBuilder result = new StringBuilder();

        // 각 지표에서 더 높은 점수를 받은 성격 유형을 결과 문자열에 추가
        result.append(getPersonalityType(typeScores[0], typeScores[1], 'R', 'T'));
        result.append(getPersonalityType(typeScores[2], typeScores[3], 'C', 'F'));
        result.append(getPersonalityType(typeScores[4], typeScores[5], 'J', 'M'));
        result.append(getPersonalityType(typeScores[6], typeScores[7], 'A', 'N'));

        return result.toString();
    }

    private static int getIndex(char personalityType) {
        switch (personalityType) {
            case 'R':
                return 0;
            case 'T':
                return 1;
            case 'C':
                return 2;
            case 'F':
                return 3;
            case 'J':
                return 4;
            case 'M':
                return 5;
            case 'A':
                return 6;
            case 'N':
                return 7;
            default:
                return -1;
        }
    }

    private static char getPersonalityType(int firstScore, int secondScore, char firstType, char secondType) {
        if (firstScore == secondScore) {
            return firstType < secondType ? firstType : secondType;
        }
        return firstScore > secondScore ? firstType : secondType;
    }
}