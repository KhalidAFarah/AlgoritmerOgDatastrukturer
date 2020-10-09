# Obligatorisk oppgave 1 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Navn Khalid Ahmed Farah, s341843, s341843@oslomet.no

# Arbeidsfordeling
jeg var ikke på noen gruppe og gjorde da oppgave 1,2,3,4,5,7. jeg hadde litt ekstra tid og begynte da med oppgave 6, men
rakk desverre ikke å fullføre den.

Oppgave 1
Det blir færrest ombyttinger dersom arrayet er sortert avtakende, på grunn av at funksjonen maks leter etter største
tallet og dersom arrayet er sortert etter avtakende vil første elementet være det største og maks starter på det første elementet.
det blir flest ombyttinger når arrayet er sortert etter størst i og med det neste tallet enten vil være det samme eller større.
Vi finner de gjennomsnittlige ombyttinger i flere tilfeldig arrayer på lengde 1000 med tilfeldige verdier med hjelpe
metoden randPerm og tar antall ombyttinger for hver arrayene og delte resultatet med antall arrayer brukt
metode står i oblig1UnitTest og heter gjennomsnittligOmbyttninger. Vi får som svar fra programkoden at maks metoden
ombytter gjennomsnittlig 992 ganger i arrayene.

Oppgave 6
tankegangen min for rotasjonen for oppgave 6 var å rotere å dytte elementene flere ganger ved å bruke løsningen i
oppgave 5, men dette førte til at koden var svært ineffektiv (se utkommentert kode). derfor tenkte jeg at istedet for
å flytte elementet med 1 som i oppgave 5 dytter jeg istedet det frem med k og fortsett rundt i tabellen.
 