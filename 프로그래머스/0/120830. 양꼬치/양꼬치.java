class Solution {
    public int solution(int n, int k) {
        int lamb = 12000; 
        int drink = 2000;  
        int service = n / 10; 
        
        int total = (n * lamb) + ((k - service) * drink);
        
        return total;
    }
}
