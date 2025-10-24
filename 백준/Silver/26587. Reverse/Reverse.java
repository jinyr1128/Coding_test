import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    /**
     * 문자가 모음(a, e, i, o, u)인지 대소문자 구분 없이 확인합니다.
     */
    private static boolean isVowel(char c) {
        c = Character.toLowerCase(c); // 소문자로 변경
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력의 끝까지(unknown number of lines) 계속 읽습니다.
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] words = line.split(" ");

            // 1. 모음으로 시작하는 단어들을 순서대로 수집합니다.
            List<String> vowelWords = new ArrayList<>();
            for (String word : words) {
                // 단어가 비어있지 않은지, 모음으로 시작하는지 확인
                if (word.length() > 0 && isVowel(word.charAt(0))) {
                    vowelWords.add(word);
                }
            }

            // 2. 수집된 모음 단어 리스트를 뒤집습니다.
            Collections.reverse(vowelWords);

            // 3. 문장을 재구성합니다.
            StringBuilder result = new StringBuilder();
            int vowelIndex = 0; // 뒤집힌 모음 리스트의 인덱스

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                
                if (word.length() > 0 && isVowel(word.charAt(0))) {
                    // 모음으로 시작하는 단어의 "자리"일 경우,
                    // 뒤집힌 리스트에서 단어를 가져와 채웁니다.
                    result.append(vowelWords.get(vowelIndex));
                    vowelIndex++;
                } else {
                    // 자음으로 시작하는 단어는 그대로 둡니다.
                    result.append(word);
                }

                // 마지막 단어가 아니면 공백을 추가합니다.
                if (i < words.length - 1) {
                    result.append(" ");
                }
            }

            // 완성된 줄을 출력합니다.
            System.out.println(result.toString());
        }

        sc.close();
    }
}