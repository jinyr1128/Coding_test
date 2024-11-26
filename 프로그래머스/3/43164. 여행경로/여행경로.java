import java.util.*;

class Solution {
    private List<String> path = new ArrayList<>();
    private boolean[] visited;
    
    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (a, b) -> a[1].compareTo(b[1]));

        visited = new boolean[tickets.length];
        dfs("ICN", "ICN", tickets, 0);
        
        return path.toArray(new String[0]);
    }
    
    private boolean dfs(String current, String route, String[][] tickets, int count) {
        if (count == tickets.length) {
            path = new ArrayList<>(Arrays.asList(route.split(",")));
            return true;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(current)) {
                visited[i] = true; 
                if (dfs(tickets[i][1], route + "," + tickets[i][1], tickets, count + 1)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}
