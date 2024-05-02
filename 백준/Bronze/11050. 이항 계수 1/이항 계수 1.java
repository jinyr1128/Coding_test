import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        
        BigInteger result = binomialCoefficient(N, K);
        
        System.out.println(result);
    }
    
    private static BigInteger binomialCoefficient(int N, int K) {
        BigInteger numerator = factorial(N); 
        BigInteger denominator = factorial(K).multiply(factorial(N - K)); 
        
        return numerator.divide(denominator); 
    }
    
    private static BigInteger factorial(int num) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= num; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
