package ukeoppgaver.eksempelKlasser;

import ukeoppgaver.resources.*;
import ukeoppgaver.resources.Iterable;
import ukeoppgaver.resources.Iterator;


import java.util.*;
import java.util.function.Consumer;

public class BinTre<T> implements Oppgave<T>, Iterable<T>           // et generisk binærtre
{
    private static final class Node<T>  // en indre nodeklasse
    {
        private T verdi;            // nodens verdi
        private Node<T> venstre;    // referanse til venstre barn/subtre
        private Node<T> høyre;      // referanse til høyre barn/subtre

        private Node(T verdi, Node<T> v, Node<T> h)    // konstruktør
        {
            this.verdi = verdi; venstre = v; høyre = h;
        }

        private Node(T verdi) { this.verdi = verdi; }  // konstruktør

        public String toString(){
            return ""+verdi;
        }

    } // class Node<T>

    private Node<T> rot;      // referanse til rotnoden
    private int antall;       // antall noder i treet
    private int endringer;

    public BinTre() { rot = null; antall = 0; endringer = 0;}          // konstruktør

    public BinTre(int[] posisjon, T[] verdi)  // konstruktør
    {
        if (posisjon.length > verdi.length) throw new
                IllegalArgumentException("Verditabellen har for få elementer!");

        for (int i = 0; i < posisjon.length; i++) leggInn(posisjon[i],verdi[i]);
    }

    public final void leggInn(int posisjon, T verdi) {
        if (posisjon < 1) throw new
                IllegalArgumentException("Posisjon (" + posisjon + ") < 1!");

        Node<T> p = rot, q = null;    // nodereferanser

        int filter = Integer.highestOneBit(posisjon) >> 1;   // filter = 100...00

        while (p != null && filter > 0)
        {
            q = p;
            p = (posisjon & filter) == 0 ? p.venstre : p.høyre;
            filter >>= 1;  // bitforskyver filter
        }

        if (filter > 0) throw new
                IllegalArgumentException("Posisjon (" + posisjon + ") mangler forelder!");
        else if (p != null) throw new
                IllegalArgumentException("Posisjon (" + posisjon + ") finnes fra før!");

        p = new Node<>(verdi);          // ny node

        if (q == null) rot = p;         // tomt tre - ny rot
        else if ((posisjon & 1) == 0)   // sjekker siste siffer i posisjon
            q.venstre = p;                // venstre barn til q
        else
            q.høyre = p;                  // høyre barn til q

        antall++;                       // en ny verdi i treet
        endringer++;
    }

    public int antall2() { return antall; }               // returnerer antallet

    private static int antall(Node<?> p)  // ? betyr vilkårlig type
    {
        if (p == null) return 0;            // et tomt tre har 0 noder

        return 1 + antall(p.venstre) + antall(p.høyre);
    }

    public int antall()
    {
        return antall(rot);                 // kaller hjelpemetoden
    }

    private static int høyde(Node<?> p)  // ? betyr vilkårlig type
    {
        if (p == null) return -1;          // et tomt tre har høyde -1

        return 1 + Math.max(høyde(p.venstre), høyde(p.høyre));
    }

    public int høyde()
    {
        return høyde(rot);                 // kaller hjelpemetoden
    }

    private static <T> boolean inneholder(Node<T> p, T verdi)
    {
        if (p == null) return false;    // kan ikke ligge i et tomt tre
        return verdi.equals(p.verdi) || inneholder(p.venstre,verdi)
                || inneholder(p.høyre,verdi);
    }

    public boolean inneholder(T verdi)
    {
        return inneholder(rot,verdi);   // kaller den private metoden
    }


    private static <T> int posisjon(Node<T> p, int k, T verdi)
    {
        if (p == null) return -1;                  // ligger ikke i et tomt tre
        if (verdi.equals(p.verdi)) return k;       // verdi ligger i p
        int i = posisjon(p.venstre,2*k,verdi);     // leter i venstre subtre
        if (i > 0) return i;                       // ligger i venstre subtre
        return posisjon(p.høyre,2*k+1,verdi);      // leter i høyre subtre
    }

    public int posisjon(T verdi)
    {
        return posisjon(rot,1,verdi);  // kaller den private metoden
    }

    public boolean tom() { return antall == 0; }         // tomt tre?

