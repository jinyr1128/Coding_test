import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 파일의 개수 N을 입력받음
        int N = sc.nextInt();
        sc.nextLine();  // 개행 문자 처리
        
        // 첫 번째 파일 이름을 패턴의 초기 값으로 설정
        String pattern = sc.nextLine();
        
        // 나머지 파일 이름들과 비교하면서 패턴을 업데이트
        for (int i = 1; i < N; i++) {
            String fileName = sc.nextLine();
            
            // 각 파일의 문자들과 비교하여 다르면 ?로 바꾸기
            StringBuilder newPattern = new StringBuilder();
            for (int j = 0; j < pattern.length(); j++) {
                if (pattern.charAt(j) == fileName.charAt(j)) {
                    newPattern.append(pattern.charAt(j));
                } else {
                    newPattern.append('?');
                }
            }
            pattern = newPattern.toString();  // 패턴 업데이트
        }
        
        // 결과 패턴 출력
        System.out.println(pattern);
        
        sc.close();
    }
}
