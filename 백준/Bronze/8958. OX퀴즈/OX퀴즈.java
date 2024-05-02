import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  
        sc.nextLine(); 

        for (int t = 0; t < T; t++) {
            String results = sc.nextLine();  
            int score = 0;  
            int consecutive = 0;  

            for (int i = 0; i < results.length(); i++) {
                if (results.charAt(i) == 'O') {
                    consecutive++;  
                    score += consecutive;  
                } else {
                    consecutive = 0; 
                }
            }

            System.out.println(score);  
        }
    }
}
