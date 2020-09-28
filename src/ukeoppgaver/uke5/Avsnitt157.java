package ukeoppgaver.uke5;

public class Avsnitt157 {
    private static int parter0(int[] a, int v, int h, int skilleverdi)
    {
        while (true)                                  // stopper når v > h
        {
            while (v <= h && a[v] < skilleverdi) v++;   // h er stoppverdi for v
            while (v <= h && a[h] >= skilleverdi) h--;  // v er stoppverdi for h

            if (v < h) bytt(a,v++,h--);                 // bytter om a[v] og a[h]
            else  return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
        }
    }
    public static void bytt(int[] a, int m, int n){
        int temp = a[m];
        a[m] = a[n];
        a[n] = temp;
    }
    public static int sParter0(int[] a, int v, int h, int indeks)
    {
        bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
        int pos = parter0(a, v, h - 1, a[h]);  // partisjonerer a[v:h - 1]
        bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
        return pos;                   // returnerer posisjonen til skilleverdien
    }
    public static void kvikksortering0(int[] a, int v, int h)
    {
        if (v >= h && v-h <= 1) return;   // tomt eller maks ett element
        System.out.println("Kallet med [" + v + ":" + h + "] starter!");

        int k = sParter0(a,v,h,(v + h)/2);   // se Programkode 1.3.9 f)
        kvikksortering0(a,v,k-1);
        kvikksortering0(a,k+1,h);
        System.out.println("Kallet med [" + v + ":" + h + "] er ferdig!");

    }
}
