package game;

import angels.AngelVisitor;
import angels.AngelsFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Game {
    private Scanner scanner;
    private PrintWriter writer;
    private final int m;
    private final int n;
    private char[][] mapLand;
    private int p, r;
    private Hero[] heroList;
    private AngelVisitor[] angelsList;
    private char[][] mapMove;
    private int numberOfAngels;
    Map map = Map.getInstance();

    public Game(final String inputName, final String outputName) {
        try {
            this.scanner = new Scanner(new FileReader(inputName));
            this.writer = new PrintWriter(outputName);
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
        //scanner.useDelimiter(",");
        moveAndFight();

    }

    public void chooseTheBestStrategy(Hero hero) {
        int maxLevelHp = hero.getInitialHp() + hero.getHpPerLevel() * hero.getLevel();
        hero.setStrategy(null);
        if (maxLevelHp/3 < hero.getHp() && hero.getHp() < maxLevelHp/2 && hero.getHeroType() == 'K') {
            hero.setStrategy(new FirstKnightStrategy());
        }
        if (hero.getHp() < maxLevelHp/3 && hero.getHeroType() == 'K') {
            hero.setStrategy(new SecondKnightStrategy());
        }

        if (maxLevelHp/4 < hero.getHp() && hero.getHp() < maxLevelHp/3 && hero.getHeroType() == 'P') {
            hero.setStrategy(new FirstPyromancerStrategy());
        }
        if (hero.getHp() < maxLevelHp/4 && hero.getHeroType() == 'P') {
            hero.setStrategy(new SecondPyromancerStrategy());
        }

        if (maxLevelHp/7 < hero.getHp() && hero.getHp() < maxLevelHp/5 && hero.getHeroType() == 'R') {
            hero.setStrategy(new FirstRogueStrategy());
        }
        if (hero.getHp() < maxLevelHp/7 && hero.getHeroType() == 'R') {
            hero.setStrategy(new SecondRogueStrategy());
        }

        if (maxLevelHp/4 < hero.getHp() && hero.getHp() < maxLevelHp/2 && hero.getHeroType() == 'W') {
            hero.setStrategy(new FirstWizardStrategy());
        }

        if (hero.getHp() < maxLevelHp/4 && hero.getHeroType() == 'W') {
            hero.setStrategy(new SecondWizardStrategy());
        }
    }

    /**
     *  Metoda construieste eroii ce vor lua parte la joc si ii plaseaza pe toti intr-un vector.
     */
    private void getPlayers() {
        int i;
        this.p = this.scanner.nextInt();
        heroList = new Hero[p];
        for (i = 0; i < p; i++) {
            char heroType = this.scanner.next(".").charAt(0);
            int x = this.scanner.nextInt();
            int y = this.scanner.nextInt();
            HeroFactory heroFactory = new HeroFactory();
            heroList[i] = heroFactory.getHero(heroType, x, y, map.landType(x, y));
        }
    }

    /**
     *  Metoda construieste o matrice in care voi retine directiile in care se
     *  vor misca eroii.
     */
    private void getMapMove() {
        int i, j;
        this.r = this.scanner.nextInt();
        mapMove = new char[r][p];
        this.scanner.nextLine();
        for (i = 0; i < r; i++) {
            String line = this.scanner.nextLine();
            for (j = 0; j < p; j++) {
                mapMove[i][j] = line.charAt(j);
            }
        }
    }

    /**
     *  Metoda construieste o matrice in care voi retine tipul terenurilor.
     */
    private void getMapLand() {
        int i, j;
        mapLand = new char[n][m];
        for (i = 0; i < n; i++) {
            String line = this.scanner.nextLine();
            for (j = 0; j < m; j++) {
                //map.buildMap(line.charAt(j), i, j);
                mapLand[i][j] = line.charAt(j);
            }
        }
        map.buildMap(mapLand);
    }

    /**
     *  Cu ajutorul acestei metode efectuez mutarea jucatorilor la fiecare runda.
     *  Le voi aplica apoi jucatorilor damage-ul primit de la abilitatile overtime.
     *  Apoi in cazul in care 2 jucatori ajung in aceeasi locatie, ii pun sa se
     *  atace reciproc. Daca a avut loc o lupta intre 2 jucatori, atunci acestia
     *  isi vor modifica XP-ul si HP-ul in mod corespunzator.
     */
    private void moveAndFight() {
        int i, j, k;
        StringBuilder output = new StringBuilder();
        for (int round = 0; round < r; round++) {
            this.writer.println("~~ Round " + (round+1) + " ~~");
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
            System.out.println("~~ Round " + (round+1) + " ~~");
            for (i = 0; i < p; i++) {
                if (heroList[i].getName().equals("Wizard")) {
                    System.out.println(heroList[i].getName() + i + " " + heroList[i].getHp());
                }
            }
            System.out.println("=============================================");
//            for (i = 0; i < p; i++) {
//                System.out.println(heroList[i].getName() + " " + heroList[i].getLevel() + " " + heroList[i].getXp() + " " + heroList[i].getHp());
//            }
//            System.out.println("==============================================");
            if (round != 0) {
                for (i = 0; i < p; i++) {
                    if (!heroList[i].isDead()) {
                        if (heroList[i].getPassiveTurns() != 0) {
                            heroList[i].receivePassiveDamage();
                            if (heroList[i].getHp() <= 0) {
                                heroList[i].setDead();
                            }
                        }
                    }
                }
                if (round == 13) {
                    System.out.println(heroList[5].getName() + " 5 are hp-ul" + heroList[5].getHp());
                }

                for (i = 0; i < p; i++) {
                    if (heroList[i].getImmobilized() == 0) {
                        chooseTheBestStrategy(heroList[i]);
                        if (heroList[i].getStrategy() != null) {
                            if (round == 13 && i == 5) {
                                System.out.println(heroList[5].getName() + "5 aplica strategia" + heroList[5].getHp());
                            }
                            heroList[i].executeStrategy();
                            if (round == 13 && i == 5) {
                                System.out.println(heroList[5].getName() + "are un nou hp" + heroList[5].getHp());
                            }
                        }
                    }
                }
                //System.out.println(heroList[0].getHp() + " " + heroList[1].getHp());
//                Knight knight = (Knight) heroList[0];
//                System.out.println(knight.getSlamModifier() + " " + knight.getExecuteModifier());
            }

            for (int indexPlayer = 0; indexPlayer < p; indexPlayer++) {
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
                    heroList[indexPlayer].move(heroX, heroY, mapLand[heroX][heroY]);
                } else {
                    heroList[indexPlayer].moveOutsideTheMap(heroX, heroY);
                }
            }

//            System.out.println("=============================================");
//            for (i = 0; i < p; i++) {
//                System.out.println(heroList[i].getName() + " " + heroList[i].getLevel() + " " + heroList[i].getXp() + " " + heroList[i].getHp());
//            }
//            System.out.println("==============================================");

            for (i = 0; i < p - 1; i++) {
                for (j = i + 1; j < p; j++) {
                    if (!heroList[i].isDead() && !heroList[j].isDead()
                            && heroList[i].getX() == heroList[j].getX()
                            && heroList[i].getY() == heroList[j].getY()) {
                        int currentLevel1 = heroList[i].getLevel();
                        int currentLevel2 = heroList[j].getLevel();
                        if (round == 14) {
//                            Wizard w1 = (Wizard) heroList[i];
//                            Wizard w2 = (Wizard) heroList[j];
                            System.out.println("FII ATENT AICI!!!!!!!!!!!!");
                            //System.out.println(heroList[4].getHp());
                            System.out.println(heroList[i].getName() + i + " se bate cu " + heroList[j].getName() + j);
//                            System.out.println("Drain:" + i + w1.drainWizardModifier);
//                            System.out.println("Drain:" + j + w2.drainWizardModifier);
                        }
                        heroList[i].attack(heroList[j]);
                        heroList[j].attack(heroList[i]);
                        if (round == 14) {
                            System.out.println("FII ATENT AICI!!!!!!!!!!!!");
                            System.out.println(heroList[4].getHp());
                        }

                        if (heroList[j].isDead() && !heroList[i].isDead()) {
                            this.writer.println("Player " + heroList[j].getName() + " " + j + " was killed by " + heroList[i].getName() + " " + i);
                            heroList[i].afterFightEffects(heroList[j].getLevel());
                            if (currentLevel1 != heroList[i].getLevel()) {
                                for (int level = currentLevel1 + 1; level <= heroList[i].getLevel(); level++) {
                                    this.writer.println(heroList[i].getName() + " " + i + " reached level " + level);
                                }
                            }
                        }
                        if (heroList[i].isDead() && !heroList[j].isDead()) {
                            this.writer.println("Player " + heroList[i].getName() + " " + i + " was killed by " + heroList[j].getName() + " " + j);
                            heroList[j].afterFightEffects(heroList[i].getLevel());
                            if (currentLevel2 != heroList[j].getLevel()) {
                                for (int level = currentLevel2 + 1; level <= heroList[j].getLevel(); level++) {
                                    this.writer.println(heroList[j].getName() + " " + j + " reached level " + level);
                                }
                            }
                        }

                        if (heroList[i].isDead() && heroList[j].isDead()) {
                            this.writer.println("Player " + heroList[j].getName() + " " + j + " was killed by " + heroList[i].getName() + " " + i);
                            this.writer.println("Player " + heroList[i].getName() + " " + i + " was killed by " + heroList[j].getName() + " " + j);
                        }
                    }
                }
            }

//            System.out.println("=============================================");
//            for (i = 0; i < p; i++) {
//                System.out.println(heroList[i].getName() + " " + heroList[i].getLevel() + " " + heroList[i].getXp() + " " + heroList[i].getHp());
//            }
//            System.out.println("==============================================");
            for (k = 0; k < numberOfAngels; k++) {
                this.writer.println("Angel " + angelsList[k].getName() + " was spawned at " + angelsList[k].getX() + " " + angelsList[k].getY());
                for (i = 0; i < p; i++) {
                    if (!heroList[i].isDead() && heroList[i].getX() == angelsList[k].getX()
                            && heroList[i].getY() == angelsList[k].getY()) {
                        int currentLevel = heroList[i].getLevel();
                        heroList[i].receiveAngelPower(angelsList[k]);
                        //System.out.println(heroList[i].getName() + " " + heroList[i].getXp());
                        String verb;
                        if (angelsList[k].getAngelType().equals("good")) {
                            verb = "helped ";
                        } else {
                            verb = "hit ";
                        }
                        if (!angelsList[k].getName().equals("Spawner") || (angelsList[k].getName().equals("Spawner") && heroList[i].isDead())) {
                            this.writer.println(angelsList[k].getName() + " " + verb + heroList[i].getName() + " " + i);
                        }
                        if (angelsList[k].getName().equals("TheDoomer")) {
                            this.writer.println("Player " + heroList[i].getName() + " " + i + " was killed by an angel");
                        }
                        if (angelsList[k].getName().equals("LevelUpAngel")) {
                            this.writer.println(heroList[i].getName() + " " + i + " reached level " + heroList[i].getLevel());
                        }
                        if (angelsList[k].getName().equals("XPAngel") && heroList[i].getLevel() != currentLevel) {
                            for (int level = currentLevel + 1; level <= heroList[i].getLevel(); level++) {
                                this.writer.println(heroList[i].getName() + " " + i + " reached level " + level);
                            }
                        }
                        if (angelsList[k].getName().equals("Dracula") && heroList[i].getHp() < 0) {
                            this.writer.println("Player " + heroList[i].getName() + " " + i + " was killed by an angel");
                        }
                    } else if (heroList[i].isDead() && heroList[i].getX() == angelsList[k].getX()
                            && heroList[i].getY() == angelsList[k].getY() && angelsList[k].getName().equals("Spawner")) {
                        //heroList[i].setXp(0);
                        heroList[i].receiveAngelPower(angelsList[k]);
                        this.writer.println(angelsList[k].getName() + " " + "helped " + heroList[i].getName() + " " + i);
                        this.writer.println("Player " + heroList[i].getName() + " " + i + " was brought to life by an angel");
                    }
                }
            }

            //System.out.println(heroList[0].getHp() + " " + heroList[1].getHp());
//            System.out.println("=============================================");
//            for (i = 0; i < p; i++) {
//                System.out.println(heroList[i].getName() + " " + heroList[i].getLevel() + " " + heroList[i].getXp() + " " + heroList[i].getHp());
//            }
//            System.out.println("==============================================");
            this.writer.println("");
        }
    }

    /**
     * Aceasta metoda ma va ajuta la formarea output-ului cerut.
     */
    public void printPlayers() {
        this.writer.println("~~ Results ~~");
        for (int i = 0; i < p; i++) {
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
            this.writer.println(output);
        }

        this.writer.println("");

        this.writer.close();
        this.scanner.close();
    }
}
