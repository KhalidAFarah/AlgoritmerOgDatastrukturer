package ukeoppgaver.uke8;

import ukeoppgaver.eksempelKlasser.Komparator;
import ukeoppgaver.resources.*;

import java.util.Comparator;

public class Uke8Main {
    public static void main(){
        avsnitt422();
    }



    private static void avsnitt412(){
        Stakk<Integer> A = new TabellStakk<>(3);
        A.leggInn(1);
        A.leggInn(2);
        A.leggInn(3);
        System.out.println(A.toString());
        TabellStakk.snu2(A);
        System.out.println(A.toString());
        System.out.println();

        TabellStakk<Integer> B = new TabellStakk<>();
        TabellStakk.kopier2(A, B);
        System.out.println("A: " + A.toString());
        System.out.println("B: " + B.toString());
        System.out.println();

        Stakk<Integer> T = new TabellStakk<>();
        T.leggInn(1);
        T.leggInn(2);
        T.leggInn(3);
        System.out.println(T);
        Tabell.sorter(T, Comparator.naturalOrder());
        System.out.println(T.toString());
    }

    private static void avsnitt422(){
        Stakk<Integer> A = new TabellStakk<>(3);
        A.leggInn(1);
        A.leggInn(2);
        A.leggInn(3);
        System.out.println(A.toString());
        TabellKø.snu(A);
        System.out.println(A.toString());

        System.out.println("--------------");

        TabellKø<Integer> B = new TabellKø<>();
        B.leggInn(1);
        B.leggInn(2);
        B.leggInn(3);
        System.out.println(B.toString());
        TabellKø.snu(B);
        System.out.println(B.toString());
        TabellKø.snu2(B);
        System.out.println(B);

    }

    private static void avsnitt424(){
        Kø<Integer> kø2 = new EnkeltLenketListe<>();

        for (int i = 1; i <= 10; i++) kø2.leggInn(i);

        while (!kø2.tom())
        {
            System.out.print(kø2.taUt() + " ");
        }

        System.out.println();
        System.out.println();


        Integer[] a = Tabell.randPermInteger(8);

        Kø<Integer> kø = new EnkeltLenketListe<>();

        for (Integer i : a) kø.leggInn(i);

        System.out.println(kø);    // usortert

        Stakk<Integer> stakk = new TabellStakk<>();

        Tabell.sorter(kø, stakk, Comparator.naturalOrder());

        System.out.println(kø);    // sortert
    }
}
