package main;

import game.Game;

import java.io.File;
import java.io.PrintStream;

final class Main {
    private Main() { }
    public static void main(final String[] args) {
        try {
            PrintStream o = new PrintStream(new File(args[1]));
            System.setOut(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Game game = new Game(args[0]);
        game.printPlayers();
    }
}
