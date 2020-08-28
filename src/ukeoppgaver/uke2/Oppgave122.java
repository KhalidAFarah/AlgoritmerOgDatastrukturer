package ukeoppgaver.uke2;

public class Oppgave122 {
    /*------------------------1.2.2------------------------*/
    //Oppgave 1 lagde Tabell klassen
    //Oppgave 2
    public static void Oppgave2(){
        int[] a = Tabell.randPerm(20);              // en tilfeldig tabell
        for (int k : a) System.out.print(k + " ");  // skriver ut a

        int m = Tabell.maks(a);   // finner posisjonen til største verdi

        System.out.println("\nStørste verdi ligger på plass " + m);
    }
    //Oppgave 3 la til bytt metoden i Tabell klassen
    //Oppgave 4

}
