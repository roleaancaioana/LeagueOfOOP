package game;

public class Wizard extends Hero implements Visitable{
    private final int HP_WIZARD = 400;
    private final int HP_PER_LEVEL_WIZARD = 30;

    Wizard(int x, int y, char land) {
        super(x, y, land);
        super.setInitialHp(HP_WIZARD);
        super.setHpPerLevel(HP_PER_LEVEL_WIZARD);
        super.setHp(HP_WIZARD);
    }

    @Override
    public void attack(Hero hero) {

    }

    private int getDrainDamage(Hero hero) {
        final float coefficient = 0.3f;
        final float initialDrainPercentage = 0.2f;
        final float perLevelDrainPercentage = 0.05f;
        final float desertModifier = 1.1f;

    }

    private int getDeflectDamage(Hero hero) {
        final float desertModifier = 1.1f;
        final float initialDeflectPercentage = 0.35f;
        final float perLevelDeflectPercentage = 0.02f;
        final float maxPercentage = 0.7f;

    }

    @Override
    public void accept(Visitor v) {
        v.attack(this);
    }
}
