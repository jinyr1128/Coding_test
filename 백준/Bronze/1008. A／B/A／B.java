import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        
        double A = scanner.nextDouble(); // 입력을 double로 받음
        double B = scanner.nextDouble();
        
        System.out.println(A / B); // A를 B로 나눈 결과를 출력
        
        scanner.close();  
    }
}
