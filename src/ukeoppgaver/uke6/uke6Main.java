package ukeoppgaver.uke6;

import ukeoppgaver.resources.*;

import javax.swing.text.html.ListView;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class uke6Main {
    public static void main(){
        /*avsnitt322();
        avsnitt324();
        avsnitt325();
        avsnitt331();
        avsnitt333();*/
        avsnitt334();
    }
    private static void avsnitt322(){
        System.out.println("------------------------------");
        //Oppgave 2
        String[] s = {"Per", "Kari", "Ole", "Azra"};
        Liste<String> liste = new TabellListe<String>(s);
        System.out.println("Antall : " + liste.antall() + " tom: " + liste.tom() + " tabell: " + liste.toString());
        s = new String[]{};
        liste = new TabellListe<String>(s);
        System.out.println("Antall : " + liste.antall() + " tom: " + liste.tom() + " tabell: " + liste.toString());
        s = new String[]{null, null};
        liste = new TabellListe<String>(s);
        System.out.println("Antall : " + liste.antall() + " tom: " + liste.tom() + " tabell: " + liste.toString());
        //Oppgave 3

    }
    private static void avsnitt324(){
        System.out.println("----------------------------------");
        String[] s = {"Per", "Kari", "Ole", "Azra"};
        Liste<String> liste = new TabellListe<String>(s);

        System.out.println(liste.fjernHvis(x -> x.equals("Per")));  // fjerner alle forekomster av Per
        for(int i = 0; i < liste.antall(); i++){
            System.out.println(liste.hent(i));
        }
        liste.forEach(x -> System.out.print(x + " "));
        System.out.println("\n");

    }
    private static void avsnitt325(){
        String[] s = {"Per","Kari","Ole"};

        Liste<String> liste = new TabellListe<>();

        for (String navn : s) liste.leggInn(navn);

        //System.out.println(liste);

        // Utskrift: [Per, Kari, Ole]

        /*
        Iterator<String> i = liste.iterator();     // oppretter en iterator
        System.out.println(i.next());              // den første i listen

        i.remove();                        // fjerner den første
        System.out.println(i.next());              // den neste i listen
        */
        Iterator<String> i = liste.iterator();     // oppretter en iterator i
        Iterator<String> j = liste.iterator();     // oppretter en iterator j

        System.out.println(i.next());              // den første i listen
        i.remove();                                // fjerner den første
        System.out.println(j.next());              // den første i listen

    }

    private static void avsnitt331(){
        System.out.println("---------------------------------------");
        EnkeltLenketListe<Integer> lenketListe = new EnkeltLenketListe<>();
        lenketListe.leggInn(1);
        lenketListe.leggInn(5);
        lenketListe.leggInn(1, 3 );
        System.out.println("Tabell: " + lenketListe.toString() + " Antall: " + lenketListe.antall());
        lenketListe.nullstill();
        System.out.println(lenketListe.toString());
    }

    private static void avsnitt333(){
        System.out.println("---------------------------------------");
        EnkeltLenketListe<Integer> lenketListe = new EnkeltLenketListe<>();
        lenketListe.leggInn(1);
        lenketListe.leggInn(5);
        lenketListe.leggInn(1, 3 );

        System.out.println(lenketListe.toString());
        //lenketListe.fjern(2);
        //lenketListe.fjern(0);
        //System.out.println(lenketListe.hale.verdi);
        //System.out.println(lenketListe.hode.verdi);  bytter hode og hale
    }
    private static void avsnitt334(){
        System.out.println("-----------------------------------------");
        EnkeltLenketListe<Integer> lenketListe = new EnkeltLenketListe<>();
        lenketListe.leggInn(1);
        lenketListe.leggInn(5);
        lenketListe.leggInn(1, 3 );
        Iterator<Integer> i =lenketListe.iterator();
        /*System.out.println(i.next());
        i.remove();
        System.out.println(i.next());*/

        lenketListe.fjernHvis(x -> x.equals(3));
        System.out.println(lenketListe.toString());

        lenketListe.forEach(System.out::println);

        //Oppg. 5
    }
}
