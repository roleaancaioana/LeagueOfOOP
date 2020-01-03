package strategies;

public class SecondPyromancerStrategy implements Strategy {
    @Override
    public final int changeHp(final int hp) {
        final int coefficient = 3;
        return (hp + hp / coefficient);
    }

    @Override
    public final float changeDamage(final float damageCoefficient) {
        final float coefficient = 0.3f;
        return (damageCoefficient - coefficient);
    }
}
