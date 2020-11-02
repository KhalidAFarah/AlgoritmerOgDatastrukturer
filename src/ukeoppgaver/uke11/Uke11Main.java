package ukeoppgaver.uke11;

import ukeoppgaver.resources.HeapPrioritetsKø;
import ukeoppgaver.resources.PrioritetsKø;
import ukeoppgaver.resources.Tabell;

public class Uke11Main {
    public static void main(){
        avsnitt533();
    }
    private static void avsnitt533(){
        int[] a = {3,5,7,10,5,8,18,12,17,11,10,14};  // verdiene i Figur 5.3.2 a)
        PrioritetsKø<Integer> kø = HeapPrioritetsKø.naturligOrden();
        for (int k : a) kø.leggInn(k);

        kø.leggInn(6);  System.out.println(kø);     // legger inn 6
        kø.leggInn(10); System.out.println(kø);     // legger inn 10
        kø.leggInn(12); System.out.println(kø);     // legger inn 12

        // Utskrift:
        // [3, 5, 6, 10, 5, 7, 18, 12, 17, 11, 10, 14, 8]          se Figur 5.3.2 c)
        // [3, 5, 6, 10, 5, 7, 10, 12, 17, 11, 10, 14, 8, 18]      se Figur 5.3.2 d)
        // [3, 5, 6, 10, 5, 7, 10, 12, 17, 11, 10, 14, 8, 18, 12]  se Figur 5.3.2 e)

        a = new int[]{7, 6, 5, 4, 3, 2, 1};
        kø = HeapPrioritetsKø.naturligOrden();
        for(int k : a) kø.leggInn(k);

        System.out.println(kø);

        //Oppgave 2

        a = new int[]{3,5,4,10,5,8,18,12,17,11,10,14};

        HeapPrioritetsKø<Integer> kø2 = HeapPrioritetsKø.naturligOrden();

        for (int k : a) kø2.leggInn(k);

        System.out.println(kø2.minimumsGrenen());
        kø2.leggInn(9);
        System.out.println(kø2.minimumsGrenen());

        //Oppgave 3
        int n = 20;                           // velg n >= 0
        a = Tabell.randPerm(n);         // en permutasjon av tallene fra 1 til n

        kø = HeapPrioritetsKø.naturligOrden();
        for (int k: a) kø.leggInn(k);         // ett og ett tall inn i køen

        while (!kø.tom())
        {
            System.out.print(kø.taUt() + " ");  // tar ut fra køen
        }
        // Utskrift: 1 2 3 4 5 6 7 8 9 10
        //

    }

    private static void avsnitt542(){

    }
}
