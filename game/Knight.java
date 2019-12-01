package game;

public class Knight extends Hero implements Visitable {
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

    Knight(final int x, final int y, final char land) {
        super(x, y, land);
        super.setInitialHp(hpKnight);
        super.setHpPerLevel(hpPerLevelKnight);
        super.setHp(hpKnight);
    }

    @Override
    public final void attack(final Hero hero) {
        float executeDamage, slamDamage;
        int roundsImobilised = 1;
        float executeModifier, slamModifier;

        KnightVisitor visitor = new KnightVisitor();
        hero.accept(visitor);
        executeModifier = visitor.getExecuteModifier();
        slamModifier = visitor.getSlamModifier();

        float maxHp = hero.getInitialHp() + hero.getHpPerLevel() * hero.getLevel();
        float hpLimit = hpLimitCoefficient * maxHp;  // determinare hp limit

        hpLimit += (float) this.getLevel() * hundreadPercentage * maxHp;

        if (hpLimit > maxHpLimit) {
            hpLimit = maxHpLimit;
        }


        if ((float) hero.getHp() < hpLimit) {
            executeDamage = hero.getHp(); /*damage-ul este egal cu hp-ul adversarului*/
            slamDamage = 0; /* daca adversarul e executat nu va mai avea si slamDamage*/
        } else {  // daca nu este executat adversarul
            executeDamage = executeBaseDamage + executeBaseDamagePerLevel * this.getLevel();

            // calcul slam damage
            slamDamage = slamBaseDamage + slamBaseDamagePerLevel * this.getLevel();

            executeDamage *= executeModifier;
            slamDamage *= slamModifier;

            if (this.getLand() == 'L') {  // land modifier aplicat doar
                executeDamage *= landModifier;
                slamDamage *= landModifier; // daca adversarul nu
            }  // urmeaza sa fie executat in runda curenta
        }

        int intExecuteDamage = (int) Math.round(executeDamage);
        int intSlamDamage = (int) Math.round(slamDamage);
        int totalDamage = intExecuteDamage + intSlamDamage;

        hero.becomeImmobilized(roundsImobilised);
        hero.setDamage(totalDamage);
        hero.getActiveDamage();
    }

    final int damageWithoutRaceModifiers(final Hero hero) {
        float executeDamageGived, slamDamageGived;

        float maxHp = hero.getInitialHp() + hero.getHpPerLevel() * hero.getLevel();
        float hpLimit = hpLimitCoefficient * maxHp;  // determinare hp limit

        hpLimit += (float) this.getLevel() * hundreadPercentage * maxHp;

        if (hpLimit > maxHpLimit) {
            hpLimit = maxHpLimit;
        }

        if ((float) hero.getHp() < hpLimit && hero.getHp() > 0) {
            executeDamageGived = hero.getHp(); /*damage-ul este egal cu hp-ul adversarului*/
            slamDamageGived = 0; /* daca adversarul e executat nu va mai avea si slamDamage*/
        } else {  // daca nu este executat adversarul
            executeDamageGived = executeBaseDamage + executeBaseDamagePerLevel * this.getLevel();

            // calcul slam damage
            slamDamageGived = slamBaseDamage + slamBaseDamagePerLevel * this.getLevel();

            if (this.getLand() == 'L') {  // land modifier aplicat doar
                executeDamageGived *= landModifier;
                slamDamageGived *= landModifier; // daca adversarul nu
            }  // urmeaza sa fie executat in runda curenta

        }

        int intExecuteDamageGived = (int) Math.round(executeDamageGived);
        int intSlamDamageGived = (int) Math.round(slamDamageGived);
        return (intExecuteDamageGived + intSlamDamageGived);
    }

    @Override
    public final void accept(final Visitor v) {
        v.attack(this);
    }

}
