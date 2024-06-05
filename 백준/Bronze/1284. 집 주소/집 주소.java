import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            String number = sc.nextLine();
            if (number.equals("0")) {
                break;
            }
            
            int width = 2; // 시작과 끝의 여백 1cm씩 합쳐서 2cm
            
            for (char digit : number.toCharArray()) {
                if (digit == '1') {
                    width += 2;
                } else if (digit == '0') {
                    width += 4;
                } else {
                    width += 3;
                }
                width += 1; // 각 숫자 사이의 여백
            }
            
            // 마지막 숫자 뒤의 여백 1cm는 제외해야 한다.
            width -= 1;
            
            System.out.println(width);
        }
        
        sc.close();
    }
}