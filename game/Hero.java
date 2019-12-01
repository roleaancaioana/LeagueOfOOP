package game;

public abstract class Hero {
    private int xp, hp, level, initialHp, hpPerLevel;
    private int x, y;
    private char land;
    private int damage;
    private int damageOvertime;
    private boolean dead;
    private int passiveTurns;
    private int immobilized;

    Hero(final int x, final int y, final char land) {
        this.xp = 0;
        this.level = 0;
        this.x = x;
        this.y = y;
        this.damage = 0;
        this.damageOvertime = 0;
        this.immobilized = 0;
        this.land = land;
        this.passiveTurns = 0;
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

    final int getInitialHp() {
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

    final int getLevel() {
        return level;
    }

    final int getHp() {
        return hp;
    }

    final void setHp(final int hp) {
        this.hp = hp;
    }

    final int getXp() {
        return xp;
    }

    final int getHpPerLevel() {
        return hpPerLevel;
    }

    final void setHpPerLevel(final int hpPerLevel) {
        this.hpPerLevel = hpPerLevel;
    }

    final boolean isDead() {
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
    private void levelUp() {
        final int xpLevelOne = 250;
        final int coefficient = 50;
        int xpLevelUp = xpLevelOne + this.level * coefficient;
        if (this.xp > xpLevelUp) {
            int levelCurrent = this.level;
            this.level = ((this.xp - xpLevelOne) / coefficient) + 1;
            if (levelCurrent != this.level) {
                this.hp = initialHp + hpPerLevel * (this.level - levelCurrent); // revine la 100% hp
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
     * Aceasta metoda ma va ajuta sa-i modific eroului care a fost implicat intr-o lupta
     * XP-ul si nivelul.
     * @param heroLevel
     */
    final void afterFightEffects(final int heroLevel) {
        getXpWinner(heroLevel);
        levelUp();
    }

    public abstract void attack(Hero hero);

    public abstract void accept(Visitor visitor);
}