    private Node<T> finnNode(int posisjon)  // finner noden med gitt posisjon
    {
        if (posisjon < 1) return null;

        Node<T> p = rot;   // nodereferanse
        int filter = Integer.highestOneBit(posisjon >> 1);   // filter = 100...00

        for (; p != null && filter > 0; filter >>= 1)
            p = (posisjon & filter) == 0 ? p.venstre : p.høyre;

        return p;   // p blir null hvis posisjon ikke er i treet
    }

    public boolean finnes(int posisjon)
    {
        return finnNode(posisjon) != null;
    }

    public T hent(int posisjon)
    {
        Node<T> p = finnNode(posisjon);

        if (p == null) throw new
                IllegalArgumentException("Posisjon (" + posisjon + ") finnes ikke i treet!");

        return p.verdi;
    }

    public T oppdater(int posisjon, T nyverdi)
    {
        Node<T> p = finnNode(posisjon);

        if (p == null) throw new
                IllegalArgumentException("Posisjon (" + posisjon + ") finnes ikke i treet!");

        T gammelverdi = p.verdi;
        p.verdi = nyverdi;
        endringer++;

        return gammelverdi;
    }

    public T fjern(int posisjon){
        Node<T> node = finnNode(posisjon);
        if(node == null)
            return null;

        if(node.høyre != null ||  node.venstre != null) //indre node
            return null;

        if(rot == node){
            T temp = rot.verdi;
            rot.verdi = null;
            rot = null;
            antall--;
            endringer++;
            return temp;
        }


        T temp = node.verdi;
        node.verdi = null;
        node = null;
        antall--;
        endringer++;
        return temp;

    }
    public void nivåorden(Oppgave<? super T> oppgave)    // ny versjon
    {
        if (tom()) return;                   // tomt tre
        Kø<Node<T>> kø = new TabellKø<>();   // Se Avsnitt 4.2.3
        kø.leggInn(rot);                     // legger inn roten

        while (!kø.tom())                    // så lenge køen ikke er tom
        {
            Node<T> p = kø.taUt();             // tar ut fra køen
            oppgave.utførOppgave(p.verdi);     // den generiske oppgaven

            if (p.venstre != null) kø.leggInn(p.venstre);
            if (p.høyre != null) kø.leggInn(p.høyre);
        }
    }

    public int[] nivåer()   // returnerer en tabell som inneholder nivåantallene
    {
        if (tom()) return new int[0];       // en tom tabell for et tomt tre

        int[] a = new int[8];               // hjelpetabell
        Kø<Node<T>> kø = new TabellKø<>();  // hjelpekø
        int nivå = 0;                       // hjelpevariabel

        kø.leggInn(rot);    // legger roten i køen

        while (!kø.tom())   // så lenge som køen ikke er tom
        {
            // utvider a hvis det er nødvendig
            if (nivå == a.length) a = Arrays.copyOf(a,2*nivå);

            int k = a[nivå] = kø.antall();  // antallet på dette nivået

            for (int i = 0; i < k; i++)  // alle på nivået
            {
                Node<T> p = kø.taUt();

                if (p.venstre != null) kø.leggInn(p.venstre);
                if (p.høyre != null) kø.leggInn(p.høyre);
            }

            nivå++;  // fortsetter på neste nivå
        }

        return Arrays.copyOf(a, nivå);  // fjerner det overflødige
    }

    private static <T> void preorden(Node<T> p, Oppgave<? super T> oppgave) {
        oppgave.utførOppgave(p.verdi);                       // utfører oppgaven

        if (p.venstre != null) preorden(p.venstre,oppgave);  // til venstre barn
        if (p.høyre != null) preorden(p.høyre,oppgave);      // til høyre barn
    }
    public void preorden(Oppgave<? super T> oppgave) {
        if (!tom()) preorden(rot,oppgave);  // sjekker om treet er tomt
    }

    private static <T> void preorden2(Node<T> p, Oppgave<? super T> oppgave) {
        while(true) {
            oppgave.utførOppgave(p.verdi);                       // utfører oppgaven
            if (p.venstre != null) preorden2(p.venstre, oppgave);  // til venstre barn
            if (p.høyre == null) return;      // til høyre barn
            p = p.høyre;
        }
    }
    public void preorden2(Oppgave<? super T> oppgave) {
        if (!tom()) preorden2(rot,oppgave);  // sjekker om treet er tomt
    }
    private static <T> void inorden(Node<T> p, Oppgave<? super T> oppgave)
    {
        if (p.venstre != null) inorden(p.venstre,oppgave);  // til venstre barn
        oppgave.utførOppgave(p.verdi);                       // utfører oppgaven
        if (p.høyre != null) inorden(p.høyre,oppgave);      // til høyre barn
    }

