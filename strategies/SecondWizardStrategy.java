package strategies;

public class SecondWizardStrategy implements Strategy {
    @Override
    public final int changeHp(final int hp) {
        final int coefficient = 5;
        return (hp + hp / coefficient);
    }

    @Override
    public final float changeDamage(final float damageCoefficient) {
        final float coefficient = 0.2f;
        return (damageCoefficient - coefficient);
    }
}
