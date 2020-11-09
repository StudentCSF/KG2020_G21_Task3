package course2.kg.task3.utils;

public class MathUtils {

    public static int[] getBinomCoeffs(int degree) {
        int[] coeffs = new int[degree + 1];
        for (int i = 0; i < coeffs.length / 2 + 1; i++) {
            int v = countCombination(degree, i);
            coeffs[i] = v;
            coeffs[coeffs.length - 1 - i] = v;
        }
        return coeffs;
    }

    public static int countCombination(int n, int k) {
        int res = 1;
        for (int i = k + 1; i <= n; i++) {
            res *= i;
        }
        int mult = 1;
        for (int i = 2; i <= n - k; i++) {
            mult *= i;
        }
        return res / mult;
    }
}
