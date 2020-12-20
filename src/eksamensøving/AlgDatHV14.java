package eksamensøving;

import ukeoppgaver.resources.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.StringJoiner;

public class AlgDatHV14 {
    public static void main(String[] args){
        oppgave1();
        oppgave2();
        oppgave3();
    }

    private static void oppgave1(){
        char[] c = {'C','D','J','A','H','F','B','I','G','E'};
        int[] indeks = Tabell.indeksTabell(c);               // returnerer indekstabellen
        System.out.println(Arrays.toString(indeks));  // skriver ut indekstabellen

        // Utskrift: [3, 6, 0, 1, 9, 5, 8, 4, 7, 2]   // c[indeks[0]] = A, osv.
        //





        int[] b = {1,2,5,9,11,13,0,0,0,0};
        Mengde B = new Mengde(b, 6);   // de 6 første verdiene i b
        System.out.println(B);         // et implisitt kall på toString

        // Utskrift: [1, 2, 5, 9, 11, 13]


        int[] a = {2,3,4,5,7,9,11,15};
        b = new int[]{1,2,5,9,11,13};
        int[] c2 = {3,4,6,10,12};

        Mengde A = new Mengde(a, a.length);  // hele a
        B = new Mengde(b, b.length);  // hele b
        Mengde C = new Mengde(c2, c2.length);  // hele c

        System.out.println(A.snitt(B) + "  " + B.snitt(C));
        // Utskrift: [2, 5, 9, 11]  []

        System.out.println("----------------------------------------");
    }
    private static void oppgave2(){
        Kø<Character> kø = new LenketKø<>();
        Stakk<Character> stakk = new TabellStakk<>();

        kø.leggInn('N'); kø.leggInn('I'); kø.leggInn('L'); kø.leggInn('E');

        while (!kø.tom()) stakk.leggInn(kø.taUt());
        while (!stakk.tom()) kø.leggInn(stakk.taUt());

        System.out.println(kø);

        while (!kø.tom()) System.out.print(kø.taUt() + " ");
        System.out.println();
        System.out.println("---------------------------------------------");
    }
    private static void oppgave3(){
        int[] a = {15, 18, 4, 8, 17, 5, 2, 12, 25, 9, 20, 13};
        SBinTre<Integer> tre = new SBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);

        System.out.println(tre.omvendtPreString());
        // Utskrift: [20, 25, 17, 18, 13, 9, 12, 5, 8, 2, 4, 15]
        System.out.println(tre.lengstGren());
        System.out.println(tre.kortestGren());
    }
}

class Mengde
{
    private int[] a;

    public Mengde()  // konstruktør
    {
        a = new int[0];
    }

    public Mengde(int[] b, int n)  // konstruktør
    {
        a = new int[n];
        for(int i = 0; i < n; i++){
            if(b[i] != 0){
                a[i] = b[i];
            }

            if(i > 0){
                if(b[i] <= b[i-1]) throw new IllegalArgumentException("Listen er ikke sortert!");
            }
        }
    }

    public String toString()
    {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for(int verdi : a) joiner.add(""+verdi);
        return joiner.toString();
    }

    public boolean equals(Mengde B)
    {
        return toString().equals(B.toString());
    }

    public Mengde snitt(Mengde B)
    {

        int[] c = new int[a.length > B.a.length ? a.length : B.a.length];
        int teller = 0;

        for(int valueA : a){
            for(int valueB : B.a){
                if(valueA == valueB){
                    c[teller] = valueA;
                    teller++;
                }
            }
        }
        return new Mengde(c, teller);
    }

} // class Mengde