    public void inorden(Oppgave<? super T> oppgave)
    {
        if (!tom()) inorden(rot,oppgave);  // sjekker om treet er tomt
    }
    private static <T> void inorden2(Node<T> p, Oppgave<? super T> oppgave)
    {
        while (true) {
            if (p.venstre != null) inorden2(p.venstre, oppgave);  // til venstre barn
            oppgave.utførOppgave(p.verdi);                       // utfører oppgaven
            if (p.høyre == null) return;      // til høyre barn
            p = p.høyre;
        }
    }

    public void inorden2(Oppgave<? super T> oppgave)
    {
        if (!tom()) inorden2(rot,oppgave);  // sjekker om treet er tomt
    }

    private static <T> void postorden(Node<T> p, Oppgave<? super T> oppgave)
    {
        if (p.venstre != null) postorden(p.venstre,oppgave);  // til venstre barn
        if (p.høyre != null) postorden(p.høyre,oppgave);      // til høyre barn
        oppgave.utførOppgave(p.verdi);                       // utfører oppgaven
    }
    public void postorden(Oppgave<? super T> oppgave)
    {
        if (!tom()) postorden(rot,oppgave);  // sjekker om treet er tomt
    }

    private static <T> void finnPostorden(Node<T> p, T verdi)
    {

        if(p.verdi.equals(verdi)){
            if (p.venstre != null) finnPostorden(p.venstre, verdi);  // til venstre barn
            if (p.høyre != null) finnPostorden(p.høyre, verdi);      // til høyre barn
        }
        if (p.venstre != null) finnPostorden(p.venstre, verdi);  // til venstre barn
        if (p.høyre != null) finnPostorden(p.høyre, verdi);      // til høyre barn
        // utfører oppgaven
    }
    public void finnPostorden(T verdi)
    {
        if (!tom()) finnPostorden(rot, verdi);  // sjekker om treet er tomt
    }

    private static <T> void postorden2(Node<T> p, Oppgave<? super T> oppgave)
    {
        if (p.venstre != null) postorden2(p.venstre,oppgave);  // til venstre barn
        if (p.høyre == null) return;      // til høyre barn
        oppgave.utførOppgave(p.verdi);                       // utfører oppgaven
        p = p.høyre;
    }
    public void postorden2(Oppgave<? super T> oppgave)
    {
        if (!tom()) postorden2(rot,oppgave);  // sjekker om treet er tomt

    }

    public void postordenNonRecursiv(Oppgave<? super T> oppgave) {
        TabellStakk<Node<T>> s = new TabellStakk<>();
        TabellStakk<Node<T>> s2 = new TabellStakk<>(antall);
        s.leggInn(rot);
        Node<T> current = rot;

        while (!s.tom()){
            s2.leggInn(current);

            if (current.høyre != null){
                if(current.venstre != null) {
                    s.leggInn(current.venstre);
                }
                current = current.høyre;
            }else if(current.venstre != null){


                current = current.venstre;


            }else {
                current = s.taUt();
            }
        }
        while (!s2.tom()) oppgave.utførOppgave(s2.taUt().verdi);
    }

    public String toString(){
        String ut = "[";
        ArrayList<T> lis = new ArrayList<>();
        nivåorden(c -> lis.add(c));
        for(int i = 0; i < lis.size(); i++){
            ut += ", " + lis.get(i);
        }
        ut += "]";
        return ut;
    }

    public void nullstill(){
        nullstill(rot);
        rot = null;
        endringer = endringer + antall;
        antall = 0;
    }

    private void nullstill(Node<T> node){
        if(node.venstre != null){
            nullstill(node.venstre);
            node.venstre = null;
        }
        if(node.høyre != null){
            nullstill(node.høyre);
            node.høyre = null;
        }
    }

    public <T> int antallBladnoder(){
        if(rot == null)
            return 0;
        return antallBladnoder(rot);
    }

