import java.math.BigDecimal;
import java.math.MathContext;

public class Main {
    // method to find the factorial of 'n'
    public static BigDecimal factorial(int n){
        BigDecimal result = BigDecimal.ONE;
        for (int i = 2; i <= n; i++){
            result = result.multiply(BigDecimal.valueOf(i));
        }
        return result;
    }
    public static BigDecimal euleur(int n){
        // 'n' is the number we go up to (till the max int)
        BigDecimal fact;
        BigDecimal factSum = BigDecimal.ZERO;
        for (int i = 0; i <= n; i++){
            fact = factorial(i);
            BigDecimal result = BigDecimal.ONE.divide(fact, MathContext.DECIMAL32);
            factSum = factSum.add(result);
        }
        // prints the sum
        return factSum;
    }
    public static boolean primeNum(int n){
        for (int i = 2; i < n; i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }
}
