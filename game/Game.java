package game;

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
    private char[][] mapMove;

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

        moveAndFight();

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
            heroList[i] = heroFactory.getHero(heroType, x, y, mapLand[x][y]);
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
                mapLand[i][j] = line.charAt(j);
            }
        }
    }

    /**
     *  Cu ajutorul acestei metode efectuez mutarea jucatorilor la fiecare runda.
     *  Le voi aplica apoi jucatorilor damage-ul primit de la abilitatile overtime.
     *  Apoi in cazul in care 2 jucatori ajung in aceeasi locatie, ii pun sa se
     *  atace reciproc. Daca a avut loc o lupta intre 2 jucatori, atunci acestia
     *  isi vor modifica XP-ul si HP-ul in mod corespunzator.
     */
    private void moveAndFight() {
        int i, j;
        for (int round = 0; round < r; round++) {
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
                heroList[indexPlayer].move(heroX, heroY, mapLand[heroX][heroY]);
            }

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
            }

            for (i = 0; i < p - 1; i++) {
                for (j = i + 1; j < p; j++) {
                    if (!heroList[i].isDead() && !heroList[j].isDead()
                            && heroList[i].getX() == heroList[j].getX()
                            && heroList[i].getY() == heroList[j].getY()) {

                            heroList[i].attack(heroList[j]);
                            heroList[j].attack(heroList[i]);

                        if (heroList[i].isDead()) {
                            heroList[j].afterFightEffects(heroList[i].getLevel());
                        }
                        if (heroList[j].isDead()) {
                            heroList[i].afterFightEffects(heroList[j].getLevel());
                        }
                    }
                }
            }
        }
    }

    /**
     * Aceasta metoda ma va ajuta la formarea output-ului cerut.
     */
    public void printPlayers() {
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
