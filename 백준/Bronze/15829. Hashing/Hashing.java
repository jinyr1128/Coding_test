import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt(); 
        String str = sc.next(); 
        long M = 1234567891; 
        long r = 31; 

        long result = 0; 
        long rPow = 1; 

        for (int i = 0; i < L; i++) {
            int charValue = str.charAt(i) - 'a' + 1; 
            result = (result + (charValue * rPow) % M) % M; 
            rPow = (rPow * r) % M; 
        }

        System.out.println(result); 
        sc.close();
    }
}
