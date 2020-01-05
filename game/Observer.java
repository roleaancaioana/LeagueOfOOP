package game;

import angels.AngelVisitor;

interface Observer {
    void updateAngelPosition(AngelVisitor angelVisitor);
    void updateHeroPowers(AngelVisitor angelVisitor, Hero hero,
                          String verb, int i);
    void updateHeroKilledByAnAngel(Hero hero, int i);
    void updateHeroKilledByAHero(Hero firstHero, Hero secondHero,
                                 int i, int j);
    void updateHeroBroughtToLifeByAnAngel(Hero hero, int i);
}
