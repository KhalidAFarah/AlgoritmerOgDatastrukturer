package ukeoppgaver.resources;

import java.util.*;
import java.util.stream.Stream;

public class SBinTre<T> implements Beholder<T>
{
    private static final class Node<T> // en indre nodeklasse
    {
        private T verdi;                 // nodens verdi
        private Node<T> venstre, høyre;  // venstre og høyre barn

        private Node(T verdi, Node<T> v, Node<T> h)  // konstruktør
        {
            this.verdi = verdi; venstre = v; høyre = h;
        }

        private Node(T verdi)  // konstruktør
        {
            this(verdi, null, null);
        }
    } // class Node

    private Node<T> rot;                       // peker til rotnoden
    private int antall;                        // antall noder
    private final Comparator<? super T> comp;  // komparator
    private int endringer;

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null; antall = 0; comp = c; endringer = 0;
    }

    public static <T extends Comparable<? super T>> SBinTre<T> sbintre()
    {
        return new SBinTre<>(Comparator.naturalOrder());
    }

    public static <T> SBinTre<T> sbintre(Comparator<? super T> c)
    {
        return new SBinTre<>(c);
    }
    public static <T> SBinTre<T> sbintre(Stream<T> s, Comparator<? super T> c)
    {
        SBinTre<T> tre = new SBinTre<>(c);             // komparatoren c
        s.forEach(tre::leggInn);                       // bygger opp treet
        return tre;                                    // treet returneres
    }

    public static <T extends Comparable<? super T>> SBinTre<T> sbintre(Stream<T> s)
    {
        return sbintre(s, Comparator.naturalOrder());  // naturlig ordning
    }

    public int antall()        // antall verdier i treet
    {
        return antall;
    }

    public boolean tom()       // er treet tomt?
    {
        return antall == 0;
    }

    public void nullstill()
    {
        endringer += antall;
        rot = null; antall = 0;
    }

    public void nullstill2(){
        endringer += antall;
        while (!tom()) fjernMin();
    }

    private class IntO{
        public int høyde = 0;
    }
    public int høyde(){

        IntO o = new IntO();
        høyde(rot, o, 1);
        return o.høyde;
    }

    private void høyde(Node<T> node,IntO o, int posisjon){

        //System.out.println(node.verdi);
        if(posisjon > o.høyde)
            o.høyde = posisjon;

        if(node.høyre != null)
            høyde(node.høyre, o, posisjon++);
        if(node.venstre != null)
            høyde(node.venstre, o, posisjon++);
    }



    public final boolean leggInn(T verdi)    // skal ligge i class SBinTre
    {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi);                   // oppretter en ny node

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q

        antall++;                                // én verdi mer i treet
        endringer++;
        return true;                             // vellykket innlegging
    }

    public String toString(){
        StringBuilder utskrift = new StringBuilder();
        utskrift.append("[");
        if(rot != null) {
            utskrift.append(rot.verdi);
            TabellKø<Node<T>> s = new TabellKø<>();
            if (rot.venstre != null) s.leggInn(rot.venstre);
            if (rot.høyre != null) s.leggInn(rot.høyre);
            //Node<T> current = rot;
            while (!s.tom()) {
                Node<T> current = s.taUt();
                utskrift.append(", " + current.verdi);

                if (current.venstre != null) s.leggInn(current.venstre);
                if (current.høyre != null) s.leggInn(current.høyre);


            /*if(current.venstre != null){
                if(current.høyre != null){
                    s.leggInn(current.høyre);
                }

                current = current.venstre;
            }
            else if(current.høyre != null){
                current = current.høyre;
            }else{
                current = s.taUt();
            }
            utskrift.append(", " + current.verdi);*/
            }
        }
        utskrift.append("]");
        return utskrift.toString();
    }

    private static <T> Node<T> balansert(T[] a, int v, int h)  // en rekursiv metode
    {
        if (v > h) return null;                       // tomt intervall -> tomt tre

        int m = (v + h)/2;                            // midten
        T verdi = a[m];                               // midtverdien

        while (v < m && verdi.equals(a[m-1])) m--;    // til venstre

        Node<T> p = balansert(a, v, m - 1);           // venstre subtre
        Node<T> q = balansert(a, m + 1, h);           // høyre subtre

        return new Node<>(verdi, p, q);               // rotnoden
    }
    public static <T> SBinTre<T> balansert(T[] a, Comparator<? super T> c)
    {
        SBinTre<T> tre = new SBinTre<>(c);          // oppretter et tomt tre
        tre.rot = balansert(a, 0, a.length - 1);    // bruker den rekursive metoden
        tre.antall = a.length;                      // setter antallet
        tre.endringer = a.length;
        return tre;                                 // returnerer treet
    }

    public static <T extends Comparable<? super T>> SBinTre<T> balansert(T[] a)
    {
        return balansert(a, Comparator.naturalOrder());
    }
    public int antall(T verdi){
        int i = 0;

        if (verdi == null) return 0;            // treet har ikke nullverdier

        Node<T> p = rot;                            // starter i roten
        while (p != null)                           // sjekker p
        {
            int cmp = comp.compare(verdi, p.verdi);   // sammenligner
            if (cmp < 0) p = p.venstre;               // går til venstre
            else if (cmp > 0) p = p.høyre;            // går til høyre
            else if (cmp == 0){
                i++;
                p = p.høyre;
            }
        }
        return i;                               // ikke funnet

    }

    public boolean inneholder(T verdi)     // skal ligge i klassen SBinTre
    {
        if (verdi == null) return false;            // treet har ikke nullverdier

        Node<T> p = rot;                            // starter i roten
        while (p != null)                           // sjekker p
        {
            int cmp = comp.compare(verdi, p.verdi);   // sammenligner
            if (cmp < 0) p = p.venstre;               // går til venstre
            else if (cmp > 0) p = p.høyre;            // går til høyre
            else return true;                         // cmp == 0, funnet
        }
        return false;                               // ikke funnet
    }

    public Liste<T> intervallsøk(T fraverdi, T tilverdi){
        if (fraverdi == null || tilverdi == null) return new TabellListe<>(); // treet har ikke nullverdier

        Stakk<Node<T>> s = new TabellStakk<>();
        s.leggInn(rot);
        Node<T> p = rot;                            // starter i roten
        while (p != null)                           // sjekker p
        {
            int cmp = comp.compare(fraverdi, p.verdi);// sammenligner
            if (cmp < 0) {
                s.leggInn(p);
                p = p.venstre;
            }               // går til venstre
            else if (cmp > 0) p = p.høyre;            // går til høyre
            else if (cmp == 0) {                        // cmp == 0, funnet
                break;
            }
        }
        if(p == null) p = s.taUt();
        Liste<T> liste = new TabellListe<>();
        while(p != null && comp.compare(p.verdi, tilverdi) < 0){
            liste.leggInn(p.verdi);
            if(p.høyre != null){
               p = p.høyre;
               while(p.verdi != null){
                   s.leggInn(p);
                   p = p.venstre;
               }
            }else if(!s.tom()){
                p = s.taUt();
            }else{
                p = null;
            }
        }

        return liste;
    }

    public T Maks(){
        if(rot == null) throw new NoSuchElementException("Treet er tomt");

        Node<T> p = rot;
        while (p.høyre != null)p = p.høyre;
        return p.verdi;
    }

    public T Maks2(){
        if(rot == null) throw new NoSuchElementException("Treet er tomt");

        T maks = rot.verdi;
        Node<T> p = rot;
        Comparator c = Comparator.naturalOrder();
        while (p.høyre != null){
            if(c.compare(p.verdi, maks) > 0)
                maks = p.verdi;
        }
        return maks;
    }

    public T gulv(T verdi)
    {
        Objects.requireNonNull(verdi, "Treet har ingen nullverdier!");
        if (tom()) throw new NoSuchElementException("Treet er tomt!");

        Node<T> p = rot; T gulv = null;

        while (p != null)
        {
            int cmp = comp.compare(verdi, p.verdi);

            if (cmp < 0) p = p.venstre;  // gulvet ligger til venstre
            else if (cmp > 0)
            {
                gulv = p.verdi;            // nodeverdien er en kandidat
                p = p.høyre;
            }
            else return p.verdi;         // verdi ligger i treet
        }
        return gulv;
    }

    public T gulv2(T verdi)
    {
        Objects.requireNonNull(verdi, "Treet har ingen nullverdier!");
        if (tom()) throw new NoSuchElementException("Treet er tomt!");

        Node<T> p = rot; T gulv = null;

        while (p != null)
        {
            int cmp = comp.compare(verdi, p.verdi);

            if (cmp < 0) p = p.venstre;  // gulvet ligger til venstre
            else if (cmp >= 0)
            {
                gulv = p.verdi;            // nodeverdien er en kandidat
                p = p.høyre;
            }
            //else return p.verdi;         // verdi ligger i treet
        }
        return gulv;
    }

    public T tak(T verdi){
        if(tom()) return null;
        Comparator c = Comparator.naturalOrder();
        Node<T> p = rot;
        T tak = null;
        while(p.høyre != null){
            int cmp = c.compare(verdi, p.verdi);
            if(cmp < 0){
                if(c.compare(tak, p.verdi) < 0)
                    tak = p.verdi;

                p = p.venstre;
            }else if(cmp > 0){
                p = p.høyre;
            }else
                return p.verdi;


        }
        return tak;
    }

    public T mindre(T verdi){
        if(tom()) return null;
        Comparator c = Comparator.naturalOrder();
        Node<T> p = rot;
        T mindre = null;
        while(p.høyre != null){
            int cmp = c.compare(verdi, p.verdi);
            if(cmp < 0){
                p = p.venstre;
            }else if(cmp > 0){
                if(c.compare(mindre, p.verdi) < 0)
                    mindre = p.verdi;
                p = p.høyre;
            }


        }
        return mindre;
    }

    public boolean fjern(T verdi)  // hører til klassen SBinTre
    {
        if (verdi == null) return false;  // treet har ingen nullverdier

        Node<T> p = rot, q = null;   // q skal være forelder til p

        while (p != null)            // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
            else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
            else break;    // den søkte verdien ligger i p
        }
        if (p == null) return false;   // finner ikke verdi

        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
            if (p == rot) rot = b;
            else if (p == q.venstre) q.venstre = b;
            else q.høyre = b;
        }
        else  // Tilfelle 3)
        {
            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null)
            {
                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p) s.venstre = r.høyre;
            else s.høyre = r.høyre;
        }

        antall--;   // det er nå én node mindre i treet
        endringer++;
        return true;
    }

    public int fjernAlle(T verdi){
        int antallVerdier = 0;
        while (fjern(verdi)) antallVerdier++;
        return antallVerdier;
    }

    public void fjernMin()  // hører til klassen SBinTre
    {
        if (tom()) throw new NoSuchElementException("Treet er tomt!");

        if (rot.venstre == null) rot = rot.høyre;  // rotverdien er minst
        else
        {
            Node<T> p = rot.venstre, q = rot;
            while (p.venstre != null)
            {
                q = p;  // q er forelder til p
                p = p.venstre;
            }
            // p er noden med minst verdi
            q.venstre = p.høyre;
        }
        antall--;  // det er nå én node mindre i treet
        endringer++;
    }

    public void fjernMaks(){
        if(rot == null) throw new NoSuchElementException("Treet er tom");
        T maks = rot.verdi;
        Node<T> p = rot;
        Comparator c = Comparator.naturalOrder();
        while(p.høyre != null){
            p = p.høyre;
        }
        fjern(maks);
    }

    public int fjernAllMaks(){
        if(rot == null) throw new NoSuchElementException("Treet er tom");
        T maks = rot.verdi;
        Node<T> p = rot;
        int antallVerdier = 0;
        Comparator c = Comparator.naturalOrder();
        while(p.høyre != null){
            p = p.høyre;
        }
        while(fjern(maks)) antallVerdier++;
        return antallVerdier;
    }

    private class InordenIterator implements Iterator<T>
    {
        private Stakk<Node<T>> s = new TabellStakk<>();
        private Node<T> p = null;
        private int iteratorEndringer = endringer;

        private Node<T> først(Node<T> q)   // en hjelpemetode
        {
            while (q.venstre != null)        // starter i q
            {
                s.leggInn(q);                  // legger q på stakken
                q = q.venstre;                 // går videre mot venstre
            }
            return q;                        // q er lengst ned til venstre
        }

        private InordenIterator()          // konstruktør
        {
            if (tom()) return;               // treet er tomt
            p = først(rot);                  // bruker hjelpemetoden
        }
        private InordenIterator(T verdi)          // konstruktør
        {
            if (tom()) return;               // treet er tomt

            p = rot;                  // bruker hjelpemetoden
            Comparator c = Comparator.naturalOrder();
            while (p != null){
                int cmp = c.compare(verdi, p.verdi);

                if(cmp < 0){
                    s.leggInn(p);
                    p = p.venstre;
                }else if(cmp > 0){
                    p = p.høyre;
                }else break;
            }
            if(p == null){
                if(!s.tom()) p = s.taUt();
            }
        }

        @Override
        public T next()
        {
            if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");
            if(iteratorEndringer != endringer)
                throw new ConcurrentModificationException("iteratorendringer er ikke lik endringer");

            T verdi = p.verdi;                        // tar vare på verdien

            if (p.høyre != null) p = først(p.høyre);  // p har høyre subtre
            else if (s.tom()) p = null;               // stakken er tom
            else p = s.taUt();                        // tar fra stakken

            return verdi;                             // returnerer verdien
        }

        @Override
        public boolean hasNext()
        {
            return p != null;
        }

    } // InordenIterator

    public Iterator<T> iterator()  // returnerer en iterator
    {
        return new InordenIterator();
    }

    public Iterator<T> iterator(T verdi){
        return new InordenIterator(verdi);
    }

    private class omvendtInordenIterator implements Iterator<T>
    {
        private Stakk<Node<T>> s = new TabellStakk<>();
        private Stakk<Node<T>> s2 = new TabellStakk<>();
        private Node<T> p = null;
        private int iteratorEndringer = endringer;

        private Node<T> sist(Node<T> q)   // en hjelpemetode
        {
            while (q.høyre != null)        // starter i q
            {
                s.leggInn(q);                  // legger q på stakken
                q = q.høyre;                 // går videre mot venstre
            }
            return q;
        }

        private omvendtInordenIterator()          // konstruktør
        {
            if (tom()) return;               // treet er tomt
            p = sist(rot);                  // bruker hjelpemetoden
        }

        @Override
        public T next()
        {
            if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");
            if(iteratorEndringer != endringer)
                throw new ConcurrentModificationException("iteratorendringer er ikke lik endringer");

            T verdi = p.verdi;                        // tar vare på verdien

            if (p.venstre != null) p = sist(p.venstre);  // p har høyre subtre
            else if (s.tom()) p = null;               // stakken er tom
            else p = s.taUt();                        // tar fra stakken

            return verdi;                             // returnerer verdien
        }

        @Override
        public boolean hasNext()
        {
            return p != null;
        }

    } // InordenIterator

    public Iterator<T> riterator(){
        return new omvendtInordenIterator();
    }


} // class SBinTre
