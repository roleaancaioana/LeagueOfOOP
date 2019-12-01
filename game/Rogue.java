package game;

public class Rogue extends Hero implements Visitable{
    final int HP_ROGUE = 600;
    final int HP_PER_LEVEL_ROGUE = 40;
    private int countAttacksRogue;
    private final float backstabBaseDamage = 200;
    private final float backstabBaseDamagePerLevel = 20;
    private final float backstabWoodsModifier = 1.5f;
    private final float paralysisBaseDamage = 40;
    private final float paralysisBaseDamagePerLevel = 10;
    private float bonusWoodsModifier = 1.15f;
    private int oldCountAttacksRogue;

    Rogue(int x, int y, char land) {
        super(x, y, land);
        super.setInitialHp(HP_ROGUE);
        super.setHpPerLevel(HP_PER_LEVEL_ROGUE);
        super.setHp(HP_ROGUE);
        this.countAttacksRogue = 0;
        this.oldCountAttacksRogue = 0;
    }

    @Override
    public void attack(Hero hero) {
        int passiveTurns;
        if (this.getLand() == 'W') {
            passiveTurns = 6;
        } else {
            passiveTurns = 3;
        }

    }

    private int getBackstabDamage(Hero hero) {

    }

    private int getParalysisDamage(Hero hero) {

    }

    public int damageWithoutRaceModifiers(Hero hero) {

    }

    @Override
    public void accept(Visitor v) {
        v.attack(this);
    }

}
