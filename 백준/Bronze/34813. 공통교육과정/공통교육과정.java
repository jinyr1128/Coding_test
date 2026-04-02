import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String courseCode = sc.next();
        
        // 문자열의 첫 번째 문자를 추출합니다.
        char firstLetter = courseCode.charAt(0);
        
        // 문자에 맞게 결과를 출력합니다.
        switch (firstLetter) {
            case 'F':
                System.out.println("Foundation");
                break;
            case 'C':
                System.out.println("Claves");
                break;
            case 'V':
                System.out.println("Veritas");
                break;
            case 'E':
                System.out.println("Exploration");
                break;
        }
        
        sc.close();
    }
}