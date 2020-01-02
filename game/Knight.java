package game;

import angels.AngelVisitor;

public class Knight extends Hero implements Fighter {
    private final float executeBaseDamage = 200;
    private final float executeBaseDamagePerLevel = 30;
    private final float slamBaseDamage = 100;
    private final float slamBaseDamagePerLevel = 40;
    private final float hpLimitCoefficient = 0.2f;
    private final float maxHpLimit = 0.6f;
    private final float hundreadPercentage = 0.01f;
    private final float landModifier = 1.15f;
    private final int hpKnight = 900;
    private final int hpPerLevelKnight = 80;
    private float executeModifier, slamModifier;
    private boolean executeModifierIsInitialZero, slamModifierisInitialZero = false;
    private float executeKnightModifier, slamKnightModifier;
    private float executeRogueModifier, slamRogueModifier;
    private float executePyromancerModifier, slamPyromancerModifier;
    private float executeWizardModifier, slamWizardModifier;

    Knight(final char heroType, final int x, final int y, final char land) {
        super(heroType, x, y, land, "Knight");
        super.setInitialHp(hpKnight);
        super.setHpPerLevel(hpPerLevelKnight);
        super.setHp(hpKnight);
        this.executeModifier = 0;
        this.slamModifier = 0;
        this.executeRogueModifier = 1.15f;
        this.slamRogueModifier = 0.8f;
        this.executePyromancerModifier = 1.1f;
        this.slamPyromancerModifier = 0.9f;
        this.executeWizardModifier = 0.8f;
        this.slamWizardModifier = 1.05f;
        this.executeKnightModifier = 1.0f;
        this.slamKnightModifier = 1.2f;
    }

    @Override
    public void executeStrategy() {
        Strategy strategy = super.getStrategy();
        int newHp = strategy.changeHp(super.getHp());
        super.setHp(newHp);
        this.executeRogueModifier = strategy.changeDamage(this.executeRogueModifier);
        this.slamRogueModifier = strategy.changeDamage(this.slamRogueModifier);
        this.executePyromancerModifier = strategy.changeDamage(this.executePyromancerModifier);
        this.slamPyromancerModifier = strategy.changeDamage(this.slamPyromancerModifier);
        this.executeWizardModifier = strategy.changeDamage(this.executeWizardModifier);
        this.slamWizardModifier = strategy.changeDamage(this.slamWizardModifier);
        //this.executeKnightModifier = strategy.changeDamage(this.executeKnightModifier);?????????????
        this.slamKnightModifier = strategy.changeDamage(this.slamKnightModifier);
    }

    @Override
    public void changeAllModifiers(float change) {
        this.executePyromancerModifier += change;
        this.slamPyromancerModifier += change;
        this.executeWizardModifier += change;
        this.slamWizardModifier += change;
        //this.executeKnightModifier += change;
        this.slamKnightModifier += change;
        this.executeRogueModifier += change;
        this.slamRogueModifier += change;
    }

    /**
     * Aceasta metoda ilustreaza modul de atac al unui erou de tip Knight.
     * @param hero reprezinta oponentul eroului Knight.
     */
    @Override
    public final void attack(final Hero hero) {
        float executeDamage, slamDamage;
        int roundsImobilised = 1;

        if (hero instanceof Pyromancer) {
            this.executeModifier = executePyromancerModifier;
            this.slamModifier = slamPyromancerModifier;
        }
        if (hero instanceof Rogue) {
            this.executeModifier = executeRogueModifier;
            this.slamModifier = slamRogueModifier;
        }
        if (hero instanceof Wizard) {
            this.executeModifier = executeWizardModifier;
            this.slamModifier = slamWizardModifier;
        }
        if (hero instanceof Knight) {
            this.executeModifier = executeKnightModifier;
            this.slamModifier = slamKnightModifier;
        }

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
            executeDamage = Math.round(executeDamage); // new!!!!!!

            /*
            Calculez damage-ul dat de abilitatea slam.
             */
            slamDamage = slamBaseDamage + slamBaseDamagePerLevel * this.getLevel();
            slamDamage *= slamModifier;
            slamDamage = Math.round(slamDamage); // new!!!!!!!

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

    public boolean isExecuteModifierIsInitialZero() {
        return executeModifierIsInitialZero;
    }

    public boolean isSlamModifierisInitialZero() {
        return slamModifierisInitialZero;
    }
}
