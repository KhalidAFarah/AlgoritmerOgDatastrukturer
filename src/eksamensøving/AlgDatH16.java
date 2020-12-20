package eksamensøving;

import Oblig.Oblig2.DobbeltLenketListe;
import Oblig.Oblig2.Liste;
import ukeoppgaver.resources.Stakk;
import ukeoppgaver.resources.Tabell;
import ukeoppgaver.resources.TabellKø;
import ukeoppgaver.resources.TabellStakk;

import java.util.Arrays;
import java.util.Comparator;

public class AlgDatH16 {
    public static void main(String[] args){
        oppgave1();
        oppgave2();
        oppgave3();
        oppgave4();
    }

    private static void oppgave1() {
        //Oppgave 1
        char[] c = "AbaAcBbAAaCCbcAB".toCharArray();
        int antall = omorganiser(c);
        System.out.println("A > a: " + ('Z' > 'a'));
        System.out.println(antall + "  " + Arrays.toString(c));
        // Utskrift: 7  [c, b, a, b, c, a, b, A, A, B, C, C, A, A, A, B]
        System.out.println("----------------------------");
    }

    private static void oppgave2(){
        //Oppgave 2
        Character[] tegn1 = {'A','B','C'}, tegn2 = {'A','B','D'};
        Integer[] tall1 = {1,2,3,4,5}, tall2 = {1,2,3,4};

        Liste<Character> a = new DobbeltLenketListe<>(tegn1);  // A,B,C
        Liste<Character> b = new DobbeltLenketListe<>(tegn2);  // A,B,D

        Liste<Integer> c = new DobbeltLenketListe<>(tall1);    // 1,2,3,4,5
        Liste<Integer> d = new DobbeltLenketListe<>(tall2);    // 1,2,3,4

        int cmp1 = compare(a, b, Comparator.naturalOrder());  // cmp1 skal bli negativ
        int cmp2 = compare(c, d, Comparator.naturalOrder());  // cmp2 skal bli positiv

        System.out.println("cmp1: " + cmp1);
        System.out.println("cmp2: " + cmp2);
        System.out.println("------------------------------------");
    }

    private static void oppgave3(){
        Stakk<String> a = new TabellStakk<>(), b = new TabellStakk<>();
        a.leggInn("C"); a.leggInn("B"); a.leggInn("A");
        System.out.println(a + " " + b);  // utskrift: [A, B, C] []
        omvendtkopi(a,b);
        System.out.println(a + " " + b);  // utskrift: [A, B, C] [C, B, A]
        System.out.println("-----------------------------------");
    }

    private static void oppgave4(){

    }

    public static int omorganiser(char[] c){//O(n)
        int left = 0;
        int right = c.length-1;
        int antallOmbyttinger = 0;
        boolean leftReady = false;
        boolean rightReady = false;
        while(left <= right){
            if(c[left] < 'a' && !leftReady){
                leftReady = true;
            }else if(!leftReady){
                left++;
            }

            if(c[right] >= 'a' && !rightReady){
                rightReady = true;
            }else if(!rightReady){
                right--;
            }

            if(leftReady && rightReady){
                antallOmbyttinger++;
                char temp = c[left];
                c[left] = c[right];
                c[right] = temp;
                left++;
                right--;

                rightReady = false;
                leftReady = false;
            }
        }
        return antallOmbyttinger;
    }

    public static <T> int compare(Liste<T> a, Liste<T> b, Comparator<? super T> comp){
        int større = 0;
        int mindre = 0;

        if(a.antall() > b.antall()) return 1;
        if(a.antall() < b.antall()) return -1;

        for(int i = 0; i < a.antall(); i++){
            int cmp = comp.compare(a.hent(i), b.hent(i));
            if(cmp > 0) større++;
            else if(cmp < 0) mindre++;
        }

        if(større == 0 && mindre == 0) return 0;

        return større > mindre ? 1 : -1;

        /*eller
        * return a.toString().compareTo(b.toString())
        * */
    }

    public static <T> void omvendtkopi(Stakk<T> a, Stakk<T> b){
        TabellKø<T> kø = new TabellKø<>();
        while(!a.tom()){
            kø.leggInn(a.taUt());
        }

        while (!kø.tom()){
            T temp = kø.taUt();
            a.leggInn(temp);
            b.leggInn(temp);
        }
    }
}
