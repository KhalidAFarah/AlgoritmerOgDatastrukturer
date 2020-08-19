package ukeoppgaver;

import java.util.Random;

public class Uke1 {
    /*------------------------1.1.2-----------------------*/
    //1. det ville ha returnert tallet 1 som har posisjon 6
    //oppgave  del 2
    public static int min(int[] a)  // a er en heltallstabell
    {
        if (a.length < 1)
            throw new java.util.NoSuchElementException("Tabellen a er tom!");
        int m = 0;  // indeks til foreløpig minste verdi
        for (int i = 1; i < a.length; i++) // obs: starter med i = 1
        {
            if (a[i] < a[m]) m = i;  // indeksen oppdateres
        }
        return m;  // returnerer indeksen/posisjonen til minste verdi
    }
    public static int maks(int[] a)  // deloppgave 3
    {
        if (a.length < 1)
            throw new java.util.NoSuchElementException("Tabellen a er tom!");

        int m = 0;  // indeks til foreløpig største verdi

        for (int i = 1; i < a.length; i++) // obs: starter med i = 1
        {
            if (a[i] >= a[m]) m = i;  // endrer til >= istedet for > slik at hvis en ny verdi er det
            // samme som den største oppdateres m til den nyligste
        }

        return m;  // returnerer indeksen/posisjonen til største verdi

    }
    /*------------------------1.1.3-----------------------*/
    //deloppgave 5
    public int[] minmaks(int[] a) {
        int[] b = new int[2];
        if(a.length == 2){
            if(a[0] > a[1]){
                b = new int[]{a[1], a[0]};
            }else if(a[1] > a[0]) {
                b = new int[]{a[0], a[1]};
            }
        }
        return b;
    }

    //deloppgave 6
    public long fak(int n){
        /*if(n > 0)
        return n*fak(n-1);
        else
            return 1;*/
        int teller = 0;
        int sum = n;
        for(int i = n-1; i > 0; i--){
            teller++;
            sum = sum * i;
        }
        System.out.println("antall multiplikasjoner: " + teller + " " + n + "! er: " + sum);
        return sum;
    }
    /*------------------------1.1.4-----------------------*/
    //i)
    /*
    public static int maks(int[] a) [10, 5, 7, 2, 9, 1, 3, 8, 4, 6]
    {
        int m = 0;               //1 operasjon
        int maksverdi = a[0];    //2 operasjoner

        for (int i = 1; i < a.length; i++) 1 + 10 + 9 = 20 operasjoner
        if (a[i] > maksverdi) // 9 operasjoner
        {
            maksverdi = a[i];     // 2 * 0 = 0 operasjoner
            m = i;                // 1 * 0 = 0 operasjoner
        }
        return m;   //

    } // totalt 32 operasjoner
    */

    //ii)
    /*
    public static int maks(int[] a) [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    {
        int m = 0;               1 operasjon
        int maksverdi = a[0];    2 operasjoner

        for (int i = 1; i < a.length; i++) 1 + 10 + 9 = 20 operasjoner
        if (a[i] > maksverdi) //9 operasjoner
        {
            maksverdi = a[i];     // 2 * 9 = 18 operasjoner
            m = i;                // 1 * 9 = 9 operasjoner
        }
        return m;   //

    } // totalt 58 opprasjoner
    */
    /*------------------------1.1.5-----------------------*/
    public static int maks115(int[] a)  // versjon 3 av maks-metoden
    {
        int sist = a.length - 1;       // siste posisjon i tabellen
        int m = 0;                     // indeks til største verdi
        int maksverdi = a[0];          // største verdi
        int temp = a[sist];            // tar vare på siste verdi
        a[sist] = 0x7fffffff;          // legger tallet 2147483647 sist

        for (int i = 0; ; i++)         // mangler en sammenligning
            if (a[i] >= maksverdi)       // denne blir sann til slutt
            {
                if (i == sist)             // sjekker om vi er ferdige
                {
                    a[sist] = temp;          // legger siste verdi tilbake
                    return temp >= maksverdi ? sist : m;   // er siste størst?  if - setning i en return setning
                    //hvis temp er større eller lik maksverdi returner sist hvis ikke returner m
                }
                else
                {
                    maksverdi = a[i];        // maksverdi oppdateres
                    m = i;                   // m oppdateres
                }
            }
    } // maks

    //dersom int[] a er en tom array for vi en out of bounds exception fra a[sist]
    //dersom int[] a har et element så kjøres for løkken en gang og går inn i
    // if setningen if(i == sist) og returnere sist

    /*------------------------1.1.10-----------------------*/

    public static void bytt(int[] a, int i, int j)
    {
        int temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

    public static int[] randPerm(int n)  // en versjon som ikke virker
    {
        Random r = new Random();      // en randomgenerator
        int[] a = new int[n];         // en tabell med plass til n tall

        for (int i = 0; i < n; i++)
            a[i] = r.nextInt(n) + 1;    // tabellen fylles med tall

        return a;                     // tabellen returneres
    }

    public static int kostnader(int[] a)  // legges i class Program
    {
        int m = 0;
        for (int i = 1; i < a.length; i++) {}  // en tom blokk
        return m;
    }

    public static int maks2(int[] a)   // versjon 2 av maks-metoden
    {
        int m = 0;               // indeks til største verdi
        int maksverdi = a[0];    // største verdi

        for (int i = 1; i < a.length; i++) if (a[i] > maksverdi)
        {
            maksverdi = a[i];     // største verdi oppdateres
            m = i;                // indeks til største verdi oppdateres
        }
        return m;   // returnerer indeks/posisjonen til største verdi

    } // maks

    public void time() {
        // main-metoden i class Program skal nå inneholde:
        int n = 100_000, antall = 2_000; // tabellstørrelse og gjentagelser
        long tid = 0;                    // for tidsmåling
        int a[] = randPerm(n);           // en permutasjon av 1, . .  n

        tid = System.currentTimeMillis();    // leser av klokken
        for (int i = 0; i < antall; i++) kostnader(a);
        tid = System.currentTimeMillis() - tid;    // medgått tid
        System.out.println("Faste kostnader: " + tid + " millisek");

        tid = System.currentTimeMillis();    // leser av klokken
        for (int i = 0; i < antall; i++) maks(a);  // Programkode 1.1.2
        tid = System.currentTimeMillis() - tid;     // medgått tid
        System.out.println("Maks1-metoden: " + tid + " millisek");

        tid = System.currentTimeMillis();    // leser av klokken
        for (int i = 0; i < antall; i++) maks2(a);  // Programkode 1.1.4
        tid = System.currentTimeMillis() - tid;     // medgått tid
        System.out.println("Maks2-metoden: " + tid + " millisek");

        tid = System.currentTimeMillis();    // leser av klokken
        for (int i = 0; i < antall; i++) maks115(a);  // Programkode 1.1.5
        tid = System.currentTimeMillis() - tid;     // medgått tid
        System.out.println("Maks3-metoden: " + tid + " millisek");
        //maks 2 og 3 er de raskeste men bytter vanligvis mellom hverandre
        // der noen kompileringer er maks 2 raskere enn 3 og andre 3 raskere enn 2
    }
}
