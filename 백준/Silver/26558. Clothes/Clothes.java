import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 데이터셋의 개수 n 입력
        if (sc.hasNextInt()) {
            int n = sc.nextInt();

            while (n-- > 0) {
                int c = sc.nextInt();
                sc.nextLine(); // 개행 문자 소비

                // 3가지 종류의 옷을 저장할 스택 생성
                Stack<String> shirts = new Stack<>();
                Stack<String> pants = new Stack<>();
                Stack<String> socks = new Stack<>();

                for (int i = 0; i < c; i++) {
                    String line = sc.nextLine();
                    
                    // 문자열 파싱: "이름 (종류)" 형태
                    // 뒤에서부터 ' ('를 찾아서 이름과 종류를 분리
                    int splitIndex = line.lastIndexOf(" (");
                    String name = line.substring(0, splitIndex);
                    // 괄호 안의 종류 추출 (" (" 다음부터 마지막 ")" 전까지)
                    String type = line.substring(splitIndex + 2, line.length() - 1);

                    // 종류에 따라 해당 스택에 push
                    switch (type) {
                        case "shirt":
                            shirts.push(name);
                            break;
                        case "pants":
                            pants.push(name);
                            break;
                        case "socks":
                            socks.push(name);
                            break;
                    }
                }

                // 세 가지 옷이 모두 최소 하나씩 있을 때까지 반복해서 꺼내 입음
                while (!shirts.isEmpty() && !pants.isEmpty() && !socks.isEmpty()) {
                    String shirt = shirts.pop();
                    String pant = pants.pop();
                    String sock = socks.pop();

                    System.out.println(shirt + ", " + pant + ", " + sock);
                }
                
                // 각 데이터셋 뒤에는 빈 줄 출력
                System.out.println();
            }
        }
        sc.close();
    }
}