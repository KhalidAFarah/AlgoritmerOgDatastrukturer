package ukeoppgaver.resources;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

public class EnkeltLenketListe<T> implements Liste<T>
{
    private static final class Node<T>       // en indre nodeklasse
    {
        private T verdi;                       // nodens verdi
        private Node<T> neste;                 // den neste noden

        private Node(T verdi,Node<T> neste)    // konstruktør
        {
            this.verdi = verdi;
            this.neste = neste;
        }
    }  // Node

    private Node<T> hode, hale;  // pekere til første og siste node
    private int endringer;
    private int antall;          // antall verdier/noder i listen

    public EnkeltLenketListe()   // standardkonstruktør
    {
        hode = hale = null;        // hode og hale til null
        antall = 0;                // ingen verdier - listen er tom
        endringer = 0;
    }
    public EnkeltLenketListe(T[] a)   // standardkonstruktør
    {
        hode = hale = null;        // hode og hale til null
        antall = 0;
        endringer = 0;
        if(a.length > 0) {
            antall = a.length - 1;// ingen verdier - listen er tom
            hode = new Node<>(a[0], null);
            Node currentNode = hode;
            for (int i = 1; i < a.length; i++) {
                currentNode.neste = new Node(a[i], null);
                currentNode = currentNode.neste;
            }
        }
    }

    @Override
    public boolean leggInn(T verdi)   // verdi legges bakerst
    {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        if (antall == 0)  hode = hale = new Node<>(verdi, null);  // tom liste
        else hale = hale.neste = new Node<>(verdi, null);         // legges bakerst

        antall++;        // en mer i listen
        return true;     // vellykket innlegging
    }

    @Override
    public void leggInn(int indeks, T verdi)    // verdi til posisjon indeks
    {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        indeksKontroll(indeks, true);        // true: indeks = antall er lovlig

        if (indeks == 0)                     // ny verdi skal ligge først
        {
            hode = new Node<>(verdi, hode);    // legges først
            if (antall == 0) hale = hode;      // hode og hale peker på samme node
        }
        else if (indeks == antall)           // ny verdi skal ligge bakerst
        {
            hale = hale.neste = new Node<>(verdi, null);  // legges bakerst
        }
        else
        {
            Node<T> p = hode;                  // p flyttes indeks - 1 ganger
            for (int i = 1; i < indeks; i++) p = p.neste;

            p.neste = new Node<>(verdi, p.neste);  // verdi settes inn i listen
        }

        antall++;                            // listen har fått en ny verdi
    }

    @Override
    public boolean inneholder(T t)
    {
        /*Node<T> currentNode = hode;
        for(int i = 0;currentNode != null; i++){
            if(currentNode.verdi.equals(t))
                return true;
            else
                currentNode = currentNode.neste;
        }
        return false;*/
        return indeksTil(t) != -1;
    }

    @Override
    public T hent(int indeks)
    {
        throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public int indeksTil(T t) {
        Node<T> currentNode = hode;
        for(int i = 0;currentNode != null; i++){
            if(currentNode.verdi.equals(t))
                return i;
            else
                currentNode = currentNode.neste;
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T t) {
        indeksKontroll(indeks, false);
        T gammel = finnNode(indeks).verdi;
        finnNode(indeks).verdi = t;
        return gammel;
    }

    public Node<T> finnNode(int indeks){
        indeksKontroll(indeks, false);
        Node<T> currentNode = hode;
        for(int i = 1; i <= indeks; i++){
            currentNode = currentNode.neste;
        }
        return currentNode;
    }
    @Override
    public T fjern(int indeks)
    {
        indeksKontroll(indeks, false);  // Se Liste, false: indeks = antall er ulovlig

        T temp;                              // hjelpevariabel

        if (indeks == 0)                     // skal første verdi fjernes?
        {
            temp = hode.verdi;                 // tar vare på verdien som skal fjernes
            hode = hode.neste;                 // hode flyttes til neste node
            if (antall == 1) hale = null;      // det var kun en verdi i listen
        }
        else
        {
            Node<T> p = finnNode(indeks - 1);  // p er noden foran den som skal fjernes
            Node<T> q = p.neste;               // q skal fjernes
            temp = q.verdi;                    // tar vare på verdien som skal fjernes

            if (q == hale) hale = p;           // q er siste node
            p.neste = q.neste;                 // "hopper over" q
        }

        antall--;                            // reduserer antallet
        return temp;                         // returner fjernet verdi
    }

    @Override
    public boolean fjern(T t)
    {
        int index = indeksTil(t);
        if(index != -1){
            fjern(index);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int antall()
    {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public void nullstill() {
       hode = null;
       hale = hode;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new EnkeltLenketListeIterator();
    }

    private class EnkeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> p = hode;         // p starter på den første i listen
        private boolean fjernOK = false;  // blir sann når next() kalles
        private int iteratorendringer = endringer;  // startverdi

        @Override
        public boolean hasNext()
        {
            return p != null;  // p er ute av listen hvis den har blitt null
        }

        @Override
        public T next()
        {
            if (endringer != iteratorendringer)
                throw new ConcurrentModificationException("Listen er endret!");

            if (!hasNext()) throw new
                    NoSuchElementException("Tomt eller ingen verdier igjen!");

            fjernOK = true;            // nå kan remove() kalles

            T denneVerdi = p.verdi;    // tar vare på verdien i p
            p = p.neste;               // flytter p til den neste noden

            return denneVerdi;         // returnerer verdien
        }

        @Override
        public void remove()
        {
            if (endringer != iteratorendringer)
                throw new ConcurrentModificationException("Listen er endret!");


            if (!fjernOK) throw new IllegalStateException("Ulovlig tilstand!");

            fjernOK = false;               // remove() kan ikke kalles på nytt
            Node<T> q = hode;              // hjelpepeker

            if (hode.neste == p)           // skal den første fjernes?
            {
                hode = hode.neste;           // den første fjernes
                if (p == null) hale = null;  // dette var den eneste noden
            }
            else
            {
                Node<T> r = hode;            // må finne forgjengeren
                // til forgjengeren til p
                while (r.neste.neste != p)
                {
                    r = r.neste;               // flytter r
                }

                q = r.neste;                 // det er q som skal fjernes
                r.neste = p;                 // "hopper" over q
                if (p == null) hale = r;     // q var den siste
            }

            q.verdi = null;                // nuller verdien i noden
            q.neste = null;                // nuller nestepeker

            endringer++;             // en endring i listen
            iteratorendringer++;    // en endring av denne iteratoren
            antall--;                      // en node mindre i listen
        }
    } // EnkeltLenketListeIterator

    @Override
    public String toString() {
        String ut = "[";
        if (hode != null) {
            ut += hode.verdi;
            Node currentNode = hode.neste;
            while (currentNode != null) {
                ut += ", " + currentNode.verdi;
                currentNode = currentNode.neste;
            }
        }
        ut += "]";
        return ut;
    }

    public void forEachRemaining(Consumer<? super T> action){
        Objects.requireNonNull(action, "Handlingen kan ikke være null!");

        Node<T> currentNode = hode;
        for(int i = 0; i < antall; i++){
            action.accept(currentNode.verdi);

            currentNode = currentNode.neste;
        }
    }

}  // EnkeltLenketListe
