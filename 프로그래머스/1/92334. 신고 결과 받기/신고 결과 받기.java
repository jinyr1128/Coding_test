import java.util.*;

public class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> reports = new HashMap<>();
        Map<String, Integer> reportCount = new HashMap<>();
        for (String r : report) {
            String[] split = r.split(" ");
            String reporter = split[0], reportee = split[1];
            reports.putIfAbsent(reporter, new HashSet<>());
            if (reports.get(reporter).add(reportee)) {
                reportCount.put(reportee, reportCount.getOrDefault(reportee, 0) + 1);
            }
        }

        Set<String> banned = new HashSet<>();
        for (String reportee : reportCount.keySet()) {
            if (reportCount.get(reportee) >= k) {
                banned.add(reportee);
            }
        }

        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            for (String reportee : reports.getOrDefault(id_list[i], new HashSet<>())) {
                if (banned.contains(reportee)) {
                    answer[i]++;
                }
            }
        }
        return answer;
    }
}

