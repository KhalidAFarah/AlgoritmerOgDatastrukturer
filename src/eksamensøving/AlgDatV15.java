package eksamensøving;

import ukeoppgaver.resources.Kø;
import ukeoppgaver.resources.Tabell;
import ukeoppgaver.resources.TabellKø;

import java.util.Comparator;

public class AlgDatV15 {
    public static void main(String[] args){
        oppgave2();
    }

    public static void oppgave2(){
        //C
        Integer[] a = {3,9,6,2,8,1,5,10,7,4};               // en heltallstabell
        Kø<Integer> kø = new TabellKø<>();                  // en Integer-kø
        for (int tall : a) kø.leggInn(tall);                // legger inn i køen
        Comparator<Integer> c = Comparator.naturalOrder();  // en komparator
        Integer maksverdi = Tabell.maks(kø, c);                    // kaller metoden
        System.out.println(maksverdi);                      // skriver ut
        // Utskrift: 10
    }
}
