package ukeoppgaver.resources;

import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class LenketKø<T> implements Kø<T>
{
    private static final class Node<T>  // en indre nodeklasse
    {
        private T verdi;        // nodens verdi
        private Node<T> neste;  // peker til neste node

        private Node(Node<T> neste)  // nodekonstruktør
        {
            this.verdi = null;
            this.neste = neste;
        }

    } // class Node

    private Node<T> fra;     // den første i køen
    private Node<T> til;     // en etter den siste i køen
    private int antall;      // antall i køen

    public LenketKø()        // standardkonstruktør
    {
        fra = new Node<T>(null);
        til = fra;
        for(int i = 7; i > 0; i--){
            til.neste = new Node<>(null);
            til = til.neste;
        }

        til = fra;
        antall = 0;
    }

    public boolean leggInn(T verdi)
    {
        if(tom()){
            fra.verdi = verdi;
            til = fra.neste;
        }else if(til.neste != fra){
            til.verdi = verdi;
            til = til.neste;
        }else{
            til.neste = new Node<>(fra);
            til.verdi = verdi;
        }

        antall++;
        return true;
    }

    public T kikk()
    {
        if(tom()) throw new NoSuchElementException("Køen er tom");
        return fra.verdi;
    }

    public T taUt()
    {
        if(tom()) throw new NoSuchElementException("Køen er tom");
        T verdi = fra.verdi;
        fra.verdi = null;
        fra = fra.neste;
        antall--;
        return verdi;
    }

    public int antall()
    {
        return antall;
    }

    public boolean tom()
    {
        return fra == til;
    }

    public String toString()
    {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        Node<T> p = fra;
        while(p != null){
            joiner.add(p.verdi.toString());
            p = p.neste;
        }
        return joiner.toString();
    }

    public void nullstill()  // tar vare på 8 av nodene
    {
        Node<T> p = fra;
        for (int i = 1; i < 8; i++)
        {
            p.verdi = null;
            p = p.neste;
        }
        p.verdi = null;
        til = p.neste = fra;
        antall = 0;
    }

} // class LenketKø
