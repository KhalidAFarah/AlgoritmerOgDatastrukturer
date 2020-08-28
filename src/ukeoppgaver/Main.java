package ukeoppgaver;

import ukeoppgaver.uke2.*;

import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args){
        System.out.println("----------------------Uke 1-------------------------");
        Uke1 u1 = new Uke1();
        u1.time();

        System.out.println("----------------------Uke 2-------------------------");
        Oppgave122 O = new Oppgave122();
        //O.Oppgave2();


        /*-------------------------------------*/
        int[] i = {1,23,2,1};
        System.out.println(i.length);
        Tabell.maks(i, 0, 3);
        Tabell.skriv(i, 0, 3);

        /*------------------------------------*/

        int[] a = Tabell.randPerm(20); // tilfeldig permutasjon av 1 . . 20
        int[] b = Tabell.nestMaks3(a);  // metoden returnerer en tabell

        int m = b[0], nm = b[1];       // m for maks, nm for nestmaks
        System.out.println();
        Tabell.skrivln(b);        // se Oppgave 5 i Avsnitt 1.2.2
        System.out.print("Størst(" + a[m] + ") har posisjon " + m);
        System.out.println(", nest størst(" + a[nm] + ") har posisjon " + nm);

        // Eksempel på en utskrift:

        // 12 16 15 6 10 8 9 2 14 19 5 18 20 13 3 7 11 1 4 17
        // Størst(20) har posisjon 12, nest størst(19) har posisjon 9

        Oppgave1213 oppgave1213 = new Oppgave1213();
        int[] test = oppgave1213.nestMaks(i);

        System.out.println("størst: " + test[0] + " nest størst: " + test[1]);

        int[] test1 = new int[]{3, 2, 1};
        int[] test2 = new int[]{1, 2, 3, 4 ,5, 6, 7, 8};
        System.out.println("--------------------------------");
        Tabell.skriv(test2);
        System.out.println();
        Oppgave1213.kopier2(test1, test2, test.length);

        Tabell.skriv(test2);


    }
}
