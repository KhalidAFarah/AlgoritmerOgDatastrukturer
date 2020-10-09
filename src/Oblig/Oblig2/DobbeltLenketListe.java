package no.oslomet.cs.algdat;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;

import java.util.function.Predicate;



public class DobbeltLenketListe<T> implements Liste<T> {
    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    public Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        antall = 0;
        endringer = 0;
        hode = null;
        hale = null;

    }

    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a);

        if (a.length > 0) {
            boolean first = true;
            antall = 0;
            int i = 0;for(; first && i < a.length; i++){
                if(a[i] != null && first){
                    hode = new Node<>(a[i], null, null);
                    antall++;
                    first = false;
                }
            }
            Node<T> CurrentNode = hode;
            Node<T> PreviousNode = hode;
            for(; i < a.length; i++){
                if(a[i] != null){
                    CurrentNode.neste = new Node<>(a[i], PreviousNode, null);
                    PreviousNode = CurrentNode;
                    CurrentNode = CurrentNode.neste;
                    CurrentNode.forrige = PreviousNode;
                    antall++;
                }
            }
            hale = CurrentNode;
        }

    }

    private static void fratilKontroll(int tablengde, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > tablengde)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + tablengde + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public Liste<T> subliste(int fra, int til){
        fratilKontroll(antall, fra, til);

        DobbeltLenketListe<T> subliste = new DobbeltLenketListe<>();
        Node<T> currentNode = hode;
        for(int i = 0; i < antall; i++){
            if(fra <= i && i < til) {
                subliste.leggInn(currentNode.verdi);
            }

            currentNode = currentNode.neste;
        }
        return subliste;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() { return antall==0; }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi);

        if (hode == null && hale == null && antall == 0) {
            hode = new Node<T>(verdi, null, null);
            hale = hode;
            antall++;
            endringer++;
            return true;
        } else if (antall > 0 && hale != null) {
            Node<T> newNode = new Node<>(verdi, hale, null);
            hale.neste = newNode;
            hale = newNode;
            antall++;
            endringer++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        if(verdi == null)
            throw new NullPointerException("Oppgitt kan ikke være null!");


        if(indeks >= 0 && indeks <= antall) {
            if(!tom()) {
                if (indeks == 0) {
                    Node<T> newNode = new Node<>(verdi, null, hode);
                    hode.forrige = newNode;
                    hode = newNode;
                    antall++;
                    endringer++;
                } else if (indeks == antall) {
                    Node<T> newNode = new Node<>(verdi, hale, null);
                    hale.neste = newNode;
                    hale = newNode;
                    antall++;
                    endringer++;
                } else {
                    Node<T> nextNode = finnNode(indeks);
                    Node<T> previousNode = nextNode.forrige;
                    Node<T> currentNode = new Node<T>(verdi, previousNode, nextNode);
                    previousNode.neste = currentNode;
                    nextNode.forrige = currentNode;
                    antall++;
                    endringer++;
                }
            }else{
                leggInn(verdi);
            }
        }else {
            throw new IndexOutOfBoundsException("Indexen oppgitt er i tillat!");
        }

    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    private Node<T> finnNode(int index){
        if(index <= antall/2){
            if(index == 0)
                return hode;

            Node<T> currentNode = hode;
            for(int i = 0; i < index; i++){
                if(currentNode.neste != null)
                currentNode = currentNode.neste;
            }
            return currentNode;
        }else{
            if(index == antall-1)
                return hale;

            Node<T> currentNode = hale;
            for(int i = antall; i >= index; i--){
                if(currentNode.forrige != null)
                currentNode = currentNode.forrige;
            }
            return currentNode;
        }
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        Node<T> currentNode = hode;
        int indeks = 0;

        while(currentNode != null){
            if(currentNode.verdi.equals(verdi))
                return indeks;

            currentNode = currentNode.neste;
            indeks++;
        }

        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        if(nyverdi != null){
            indeksKontroll(indeks, false);
            T gammelVerdi = finnNode(indeks).verdi;
            finnNode(indeks).verdi = nyverdi;
            endringer++;
            return gammelVerdi;
        }else
            throw new NullPointerException("ny verdi kan ikke være null");
    }

    @Override
    public boolean fjern(T verdi) {
        if(!tom() && verdi != null) {
            Node<T> currentNode = hode;
            while(currentNode.neste != null){
                if(currentNode.verdi.equals(verdi)){
                    if(currentNode.forrige == null && currentNode.neste != null){
                        hode = currentNode.neste;
                        currentNode.neste.forrige = null;
                        currentNode.neste = null;
                    }else{
                        currentNode.neste.forrige = currentNode.forrige;
                        currentNode.forrige.neste = currentNode.neste;
                    }
                    antall--;
                    endringer++;
                    return true;
                }else{
                    currentNode = currentNode.neste;
                }
            }if(hale.verdi.equals(verdi)){
                if(antall() != 1) {
                    hale = hale.forrige;
                    hale.neste.forrige = null;
                    hale.neste = null;
                    antall--;
                    endringer++;
                    return true;
                }else{
                    hode = null;
                    hale = hode;
                    antall--;
                    endringer++;
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);

        if(indeks == antall()-1){
            if(indeks == 0){
                T temp = hode.verdi;
                hode = null;
                hale = hode;

                antall--;
                endringer++;
                return temp;
            }

            T temp = hale.verdi;
            hale = hale.forrige;
            hale.neste = null;
            antall--;
            endringer++;
            return temp;
        }else if(indeks == 0){
            T temp = hode.verdi;
            hode = hode.neste;
            hode.forrige = null;
            antall--;
            endringer++;
            return temp;
        }

        Node<T> CurrentNode = hode;
        for(int i = 0; i < indeks; i++){
            if(CurrentNode.neste != null);
            CurrentNode = CurrentNode.neste;
        }

        CurrentNode.forrige.neste = CurrentNode.neste;
        CurrentNode.neste.forrige = CurrentNode.forrige;

        antall--;
        endringer++;

        return CurrentNode.verdi;

    }

    @Override
    public void nullstill() {
        //Måte Nr. 1
        hode.verdi = null;
        hode = hode.neste;
        antall--;
        endringer++;
        while (hode.neste != null){
            hode.forrige = null;
            hode.verdi = null;
            hode = hode.neste;

            antall--;
            endringer++;
        }
        hale.verdi = null;
        hale.forrige = null;
        hode = null;
        hale = hode;
        antall--;
        endringer++;
    }

    public void nullstill2(){
        //Måte Nr. 2
        while (antall > 0){
            fjern(0);
        }
    }

    @Override
    public String toString() {
        StringBuilder ut = new StringBuilder();
        if(hode != null) {
            ut.append("[" + hode.verdi);

        Node<T> currentNode = hode;
        for(int i = 0; i <= antall; i++){
            if(currentNode.neste != null){
                currentNode = currentNode.neste;
                ut.append(", " + currentNode.verdi);
            }
        }
        }else{
            ut.append("[");
        }
        ut.append("]");

        return ut.toString();
    }

    public String omvendtString() {
        StringBuilder ut = new StringBuilder();
        if(hale != null) {
            ut.append("[" + hale.verdi);

            Node<T> currentNode = hale;
            for (int i = antall; i >= 0; i--) {
                if (currentNode.forrige != null) {
                    currentNode = currentNode.forrige;
                    ut.append(", " + currentNode.verdi);
                }
            }
        }else{
            ut.append("[");
        }
        ut.append("]");

        return ut.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);

        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks){
            denne = finnNode(indeks);
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            if(iteratorendringer != endringer)
                throw new ConcurrentModificationException("Iterator endringer og endringer er like");

            if(!hasNext())
                throw new NoSuchElementException("Der er ikke noen neste verdier");

            fjernOK = true;
            T temp = denne.verdi;
            denne = denne.neste;
            return temp;
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


