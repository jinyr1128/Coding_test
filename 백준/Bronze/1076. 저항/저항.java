import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Map<String, Integer> valueMap = new HashMap<>();
        Map<String, Long> multiplierMap = new HashMap<>();
        
        // 색상별 값과 곱 저장
        String[] colors = {"black", "brown", "red", "orange", "yellow", 
                           "green", "blue", "violet", "grey", "white"};
        
        for (int i = 0; i < colors.length; i++) {
            valueMap.put(colors[i], i);
            multiplierMap.put(colors[i], (long) Math.pow(10, i));
        }
        
        // 색상 입력 받기
        String firstColor = scanner.next();
        String secondColor = scanner.next();
        String thirdColor = scanner.next();
        
        // 저항 값 계산
        int resistanceValue = valueMap.get(firstColor) * 10 + valueMap.get(secondColor);
        long result = resistanceValue * multiplierMap.get(thirdColor);
        
        System.out.println(result);
        
        scanner.close();
    }
}
