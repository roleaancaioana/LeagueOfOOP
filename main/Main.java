package main;

import game.Game;

final class Main {
    private Main() { }
    public static void main(final String[] args) {
        Game game = new Game(args[0], args[1]);
        game.printPlayers();
    }
}
