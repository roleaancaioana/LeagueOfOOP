Copyright Rolea Anca-Ioana, grupa 323CA

=========================Descrierea modului de implementare====================

- Proiectul este impartit in 4 pachete. Pachetul "game" contine
urmatoarele clase:

=> Clasa Game in care voi citi datele de intrare, voi crea eroii, harta pe care
se vor lupta, ingerii si voi pune in aplicare regulile jocului. De asemenea, 
tot aici voi afisa output-ul corespunzator.

=> Clasa Hero este o clasa abstracta ce sta la baza crearii oricarui erou. Am
implementat aici metoda "moveOutsideTheMap" deoarece aveam o problema la testul
dense (am vazut pe forum ca au mai avut-o si altii asa ca am incercat sa aplic
solutia pe care au aplicat-o si ei). Un jucator de tip Pyromancer iesea la un 
moment dat in afara hartii.

=> Clasa HeroesFactory reprezinta un design pattern menit sa ajute la crearea
eroilor in clasa Game.

=> Clasele Knight, Pyromancer, Rogue si Wizard ilustreaza comportamentul
fiecarui tip de erou.
	Dat fiind faptul ca la finalul fiecarei runde de joc pot aparea ingeri
care sa le modifice amplificatorii de rasa eroilor, am decis ca ar fi o idee
buna ca in fiecare dintre aceste 4 clase sa existe cate o metoda
"changeAllModifiers" prin care sa schimb acei modificatori. Daca la prima etapa
a acestui proiect aveam implementat un visitor care se ocupa sa-mi redea 
amplificatorul potrivit atunci cand avea loc o lupta intre 2 jucatori, la
aceasta etapa a proiectului am fost nevoita sa renunt la el. Acel visitor imi
oferea mereu valoarea initiala (de dinainte sa actioneze ingerii) a
amplificatorilor de rasa, ceea ce imi influenta in mod negativ functionarea 
programului.
	Totodata, in aceste clase am implementata cate o metoda "executeStrategy"
cu ajutorul careia le aplic jucatorilor strategia potrivita atunci cand este
cazul.
	In clasele Knight, Pyromancer si Rogue am decis ca ar
fi bine sa am cate o metoda care-mi calculeaza damage-ul total, fara 
amplificatori de rasa, dat de fiecare dintre acesti eroi unui alt erou de tip 
Wizard. Acest lucru m-a ajutat la calculul damage-ului dat de abilitatea 
Deflect a eroului Wizard. In clasa Wizard nu exista aceasta metoda deoarece 2 
eroi de tip Wizard nu isi vor da niciodata reciproc damage cu abilitatea 
Deflect. 
	
=> Clasa Magician este un Singleton menit sa observe anumite aspecte ale
desfasurarii jocului. Astfel, prin intermediul acestei clase am reusit sa
introduc in proiectul meu 2 tipuri de design pattern foarte cunoscute:Singleton
si Observer.

=> Clasa Map este un Singleton ce contine tipurile de teren prezente in joc.

- Pachetul "main" contine doar clasa Main unde am creat o instanta a
clasei Game si am afisat rezultatele finale ale jocului. Este suficient sa 
creez o instanta a clasei Game pentru a pune jocul "in functiune" deoarece in
constructorul acestei clase se apeleaza metode care pun in aplicare regulile 
jocului.

- Pachetul "angels" contine clase ce ilustreaza comportamentul fiecarui tip de
inger care poate aparea in joc. 

=> Clasa AngelVisitor este o clasa abstracta ce sta la baza crearii tuturor
tipurilor de ingeri. Prin intermediul metodei "notifyObserver" ii dau de stire
magicianului cu privire la pozitia unui inger in joc.

=> Clasa AngelsFactory este un design pattern care ajuta de asemenea la crearea 
ingerilor.

- Pachetul "strategies" contine clase ce ilustreaza strategiile pe care le 
poate aborda un jucator in functie de Hp-ul pe care il are, dar si o interfata
ce sta la baza crearii strategiilor.

- De asemenea, precizez faptul ca diagrama UML corespunzatoare acestui proiect
este reprezentata in fotografia atasata arhivei.

======================Mentiuni asupra modului de implementare==================

=> In clasa Rogue, in timp ce variabila "counterAttacksRogue" m-a ajutat sa 
numar loviturile date de eroul Rogue, variabila "oldCounterAttacksRogue" mi-a
influentat calculul corect al damage-ului total, fara amplificatorii de rasa,
dat de acest erou. Aveam probleme atunci cand se lupta un Wizard cu un Rogue,
deoarece variabila "counterAttacksRogue" se incrementa inainte de a se termina
duelul dintre cei doi. Din aceasta cauza a fost necesar sa-i creez o copie.

=> In clasa Knight, in implementarea metodei de atac a eroului, atunci cand 
adversarul are numarul de HP mai mic decat limita impusa de abilitatea Execute,
acesta va fi ucis si, in plus, nu i se va mai aplica si abilitatea slam.
Am considerat ca damage-ul provocat de aceasta abilitate poate fi considerat 0
in acest caz deoarece adversarul va fi deja mort in urma aplicarii abilitatii 
Execute.

=> In clasa Game, prin intermediul metodei "playGame" le voi aplica jucatorilor
damage-ul primit de la abilitatile overtime. Apoi, fiecare jucator
va aborda o noua strategie de joc daca e cazul. Dupa aceea,
jucatorii se vor muta pe pozitiile corespunzatoare rundei de joc.
In cazul in care 2 jucatori ajung in aceeasi locatie, ei se vor
ataca reciproc. Daca a avut loc o lupta intre 2 jucatori, atunci acestia
isi vor modifica XP-ul si HP-ul conform regulilor jocului, iar magicianul
va primi notificari atunci cand e cazul cu noutatile luptelor care au avut loc.
La finalul fiecarei runde isi vor face aparitia si ingerii buni sau rai.

=================================Feedback======================================

Partea a doua a proiectului a fost interesanta pentru ca am folosit multe
tipuri de design pattern. Chiar daca am facut tema cu mirosul de cozonaci pe
fundal, dintre toate temele pe care le-am avut la POO, asta a fost cea care 
mi-a placut cel mai mult.