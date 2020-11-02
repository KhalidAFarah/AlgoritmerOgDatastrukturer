package ukeoppgaver.uke9;

import ukeoppgaver.eksempelKlasser.BinTre;
import ukeoppgaver.eksempelKlasser.Oppgave;
import ukeoppgaver.resources.*;

import java.util.Arrays;
import java.util.StringJoiner;

public class Uke9Main {
    public static void main(){
        avsnitt5110();
    }

    private static void avsnitt515(){
        int[] posisjon = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,17,18,19,21,23,26,27,29};
        Character[] verdi = {'A','B','C','D','E','F','G','H','I','J','K',
                'L','M','N','O','P','Q','R','S','T','U','V'};

        BinTre<Character> tre = new BinTre<>(posisjon, verdi);  // den nye konstruktøren

        //Oppgave 2
        //tre 1
        posisjon = new int[]{1, 2, 3, 5, 6, 7, 10, 11, 12, 13, 21, 24, 25, 42, 43};
        verdi = new Character[]{'D','I','H','L','O','B','A','E','N','G','K', 'M','J','F','C'};
        tre = new BinTre<>(posisjon, verdi);
        //tre 2
        posisjon = new int[]{1, 2, 3, 4, 5, 6, 7, 10, 11, 13, 14, 22, 23, 28, 29};
        verdi = new Character[]{'E','I','B','G','A','H','K','L','O','D','N', 'M','C','J','F'};
        tre = new BinTre<>(posisjon, verdi);

        //Oppgave 3
        //Oppgave 4
        /*posisjon = new int[]{5,23,2,10,3,11,47,1,22,44};
        Integer[] verdi2 = {4,8,2,5,3,6,10,1,7,9};
        BinTre<Integer> tre2 = new BinTre<Integer>(posisjon, verdi2);*/

        //Oppgave 5
        BinTre<Integer> tre2 = new BinTre<>();
        for(int i = 1; i <= 15; i++) tre2.leggInn(i,i);

        //Oppgave 6
        tre2 = new BinTre<>();
        int antallGanger = 1;
        for(int i = 1; antallGanger <= 15; i = i * 2){
            tre2.leggInn(i, antallGanger);
            antallGanger++;
        }

