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


        final float volcanicModifier = 1.25f;
        int intFireblastDamage = getFireblastDamage(hero, modifier, volcanicModifier);
        int intTotalIgniteBaseDamage = getActiveIgniteDamage(hero, modifier, volcanicModifier);
        int intTotalIgniteDamagePerTurn = getPassiveIgniteDamage(hero, modifier, volcanicModifier);

        int totalActiveDamage = intFireblastDamage + intTotalIgniteBaseDamage;

        hero.setPassiveTurns(passiveTurns);
        hero.setDamage(totalActiveDamage);
        hero.setDamageOvertime(intTotalIgniteDamagePerTurn);
        hero.getActiveDamage();
    }

    private int getFireblastDamage(final Hero hero, final float modifier,
                                   final float volcanicModifier) {
        final float initialFireblastDamage = 350;  // calcul damage fireblast
        final float fireblastDamagePerLevel = 50;

        float fireblastDamage = initialFireblastDamage + fireblastDamagePerLevel * this.getLevel();
        fireblastDamage *= modifier;

        if (this.getLand() == 'V') {
            fireblastDamage *= volcanicModifier;
        }
        return (Math.round(fireblastDamage));
    }

    private int getActiveIgniteDamage(final Hero hero, final float modifier,
                                      final float volcanicModifier) {
        final float igniteBaseDamage = 150;  // calcul damage ignite
        final float igniteBaseDamagePerLevel = 20;

        float totalIgniteBaseDamage = igniteBaseDamage + igniteBaseDamagePerLevel * this.getLevel();

        totalIgniteBaseDamage *= modifier;

        if (this.getLand() == 'V') {
            totalIgniteBaseDamage *= volcanicModifier;
        }
        return (Math.round(totalIgniteBaseDamage));
    }

    private int getPassiveIgniteDamage(final Hero hero, final float modifier,
                                       final float volcanicModifier) {
        final float igniteDamagePerTurn = 50;
        final float igniteDamagePerTurnPerLevel = 30;

        float totalIgniteDamagePerTurn = igniteDamagePerTurn
                + igniteDamagePerTurnPerLevel * this.getLevel();

        totalIgniteDamagePerTurn *= modifier;

        if (this.getLand() == 'V') {
            totalIgniteDamagePerTurn *= volcanicModifier;
        }
        return (Math.round(totalIgniteDamagePerTurn));
    }

    /*
    Aceasta functie calculeaza damage-ul total, fara race modifiers, dat de erou.
     */
    final int damageWithoutRaceModifiers(final Hero hero) {
        final float initialFireblastDamage = 350;  // calcul damage fireblast
        final float fireblastDamagePerLevel = 50;
        final float volcanicModifier = 1.25f;
        final float igniteBaseDamage = 150;  // calcul damage ignite
        final float igniteBaseDamagePerLevel = 20;

        float fireblastDamageGived = initialFireblastDamage
                + fireblastDamagePerLevel * this.getLevel();
        float igniteDamageGived = igniteBaseDamage + igniteBaseDamagePerLevel * this.getLevel();

        if (this.getLand() == 'V') {
            fireblastDamageGived *= volcanicModifier;
            igniteDamageGived *= volcanicModifier;
        }

        int intFireblastDamageGived = (int) Math.round(fireblastDamageGived);
        int intIgniteDamageGived = (int) Math.round(igniteDamageGived);

        return (intFireblastDamageGived + intIgniteDamageGived);
    }

    @Override
    public final void accept(final Visitor v) {
        v.attack(this);
    }
}
