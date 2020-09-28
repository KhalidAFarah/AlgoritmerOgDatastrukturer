Avsnitt 1.4.4
* oppgave 1
 -      Heltall x = new Heltall(3), y = new Heltall(3);
        System.out.println(x.compareTo(y));
        System.out.println(x.hashCode() + " " + y.hashCode());
        x = new Heltall(1); y = new Heltall(3);
        System.out.println(x.compareTo(y));
        x = new Heltall(3); y = new Heltall(1);
        System.out.println(x.compareTo(y));
        
 -      Utskrift:
        0
        34 34
        -1
        1
        
