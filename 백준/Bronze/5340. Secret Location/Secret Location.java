import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] coord = new int[6];  // 위도와 경도 정보를 담을 배열

        // 6줄의 입력을 받고 각 줄의 길이를 저장
        for (int i = 0; i < 6; i++) {
            String line = sc.nextLine();
            coord[i] = line.trim().length();  // 입력받은 줄의 길이를 저장 (공백 제거)
        }

        // 위도 정보 출력
        System.out.println("Latitude " + coord[0] + ":" + coord[1] + ":" + coord[2]);

        // 경도 정보 출력
        System.out.println("Longitude " + coord[3] + ":" + coord[4] + ":" + coord[5]);

        sc.close();
    }
}
