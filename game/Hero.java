package game;

import angels.AngelVisitor;
import strategies.Strategy;

/**
 * Aceasta clasa intruneste caracteristicile comune ale tuturor eroilor.
 */
public abstract class Hero {
    private int xp, hp, level, initialHp, hpPerLevel;
    private int x, y;
    private char land;
    private int damage;
    private int damageOvertime;
    private boolean dead;
    private String name;
    private int passiveTurns;
    private int immobilized;
    private char heroType;
    private Strategy strategy;
    private Magician magician = Magician.getInstance();

    Hero(final char heroType, final int x, final int y, final char land, final String name) {
        this.xp = 0;
        this.level = 0;
        this.x = x;
        this.y = y;
        this.damage = 0;
        this.damageOvertime = 0;
        this.immobilized = 0;
        this.land = land;
        this.passiveTurns = 0;
        this.heroType = heroType;
        this.strategy = null;
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    public final void setStrategy(final Strategy strategy) {
        this.strategy = strategy;
    }

    public final Strategy getStrategy() {
        return strategy;
    }

    final char getHeroType() {
        return heroType;
    }

    final void becomeImmobilized(final int rounds) {
        this.immobilized = rounds;
    }

    final void setPassiveTurns(final int passiveTurns) {
        this.passiveTurns = passiveTurns;
    }

    final void setDamageOvertime(final int damageOvertime) {
        this.damageOvertime = damageOvertime;
    }

    final char getLand() {
        return land;
    }

    public final int getInitialHp() {
        return initialHp;
    }

    final int getPassiveTurns() {
        return passiveTurns;
    }

    final void receivePassiveDamage() {
        this.hp -= this.damageOvertime;
        this.passiveTurns--;
    }

    final void setDead() {
        this.dead = true;
    }

    final void setInitialHp(final int initialHp) {
        this.initialHp = initialHp;
    }

    public final int getLevel() {
        return level;
    }

    public final int getHp() {
        return hp;
    }

    public final void setHp(final int hp) {
        this.hp = hp;
    }

    public final int getXp() {
        return xp;
    }

    public final int getHpPerLevel() {
        return hpPerLevel;
    }

    final void setHpPerLevel(final int hpPerLevel) {
        this.hpPerLevel = hpPerLevel;
    }

    public final boolean isDead() {
        return dead;
    }

    final int getX() {
        return x;
    }

    final int getY() {
        return y;
    }

    final int getImmobilized() {
        return immobilized;
    }

    public final void setDead(final boolean dead) {
        this.dead = dead;
    }

    public final void setXp(final int xp) {
        this.xp = xp;
    }

    public final void setLevel(final int level) {
        this.level = level;
    }

    public final void setHpLevelUp() {
        this.hp = initialHp + hpPerLevel * this.level;
    }

    /**
     * Aceasta metoda ma va ajuta sa-i acord eroului care a castigat o lupta
     * XP-ul corespunzator.
     * @param opponentLevel reprezinta nivelul eroului care a fost invins
     */
    private void getXpWinner(final int opponentLevel) {
        final int xpCoefficient = 40;
        final int points = 200;
        int additionalXp = points - (this.level - opponentLevel)
                * xpCoefficient;
        this.xp += Math.max(0, additionalXp);
    }

    /**
     * Aceasta metoda ma va ajuta sa modific nivelul unui erou la finalul
     * unei lupte in care acesta a fost implicat.
     */
    public final void levelUp() {
        final int xpLevelOne = 250;
        final int coefficient = 50;
        int xpLevelUp = xpLevelOne + this.level * coefficient;
        if (this.xp >= xpLevelUp) {
            int levelCurrent = this.level;
            this.level = ((this.xp - xpLevelOne) / coefficient) + 1;
            if (levelCurrent != this.level) {
                this.hp = initialHp + hpPerLevel * this.level;
            }
        }
    }

    private void receiveDamage() {
        this.hp -= this.damage;
    }

    final void setDamage(final int damage) {
        this.damage = damage;
    }

    /**
     * Prin intermediul acestei metode orice erou care este implicat
     * intr-o lupta va primi la final un damage activ care poate fi
     * fatal sau nu. Daca este fatal, atunci vom marca faptul ca eroul
     * a murit.
     */
    final void getActiveDamage() {
        this.receiveDamage();
        if (this.hp <= 0) {
            this.dead = true;
        }
    }

    /**
     * Aceasta metoda ma va ajuta la mutarea jucatorilor intr-o alta locatie
     * de pe harta.
     * @param newX reprezinta randul pe care se va muta eroul
     * @param newY reprezinta coloana pe care se va muta eroul
     * @param newLand reprezinta tipul terenului pe care se va muta eroul
     */
    final void move(final int newX, final int newY, final char newLand) {
        if (this.immobilized == 0) {
                this.land = newLand;
                this.x = newX;
                this.y = newY;
        }
        if (this.immobilized != 0) {
            this.immobilized--;
        }
    }

    /**
     * Aceasta metoda ma va ajuta sa mut jucatorii in afara hartii.
     * @param newX reprezinta randul pe care se va muta eroul
     * @param newY reprezinta coloana pe care se va muta eroul
     */
    final void moveOutsideTheMap(final int newX, final int newY) {
        if (this.immobilized == 0) {
            this.x = newX;
            this.y = newY;
        }
        if (this.immobilized != 0) {
            this.immobilized--;
        }
    }

    /**
     * Aceasta metoda ma va ajuta sa-i modific eroului care a fost implicat intr-o lupta
     * XP-ul si nivelul.
     * @param heroLevel reprezinta eroul caruia urmeaza sa-i modific XP-ul si nivelul.
     */
    final void afterFightEffects(final int heroLevel) {
        getXpWinner(heroLevel);
        levelUp();
    }

    final void notifyObserverForNewPowers(final AngelVisitor angelVisitor,
                                          final String verb, final int i) {
        magician.updateHeroPowers(angelVisitor, this, verb, i);
    }

    final void notifyObserverForBeingKilledByAnAngel(final int i) {
        magician.updateHeroKilledByAnAngel(this, i);
    }

    final void notifyObserverForBeingKilledByAHero(final Hero opponent,
                                                   final int i, final int j) {
        magician.updateHeroKilledByAHero(this, opponent, i, j);
    }

    final void notifyObserverForRevival(final int i) {
        magician.updateHeroBroughtToLifeByAnAngel(this, i);
    }

    public abstract void attack(Hero hero);
    public abstract void receiveAngelPower(AngelVisitor angelVisitor);
    public abstract void changeAllModifiers(float change);
    public abstract void executeStrategy();
}
