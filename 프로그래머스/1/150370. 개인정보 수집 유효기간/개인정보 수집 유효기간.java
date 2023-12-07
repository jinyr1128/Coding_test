import java.text.SimpleDateFormat;
import java.util.*;

public class Solution {
    static class Info {
        String date;
        String term;

        public Info(String date, String term) {
            this.date = date;
            this.term = term;
        }
    }

    public int[] solution(String today, String[] terms, String[] privacies) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Date todayDate = format.parse(today);
        Map<String, Integer> termMap = new HashMap<>();

        for (String term : terms) {
            String[] termElements = term.split(" ");
            termMap.put(termElements[0], Integer.parseInt(termElements[1]));
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] privacyElements = privacies[i].split(" ");
            Calendar cal = Calendar.getInstance();
            cal.setTime(format.parse(privacyElements[0]));
            cal.add(Calendar.MONTH, termMap.get(privacyElements[1]));

            // 마지막 날짜 계산을 위해 하루 뺄 필요 없이 월만 조정
            if (!cal.getTime().after(todayDate)) {
                result.add(i + 1);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}

