Copyright Rolea Anca-Ioana, grupa 323CA

=========================Descrierea modului de implementare====================

- Proiectul este impartit in 2 pachete. Primul pachet, "game", contine
urmatoarele clase:

=> clasa Game in care voi citi datele de intrare, voi crea eroii, harta pe care
se vor lupta si voi pune in aplicare regulile jocului, mutand jucatorii
corespunzator si facandu-i sa se dueleze daca este cazul. De asemenea, tot aici
voi afisa output-ul corespunzator.

=> clasa Hero este o clasa abstracta ce sta la baza crearii oricarui erou. 
Aceasta intruneste caracteristicile (metode, campuri) comune ale tuturor 
eroilor.

=> clasa HeroFactory reprezinta un design pattern menit sa ajute la crearea
eroilor in clasa Game.

=> clasele Knight, Pyromancer, Rogue si Wizard ilustreaza comportamentul
fiecarui tip de erou (felul in care acestia ataca un alt personaj). In 
clasele Knight, Pyromancer si Rogue am decis ca ar fi bine sa am cate o
metoda care-mi calculeaza damage-ul total, fara amplificatori de rasa, dat de
fiecare dintre acesti eroi unui alt erou de tip Wizard. Acest lucru m-a ajutat
la calculul damage-ului dat de abilitatea Deflect a eroului Wizard. In clasa
Wizard nu exista aceasta metoda deoarece 2 eroi de tip Wizard nu isi vor da
niciodata reciproc damage.

=> clasele KnightVisitor, PyromancerVisitor, RogueVisitor, WizardVisitor, dar
si interfetele Visitable si Visitor m-au ajutat sa introduc conceptul de
Double Dispatch in implementarea temei.Practic, cu ajutorul design pattern-ului
Visitor am reusit sa stabilesc care sunt modificatorii de rasa pe care ii voi
aplica damage-ului dat de un erou. Astfel, am evitat folosirea instanceof.

- Al doilea pachet, "main", contine doar clasa Main unde am creat o instanta a
clasei Game si am afisat datele de iesire. Este suficient sa creez o instanta
a clasei Game pentru a pune jocul "in functiune" deoarece in constructorul
acestei clase se apeleaza metode care pun in aplicare regulile jocului.

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

=================================Feedback======================================

Tema a fost interesanta, am invatat sa folosesc git-ul, ceea ce inainte nu
stiam sa fac. Recunosc ca mi-a dat cateva batai de cap utilizarea 
acestuia, dar totul e bine cand se termina cu bine. :)