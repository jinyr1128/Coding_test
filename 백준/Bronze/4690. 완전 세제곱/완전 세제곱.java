public class Main {
    public static void main(String[] args) {
        for (int a = 2; a <= 100; a++) {
            int aCube = a * a * a;
            
            for (int b = 2; b < a; b++) {
                int bCube = b * b * b;
                
                for (int c = b; c < a; c++) {
                    int cCube = c * c * c;
                    
                    for (int d = c; d < a; d++) {
                        int dCube = d * d * d;
                        
                        if (aCube == bCube + cCube + dCube) {
                            System.out.printf("Cube = %d, Triple = (%d,%d,%d)%n", a, b, c, d);
                        }
                    }
                }
            }
        }
    }
}
