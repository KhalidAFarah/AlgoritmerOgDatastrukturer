package ukeoppgaver.resources;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class LenketHashTabell<T> implements Beholder<T>
{
    private static class Node<T>      // en indre nodeklasse
    {
        private final T verdi;          // nodens verdi
        private final int hashverdi;    // lagrer hashverdien
        private Node<T> neste;          // peker til neste node

        private Node(T verdi, int hashverdi, Node<T> neste)  // konstruktør
        {
            this.verdi = verdi;
            this.hashverdi = hashverdi;
            this.neste = neste;
        }
    } // class Node

    private Node<T>[] hash;           // en nodetabell
    private final float tetthet;      // eng: loadfactor
    private int grense;               // eng: threshold (norsk: terskel)
    private int antall;               // antall verdier

    @SuppressWarnings({"rawtypes","unchecked"})  // en annotasjon
    public LenketHashTabell(int dimensjon)       // konstruktør
    {
        if (dimensjon < 0) throw new IllegalArgumentException("Negativ dimensjon!");

        hash = new Node[dimensjon];                // bruker raw type
        tetthet = 0.75f;                           // maksimalt 75% full
        grense = (int)(tetthet * hash.length);     // gjør om til int
        antall = 0;                                // foreløpig ingen verdier
    }

    public LenketHashTabell()  // standardkonstruktør
    {
        this(13);  // velger 13 som startdimensjon inntil videre
    }

    public int antall()
    {
        return antall;
    }

    public boolean tom()
    {
        return antall == 0;
    }

    // flere metoder skal inn her
    public boolean leggInn(T verdi)
    {
        Objects.requireNonNull(verdi, "verdi er null!");

        if (antall >= grense)
        {
            // her skal metoden utvid() kalles, men det tas opp senere
            utvid();
        }

        int hashverdi = verdi.hashCode() & 0x7fffffff;  // fjerner fortegn
        int indeks = hashverdi % hash.length;           // finner indeksen

        // legger inn først i listen som hører til indeks
        hash[indeks] = new Node<>(verdi, hashverdi, hash[indeks]);  // lagrer hashverdi

        antall++;        // en ny verdi
        return true;     // vellykket innlegging
    }

    public String toString()
    {
        StringJoiner s = new StringJoiner(", ", "[", "]");

        for (Node<T> p : hash)              // går gjennom tabellen
        {
            for (; p != null; p = p.neste)    // går gjennom listen
            {
                s.add(p.verdi.toString());
            }
        }
        return s.toString();
    }

    public boolean inneholder(T verdi){
        int hashverdi = verdi.hashCode() & 0x7fffffff;  // fjerner fortegn
        int indeks = hashverdi % hash.length;           // finner indeksen
        Node<T> p = hash[indeks];

        while(p != null){
            if(p.verdi == verdi) return true;
            p = p.neste;
        }
        return false;
    }

    public boolean fjern(T verdi){
        int hashverdi = verdi.hashCode() & 0x7fffffff;  // fjerner fortegn
        int indeks = hashverdi % hash.length;           // finner indeksen


        Node<T> p = hash[indeks];
        Node<T> q = null;
        while (p != null){
            if(p.verdi == verdi){
                if(q == null){
                    hash[indeks] = p.neste;
                }

                q.neste = p.neste;
                antall--;
                return true;
            }

            q = p;
            p = p.neste;
        }
        return false;
    }

    public void nullstill(){
        for(int i = 0; i < hash.length; i++){
            hash[i] = null;
        }
    }

    private void utvid()                               // hører til LenketHashTabell
    {
        @SuppressWarnings({"rawtypes","unchecked"})      // bruker raw type
                Node<T>[] nyhash = new Node[2*hash.length + 1];  // dobling + 1

        for (int i = 0; i < hash.length; i++)            // den gamle tabellen
        {
            Node<T> p = hash[i];                           // listen til hash[i]

            while (p != null)                              // går nedover
            {
                Node<T> q = p.neste;                         // hjelpevariabel
                int nyindeks = p.hashverdi % nyhash.length;  // indeks i ny tabell

                p.neste = nyhash[nyindeks];                  // p skal legges først

                nyhash[nyindeks] = p;
                p = q;                                       // flytter p til den neste
            }

            hash[i] = null;                                // nuller i den gamle
        }

        hash = nyhash;                                   // bytter tabell
        grense = (int)(tetthet * hash.length);           // ny grense
    }

    private class HashTabellIterator implements Iterator<T>
    {
        private int indeks = 0;
        private Node<T> p = null;
        private boolean fjernOK = false;

        private HashTabellIterator()
        {
            while (indeks < hash.length && hash[indeks] == null) indeks++;
            p = indeks < hash.length ? hash[indeks] : null;
        }

        public boolean hasNext()
        {
            return p != null;
        }

        public T next()
        {
            if (!hasNext())
                throw new NoSuchElementException("Ingen flere verdier");

            T verdi = p.verdi;                  // tar vare på verdien
            fjernOK = true;

            if (p.neste != null)
            {
                p = p.neste;   // hvis p ikke er den siste
            }
            else  // må gå til neste indeks der hash[indeks] er ulik null
            {
                while (++indeks < hash.length && hash[indeks] == null);
                p = indeks < hash.length ? hash[indeks] : null;
            }
            return verdi;                       // returnerer verdien
        }
        public void remove(){
            if(!fjernOK) throw new IllegalStateException("FjenOk er false");

            fjernOK = false;
            int fjernIndeks = indeks-1;
            while (fjernIndeks >= 0 && hash[fjernIndeks] == null) fjernIndeks--;
            Node<T> q = null;
            Node<T> current = hash[indeks];

            while (current != null){


                if(current.neste != null){
                    if(current.neste.verdi.equals(p.verdi)){
                        break;
                    }
                    current = current.neste;
                    q = current;
                }else break;

            }
            if(q == null){
                hash[fjernIndeks] = current.neste;
            }else{
                q.neste = current.neste;
            }



        }

    } // class HashTabellIterator

    @Override
    public Iterator<T> iterator() {
        return new HashTabellIterator();
    }

}  // class LenketHashTabell
