import java.util.Scanner;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<Integer> remainders = new HashSet<>();  // 나머지를 저장할 HashSet 생성

        for (int i = 0; i < 10; i++) {
            int num = sc.nextInt();  // 숫자 입력 받기
            remainders.add(num % 42);  // 42로 나눈 나머지를 HashSet에 추가
        }

        System.out.println(remainders.size());  // HashSet의 크기 출력 (서로 다른 나머지의 개수)
    }
}