        //Oppgave 7
        antallGanger = 1;
        int v = 1;
        int h = 1;
        tre2 = new BinTre<>();
        while (antallGanger <= 15){
            if(v == h){
                tre2.leggInn(v, antallGanger);
            }else{
                tre2.leggInn(v, antallGanger);
                tre2.leggInn(h,antallGanger);
                antallGanger++;
            }
            antallGanger++;
            v = v * 2;
            h = h * 2 + 1;
        }
    }
    private static void avsnitt516(){
        int[] posisjon = {1,2,3,4,5,6,7,10,11,13,14,22,23,28,29};  // posisjoner og
        String[] verdi = "EIBGAHKLODNMCJF".split("");              // verdier i nivåorden

        BinTre<String> tre = new BinTre<>(posisjon, verdi);        // en konstruktør
        tre.nivåorden(Oppgave.konsollutskrift());  // Utskrift: E I B G A H K L O D N M C J F
        tre.nivåorden(c -> System.out.print(c + " "));  // lambda-uttrykk som argument

        System.out.println();
        Character[] verdi2 = {'E','I','B','G','A','H','K','L','O','D','N','M','C','J','F'};
        BinTre<Character> tre2 = new BinTre<>(posisjon, verdi2);
        Liste<Character> liste = new TabellListe<>();  // en liste
        tre2.nivåorden(c2 -> liste.leggInn(c2));          // lambda-uttrykk som argument
        System.out.println(liste);

        tre.nivåorden(Oppgave.konsollutskrift().deretter(c -> System.out.print(' ')));

        //Oppgave 4
        System.out.println();
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        StringJoiner finalJoiner1 = joiner;
        tre2.nivåorden(c -> finalJoiner1.add(c.toString()));
        System.out.println(joiner.toString());

        //Oppgave 5
        joiner = new StringJoiner(", ", "[", "]");
        StringJoiner finalJoiner2 = joiner;
        tre2.nivåorden(c -> {if(c > 'D') finalJoiner2.add(c.toString());});  // lambda-uttrykk som argument
        System.out.println(joiner);

        //Oppgave 8
        joiner = new StringJoiner(", ", "[", "]");
        StringJoiner finalJoiner3= joiner;
        TabellStakk<String> stakk = new TabellStakk<>();
        stakk.leggInn(tre.hent(posisjon[0]));
        tre.nivåorden( c -> {
            if(c.compareTo(stakk.kikk()) > 0){
                stakk.leggInn(c);
            }
        });  // lambda-uttrykk som argument
        System.out.println(stakk.taUt());

        //Oppgave 10
        Integer[] tall = {1, 2, 3, 4, 5, 6, 7};


        BinTre<Integer> testtre = new BinTre<>();
        for(Integer i : tall) testtre.leggInn(i,i);


        int[] nivåer = testtre.nivåer();
        System.out.println(Arrays.toString(nivåer));
        System.out.println("treets høyde:  " + nivåer[Tabell.maks(nivåer)]);
        System.out.println("treets bredde: " + (nivåer.length-1));
    }



    private static void avsnitt517(){
        int[] posisjon = {1,2,3,4,5,6,7,10,11,13,14,22,23,28,29};  // posisjoner og
        String[] verdi = "EIBGAHKLODNMCJF".split("");              // verdier i nivåorden
        BinTre<String> tre = new BinTre<>(posisjon, verdi);

        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        tre.preorden(s -> joiner.add(s));
        System.out.println(joiner.toString());

        StringJoiner joiner2 = new StringJoiner(", ", "[", "]");
        tre.inorden(s -> joiner2.add(s));
        System.out.println(joiner2.toString());

        StringJoiner joiner3 = new StringJoiner(", ", "[", "]");
        tre.postorden(s -> joiner3.add(s));
        System.out.println(joiner3.toString());

        tre.nullstill();
        System.out.println(tre.toString());
    }

    public static void avsnitt5110(){
        int[] posisjon = {1,2,3,4,5,6,7,10,11,13,14,22,23,28,29};  // posisjoner og
        String[] verdi = "EIBGAHKLODNMCJF".split("");              // verdier i nivåorden
        BinTre<String> tre = new BinTre<>(posisjon, verdi);

        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        StringJoiner finalJoiner1 = joiner;
        tre.preorden2(s -> finalJoiner1.add(s));
        System.out.println("Preorden: " + joiner.toString());

        joiner = new StringJoiner(", ", "[", "]");
        StringJoiner finalJoiner2 = joiner;
        tre.inorden2(s -> finalJoiner2.add(s));
        System.out.println("Inorden: " + joiner.toString());

        joiner = new StringJoiner(", ", "[", "]");
        StringJoiner finalJoiner3 = joiner;
        tre.postordenNonRecursiv(s -> finalJoiner3.add(s));
        System.out.println("Postorden non recursive: " + joiner.toString());

        joiner = new StringJoiner(", ", "[", "]");
        StringJoiner finalJoiner4 = joiner;
        tre.postorden(s -> finalJoiner4.add(s));
        System.out.println("Postorden: " + joiner.toString());
    }

    private static void avsnitt5111(){
        int[] posisjon = {1,2,3,4,5,6,7,8,9,10};             // posisjoner og
        String[] verdi = "ABCDEFGHIJ".split("");             // verdier i nivåorden

        BinTre<String> tre = new BinTre<>(posisjon, verdi);  // konstruktør

        //for (String s : tre) System.out.print(s + " ");      // for-alle-løkke
        // Utskrift: H D I B J E A F C G

        tre.forEach(s -> System.out.print(s + " "));
        System.out.println();
        tre.iterator().forEachRemaining(s -> System.out.print(s + " "));

        System.out.println();
        Iterator iterable = tre.preordeniterator();
        System.out.println(iterable.next());
        System.out.println(iterable.next());
        System.out.println(iterable.next());
        System.out.println(iterable.next());
        System.out.println(iterable.next());
    }

    private static void avsnitt5112(){
        BinTre<Character> tre = new BinTre<>();
        tre.leggInn(1, 'A');

        tre.leggInn(2, 'B');
        tre.leggInn(3, 'C');

        tre.leggInn(4, 'D');
        tre.leggInn(5, 'E');
        tre.leggInn(6, 'F');
        tre.leggInn(7, 'G');

        System.out.println("antall bladnoder: " + tre.antallBladnoder());
        System.out.println("maks posisjon: " + tre.maksPos());
    }
}
