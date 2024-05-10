import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();
        StringBuilder result = new StringBuilder();

        int bombLength = bomb.length();
        for (int i = 0; i < str.length(); i++) {
            result.append(str.charAt(i));

            // 폭발 문자열과 일치하는지 마지막부터 검사
            if (result.length() >= bombLength && 
                result.substring(result.length() - bombLength).equals(bomb)) {
                // 폭발 문자열을 제거
                result.setLength(result.length() - bombLength);
            }
        }

        // 결과 문자열 출력, 비어있다면 "FRULA"
        System.out.println(result.length() == 0 ? "FRULA" : result.toString());
    }
}
