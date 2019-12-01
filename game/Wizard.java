package game;

public class Wizard extends Hero implements Visitable {
    private final int hpWizard = 400;
    private final int hpPerLevelWizard = 30;

    Wizard(final int x, final int y, final char land) {
        super(x, y, land);
        super.setInitialHp(hpWizard);
        super.setHpPerLevel(hpPerLevelWizard);
        super.setHp(hpWizard);
    }

    /**
     * Aceasta metoda ilustreaza modul de atac al unui erou de tip Wizard.
     * @param hero reprezinta oponentul eroului de tip Wizard.
     */
    @Override
    public final void attack(final Hero hero) {
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

        WizardVisitor visitor = new WizardVisitor();
        hero.accept(visitor);
        float drainModifier = visitor.getDrainModifier();

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

        WizardVisitor visitor = new WizardVisitor();
        hero.accept(visitor);
        float deflectModifier = visitor.getDeflectModifier();

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
    public final void accept(final Visitor v) {
        v.attack(this);
    }
}
