package eksamensøving;

import Oblig.Oblig2.DobbeltLenketListe;
import Oblig.Oblig2.Liste;
import ukeoppgaver.resources.Kø;
import ukeoppgaver.resources.SBinTre;
import ukeoppgaver.resources.TabellKø;

import java.util.Arrays;
import java.util.Comparator;

public class AlgDatH15 {
    public static void main(String[] args){
        oppgave1();
        oppgave2();
    }
    private static void oppgave1(){
        //A
        String[] person = {"Per","Kari","Elin","Ali","Jens","Siri"};
        Kø<String> kø = new TabellKø<>();
        for (String p : person) kø.leggInn(p);  // legger inn i køen

        System.out.println(kø);  // [Per, Kari, Elin, Ali, Jens, Siri]
        TabellKø.byttPlass(kø, 4);  // den på indeks 4 bytter plass med den rett bak (indeks 5)
        System.out.println(kø);  // [Per, Kari, Elin, Ali, Siri, Jens]
        //B
        System.out.println("--------------------------------");

    }
    private static void oppgave2(){
        //A
        Liste<String> liste = new DobbeltLenketListe<>();
        liste.leggInn("Erik"); liste.leggInn("Kari");
        liste.leggInn(1, "Reidar"); liste.leggInn(1, "Jasmin");
        liste.fjern(2);
        System.out.println(liste); //utskrift: [Erik, Kari, Jasmin]
        //liste.fjernHvis(navn -> navn.contains("i"));
        System.out.println(liste); //utskrift: []
        //B
        int[] a = {1,3,5,5,6,8,8,8,9,10,10};    // en sortert tabell med duplikater
        int antall = fjernDuplikater(a);        // kaller metoden
        System.out.println(antall + ": " + Arrays.toString(a));  // antallet og tabellen
        // Utskrift: 7: [1, 3, 5, 6, 8, 9, 10, 0, 0, 0, 0]
        System.out.println("-----------------------------");
    }
    private static void oppgave4(){
        int[] a = {12,4,10,22,2,6,17,8,20,14,15,5,9,13};  // verdiene fra Oppgave 4A/C
        SBinTre<Integer> tre = new SBinTre<>(Comparator.naturalOrder());
        for (int k : a) tre.leggInn(k);  // legger inn
        System.out.print(tre.avstand2(12,4) + " " + tre.avstand2(4,4)); // Utskrift: 15 9
    }

    public static int fjernDuplikater(int[] a){
        if(a.length > 1) {
            int antallDuplikater = 0;


            for (int i = 0; i < a.length-1; i++) {
                int j = i + 1;
                if (a[i] == a[j]){
                    antallDuplikater++;
                    while(i < a.length && j < a.length  && a[i] == a[j]){
                        a[j] = 0;
                        antallDuplikater++;
                        j++;
                    }
                    if(i == j - 1) j -= 1;
                    else{
                        i++;
                        a[i] = a[j];
                        a[j] = 0;
                    }
                    i = j;
                }

            }return antallDuplikater;

        }
        return 0;
    }
}
