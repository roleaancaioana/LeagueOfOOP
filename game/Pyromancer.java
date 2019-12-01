package game;

public class Pyromancer extends Hero implements Visitable {
    private final int hpPyromancer = 500;
    private final int hpPerLevelPyromancer = 50;

    Pyromancer(final int x, final int y, final char land) {
        super(x, y, land);
        super.setInitialHp(hpPyromancer);
        super.setHpPerLevel(hpPerLevelPyromancer);
        super.setHp(hpPyromancer);
    }

    /**
     * Aceasta metoda ilustreaza modul de atac al unui erou de tip Pyromancer.
     * @param hero reprezinta oponentul eroului de tip Pyromancer.
     */
    @Override
    public final void attack(final Hero hero) {
        int passiveTurns = 2;
        /*
        Cu ajutorul design pattern-ului visitor voi stabili
        care este modificatorul corespunzator eroului cu care
        va avea loc atacul.
         */
        PyromancerVisitor visitor = new PyromancerVisitor();
        hero.accept(visitor);
        float modifier = visitor.getModifier();

        int intFireblastDamage = getFireblastDamage(modifier);
        int intTotalIgniteBaseDamage = getActiveIgniteDamage(modifier);
        int intTotalIgniteDamagePerTurn = getPassiveIgniteDamage(modifier);

        int totalActiveDamage = intFireblastDamage + intTotalIgniteBaseDamage;

        hero.setPassiveTurns(passiveTurns);
        hero.setDamage(totalActiveDamage);
        hero.setDamageOvertime(intTotalIgniteDamagePerTurn);
        hero.getActiveDamage();
    }

    /**
     * Aceasta metoda ma va ajuta la calculul damage-ului rezultat in urma
     * aplicarii abilitatii Fireblast.
     * @param modifier reprezinta amplificatorul de rasa
     * @return valoarea damage-ului dat de abilitatea Fireblast
     */
    private int getFireblastDamage(final float modifier) {
        final float initialFireblastDamage = 350;
        final float fireblastDamagePerLevel = 50;
        final float volcanicModifier = 1.25f;

        float fireblastDamage = initialFireblastDamage + fireblastDamagePerLevel * this.getLevel();
        fireblastDamage *= modifier;

        if (this.getLand() == 'V') {
            fireblastDamage *= volcanicModifier;
        }
        return (Math.round(fireblastDamage));
    }


    /**
     * Metoda ma va ajuta la calculul damage-ului activ rezultat din
     * abilitatea Ignite a acestui erou si aplicat asupra oponentului sau.
     * @param modifier reprezinta amplificatorul de rasa
     * @return valoarea damage-ului activ dat de abilitatea Ignite
     */
    private int getActiveIgniteDamage(final float modifier) {
        final float igniteBaseDamage = 150;
        final float igniteBaseDamagePerLevel = 20;
        final float volcanicModifier = 1.25f;

        float totalIgniteBaseDamage = igniteBaseDamage + igniteBaseDamagePerLevel * this.getLevel();

        totalIgniteBaseDamage *= modifier;

        if (this.getLand() == 'V') {
            totalIgniteBaseDamage *= volcanicModifier;
        }
        return (Math.round(totalIgniteBaseDamage));
    }

    /**
     * Metoda ma va ajuta la calculul damage-ului pasiv rezultat din
     * abilitatea Ignite a acestui erou.
     * @param modifier reprezinta amplificatorul de rasa
     * @return valoarea damage-ului pasiv dat de abilitatea Ignite
     */
    private int getPassiveIgniteDamage(final float modifier) {
        final float igniteDamagePerTurn = 50;
        final float igniteDamagePerTurnPerLevel = 30;
        final float volcanicModifier = 1.25f;

        float totalIgniteDamagePerTurn = igniteDamagePerTurn
                + igniteDamagePerTurnPerLevel * this.getLevel();

        totalIgniteDamagePerTurn *= modifier;

        if (this.getLand() == 'V') {
            totalIgniteDamagePerTurn *= volcanicModifier;
        }
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
    public final void accept(final Visitor v) {
        v.attack(this);
    }
}
