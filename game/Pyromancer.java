package game;

import angels.AngelVisitor;
import strategies.Strategy;

/**
 * Aceasta clasa ilustreaza comportamentul unui erou de tip Pyromancer.
 */
public class Pyromancer extends Hero {
    private final int hpPyromancer = 500;
    private final int hpPerLevelPyromancer = 50;
    private final float initialRogueModifier = 0.8f;
    private final float initialKnightModifier = 1.2f;
    private final float initialPyromancerModifier = 0.9f;
    private final float initialWizardModifier = 1.05f;
    private float modifier;
    private float rogueModifier;
    private float knightModifier;
    private float pyromancerModifier;
    private float wizardModifier;

    Pyromancer(final char heroType, final int x, final int y, final char land) {
        super(heroType, x, y, land, "Pyromancer");
        super.setInitialHp(hpPyromancer);
        super.setHpPerLevel(hpPerLevelPyromancer);
        super.setHp(hpPyromancer);
        this.pyromancerModifier = initialPyromancerModifier;
        this.wizardModifier = initialWizardModifier;
        this.rogueModifier = initialRogueModifier;
        this.knightModifier = initialKnightModifier;
    }

    @Override
    public final void executeStrategy() {
        Strategy strategy = super.getStrategy();
        int newHp = strategy.changeHp(super.getHp());
        super.setHp(newHp);
        this.rogueModifier = strategy.changeDamage(this.rogueModifier);
        this.pyromancerModifier = strategy.changeDamage(this.pyromancerModifier);
        this.wizardModifier = strategy.changeDamage(this.wizardModifier);
        this.knightModifier = strategy.changeDamage(this.knightModifier);
    }

    @Override
    public final void changeAllModifiers(final float change) {
        rogueModifier += change;
        knightModifier += change;
        wizardModifier += change;
        pyromancerModifier += change;
    }

    /**
     * Aceasta metoda ilustreaza modul de atac al unui erou de tip Pyromancer.
     * @param hero reprezinta oponentul eroului de tip Pyromancer.
     */
    @Override
    public final void attack(final Hero hero) {
        int passiveTurns = 2;

        if (hero instanceof Pyromancer) {
            this.modifier = pyromancerModifier;
        }
        if (hero instanceof Rogue) {
            this.modifier = rogueModifier;
        }
        if (hero instanceof Knight) {
            this.modifier = knightModifier;
        }
        if (hero instanceof Wizard) {
            this.modifier = wizardModifier;
        }

        int intFireblastDamage = getFireblastDamage();
        int intTotalIgniteBaseDamage = getActiveIgniteDamage();
        int intTotalIgniteDamagePerTurn = getPassiveIgniteDamage();

        int totalActiveDamage = intFireblastDamage + intTotalIgniteBaseDamage;

        hero.setPassiveTurns(passiveTurns);
        hero.setDamage(totalActiveDamage);
        hero.setDamageOvertime(intTotalIgniteDamagePerTurn);
        hero.getActiveDamage();
    }

    /**
     * Aceasta metoda ma va ajuta la calculul damage-ului rezultat in urma
     * aplicarii abilitatii Fireblast.
     * @return valoarea damage-ului dat de abilitatea Fireblast
     */
    private int getFireblastDamage() {
        final float initialFireblastDamage = 350;
        final float fireblastDamagePerLevel = 50;
        final float volcanicModifier = 1.25f;

        float fireblastDamage = initialFireblastDamage + fireblastDamagePerLevel * this.getLevel();

        if (this.getLand() == 'V') {
            fireblastDamage *= volcanicModifier;
        }

        fireblastDamage = Math.round(fireblastDamage);
        fireblastDamage *= modifier;

        return (Math.round(fireblastDamage));
    }


    /**
     * Metoda ma va ajuta la calculul damage-ului activ rezultat din
     * abilitatea Ignite a acestui erou si aplicat asupra oponentului sau.
     * @return valoarea damage-ului activ dat de abilitatea Ignite
     */
    private int getActiveIgniteDamage() {
        final float igniteBaseDamage = 150;
        final float igniteBaseDamagePerLevel = 20;
        final float volcanicModifier = 1.25f;

        float totalIgniteBaseDamage = igniteBaseDamage + igniteBaseDamagePerLevel * this.getLevel();

        if (this.getLand() == 'V') {
            totalIgniteBaseDamage *= volcanicModifier;
        }

        totalIgniteBaseDamage = Math.round(totalIgniteBaseDamage);

        totalIgniteBaseDamage *= modifier;

        return (Math.round(totalIgniteBaseDamage));
    }

    /**
     * Metoda ma va ajuta la calculul damage-ului pasiv rezultat din
     * abilitatea Ignite a acestui erou.
     * @return valoarea damage-ului pasiv dat de abilitatea Ignite
     */
    private int getPassiveIgniteDamage() {
        final float igniteDamagePerTurn = 50;
        final float igniteDamagePerTurnPerLevel = 30;
        final float volcanicModifier = 1.25f;

        float totalIgniteDamagePerTurn = igniteDamagePerTurn
                + igniteDamagePerTurnPerLevel * this.getLevel();

        if (this.getLand() == 'V') {
            totalIgniteDamagePerTurn *= volcanicModifier;
        }

        totalIgniteDamagePerTurn = Math.round(totalIgniteDamagePerTurn);
        totalIgniteDamagePerTurn *= modifier;

        return (Math.round(totalIgniteDamagePerTurn));
    }

    /**
     * Aceasta metoda ma va ajuta la calculul damage-ului total, fara amplificatorii
     * de rasa, pe care il da acest erou Pyromancer unui erou de tip Wizard.
     * @return damage-ul total dat, fara amplificatorii de rasa.
     */
    final int damageWithoutRaceModifiers() {
        final float initialFireblastDamage = 350;
        final float fireblastDamagePerLevel = 50;
        final float volcanicModifier = 1.25f;
        final float igniteBaseDamage = 150;
        final float igniteBaseDamagePerLevel = 20;

        float fireblastDamageGived = initialFireblastDamage
                + fireblastDamagePerLevel * this.getLevel();
        float igniteDamageGived = igniteBaseDamage + igniteBaseDamagePerLevel * this.getLevel();

        if (this.getLand() == 'V') {
            fireblastDamageGived *= volcanicModifier;
            igniteDamageGived *= volcanicModifier;
        }

        int intFireblastDamageGived = Math.round(fireblastDamageGived);
        int intIgniteDamageGived = Math.round(igniteDamageGived);

        return (intFireblastDamageGived + intIgniteDamageGived);
    }

    @Override
    public final void receiveAngelPower(final AngelVisitor angelVisitor) {
        angelVisitor.angelPower(this);
    }
}
