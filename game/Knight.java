package game;

import angels.AngelVisitor;

public class Knight extends Hero implements Fighter, Angel {
    private final float executeBaseDamage = 200;
    private final float executeBaseDamagePerLevel = 30;
    private final float slamBaseDamage = 100;
    private final float slamBaseDamagePerLevel = 40;
    private final float hpLimitCoefficient = 0.2f;
    private final float maxHpLimit = 0.6f;
    private final float hundreadPercentage = 0.001f;
    private final float landModifier = 1.15f;
    private final int hpKnight = 900;
    private final int hpPerLevelKnight = 80;
    float executeModifier, slamModifier;

    Knight(final char heroType, final int x, final int y, final char land) {
        super(heroType, x, y, land);
        super.setInitialHp(hpKnight);
        super.setHpPerLevel(hpPerLevelKnight);
        super.setHp(hpKnight);
    }

    @Override
    public void executeStrategy() {
        Strategy strategy = super.getStrategy();
        int newHp = strategy.changeHp(super.getHp());
        super.setHp(newHp);
        this.slamModifier = strategy.changeDamage(this.slamModifier);
        this.executeModifier = strategy.changeDamage(this.executeModifier);
    }

    /**
     * Aceasta metoda ilustreaza modul de atac al unui erou de tip Knight.
     * @param hero reprezinta oponentul eroului Knight.
     */
    @Override
    public final void attack(final Hero hero) {
        float executeDamage, slamDamage;
        int roundsImobilised = 1;

        KnightVisitor visitor = new KnightVisitor();
        hero.accept(visitor);
        executeModifier = visitor.getExecuteModifier();
        slamModifier = visitor.getSlamModifier();

        float maxHp = hero.getInitialHp() + hero.getHpPerLevel() * hero.getLevel();
        float hpLimit = hpLimitCoefficient * maxHp;

        hpLimit += (float) this.getLevel() * hundreadPercentage * maxHp;

        if (hpLimit > maxHpLimit) {
            hpLimit = maxHpLimit;
        }

        if ((float) hero.getHp() < hpLimit) {
            executeDamage = hero.getHp(); /* damage-ul este egal cu hp-ul adversarului */
            slamDamage = 0; /* adversarul e deja mort si nu mai e nevoie sa-i aplicam slamDamage */
        } else {
            /*
            Calculez damage-ul dat de abilitatea execute.
             */
            executeDamage = executeBaseDamage + executeBaseDamagePerLevel * this.getLevel();
            executeDamage *= executeModifier;

            /*
            Calculez damage-ul dat de abilitatea slam.
             */
            slamDamage = slamBaseDamage + slamBaseDamagePerLevel * this.getLevel();
            slamDamage *= slamModifier;

            /*
            Aplic amplificatorul corespunzator terenului Land.
             */
            if (this.getLand() == 'L') {
                executeDamage *= landModifier;
                slamDamage *= landModifier;
            }
        }

        int intExecuteDamage = Math.round(executeDamage);
        int intSlamDamage = Math.round(slamDamage);
        int totalDamage = intExecuteDamage + intSlamDamage;

        hero.becomeImmobilized(roundsImobilised);
        hero.setDamage(totalDamage);
        hero.getActiveDamage();
    }

    /**
     * Aceasta metoda ma va ajuta la calculul damage-ului total, fara amplificatorii
     * de rasa, pe care il da acest erou Knight unui erou de tip Wizard.
     * @param hero reprezinta eroul Wizard cu care se va lupta acest erou Knight
     * @return damage-ul total dat, fara amplificatorii de rasa
     */
    final int damageWithoutRaceModifiers(final Hero hero) {
        float executeDamageGived, slamDamageGived;

        float maxHp = hero.getInitialHp() + hero.getHpPerLevel() * hero.getLevel();
        float hpLimit = hpLimitCoefficient * maxHp;

        hpLimit += (float) this.getLevel() * hundreadPercentage * maxHp;

        if (hpLimit > maxHpLimit) {
            hpLimit = maxHpLimit;
        }

        if ((float) hero.getHp() < hpLimit && hero.getHp() > 0) {
            executeDamageGived = hero.getHp();
            slamDamageGived = 0;
        } else {
            executeDamageGived = executeBaseDamage + executeBaseDamagePerLevel * this.getLevel();

            slamDamageGived = slamBaseDamage + slamBaseDamagePerLevel * this.getLevel();

            if (this.getLand() == 'L') {
                executeDamageGived *= landModifier;
                slamDamageGived *= landModifier;
            }

        }

        int intExecuteDamageGived = Math.round(executeDamageGived);
        int intSlamDamageGived = Math.round(slamDamageGived);
        return (intExecuteDamageGived + intSlamDamageGived);
    }

    @Override
    public final void accept(final FighterVisitor v) {
        v.attack(this);
    }

    public float getSlamModifier() {
        return slamModifier;
    }

    public void setSlamModifier(float slamModifier) {
        this.slamModifier = slamModifier;
    }

    public float getExecuteModifier() {
        return executeModifier;
    }

    public void setExecuteModifier(float executeModifier) {
        this.executeModifier = executeModifier;
    }

    @Override
    public void receiveAngelPower(AngelVisitor angelVisitor) {
        angelVisitor.angelPower(this);
    }
}
