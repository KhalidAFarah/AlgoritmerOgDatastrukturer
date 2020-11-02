package ukeoppgaver.uke10;

import ukeoppgaver.resources.Iterator;
import ukeoppgaver.resources.SBinTre;

import java.util.Comparator;
import java.util.stream.Stream;

public class Uke10Main {
    public static void main(){
        avsnitt522();
        avsnitt523();
        avsnitt525();
        avsnitt527();
        avsnitt528();
        avsnitt529();
        avsnitt531();
    }

    private static void avsnitt522(){
        SBinTre<Integer> tre1 = SBinTre.sbintre();          // 1. konstruksjonsmetode

        Comparator<Integer> c = Comparator.naturalOrder();
        SBinTre<Integer> tre2 = SBinTre.sbintre(c);         // 2. konstruksjonsmetode

        System.out.println(tre1.antall() + " " + tre2.antall());

        // Utskrift: 0 0
    }

    private static void avsnitt523(){
        System.out.println("------------------");

        SBinTre<Character> tre = SBinTre.sbintre();
        //Nivå 0
        tre.leggInn('I');
        //Nivå 1
        tre.leggInn('E');
        tre.leggInn('O');
        //Niva 2
        tre.leggInn('A');
        tre.leggInn('G');
        tre.leggInn('M');
        //Niva 3
        tre.leggInn('C');
        tre.leggInn('F');
        tre.leggInn('H');
        tre.leggInn('K');
        tre.leggInn('N');
        //Nivå 4
        tre.leggInn('B');
        tre.leggInn('D');
        tre.leggInn('J');
        tre.leggInn('L');

        System.out.println("antall: " + tre.antall() + ", høyde: " + tre.høyde()  + ", treet: " + tre.toString());

        Character[] chars = {'E', 'H', 'B', 'E', 'G', 'F', 'D', 'I', 'H', 'A', 'E', 'C'};
        tre = SBinTre.sbintre(Stream.of(chars));

        System.out.println("det nye treet: " + tre);

    }
    private static void avsnitt525(){
        System.out.println("--------------------");
        //Oppgave 1
        SBinTre<String> tre = SBinTre.balansert("ABCDDEFFGH".split(""));
        System.out.println("Antall: " + tre.antall() + ", Høyde: " + tre.høyde() + ", treet: " + tre);
        //Oppgave 4
        tre = SBinTre.balansert("ABCDEFGHIJKLMNO".split(""));
        System.out.println("Antall: " + tre.antall() + ", Høyde: " + tre.høyde() + ", treet: " + tre);
        //Oppgave 6
        tre = SBinTre.balansert("AAAAABBBBB".split(""));
        System.out.println("Antall: " + tre.antall() + ", Høyde: " + tre.høyde() + ", treet: " + tre);
    }

    private static void avsnitt527(){
        System.out.println("-----------------");
       //Oppgave 3
        Integer[] ints = {1,3,3,3,5,6,7,8,9};
        SBinTre<Integer> tre = SBinTre.balansert(ints);

        System.out.println(tre.gulv(2));
    }
    private static void avsnitt528(){
        System.out.println("----------------");
        //Oppgave 1
        Character[] chars = {'H', 'J', 'C', 'F', 'D', 'M', 'A', 'I', 'E', 'K', 'G', 'L', 'B'};
        SBinTre<Character> tre = SBinTre.sbintre(Stream.of(chars));
        System.out.println(tre);
        tre.fjern('C');
        tre.fjern('A');
        tre.fjern('H');
        tre.fjern('J');
        tre.fjern('L');
        System.out.println(tre);

        //Oppgave 2
        Integer[] ints = {4, 1, 8, 5, 3, 10, 7, 2, 6, 9};
        SBinTre<Integer> tre2 = SBinTre.sbintre(Stream.of(ints));
        System.out.println(tre2);
        tre2.fjern(4);
        tre2.fjern(7);
        tre2.fjern(3);
        tre2.fjern(8);
        System.out.println(tre2);

        //Oppgave 3
        tre.nullstill();
        tre2.nullstill2();
        System.out.println(tre);
        System.out.println(tre2);
    }
    private static void avsnitt529(){
        System.out.println("-------------------------");
        Integer[] a = {2,8,6,1,7,4,3,9,5,10};                  // verdier
        SBinTre<Integer> tre = SBinTre.sbintre(Stream.of(a));  // Programkode 5.2.3 c)

        System.out.println(tre);
        tre.fjernMin();
        System .out.println(tre);

        tre.leggInn(6);  // en innlegging er en endring
        Iterator<Integer> i = tre.iterator();      // en iterator er opprettet
        System.out.println(i.next());        // kaster en ConcurrentModificationException

        SBinTre<String> tre2 = SBinTre.sbintre();
        tre2.leggInn("B");
        tre2.leggInn("A");
        tre2.leggInn("C");
        tre = SBinTre.balansert(a);
        System.out.println(tre2);
        Iterator<String> iterator = tre2.riterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        //System.out.println(iterator.next());
    }
    private static void avsnitt531(){

    }
}
