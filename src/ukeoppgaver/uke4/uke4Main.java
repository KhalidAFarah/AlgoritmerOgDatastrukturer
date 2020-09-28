package ukeoppgaver.uke4;

import ukeoppgaver.eksempelKlasser.*;
import ukeoppgaver.resources.Tabell;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class uke4Main {
    public static void ukeMain(){
        /*String[] s = {"Per","Kari","Ole","Anne","Ali","Eva"};
        Tabell.innsettingssortering(s);
        System.out.println(Arrays.toString(s));  // [Ali, Anne, Eva, Kari, Ole, Per]

        Heltall x = new Heltall(3), y = new Heltall(3);
        System.out.println(x.compareTo(y));
        System.out.println(x.hashCode() + " " + y.hashCode());
        x = new Heltall(1); y = new Heltall(3);
        System.out.println(x.compareTo(y));
        x = new Heltall(3); y = new Heltall(1);
        System.out.println(x.compareTo(y));

        Person[] p = new Person[5];                   // en persontabell

        p[0] = new Person("Kari","Svendsen");         // Kari Svendsen
        p[1] = new Person("Boris","Zukanovic");       // Boris Zukanovic
        p[2] = new Person("Ali","Kahn");              // Ali Kahn
        p[3] = new Person("Azra","Zukanovic");        // Azra Zukanovic
        p[4] = new Person("Kari","Pettersen");        // Kari Pettersen

        int m = Tabell.maks(p);                       // posisjonen til den største
        System.out.println(p[m] + " er størst");      // skriver ut den største

        Tabell.innsettingssortering(p);               // generisk sortering
        System.out.println(Arrays.toString(p));

        System.out.println("------------------------");
        for(Studium studium : Studium.values()){
            System.out.println(studium.toString() + " - " + studium.name());
        }

        System.out.println("---------------");
        System.out.println("-Vår-");
        for(Måned mnd : Måned.vår()){
            System.out.println(mnd.toString());

        }
        System.out.println("-Sommer-");
        for(Måned mnd : Måned.sommer()){
            System.out.println(mnd.toString());
        }
        System.out.println("-Høst-");
        for(Måned mnd : Måned.høst()){
            System.out.println(mnd.toString());
        }
        System.out.println("-Vinter-");
        for(Måned mnd : Måned.vinter()){
            System.out.println(mnd.toString());
        }

        Student[] students = new Student[5];                             // en studenttabell
        students[0] = new Student("Kari","Svendsen", Studium.Data);      // Kari Svendsen
        students[1] = new Student("Boris","Zukanovic", Studium.IT);      // Boris Zukanovic
        students[2] = new Student("Ali","Kahn", Studium.Anvendt);        // Ali Kahn
        students[3] = new Student("Azra","Zukanovic", Studium.IT);       // Azra Zukanovic
        students[4] = new Student("Kari","Pettersen", Studium.Data);     // Kari Pettersen

        //komparator lambda uttrykk
        Tabell.innsettingssortering(students, (students1,students2) -> students1.getStudium().compareTo(students2.getStudium()));
        System.out.println(Arrays.toString(s));

        Komparator<Person> c = (p1,p2) -> p1.fornavn().compareTo(p2.fornavn());
        Tabell.innsettingssortering(p, c);                // se Programkode 1.4.6 b)
        System.out.println(Arrays.toString(p));           // Utskrift av tabellen p

        Tabell.innsettingssortering(students, (s1,s2) -> {
            int i = s1.fornavn().compareTo(s2.fornavn());
            if(i != 0) return i;
            return s1.compareTo(s2);
        });
        System.out.println(Arrays.toString(p));

        Tabell.innsettingssortering(students, (s1,s2) -> {
            int i = s1.getStudium().compareTo(s2.getStudium());
            if(i != 0) return i;
            i = s1.fornavn().compareTo(s2.fornavn());
            if(i != 0) return i;
            i = s1.etternavn().compareTo(s2.etternavn());
            return i;
        });
        System.out.println(Arrays.toString(p));

        System.out.println("------------------------");
        String[] strings = {"21","18","8","13","20","6","16","25","3","10"};
        Tabell.innsettingssortering(strings, (s1,s2) ->{
            if(s1.length() == s2.length()){
                return s1.compareTo(s2);
            }else if(s1.length() < s2.length()){
                return -1;
            }
            return 1;
        });

        System.out.println(Arrays.toString(strings));
        // Utskrift: [Per, Lars, Kari, Bodil, Berit, Anders]

        Komparator<Student> Studiumcomparator = (s1,s2) ->
        {
            int cmp = s1.getStudium().name().compareTo(s2.getStudium().name());
            return cmp != 0 ? cmp : s1.compareTo(s2);
        };

        Tabell.innsettingssortering(students, Studiumcomparator);    // Programkode 1.4.6 b)
        System.out.println(Arrays.toString(students));
        avsnitt147();
        avsnitt148();*/
        avsnitt149();
    }

    private static void avsnitt147(){
        //oppg.2
        Double[] d = {5.7,3.14,7.12,3.9,6.5,7.1,7.11};
        Tabell.innsettingssortering(d, Komparator.naturligOrden());
        Tabell.innsettingssortering(d, Komparator.omvendtOrden());
        System.out.println(Arrays.toString(d));

        //oppg.3
        Boolean[] b = {false, true, true, false, false, true, false, true};
        Tabell.innsettingssortering(b, Komparator.naturligOrden());
        System.out.println(Arrays.toString(b));

        //oppg. 4
        Person[] p = new Person[5];                       // en persontabell
        p[0] = new Person("Kari", "Svendsen");            // Kari Svendsen
        p[1] = new Person("Boris", "Zukanovic");          // Boris Zukanovic
        p[2] = new Person("Ali", "Kahn");                 // Ali Kahn
        p[3] = new Person("Azra", "Zukanovic");           // Azra Zukanovic
        p[4] = new Person("Kari", "Pettersen");

        Tabell.innsettingssortering(p, Komparator.orden(Person::etternavn));
        System.out.println(Arrays.toString(p));

        //oppg. 5
        String[] s = {"Lars","Anders","Bodil","Kari","Per","Berit"};
        Tabell.innsettingssortering(s, (s1, s2) -> {
            if(s1.length() > s2.length()){
                return -1;
            }else{
                return 1;
            }

        });

        System.out.println(Arrays.toString(s));
    }
    private static void avsnitt148(){
        //oppg. 4
        System.out.println("--------------------------------------");
        String[] s = {"21","18","8","13","20","6","16","25","3","10"};
        Tabell.innsettingssortering(s, Komparator.orden(String::length).deretter(x -> x));
        System.out.println(Arrays.toString(s));
    }

    private static void avsnitt149(){
        //oppg. 1
        System.out.println("--------------------------------------");
        String[] s = {"Sohil","Per","Thanh","Ann","Kari","Jon"};       // String-tabell
        Comparator<String> c =  Comparator.comparing(String::length);  // etter lengde
        Tabell.innsettingssortering2(s, c.thenComparing(x -> x));       // vanlig orden
        System.out.println(Arrays.toString(s));                        // skriver ut

        //Oppg. 3
        int[] x = {3,5,6,2,6,1,4,7,7,4};         // x-koordinater
        int[] y = {3,6,3,5,5,2,1,4,2,4};         // y-koordinater

        Point[] punkt = new Point[x.length];     // en punkttabell
        for (int i = 0; i < punkt.length; i++) punkt[i] = new Point(x[i],y[i]);

        for (Point p : punkt) System.out.print("(" + p.x + "," + p.y + ") ");
        System.out.println();                    // linjeskift

        Tabell.innsettingssortering2(punkt,
                Comparator.comparing(Point::getX).thenComparing(Point::getY));

        for (Point p : punkt) System.out.print("(" + p.x + "," + p.y + ") ");
        System.out.println("\n");
        // Utskriften blir:
        // (3,3) (5,6) (6,3) (2,5) (6,5) (1,2) (4,1) (7,4) (7,2) (4,4)
        // (1,2) (2,5) (3,3) (4,1) (4,4) (5,6) (6,3) (6,5) (7,2) (7,4)

        Comparator<Point> c2 = (p1, p2) ->
        {
            int d = p1.x - p2.x;    // forskjellen mellom x-koordinatene
            if (d != 0) return d;
            return p1.y - p2.y;     // forskjellen mellom y-koordinatene
        };

        Tabell.innsettingssortering2(punkt, c2);

        for (Point p : punkt) System.out.print("(" + p.x + "," + p.y + ") ");
        System.out.println();

    }
}
