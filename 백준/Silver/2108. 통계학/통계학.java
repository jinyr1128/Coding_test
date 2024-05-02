import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] numbers = new int[N];
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        int sum = 0;
        int maxFrequency = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            numbers[i] = num;
            sum += num;
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            maxFrequency = Math.max(maxFrequency, frequencyMap.get(num));
        }

        Arrays.sort(numbers);
        
        ArrayList<Integer> modes = new ArrayList<>();
        for (int key : frequencyMap.keySet()) {
            if (frequencyMap.get(key) == maxFrequency) {
                modes.add(key);
            }
        }
        Collections.sort(modes);

        System.out.println(Math.round((double) sum / N)); // 산술평균
        System.out.println(numbers[N / 2]); // 중앙값
        System.out.println(modes.size() > 1 ? modes.get(1) : modes.get(0)); // 최빈값
        System.out.println(numbers[N - 1] - numbers[0]); // 범위
    }
}
