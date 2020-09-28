package ukeoppgaver.uke5;

public class Avsnitt152 {
    public static int tverrsum(int n)
    {
        System.out.println("tverrsum(" + n + ") starter!");
        int sum = (n < 10) ? n : tverrsum(n / 10) + (n % 10);
        System.out.println("tverrsum(" + n + ") er ferdig!");
        return sum;
    }
    public static int euklid(int a, int b)
    {
        if (b == 0) return a;
        int r = a % b;            // r er resten
        System.out.println("euklid(" + a + ", " + b + ") starter!");
        int sum = euklid(b,r);
        System.out.println("euklid(" + a + ", " + b + ") er ferdig!");
        return sum;       // rekursivt kall
    }
}
