package ukeoppgaver.uke2;

import java.util.Arrays;
import java.util.Random;

public class Tabell { // Samleklasse for tabellmetoder
    private Tabell() {}// privat standardkonstruktør - hindrer instansiering

    // Metoden bytt(int[] a, int i, int j)       Programkode 1.1.8 d)
    public static void bytt(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //bytt metode for chars oppgave 3
    public static void bytt(char[] a, int i, int j){
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // Metoden randPerm(int n)                   Programkode 1.1.8 e)
    public static int[] randPerm(int n)  // en effektiv versjon
    {
        Random r = new Random();         // en randomgenerator
        int[] a = new int[n];            // en tabell med plass til n tall

        Arrays.setAll(a, i -> i + 1);    // legger inn tallene 1, 2, . , n

        for (int k = n - 1; k > 0; k--)  // løkke som går n - 1 ganger
        {
            int i = r.nextInt(k+1);        // en tilfeldig tall fra 0 til k
            bytt(a,k,i);                   // bytter om
        }

        return a;                        // permutasjonen returneres
    }
    // Metoden randPerm(int[] a)                 Programkode 1.1.8 f)
    public static void randPerm(int[] a)  // stokker om a
    {
        Random r = new Random();     // en randomgenerator

        for (int k = a.length - 1; k > 0; k--)
        {
            int i = r.nextInt(k + 1);  // tilfeldig tall fra [0,k]
            bytt(a,k,i);
        }
    }

    // Metoden maks(int[] a, int fra, int til)   Programkode 1.2.1 b)
    public static int maks(int[] a, int fra, int til)
    {
        if(a != null) {
            if (fra < 0 || til > a.length || fra >= til) {
                throw new IllegalArgumentException("Illegalt intervall!");
            }

            int m = fra;              // indeks til største verdi i a[fra:til>
            int maksverdi = a[fra];   // største verdi i a[fra:til>

            for (int i = fra + 1; i < til; i++) {
                if (a[i] > maksverdi) {
                    m = i;                // indeks til største verdi oppdateres
                    maksverdi = a[m];     // største verdi oppdateres
                }
            }

            return m;  // posisjonen til største verdi i a[fra:til>
        }else
            throw new IllegalArgumentException("Illegalt array");
    }
    // Metoden maks(int[] a)                     Programkode 1.2.1 c)
    public static int maks(int[] a)  // bruker hele tabellen
    {
        return maks(a,0,a.length);     // kaller metoden over
    }

    // min-metodene - se Oppgave 1 i Avsnitt 1.2.1
    public static int min(int[] a, int fra, int til)
    {
        if (fra < 0 || til > a.length || fra >= til)
        {
            throw new IllegalArgumentException("Illegalt intervall!");
        }

        int m = fra;              // indeks til største verdi i a[fra:til>
        int minimumverdi = a[fra];   // største verdi i a[fra:til>

        for (int i = fra + 1; i < til; i++)
        {
            if (a[i] < minimumverdi)
            {
                m = i;
                minimumverdi = a[m];
            }
        }

        return m;  // posisjonen til største verdi i a[fra:til>
    }
    public static int min(int[] a)  // bruker hele tabellen
    {
        return min(a,0,a.length);     // kaller metoden over
    }

    //Oppgave 4 en metode for som skriver ut deler av en liste fra og med a til b der a er fra og b er til
    public static void skriv(int[] a, int fra, int til){
        System.out.print(a[0]);
        for(int i = 1; i < a.length; i++){
            System.out.print(" " + a[i]);
        }
    }

    //skriver ut hele arrayet
    public static void skriv(int[] a){
        fratilKontroll(a.length, 0, a.length);
        System.out.print(a[0]);
        for(int i = 1; i < a.length; i++){
            System.out.print(" " + a[i]);
        }
    }

    //Oppgave 5 en metode for som skriver ut deler av en liste fra og med a til b der a er fra og b er til
    public static void skrivln(int[] a, int fra, int til){
        fratilKontroll(a.length, fra, til);
        for(int i = fra; i < til; i++){
            System.out.print(i);
            if(i != til - 1)
                System.out.print(" ");
            else
                System.out.println();
        }
    }

    //skriver ut hele arrayet
    public static void skrivln(int[] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(i);
            if(i != a.length - 1)
                System.out.print(" ");
            else
                System.out.println();
        }
    }

    public static void fratilKontroll(int tablengde, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new ArrayIndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > tablengde)                          // til er utenfor tabellen
            throw new ArrayIndexOutOfBoundsException
                    ("til(" + til + ") > tablengde(" + tablengde + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public static void vhKontroll(int tablengde, int v, int h)
    {
        if (v < 0)
            throw new ArrayIndexOutOfBoundsException("v(" + v + ") < 0");

        if (h >= tablengde)
            throw new ArrayIndexOutOfBoundsException
                    ("h(" + h + ") >= tablengde(" + tablengde + ")");

        if (v > h + 1)
            throw new IllegalArgumentException
                    ("v = " + v + ", h = " + h);
    }

    public static int[] nestMaks(int[] a)  // legges i class Tabell
    {
        int n = a.length;   // tabellens lengde

        if (n < 2) throw   // må ha minst to verdier!
                new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        int m = maks(a);  // m er posisjonen til tabellens største verdi

        int nm;           // nm skal inneholde posisjonen til nest største verdi

        if (m == 0)                            // den største ligger først
        {
            nm = maks(a, 1, n);                  // leter i a[1:n>
        }
        else if (m == n - 1)                   // den største ligger bakerst
        {
            nm = maks(a, 0, n - 1);              // leter i a[0:n-1>
        }
        else
        {
            int mv = maks(a, 0, m);              // leter i a[0:m>
            int mh = maks(a, m + 1, n);          // leter i a[m+1:n>
            nm = a[mh] > a[mv] ? mh : mv;        // hvem er størst?
        }

        return new int[] {m,nm};      // m i posisjon 0 , nm i posisjon 1

    } // nestMaks

    public static int[] nestMaks2(int[] a)  // legges i class Tabell
    {
        int n = a.length;   // tabellens lengde

        if (n < 2) throw   // må ha minst to verdier!
                new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        for(int i = 0; i < a.length; i++){
            int m = maks(a, i, a.length);
            int temp = a[i];
            a[i] = a[m];
            a[m] = temp;
        }
        return a;
    }
    public static int[] nestMaks2Omvendt(int[] a)  // legges i class Tabell
    {
        int n = a.length;   // tabellens lengde

        if (n < 2) throw   // må ha minst to verdier!
                new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        for(int i = a.length - 1; i >= 0; i--){
            int m = maks(a, i, a.length);
            int temp = a[a.length - i];
            a[a.length - i] = a[m];
            a[m] = temp;
        }


        return a;
    }

    public static int[] nestMaks3(int[] a) // ny versjon
    {
        int n = a.length;     // tabellens lengde
        if (n < 2) throw      // må ha minst to verdier
                new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        int m = 0;      // m er posisjonen til største verdi
        int nm = 1;     // nm er posisjonen til nest største verdi

        // bytter om m og nm hvis a[1] er større enn a[0]
        if (a[1] > a[0]) { m = 1; nm = 0; }

        int maksverdi = a[m];                // største verdi
        int nestmaksverdi = a[nm];           // nest største verdi

        for (int i = 2; i < n; i++)
        {
            if (a[i] > nestmaksverdi)
            {
                if (a[i] > maksverdi)
                {
                    nm = m;
                    nestmaksverdi = maksverdi;     // ny nest størst

                    m = i;
                    maksverdi = a[m];              // ny størst
                }
                else
                {
                    nm = i;
                    nestmaksverdi = a[nm];         // ny nest størst
                }
            }
        } // for

        return new int[] {m,nm};    // n i posisjon 0, nm i posisjon 1

    } // nestMaks

    public static int[] nestMin(int[] a)  // legges i class Tabell
    {
        int n = a.length;   // tabellens lengde

        if (n < 2) throw   // må ha minst to verdier!
                new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        int m = min(a);  // m er posisjonen til tabellens største verdi

        int nm;           // nm skal inneholde posisjonen til nest største verdi

        if (m == 0)                            // den største ligger først
        {
            nm = min(a, 1, n);                  // leter i a[1:n>
        }
        else if (m == n - 1)                   // den største ligger bakerst
        {
            nm = min(a, 0, n - 1);              // leter i a[0:n-1>
        }
        else
        {
            int mv = min(a, 0, m);              // leter i a[0:m>
            int mh = min(a, m + 1, n);          // leter i a[m+1:n>
            nm = a[mh] < a[mv] ? mh : mv;        // hvem er størst?
        }

        return new int[] {m,nm};      // m i posisjon 0 , nm i posisjon 1

    } // nestMaks
}
