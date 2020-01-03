package strategies;

public class FirstPyromancerStrategy implements Strategy {
    @Override
    public final int changeHp(final int hp) {
        final int coefficient = 4;
        return (hp - hp / coefficient);
    }

    @Override
    public final float changeDamage(final float damageCoefficient) {
        final float coefficient = 0.7f;
        return (damageCoefficient + coefficient);
    }
}
