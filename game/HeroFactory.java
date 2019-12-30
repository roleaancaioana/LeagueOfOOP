package game;

/**
 * Aceasta clasa ma va ajuta la crearea eroilor.
 */
final class HeroFactory {
    Hero getHero(final char heroType, final int x, final int y, final char land) {
        if (heroType == 'P') {
<<<<<<< HEAD
            return new Pyromancer(heroType, x, y, land);
        }
        if (heroType == 'K') {
            return new Knight(heroType, x, y, land);
        }
        if (heroType == 'W') {
            return new Wizard(heroType, x, y, land);
        }
        if (heroType == 'R') {
            return  new Rogue(heroType, x, y, land);
=======
            return new Pyromancer(x, y, land);
        }
        if (heroType == 'K') {
            return new Knight(x, y, land);
        }
        if (heroType == 'W') {
            return new Wizard(x, y, land);
        }
        if (heroType == 'R') {
            return  new Rogue(x, y, land);
>>>>>>> 6054d41c3db544ffcffa306e318828583d0ff416
        }
        return null;
    }
}
