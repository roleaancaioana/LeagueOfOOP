package strategies;

public class SecondKnightStrategy implements Strategy {
    @Override
    public final int changeHp(final int hp) {
        final int coefficient = 4;
        return (hp + hp / coefficient);
    }

    @Override
    public final float changeDamage(final float damageCoefficient) {
        final float coefficient = 0.2f;
        return (damageCoefficient - coefficient);
    }
}
