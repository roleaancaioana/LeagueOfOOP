package game;

import angels.AngelVisitor;

/**
 * Aceasta clasa ilustreaza atat prezenta magicianului in joc,
 * cat si comportamentul sau, acesta fiind notificat in mod constant cu
 * anumite detalii despre desfasurarea jocului.
 */
public final class Magician implements Observer {
    private static Magician magician = new Magician();

    private Magician() { }

    @Override
    public void updateAngelPosition(final AngelVisitor angelVisitor) {
        System.out.println("Angel " + angelVisitor.getName() + " was spawned at "
                + angelVisitor.getX() + " " + angelVisitor.getY());
    }

    @Override
    public void updateHeroPowers(final AngelVisitor angelVisitor, final Hero hero,
                          final String verb, final int i) {
        System.out.println(angelVisitor.getName() + " " + verb + hero.getName() + " " + i);
    }

    @Override
    public void updateHeroKilledByAnAngel(final Hero hero, final int i) {
        System.out.println("Player " + hero.getName() + " " + i + " was killed by an angel");
    }

    @Override
    public void updateHeroKilledByAHero(final Hero firstHero, final Hero secondHero,
                                 final int i, final int j) {
        System.out.println("Player " + firstHero.getName() + " " + i + " was killed by "
                + secondHero.getName() + " " + j);
    }

    @Override
    public void updateHeroBroughtToLifeByAnAngel(final Hero hero, final int i) {
        System.out.println("Player " + hero.getName() + " " + i
                + " was brought to life by an angel");
    }

    public static Magician getInstance() {
        return magician;
    }
}
