import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 모든 방향과 장소를 순서대로 저장
        List<String> directions = new ArrayList<>();
        List<String> locations = new ArrayList<>();

        while (sc.hasNext()) {
            String dir = sc.nextLine();
            String loc = sc.nextLine();
            
            directions.add(dir);
            locations.add(loc);

            // "SCHOOL"이 마지막 입력이므로, 읽으면 입력을 중단
            if (loc.equals("SCHOOL")) {
                break;
            }
        }

        // 2. 리스트를 역순으로 순회
        for (int i = directions.size() - 1; i >= 0; i--) {
            // 3. 현재 방향(R/L)을 반대 방향(LEFT/RIGHT)으로 변환
            String currentDir = directions.get(i);
            String oppositeTurn = (currentDir.equals("R")) ? "LEFT" : "RIGHT";

            // 4. 마지막 단계(i=0)인지, 중간 단계인지 확인
            if (i == 0) {
                // 5. 마지막 단계: "HOME"으로 도착
                System.out.println("Turn " + oppositeTurn + " into your HOME.");
            } else {
                // 4. 중간 단계: i-1번째 장소(직전 장소)가 목적지
                String destination = locations.get(i - 1);
                System.out.println("Turn " + oppositeTurn + " onto " + destination + " street.");
            }
        }
        
        sc.close();
    }
}