package strategies;

public class FirstWizardStrategy implements Strategy {
    @Override
    public final int changeHp(final int hp) {
        final int coefficient = 10;
        return (hp - hp / coefficient);
    }

    @Override
    public final float changeDamage(final float damageCoefficient) {
        final float coefficient = 0.6f;
        return (damageCoefficient + coefficient);
    }
}
