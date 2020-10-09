package ukeoppgaver.resources;

import ukeoppgaver.eksempelKlasser.Komparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
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
    public static <T> void bytt(T[] a, int i, int j){
        T temp = a[i];
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

    public static Integer[] randPermInteger(int n)  // en effektiv versjon
    {
        Random r = new Random();         // en randomgenerator
        Integer[] a = new Integer[n];            // en tabell med plass til n tall

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
    // Metoden randPerm(int[] a)                 Programkode 1.1.8 f)
    public static <T> void randPerm(T[] a)  // stokker om a
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
    public static <T> void skriv(T[] a, int fra, int til){
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

    public static <T> void skriv(T[] a){
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
            System.out.print(a[i]);
            if(i != til - 1)
                System.out.print(a[i] + " ");
            else
                System.out.println();
        }
    }

    //skriver ut hele arrayet
    public static void skrivln(int[] a){
        System.out.print(a[0]);
        for(int i = 1; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println();
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

    ///////////////1.3

    public static void snu(int[] a, int v, int h)  // snur intervallet a[v:h]
    {
        while (v < h) bytt(a, v++, h--);
    }

    public static void snu(int[] a, int v)  // snur fra og med v og ut tabellen
    {
        snu(a, v, a.length - 1);
    }

    public static void snu(int[] a)  // snur hele tabellen
    {
        snu(a, 0, a.length - 1);
    }


    public static boolean nestePermutasjon(int[] a)
    {
        int i = a.length - 2;                    // i starter nest bakerst
        while (i >= 0 && a[i] > a[i + 1]) i--;   // går mot venstre
        if (i < 0) return false;                 // a = {n, n-1, . . . , 2, 1}

        int j = a.length - 1;                    // j starter bakerst
        while (a[j] < a[i]) j--;                 // stopper når a[j] > a[i]
        bytt(a,i,j); snu(a,i + 1);               // bytter og snur

        return true;                             // en ny permutasjon
    }
    public static void utvalgssortering(int[] a)
    {
        for (int i = 0; i < a.length - 1; i++)
            bytt(a, i, min(a, i, a.length));  // to hjelpemetoder
    }
    public static void utvalgssortering(int[] a, int fra, int til)
    {
        if(fra < 0 || til <= a.length)
        for (int i = fra; i < til; i++)
            bytt(a, i, min(a, i, til));  // to hjelpemetoder
    }
    public static void utvalgssortering2(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = min(a, i, a.length);
            if (a[i] != a[min])
                bytt(a, i, min);  // to hjelpemetoder

        }
    }
    public static void selectionsort(int[] a){
        int teller = 0;
        for (int i = 0; i < a.length; i++){
            int min = 0;
            for(int j = teller; j < a.length; j++){
                if(a[min] > a[j]){
                    min = j;
                }
            }
            int temp = a[teller];
            a[teller] = a[min];
            a[min] = temp;
            teller++;

        }
    }

    public static int lineærsøk(int[] a, int verdi) // legges i class Tabell
    {
        if (a.length == 0 || verdi > a[a.length-1])
            return -(a.length + 1);  // verdi er større enn den største

        int i = 0; for( ; a[i] < verdi; i++);  // siste verdi er vaktpost

        return verdi == a[i] ? i : -(i + 1);   // sjekker innholdet i a[i]
    }
    public static int lineærsøk(int[] a, int verdi, int k) // legges i class Tabell
    {
        if (a.length == 0 || verdi > a[a.length-1])
            return -(a.length + 1);  // verdi er større enn den største
        if(k < 0)
            throw new IllegalArgumentException("k må være større enn 0");

        int i = 0; for( ; a[i] < verdi; i+= k);  // siste verdi er vaktpost

        return verdi == a[i] ? i : -(i + 1);   // sjekker innholdet i a[i]
    }
    public static int lineærsøkSiste(int[] a, int verdi) // legges i class Tabell
    {
        if (a.length == 0 || verdi > a[a.length-1])
            return -(a.length + 1);  // verdi er større enn den største

        int result = 0;
        for(int i = 0; i < a.length; i++){// siste verdi er vaktpost
            if(a[i] == verdi)
                result = i;
        }

        return result;
    }

    public static int binærsøk(int[] a, int fra, int til, int verdi)
    {
        Tabell.fratilKontroll(a.length,fra,til);  // se Programkode 1.2.3 a)
        int v = fra, h = til - 1;  // v og h er intervallets endepunkter

        while (v <= h)    // fortsetter så lenge som a[v:h] ikke er tom
        {
            int m = (v + h)/2;      // heltallsdivisjon - finner midten
            int midtverdi = a[m];   // hjelpevariabel for midtverdien

            if (verdi == midtverdi) return m;          // funnet
            else if (verdi > midtverdi) v = m + 1;     // verdi i a[m+1:h]
            else  h = m - 1;                           // verdi i a[v:m-1]
        }

        return -(v + 1);    // ikke funnet, v er relativt innsettingspunkt
    }

    public static int binærsøk(int[] a, int verdi)  // søker i hele a
    {
        return binærsøk(a,0,a.length,verdi);  // bruker metoden over
    }

    public static int binærsøkv3(int[] a, int fra, int til, int verdi)
    {
        Tabell.fratilKontroll(a.length,fra,til);  // se Programkode 1.2.3 a)
        int v = fra, h = til - 1;  // v og h er intervallets endepunkter

        while (v < h)  // obs. må ha v < h her og ikke v <= h
        {
            int m = (v + h)/2;  // heltallsdivisjon - finner midten

            if (verdi > a[m]) v = m + 1;   // verdi må ligge i a[m+1:h]
            else  h = m;                   // verdi må ligge i a[v:m]
        }
        if (h < v || verdi < a[v]) return -(v + 1);  // ikke funnet
        else if (verdi == a[v]) return v;            // funnet
        else  return -(v + 2);                       // ikke funnet
    }
    public static void forskyv(int[] a){
        int[] copy = new int[a.length];
        System.arraycopy(a, 0,copy, 0, a.length);

        for(int i = 0; i < a.length-1; i++){
            a[i+1] = copy[i];
        }
        a[0] = copy[copy.length-1];
    }

    public static void innsettingssortering(int[] a)
    {
        for (int i = 1; i < a.length; i++)  // starter med i = 1
        {
            int temp = a[i];  // hjelpevariabel
            for (int j = i - 1; j >= 0 && temp < a[j]; j--) Tabell.bytt(a, j, j + 1);
        }
    }
    public static void innsettingssortering(int[] a, int fra, int til)
    {
        for (int i = fra+1; i < til; i++)  // starter med i = 1
        {
            int temp = a[i];  // hjelpevariabel
            for (int j = i - 1; j >= 0 && temp < a[j]; j--) Tabell.bytt(a, j, j + 1);
        }
    }

    public static void tidForInnsettingOgUtvalgsSortering(){
        int[] a = randPerm(1000);
        long tid = System.currentTimeMillis();
        utvalgssortering(a);
        tid = System.currentTimeMillis()-tid;
        System.out.println("tid for utvalgssortering: " + tid);
        tid = System.currentTimeMillis();
        innsettingssortering(a);
        tid = System.currentTimeMillis()-tid;
        System.out.println("tid for innsettingssortering: " + tid);
    }

    public static int maks(String[] a)    // legges i class Tabell
    {
        int m = 0;                          // indeks til største verdi
        String maksverdi = a[0];            // største verdi

        for (int i = 1; i < a.length; i++) if (a[i].compareTo(maksverdi) > 0)
        {
            maksverdi = a[i];  // største verdi oppdateres
            m = i;             // indeks til største verdi oppdaters
        }
        return m;  // returnerer posisjonen til største verdi
    }
    public static int maks(double[] a)     // legges i class Tabell
    {
        int m = 0;                           // indeks til største verdi
        double maksverdi = a[0];             // største verdi

        for (int i = 1; i < a.length; i++) if (a[i] > maksverdi)
        {
            maksverdi = a[i];     // største verdi oppdateres
            m = i;                // indeks til største verdi oppdaters
        }
        return m;     // returnerer posisjonen til største verdi
    }
    public static int maks(char[] a)     // legges i class Tabell
    {
        int m = 0;                           // indeks til største verdi
        double maksverdi = a[0];             // største verdi

        for (int i = 1; i < a.length; i++) if (a[i] > maksverdi)
        {
            maksverdi = a[i];     // største verdi oppdateres
            m = i;                // indeks til største verdi oppdaters
        }
        return m;     // returnerer posisjonen til største verdi
    }

    public static int maks(Integer[] a)     // legges i class Tabell
    {
        int m = 0;                           // indeks til største verdi
        double maksverdi = a[0];             // største verdi

        for (int i = 1; i < a.length; i++) if (a[i] > maksverdi)
        {
            maksverdi = a[i];     // største verdi oppdateres
            m = i;                // indeks til største verdi oppdaters
        }
        return m;     // returnerer posisjonen til største verdi
    }
    public static <T extends Comparable<? super T>> int maks(T[] a)
    {
        int m = 0;                     // indeks til største verdi
        T maksverdi = a[0];            // største verdi

        for (int i = 1; i < a.length; i++) if (a[i].compareTo(maksverdi) > 0)
        {
            maksverdi = a[i];  // største verdi oppdateres
            m = i;             // indeks til største verdi oppdaters
        }
        return m;  // returnerer posisjonen til største verdi
    } // maks

    public static <T extends Comparable<? super T>> void innsettingssortering(T[] a)
    {
        for (int i = 1; i < a.length; i++)  // starter med i = 1
        {
            T verdi = a[i];        // verdi er et tabellelemnet
            int  j = i - 1;        // j er en indeks
            // sammenligner og forskyver:
            for (; j >= 0 && verdi.compareTo(a[j]) < 0 ; j--) a[j+1] = a[j];

            a[j + 1] = verdi;      // j + 1 er rett sortert plass
        }
    }
    public static <T> void innsettingssortering(T[] a, Komparator<? super T> c)
    {
        for (int i = 1; i < a.length; i++)  // starter med i = 1
        {
            T verdi = a[i];        // verdi er et tabellelemnet
            int  j = i - 1;        // j er en indeks

            // sammenligner og forskyver:
            for (; j >= 0 && c.compare(verdi,a[j]) < 0 ; j--) a[j+1] = a[j];

            a[j + 1] = verdi;      // j + 1 er rett sortert plass
        }
    }
    public static <T> int maks(T[] a, Komparator<? super T> c) {
        T maks = a[0];
        int maksIndex = 0;
        for (int i = 1; i < a.length; i++)  // starter med i = 1
        {
            if(c.compare(maks, a[i]) < 0){
                maks = a[i];
                maksIndex = i;
            }
        }
        return maksIndex;
    }
    public static <T> void innsettingssortering2(T[] a, Comparator<? super T> c)
    {
        for (int i = 1; i < a.length; i++)  // starter med i = 1
        {
            T verdi = a[i];        // verdi er et tabellelemnet
            int  j = i - 1;        // j er en indeks

            // sammenligner og forskyver:
            for (; j >= 0 && c.compare(verdi,a[j]) < 0 ; j--) a[j+1] = a[j];

            a[j + 1] = verdi;      // j + 1 er rett sortert plass
        }
    }
    public static <T> int maksComparator(T[] a, Comparator<? super T> c)
    {
        return maksComparator(a, 0, a.length, c);  // kaller metoden under
    }

    public static <T> int maksComparator(T[] a, int fra, int til, Comparator<? super T> c)
    {
        fratilKontroll(a.length,fra,til);

        if (fra == til) throw new NoSuchElementException
                ("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");

        int m = fra;                // indeks til største verdi
        T maksverdi = a[fra];       // største verdi

        for (int i = fra + 1; i < til; i++)   // går gjennom intervallet
        {
            if (c.compare(a[i],maksverdi) > 0)  // bruker komparatoren
            {
                maksverdi = a[i];     // største verdi oppdateres
                m = i;                // indeks til største verdi oppdateres
            }
        }
        return m;                 // posisjonen til største verdi

    }  // maks

    public static <T> int minComparator(T[] a, int fra, int til, Comparator<? super T> c)
    {
        fratilKontroll(a.length,fra,til);

        if (fra == til) throw new NoSuchElementException
                ("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");

        int m = fra;                // indeks til største verdi
        T minverdi = a[fra];       // største verdi

        for (int i = fra + 1; i < til; i++)   // går gjennom intervallet
        {
            if (c.compare(a[i],minverdi) < 0)  // bruker komparatoren
            {
                minverdi = a[i];     // største verdi oppdateres
                m = i;                // indeks til største verdi oppdateres
            }
        }
        return m;                 // posisjonen til største verdi

    }  // min

    public static <T> int minComparator(T[] a, Comparator<? super T> c)
    {
       return minComparator(a, 0, a.length, c);                // posisjonen til største verdi
    }  // min

    public static <T> void utvalgssorteringComparator(T[] a,  Comparator<? super T> c)
    {
        for (int i = 0; i < a.length - 1; i++)
            bytt(a, i, minComparator(a, i, a.length, c));  // to hjelpemetoder
    }

    public static <T> int binærsøkComparator(T[] a, int fra, int til, T verdi, Comparator<? super T> c)
    {
        Tabell.fratilKontroll(a.length,fra,til);  // se Programkode 1.2.3 a)
        int v = fra, h = til - 1;    // v og h er intervallets endepunkter

        while (v <= h)  // fortsetter så lenge som a[v:h] ikke er tom
        {
            int m = (v + h)/2;     // heltallsdivisjon - finner midten
            T midtverdi = a[m];  // hjelpevariabel for  midtverdien

            if (c.compare(midtverdi, verdi) > 0) v = m + 1;        // verdi i a[m+1:h]
            else if (c.compare(midtverdi, verdi) < 0) h = m - 1;   // verdi i a[v:m-1]
            else return m;                           // funnet
        }

        return -(v + 1);   // ikke funnet, v er relativt innsettingspunkt
    }
    public static <T> int binærsøkComparator(T[] a, T verdi, Comparator<? super T> c)
    {
        return binærsøkComparator(a, 0, a.length, verdi, c);
    }

    private static <T> int parter0(T[] a, int v, int h, T skilleverdi, Comparator<? super T> c)
    {
        while (true)                                  // stopper når v > h
        {
            while (v <= h && c.compare(a[v], skilleverdi) > 0) v++;   // h er stoppverdi for v
            while (v <= h && c.compare(a[v], skilleverdi) < 0) h--;  // v er stoppverdi for h

            if (v < h) bytt(a,v++,h--);                 // bytter om a[v] og a[h]
            else  return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
        }
    }
    private static <T> int sParter0(T[] a, int v, int h, int indeks, Comparator<? super T> c)
    {
        bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
        int pos = parter0(a, v, h - 1, a[h], c);  // partisjonerer a[v:h - 1]
        bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
        return pos;                   // returnerer posisjonen til skilleverdien
    }
    private static <T> void kvikksortering0(T[] a, int v, int h, Comparator<? super T> c)  // en privat metode
    {
        if (v >= h) return;  // a[v:h] er tomt eller har maks ett element
        int k = sParter0(a, v, h, (v + h)/2, c);  // bruker midtverdien
        kvikksortering0(a, v, k - 1, c);     // sorterer intervallet a[v:k-1]
        kvikksortering0(a, k + 1, h, c);     // sorterer intervallet a[k+1:h]
    }

    public static <T> void kvikksortering(T[] a, int fra, int til, Comparator<? super T> c) // a[fra:til>
    {
        fratilKontroll(a.length, fra, til);  // sjekker når metoden er offentlig
        kvikksortering0(a, fra, til - 1, c);  // v = fra, h = til - 1
    }

    public static <T> void kvikksortering(T[] a, Comparator<? super T> c)   // sorterer hele tabellen
    {
        kvikksortering0(a, 0, a.length - 1, c);
    }

    private static <T> void flett(T[] a, T[] b, int fra, int m, int til, Comparator<? super T> c)
    {
        int n = m - fra;                // antall elementer i a[fra:m>
        System.arraycopy(a,fra,b,0,n);  // kopierer a[fra:m> over i b[0:n>

        int i = 0, j = m, k = fra;      // løkkevariabler og indekser

        while (i < n && j < til)        // fletter b[0:n> og a[m:til> og
        {                               // legger resultatet i a[fra:til>
            a[k++] = c.compare(b[i], a[j]) > 0? b[i++] : a[j++];
        }

        while (i < n) a[k++] = b[i++];  // tar med resten av b[0:n>
    }
    private static <T> void flettesortering(T[] a, T[] b, int fra, int til, Comparator<? super T> c)
    {
        if (til - fra <= 1) return;   // a[fra:til> har maks ett element
        int m = (fra + til)/2;        // midt mellom fra og til

        flettesortering(a,b,fra,m, c);   // sorterer a[fra:m>
        flettesortering(a,b,m,til, c);   // sorterer a[m:til>

        if (c.compare(a[m-1], a[m]) > 0) flett(a,b,fra,m,til, c);  // fletter a[fra:m> og a[m:til>
    }
    public static <T> void flettesortering(T[] a, Comparator<? super T> c)
    {
        T[] b = Arrays.copyOf(a, a.length/2);   // en hjelpetabell for flettingen
        flettesortering(a,b,0,a.length, c);          // kaller metoden over
    }

    public static <T> void sorter(Kø<T> kø, Stakk<T> stakk, Comparator<? super T> c) {
        int n = kø.antall();
        int antall = n;
        for (int i = 0; i < kø.antall(); i++) {
            T valgt = kø.taUt();
            for (int j = 1; j < antall; j++) {
                T temp = kø.taUt();
                int k = c.compare(valgt, temp);
                if (k <= 0) {
                    stakk.leggInn(valgt);
                    valgt = temp;
                } else {
                    stakk.leggInn(temp);
                }
            }
            kø.leggInn(valgt);
            while (!stakk.tom()) kø.leggInn(stakk.taUt());
            while (!kø.tom()) stakk.leggInn(kø.taUt());
            while (!stakk.tom()) kø.leggInn(stakk.taUt());
            n--;
        }
    }
    public static <T> void sorter(Stakk<T> A, Comparator<? super T> c){
        Stakk<T> B = new TabellStakk<>();

        int n = A.antall();
        for(int i = 0; i < A.antall(); i++){
            T valgt = A.taUt();
            for(int j = 1; j < n; j++){
                T temp = A.taUt();
                int k = c.compare(valgt, temp);
                if(k <= 0){
                    B.leggInn(valgt);
                    valgt = temp;
                }else{
                    B.leggInn(temp);
                }
            }
            A.leggInn(valgt);
            while (!B.tom()) A.leggInn(B.taUt());
            n--;
        }
    }
}
