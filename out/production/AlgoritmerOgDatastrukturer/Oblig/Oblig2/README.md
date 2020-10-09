# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 

# Krav til innlevering

Se oblig-tekst for alle krav. Oppgaver som ikke oppfyller følgende vil ikke få godkjent:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* Ingen debug-utskrifter
* Alle testene som kreves fungerer (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet

# Arbeidsfordeling

Oppgaven er levert av følgende student:
* Khalid Ahmed Farah, s341843, s341843@oslomet.no

Jeg har brukt git til å dokumentere arbeidet mitt som har ledet til et total av 43 commits med logg-melding der det
beskriver hvilken oppgaven jeg startet på eller gjorde og om jeg hadde .

arbeidsfordeling ble da at jeg gjorde alle oppgaver fra 0 - 8.

# Beskrivelse av oppgaveløsning (maks 5 linjer per oppgave)

* Oppgave 1:
For oppgave 1 tok jeg inspirasjon fra ukesoppgavene for boolean tom ved å bare returnere antall == 0, mens for antall
returnerte jeg bare antall. Deretter startet jeg med konstruktrøren der jeg da brukte en for løkke for å kjøre gjennom
arrayet gitt i parameteren og satte hver av arayyet verdier til en node koblet til hverandre. I tillegg testet jeg
relasjonenen for halen og hodet sine neste og forrige noder og deres verdier med test lister.

* Oppgave 2:
For toString metoden loopet jeg igjennom liste med neste attibutten til listen noder og appended deres verdi i et array
format. For omvendtString startet jeg istedet fra halen til hodet med en for løkke istedet og antall attributten
og deretter testet metoden. For 2b startet jeg med å kontrollere den oppgitt verdien med requireNonNull og deretter la
til en ny node på slutten av listen med som refererte frem tilbake mellom den forrige med neste og forrige attributtene
og sette hale til den nye noden og øke antall og endringer. 

* Oppgave 3:
Jeg implementerte finnNode metoden med en for løkke og variablet currentNode fra hode og brukte
currentNode = currentNode.neste inn til jeg rukket indeksen. I tillegg starter metoden fra hale eller hode vis det er
større elle mindre enn antallet delt på 2. Hent bruker da finnNode for hente verdien til indeksen oppgitt, mens oppdater
bruker finnNode til oppdattere verdien til den hentede noden og øker da endringer med 1. Subliste metoden instansierer
et nytt DobbelLenketListe objekt som da fra det gitte området for hver verdi bruker legg inn metoden til listen.

* Oppgave 4:
For indekstil looper jeg igjennom listen fra hode intill den neste nodens sin verdi den gitt og dersom dette blir true
returnere oppgaven en teller som ble instansiert med verdi lik 0 utafor loopen som for hver loop økte med en. Dersom
loopen ikke finner en node med lik verdien returneres det etter -1. For inneholder returneres det bare
indekstil(T verdi) != -1 slik at dersom indekstil fant verdien returneres en verdi ikke lik -1 og -1 vis ikke.

* Oppgave 5:
For legginn(int indeks,T verdi) testet jeg først om verdi er null. Dersom indeksen er lik antall eller 0 så blir de laget
ny node som da er en ny hode eller hale, men hvis indeks er et tall større enn 0 og mindre antall så bruker jeg finnNode
metoden for å hente noden på indeksen og lager en ny node og endrer da referansen til neste og forrige for den nye noden
og noden fra finnNode. Dersom den lenkede listen er tom så kalles bare legginn(T verdi). Hvis metoden da går inn i noen
av sjekkene over blir antall og endringer da økt. Hvis indeksen var mindre enn 0 og større enn antall kastes det en
Exception.

* Oppgave 6:
Jeg implementerte fjern(T verdi) ved å først sjekke dersom listen er tom eller verdi er null hvis dette er true
returneres det false. Dersom det returnere false kjøres det en while løkke som kjører gjennom listen og leter etter en
node med lik verdi problemet med while løkken kjører til currentNode.neste == null som betyr at den ikke sjekker halen,
derfor settet jeg opp if setning for halen. Hvis metoden eventuelt finner en node med like verdi som parameteren
returneres det true. For fjern(int indeks) metoden tester først indeksen med indeks kontroll og deretter sjekker om
indeksen viser til halen er hodet, hvis ikke kjøres det en for løkke til indeks for å fjerne noden ved å fjerne pekere
til den og returnere nodens verdi og øke endringer og minke antall.

* Oppgave 7:
For metode nummer 1 looper jeg igjennom listen fra hode og nuller nodene sine verdier, minker antall, øker endringer og
sin forrige. Etter loopen settes hales sin verdi lik null, antall blir 0, endringer økes igjen og hales sin forrige node
blir null. For metode nummer 2 loopes det igjennom inntil listen er tom, der for hver iterasjon kalles da fjern metoden
med indeks 0. I tilleg satte jeg opp testerRaskesteNullstill() i test klassen for sjekke hvilken av metodene som var
raskest. Fra resultatet av testen ser metode en til å være den raskeste i forhold til metode 2.

* Oppgave 8:
For next() kastes det først exceptions dersom iteratorendringer er lik endringer og hasNext() returnere false, hvis ikke
settes fjernOk true og denne sin verdi skal returners og denne settes til denne.neste. For iterator metoden returneres
det new DobbeltLenketListeIterator(). For 8c setter jeg attributten denne til finnNode med parameteren indeks, og som
den forrige konstruktøren setter fjernOk lik false og iteratorendringer lik endringer. iterator(int indeks) instansieres
og returneres konstruktøren fra den forrige deloppgaven der parameteren indeks blir sendt inn i konstruktøren. 