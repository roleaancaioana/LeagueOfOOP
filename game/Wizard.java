package game;

import angels.AngelVisitor;

public class Wizard extends Hero implements Fighter {
    private final int hpWizard = 400;
    private final int hpPerLevelWizard = 30;
    private float deflectModifier, drainModifier;
    private float deflectRogueModifier, drainRogueModifier;
    public float drainWizardModifier;
    private float deflectKnightModifier, drainKnightModifier;
    private float deflectPyromancerModifier, drainPyromancerModifier;

    Wizard(final char heroType, final int x, final int y, final char land) {
        super(heroType, x, y, land, "Wizard");
        super.setInitialHp(hpWizard);
        super.setHpPerLevel(hpPerLevelWizard);
        super.setHp(hpWizard);
        this.drainRogueModifier = 0.8f;
        this.deflectRogueModifier = 1.2f;
        this.deflectKnightModifier = 1.4f;
        this.drainKnightModifier = 1.2f;
        this.deflectPyromancerModifier = 1.3f;
        this.drainPyromancerModifier = 0.9f;
        this.drainWizardModifier = 1.05f;
    }

    @Override
    public void executeStrategy() {
        Strategy strategy = super.getStrategy();
        int newHp = strategy.changeHp(super.getHp());
        super.setHp(newHp);
        this.drainRogueModifier = strategy.changeDamage(this.drainRogueModifier);
        this.deflectRogueModifier = strategy.changeDamage(this.deflectRogueModifier);
        this.deflectKnightModifier = strategy.changeDamage(this.deflectKnightModifier);
        this.drainKnightModifier = strategy.changeDamage(this.drainKnightModifier);
        this.deflectPyromancerModifier = strategy.changeDamage(this.deflectPyromancerModifier);
        this.drainPyromancerModifier = strategy.changeDamage(this.drainPyromancerModifier);
        this.drainWizardModifier = strategy.changeDamage(this.drainWizardModifier);
    }

    @Override
    public void changeAllModifiers(float change) {
        this.drainRogueModifier += change;
        this.deflectRogueModifier += change;
        this.deflectKnightModifier += change;
        this.drainKnightModifier += change;
        this.deflectPyromancerModifier += change;
        this.drainPyromancerModifier += change;
        this.drainWizardModifier += change;
    }

    /**
     * Aceasta metoda ilustreaza modul de atac al unui erou de tip Wizard.
     * @param hero reprezinta oponentul eroului de tip Wizard.
     */
    @Override
    public final void attack(final Hero hero) {
        if (hero instanceof Rogue) {
            this.drainModifier = drainRogueModifier;
            this.deflectModifier = deflectRogueModifier;
        }
        if (hero instanceof Wizard) {
            this.drainModifier = drainWizardModifier;
        }
        if (hero instanceof Pyromancer) {
            this.drainModifier = drainPyromancerModifier;
            this.deflectModifier = deflectPyromancerModifier;
        }
        if (hero instanceof Knight) {
            this.drainModifier = drainKnightModifier;
            this.deflectModifier = deflectKnightModifier;
        }

        int intDrainDamage = getDrainDamage(hero);
        int intDeflectDamage = getDeflectDamage(hero);
        int totalActiveDamage = intDeflectDamage + intDrainDamage;

        hero.setDamage(totalActiveDamage);
        hero.getActiveDamage();
    }

    /**
     * Metoda ma va ajuta la calculul damage-ului rezultat din abilitatea
     * Drain a acestui erou.
     * @param hero reprezinta eroul cu care se va duela acest personaj
     * @return valoarea damage-ului rezultat din abilitatea Drain
     */
    private int getDrainDamage(final Hero hero) {
        final float coefficient = 0.3f;
        final float initialDrainPercentage = 0.2f;
        final float perLevelDrainPercentage = 0.05f;
        final float desertModifier = 1.1f;

        float drainPercentage = initialDrainPercentage + perLevelDrainPercentage * this.getLevel();
        float maxHp = hero.getInitialHp() + hero.getHpPerLevel() * hero.getLevel();
        float baseHp = Math.min(coefficient * maxHp,
                (float) hero.getHp());

        /*
         Aplic amplificatorul de rasa asupra procentului.
         */
        drainPercentage *= drainModifier;

        float drainDamage = drainPercentage * baseHp;
        /*
         Aplic amplificatorul corespunzator terenului Desert.
        */
        if (this.getLand() == 'D') {
            drainDamage *= desertModifier;
        }

        return (Math.round(drainDamage));
    }

    /**
     * Metoda ma va ajutat la calculul damage-ului rezultat din abilitatea
     * Deflect a acestui erou.
     * @param hero reprezinta eroul cu care se va lupta acest personaj
     * @return valoarea damage-ului rezultat din abilitatea Deflect
     */
    private int getDeflectDamage(final Hero hero) {
        final float desertModifier = 1.1f;
        final float initialDeflectPercentage = 0.35f;
        final float perLevelDeflectPercentage = 0.02f;
        final float maxPercentage = 0.7f;
        int damageWithoutRaceModifiers = 0;

        float deflectPercentage = initialDeflectPercentage
                + this.getLevel() * perLevelDeflectPercentage;
        if (deflectPercentage > maxPercentage) {
            deflectPercentage = maxPercentage;
        }

        if (hero instanceof Rogue) {
            damageWithoutRaceModifiers = ((Rogue) hero).damageWithoutRaceModifiers();
        }
        if (hero instanceof Pyromancer) {
            damageWithoutRaceModifiers = ((Pyromancer) hero).damageWithoutRaceModifiers();
        }
        if (hero instanceof Knight) {
            damageWithoutRaceModifiers = ((Knight) hero).damageWithoutRaceModifiers(this);
        }

        float deflectDamage = damageWithoutRaceModifiers * deflectPercentage;
        /*
         Aplic amplificatorul de rasa.
         */
        deflectDamage *= deflectModifier;

        /*
         Aplic amplificatorul corespunzator terenului Desert.
         */
        if (this.getLand() == 'D') {
            deflectDamage *= desertModifier;
        }
        return (Math.round(deflectDamage));
    }

    @Override
    public final void accept(final FighterVisitor v) {
        v.attack(this);
    }

    public float getDrainModifier() {
        return drainModifier;
    }

    public void setDrainModifier(float drainModifier) {
        this.drainModifier = drainModifier;
    }

    public float getDeflectModifier() {
        return deflectModifier;
    }

    public void setDeflectModifier(float deflectModifier) {
        this.deflectModifier = deflectModifier;
    }

    @Override
    public void receiveAngelPower(AngelVisitor angelVisitor) {
        angelVisitor.angelPower(this);
    }
}
