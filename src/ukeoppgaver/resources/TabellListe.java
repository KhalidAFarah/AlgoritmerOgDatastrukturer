package ukeoppgaver.resources;

import java.util.*;
import java.util.function.Consumer;

public class TabellListe<T> implements Liste<T> {
    private T[] a;
    private int antall;
    private int endringer;    // ny variabel

    // konstruktører og metoder kommer her
    @SuppressWarnings("unchecked")          // pga. konverteringen: Object[] -> T[]
    public TabellListe(int størrelse)       // konstruktør
    {
        a = (T[])new Object[størrelse];       // oppretter tabellen
        antall = 0;                           // foreløpig ingen verdier
    }
    public TabellListe(T[] b)                    // en T-tabell som parameter
    {
        this(b.length);                            // kaller den andre konstruktøren

        for (T verdi : b)
        {
            if (verdi != null) a[antall++] = verdi;  // hopper over null-verdier
        }
    }

    public TabellListe()                    // standardkonstruktør
    {
        this(10);                             // startstørrelse på 10
    }


    @Override
    public boolean leggInn(T verdi)  // inn bakerst
    {
        Objects.requireNonNull(verdi, "null er ulovlig!");

        // En full tabell utvides med 50%
        if (antall == a.length)
        {
            a = Arrays.copyOf(a,(3*antall)/2 + 1);
        }

        a[antall++] = verdi;   // setter inn ny verdi

        endringer++;

        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi)
    {
        Objects.requireNonNull(verdi, "null er ulovlig!");

        indeksKontroll(indeks, true);  // true: indeks = antall er lovlig

        // En full tabell utvides med 50%
        if (antall == a.length) a = Arrays.copyOf(a,(3*antall)/2 + 1);

        // rydder plass til den nye verdien
        System.arraycopy(a, indeks, a, indeks + 1, antall - indeks);

        a[indeks] = verdi;     // setter inn ny verdi

        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);   // false: indeks = antall er ulovlig
        return a[indeks];
    }

    @Override
    public int indeksTil(T verdi) {
        for (int i = 0; i < antall; i++)
        {
            if (a[i].equals(verdi)) return i;   // funnet!
        }
        return -1;   // ikke funnet!
    }

    @Override
    public T oppdater(int indeks, T verdi) {
        if(indeks < a.length){
            T temp = a[indeks];
            a[indeks] = verdi;

            endringer++;
            return temp;
        }
        return null;
    }

    @Override
    public boolean fjern(T verdi) {
        for(int i = 0; i < a.length; i++){
            if(a[i] == verdi){
                a[i] = null;
                endringer++;
                return true;
            }
        }
        return false;
    }

    @Override
    public T fjern(int indeks) {
        if(indeks < a.length) {
            T temp = a[indeks];
            a[indeks] = null;
            endringer++;
            return temp;
        }
        return null;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public void nullstill() {
        if(a.length > 10) {
            a = (T[]) new Object[10];
        }
      else{
          for(int i = 0; i < a.length; i++){
              a[i] = null;
          }
        }
        endringer++;
    }

    private class TabellListeIterator implements Iterator<T>
    {
        private int denne = 0;       // instansvariabel
        private boolean removeOK = false;   // ny instansvariabel i TabellListeIterator
        private int iteratorendringer = endringer;  // ny variabel

        public boolean hasNext()     // sjekker om det er flere igjen
        {
            return denne < antall;     // sjekker verdien til denne
        }

        @Override
        public T next()
        {
            if (iteratorendringer != endringer)
            {
                throw new ConcurrentModificationException("Listen er endret!");
            }

            if (!hasNext())
                throw new NoSuchElementException("Tomt eller ingen verdier igjen!");

            T denneVerdi = a[denne];   // henter aktuell verdi
            denne++;                   // flytter indeksen
            removeOK = true;           // nå kan remove() kalles

            return denneVerdi;         // returnerer verdien
        }

        @Override
        public void remove()
        {
            if (iteratorendringer != endringer) throw new
                    ConcurrentModificationException("Listen er endret!");

            if (!removeOK) throw new IllegalStateException("Ulovlig tilstand!");

            removeOK = false;          // remove() kan ikke kalles på nytt

            // verdien i denne - 1 skal fjernes da den ble returnert i siste kall
            // på next(), verdiene fra og med denne flyttes derfor en mot venstre

            antall--;           // en verdi vil bli fjernet
            denne--;            // denne må flyttes til venstre

            System.arraycopy(a, denne + 1, a, denne, antall - denne);  // tetter igjen

            a[antall] = null;   // verdien som lå lengst til høyre nulles

            endringer++;
            iteratorendringer++;
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action){
            for(T t: a){
                if(t != null)
                    action.accept(t);
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("[");
        boolean first = true;
        if(a.length > 0)


        for(int i = 0; i < a.length; i++){
            if(a[i] != null) {
                if (first) {
                    s.append(a[i]);
                    first = false;
                } else {
                    s.append(", " + a[i]);
                }
            }
        }
        s.append("]");
        return s.toString();
    }
    @Override
    public Iterator<T> iterator() {
        return new TabellListeIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action){
        TabellListeIterator tabellListeIterator = new TabellListeIterator();
        tabellListeIterator.forEachRemaining(action);
    }
}