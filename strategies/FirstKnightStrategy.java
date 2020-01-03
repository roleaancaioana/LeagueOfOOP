package strategies;

public class FirstKnightStrategy implements Strategy {
    @Override
    public final int changeHp(final int hp) {
        final int coefficient = 5;
        return (hp - hp / coefficient);
    }

    @Override
    public final float changeDamage(final float damageCoefficient) {
        final float coefficient = 0.5f;
        return (damageCoefficient + coefficient);
    }
}
