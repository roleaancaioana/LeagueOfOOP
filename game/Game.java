package game;

import angels.AngelVisitor;
import angels.AngelsFactory;
import strategies.FirstPyromancerStrategy;
import strategies.FirstWizardStrategy;
import strategies.FirstKnightStrategy;
import strategies.FirstRogueStrategy;
import strategies.SecondPyromancerStrategy;
import strategies.SecondRogueStrategy;
import strategies.SecondKnightStrategy;
import strategies.SecondWizardStrategy;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    private Scanner scanner;
    private final int m;
    private final int n;
    private int numberOfPlayers, numberOfRounds;
    private Hero[] heroList;
    private AngelVisitor[] angelsList;
    private char[][] mapMove;
    private int numberOfAngels;
    private Map map = Map.getInstance();

    public Game(final String inputName) {
        try {
            this.scanner = new Scanner(new FileReader(inputName));
        } catch (IOException error) {
            System.err.println("Error");
        }

        assert this.scanner != null;
        this.n = this.scanner.nextInt();
        this.m = this.scanner.nextInt();
        this.scanner.nextLine();

        getMapLand();

        getPlayers();

        getMapMove();

        playGame();
    }

    /**
     * Prin intermediul acestei metode fiecare jucator isi alege o noua strategie
     * de joc.
     * @param hero reprezinta jucatorul ce urmeaza sa abordeze o noua logica de atac
     */
    private void chooseTheBestStrategy(final Hero hero) {
        int maxLevelHp = hero.getInitialHp() + hero.getHpPerLevel() * hero.getLevel();
        final int firstCoefficient = 2;
        final int secondCoefficient = 3;
        final int thirdCoefficient = 4;
        final int fourthCoefficient = 5;
        final int fifthCoefficient = 7;
        hero.setStrategy(null);

        if (maxLevelHp / secondCoefficient < hero.getHp()
                && hero.getHp() < maxLevelHp / firstCoefficient && hero.getHeroType() == 'K') {
            hero.setStrategy(new FirstKnightStrategy());
        }
        if (hero.getHp() < maxLevelHp / secondCoefficient && hero.getHeroType() == 'K') {
            hero.setStrategy(new SecondKnightStrategy());
        }

        if (maxLevelHp / thirdCoefficient < hero.getHp()
                && hero.getHp() < maxLevelHp / secondCoefficient && hero.getHeroType() == 'P') {
            hero.setStrategy(new FirstPyromancerStrategy());
        }
        if (hero.getHp() < maxLevelHp / thirdCoefficient && hero.getHeroType() == 'P') {
            hero.setStrategy(new SecondPyromancerStrategy());
        }

        if (maxLevelHp / fifthCoefficient < hero.getHp()
                && hero.getHp() < maxLevelHp / fourthCoefficient && hero.getHeroType() == 'R') {
            hero.setStrategy(new FirstRogueStrategy());
        }
        if (hero.getHp() < maxLevelHp / fifthCoefficient && hero.getHeroType() == 'R') {
            hero.setStrategy(new SecondRogueStrategy());
        }

        if (maxLevelHp / thirdCoefficient < hero.getHp()
                && hero.getHp() < maxLevelHp / firstCoefficient && hero.getHeroType() == 'W') {
            hero.setStrategy(new FirstWizardStrategy());
        }

        if (hero.getHp() < maxLevelHp / thirdCoefficient && hero.getHeroType() == 'W') {
            hero.setStrategy(new SecondWizardStrategy());
        }
    }

    /**
     *  Metoda construieste eroii ce vor lua parte la joc si ii plaseaza pe toti intr-un vector.
     */
    private void getPlayers() {
        int i;
        this.numberOfPlayers = this.scanner.nextInt();
        heroList = new Hero[numberOfPlayers];
        for (i = 0; i < numberOfPlayers; i++) {
            char heroType = this.scanner.next(".").charAt(0);
            int x = this.scanner.nextInt();
            int y = this.scanner.nextInt();
            HeroesFactory heroFactory = new HeroesFactory();
            heroList[i] = heroFactory.getHero(heroType, x, y, map.landType(x, y));
        }
    }

    /**
     *  Metoda construieste o matrice in care voi retine directiile in care se
     *  vor misca eroii.
     */
    private void getMapMove() {
        int i, j;
        this.numberOfRounds = this.scanner.nextInt();
        mapMove = new char[numberOfRounds][numberOfPlayers];
        this.scanner.nextLine();
        for (i = 0; i < numberOfRounds; i++) {
            String line = this.scanner.nextLine();
            for (j = 0; j < numberOfPlayers; j++) {
                mapMove[i][j] = line.charAt(j);
            }
        }
    }

    /**
     *  Metoda construieste o matrice cu tipul terenurilor.
     */
    private void getMapLand() {
        int i, j;
        char[][] mapLand = new char[n][m];
        for (i = 0; i < n; i++) {
            String line = this.scanner.nextLine();
            for (j = 0; j < m; j++) {
                mapLand[i][j] = line.charAt(j);
            }
        }
        map.buildMap(mapLand);
    }

    /**
     *  Prin intermediul acestei metode voi pune in aplicare regulile jocului.
     */
    private void playGame() {
        int i, j, k;
        for (int round = 0; round < numberOfRounds; round++) {
            System.out.println("~~ Round " + (round + 1) + " ~~");
            this.numberOfAngels = this.scanner.nextInt();
            angelsList = new AngelVisitor[numberOfAngels];

            for (k = 0; k < numberOfAngels; k++) {
                String angel = this.scanner.next();
                String[] partsOfAngel = angel.split(",");
                int x = Integer.parseInt(partsOfAngel[1]);
                int y = Integer.parseInt(partsOfAngel[2]);
                AngelsFactory angelsFactory = new AngelsFactory();
                angelsList[k] = angelsFactory.getAngel(partsOfAngel[0], x, y);
            }

            if (round != 0) {
                for (i = 0; i < numberOfPlayers; i++) {
                    if (!heroList[i].isDead()) {
                        if (heroList[i].getPassiveTurns() != 0) {
                            heroList[i].receivePassiveDamage();
                            if (heroList[i].getHp() <= 0) {
                                heroList[i].setDead();
                            }
                        }
                    }
                }

                for (i = 0; i < numberOfPlayers; i++) {
                    if (heroList[i].getImmobilized() == 0) {
                        chooseTheBestStrategy(heroList[i]);
                        if (heroList[i].getStrategy() != null) {
                            heroList[i].executeStrategy();
                        }
                    }
                }
            }

            moveHeroes(round);

            for (i = 0; i < numberOfPlayers - 1; i++) {
                for (j = i + 1; j < numberOfPlayers; j++) {
                    if (!heroList[i].isDead() && !heroList[j].isDead()
                            && heroList[i].getX() == heroList[j].getX()
                            && heroList[i].getY() == heroList[j].getY()) {

                        int currentLevel1 = heroList[i].getLevel();
                        int currentLevel2 = heroList[j].getLevel();

                        heroList[i].attack(heroList[j]);
                        heroList[j].attack(heroList[i]);

                        if (heroList[j].isDead() && !heroList[i].isDead()) {
                            heroList[j].notifyObserverForBeingKilledByAHero(heroList[i], j, i);
                            heroList[i].afterFightEffects(heroList[j].getLevel());
                            if (currentLevel1 != heroList[i].getLevel()) {
                                for (int level = currentLevel1 + 1; level <= heroList[i].getLevel();
                                     level++) {
                                    System.out.println(heroList[i].getName() + " " + i
                                            + " reached level " + level);
                                }
                            }
                        }
                        if (heroList[i].isDead() && !heroList[j].isDead()) {
                            heroList[i].notifyObserverForBeingKilledByAHero(heroList[j], i, j);
                            heroList[j].afterFightEffects(heroList[i].getLevel());
                            if (currentLevel2 != heroList[j].getLevel()) {
                                for (int level = currentLevel2 + 1; level <= heroList[j].getLevel();
                                     level++) {
                                    System.out.println(heroList[j].getName() + " " + j
                                            + " reached level " + level);
                                }
                            }
                        }

                        if (heroList[i].isDead() && heroList[j].isDead()) {
                            heroList[j].notifyObserverForBeingKilledByAHero(heroList[i], j, i);
                            heroList[i].notifyObserverForBeingKilledByAHero(heroList[j], i, j);
                        }
                    }
                }
            }
            angelsAppear();
            System.out.println();
        }
    }

    /**
     * Cu ajutorul acestei metode efectuez mutarea jucatorilor la fiecare runda.
     * @param round reprezinta numarul rundei de joc
     */
    private void moveHeroes(final int round) {
        for (int indexPlayer = 0; indexPlayer < numberOfPlayers; indexPlayer++) {
            int heroX = heroList[indexPlayer].getX();
            int heroY = heroList[indexPlayer].getY();
            if (!heroList[indexPlayer].isDead()
                    && heroList[indexPlayer].getImmobilized() == 0) {
                switch (mapMove[round][indexPlayer]) {
                    case 'U':
                        heroX--;
                        break;
                    case 'D':
                        heroX++;
                        break;
                    case 'L':
                        heroY--;
                        break;
                    case 'R':
                        heroY++;
                        break;
                    default:
                        break;
                }
            }
            if (heroX >= 0 && heroY >= 0) {
                heroList[indexPlayer].move(heroX, heroY, map.landType(heroX, heroY));
            } else {
                heroList[indexPlayer].moveOutsideTheMap(heroX, heroY);
            }
        }
    }

    /**
     * Prin intermediul acestei metode fac posibila aparitia ingerilor la finalul
     * fiecarei runde si ii ofer magicianului o parte dintre detaliile pe care
     * doreste sa le afle despre joc.
     */
    private void angelsAppear() {
        int i, k;
        for (k = 0; k < numberOfAngels; k++) {
            assert angelsList[k] != null;
            angelsList[k].notifyObserver();
            for (i = 0; i < numberOfPlayers; i++) {
                if (!heroList[i].isDead() && heroList[i].getX() == angelsList[k].getX()
                        && heroList[i].getY() == angelsList[k].getY()) {
                    int currentLevel = heroList[i].getLevel();
                    heroList[i].receiveAngelPower(angelsList[k]);
                    String verb;
                    if (angelsList[k].getAngelType().equals("good")) {
                        verb = "helped ";
                    } else {
                        verb = "hit ";
                    }
                    if (!angelsList[k].getName().equals("Spawner")
                            || (angelsList[k].getName().equals("Spawner")
                            && heroList[i].isDead())) {
                        heroList[i].notifyObserverForNewPowers(angelsList[k], verb, i);
                    }
                    if (angelsList[k].getName().equals("TheDoomer")) {
                        heroList[i].notifyObserverForBeingKilledByAnAngel(i);
                    }
                    if (angelsList[k].getName().equals("LevelUpAngel")) {
                        System.out.println(heroList[i].getName() + " " + i
                                + " reached level " + heroList[i].getLevel());
                    }
                    if (angelsList[k].getName().equals("XPAngel")
                            && heroList[i].getLevel() != currentLevel) {
                        for (int level = currentLevel + 1; level <= heroList[i].getLevel();
                             level++) {
                            System.out.println(heroList[i].getName() + " " + i
                                    + " reached level " + level);
                        }
                    }
                    if (angelsList[k].getName().equals("Dracula") && heroList[i].getHp() < 0) {
                        heroList[i].notifyObserverForBeingKilledByAnAngel(i);
                    }
                } else if (heroList[i].isDead() && heroList[i].getX() == angelsList[k].getX()
                        && heroList[i].getY() == angelsList[k].getY()
                        && angelsList[k].getName().equals("Spawner")) {
                    heroList[i].receiveAngelPower(angelsList[k]);
                    String verb = "helped ";
                    heroList[i].notifyObserverForNewPowers(angelsList[k], verb, i);
                    heroList[i].notifyObserverForRevival(i);
                }
            }
        }
    }

    /**
     * Aceasta metoda va fi de folos la afisarea rezultatelor finale ale jocului.
     */
    public void printPlayers() {
        System.out.println("~~ Results ~~");
        for (int i = 0; i < numberOfPlayers; i++) {
            StringBuilder output = new StringBuilder();
            if (heroList[i] instanceof Pyromancer) {
                output.append("P ");
            }
            if (heroList[i] instanceof Knight) {
                output.append("K ");
            }
            if (heroList[i] instanceof Rogue) {
                output.append("R ");
            }
            if (heroList[i] instanceof Wizard) {
                output.append("W ");
            }

            if (heroList[i].isDead()) {
                output.append("dead");
            } else {
                output.append(heroList[i].getLevel());
                output.append(" ");
                output.append(heroList[i].getXp());
                output.append(" ");
                output.append(heroList[i].getHp());
                output.append(" ");
                output.append(heroList[i].getX());
                output.append(" ");
                output.append(heroList[i].getY());
            }
            System.out.println(output);
        }

        System.out.println();
        this.scanner.close();
    }
}
