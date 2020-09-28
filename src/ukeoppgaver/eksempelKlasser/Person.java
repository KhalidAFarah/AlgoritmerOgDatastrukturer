package ukeoppgaver.eksempelKlasser;

import java.util.Objects;

public class Person implements Comparable<Person>
{
    private final String fornavn;         // personens fornavn
    private final String etternavn;       // personens etternavn

    public Person(String fornavn, String etternavn)   // konstruktør
    {
        if(fornavn == null || etternavn == null)
            throw new NullPointerException("enten fornavnet eller etternavnet er null.");

        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    public String fornavn() { return fornavn; }       // aksessor
    public String etternavn() { return etternavn; }   // aksessor

    public int compareTo(Person p)    // pga. Comparable<Person>
    {
        int cmp = etternavn.compareTo(p.etternavn);     // etternavn
        if (cmp != 0) return cmp;             // er etternavnene ulike?
        return fornavn.compareTo(p.fornavn);  // sammenligner fornavn
    }

    public boolean equals(Object o)      // vår versjon av equals
    {
        if (o == this) return true;
        if (!(o instanceof Person)) return false;
        return compareTo((Person)o) == 0;
    }
    public boolean equals(Person p)      // vår versjon av equals
    {
        if (p == this) return true;
        return compareTo(p) == 0;
    }
    public boolean equals2(Object o)      // vår versjon av equals
    {
        if(o == null) return false;
        if (o == this) return true;
        if (this.getClass() != o.getClass()) return false;
        Person p = (Person) o;
        return this.fornavn.equals(p.fornavn) && this.etternavn.equals(p.etternavn);
    }

    public int hashCode() { return Objects.hash(etternavn, fornavn); }

    public String toString() { return fornavn + " " + etternavn; }

} // class Person
