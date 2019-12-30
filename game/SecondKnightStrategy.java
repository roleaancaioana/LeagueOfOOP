package game;

public class SecondKnightStrategy implements Strategy{
    @Override
    public int changeHp(int hp) {
        hp += (int) hp/4;
        return hp;
    }

    @Override
    public float changeDamage(float damageCoefficient) {
        damageCoefficient -= 0.2f;
        return damageCoefficient;
    }
}