    private <T> int antallBladnoder(Node<T> node){
        if(node.høyre == null && node.venstre == null){
            return 1;
        }

        else if(node.venstre != null && node.høyre == null)
            return antallBladnoder(node.venstre);
        else if(node.høyre != null && node.venstre == null)
            return antallBladnoder(node.høyre);
        else return antallBladnoder(node.høyre) + antallBladnoder(node.venstre);
    }
    public class IntObject
    {
        private int value;    // kun denne som instansvariabel

        public IntObject(int value) { this.value = value; }

        public void add(int value) { this.value += value; }

        public void subtract(int value) { this.value -= value; }

        public void set(int value) { this.value = value; }

        public int get() { return value; }
    }
    public int maksPos(){
        IntObject o = new IntObject(-1);
        maksPos(rot,o, 1);
        return o.get();
    }

    private void maksPos(Node<T> node, IntObject o, int pos){
        if(pos > o.get()) o.set(pos);

        if(node.venstre != null) maksPos(node.venstre, o, 2*pos);
        if(node.høyre != null) maksPos(node.høyre, o, 2*pos+1);

    }

    @Override
    public void utførOppgave(T t) {

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

        @Override
        public T next()
        {
            if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");
            if(iteratorEndringer != endringer)
                throw new ConcurrentModificationException("iteratorendringer er den samme som endringer");
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

    private class OmvendtInordenIterator implements Iterator<T>{
        private Stakk<Node<T>> s;
        private Node<T> p = null;

        private Node<T> sist(Node<T> q)   // en hjelpemetode
        {
            while (q.høyre != null)
            {
                s.leggInn(q);
                q = q.høyre;
            }
            return q;
        }

        private OmvendtInordenIterator()          // konstruktør
        {
            s = new TabellStakk<>();
            if (tom()) return;               // treet er tomt
            p = sist(rot);                  // bruker hjelpemetoden


        }

        @Override
        public boolean hasNext() {
            return p != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");

            T verdi = p.verdi;                        // tar vare på verdien

            if (p.venstre != null) p = sist(p.venstre);  // p har høyre subtre
            else if (s.tom()) p = null;               // stakken er tom
            else p = s.taUt();                        // tar fra stakken

            return verdi;
        }
    }

    ////////////

    private class PreordenIterator implements Iterator<T>
    {
        private Stakk<Node<T>> s;
        private Node<T> p = null;

        private Node<T> først(Node<T> q)   // en hjelpemetode
        {
            while (q.venstre != null)        // starter i q
            {
                s.leggInn(q);                  // legger q på stakken
                q = q.venstre;                 // går videre mot venstre
            }
            return q;  // q er lengst ned til venstre
            //q = rot;
            //return q;
        }

        private PreordenIterator()          // konstruktør
        {
            s = new TabellStakk<>();
            if (tom()) return;
            p = rot;                // bruker hjelpemetoden
        }

        @Override
        public T next()
        {
            if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");


            T verdi = p.verdi;

            if (p.venstre != null){
                if(p.høyre != null) s.leggInn(p.høyre);
                p = p.venstre;
            }
            else if(p.høyre != null){
                p = p.høyre;
            }
            else if (s.tom()) p = null;
            else p = s.taUt();

            return verdi;
        }

        @Override
        public boolean hasNext()
        {
            return p != null;
        }

    } // InordenIterator

    public Iterator<T> iterator()     // skal ligge i class BinTre
    {
        return new InordenIterator();
    }

    public Iterator<T> OmvendtInordeniterator()     // skal ligge i class BinTre
    {
        return new OmvendtInordenIterator();
    }
    public Iterator<T> preordeniterator()     // skal ligge i class BinTre
    {
        return new PreordenIterator();
    }

    public boolean erMintre(Comparator<? super T> c) // legges i BinTre
    {
        if (rot == null) return true;    // et tomt tre er et minimumstre
        else return erMintre(rot,c);     // kaller den private hjelpemetoden
    }

    private static <T> boolean erMintre(Node<T> p, Comparator<? super T> c)
    {
        if (p.venstre != null)
        {
            if (c.compare(p.venstre.verdi,p.verdi) < 0) return false;
            if (!erMintre(p.venstre,c)) return false;
        }
        if (p.høyre != null)
        {
            if (c.compare(p.høyre.verdi,p.verdi) < 0) return false;
            if (!erMintre(p.høyre,c)) return false;
        }
        return true;
    }

} // class BinTre<T>
