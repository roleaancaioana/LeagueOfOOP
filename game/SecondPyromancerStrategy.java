package game;

public class SecondPyromancerStrategy implements Strategy {
    @Override
    public int changeHp(int hp) {
        hp += (int) hp/3;
        return hp;
    }

    @Override
    public float changeDamage(float damageCoefficient) {
        damageCoefficient *= 0.7f;
        return damageCoefficient;
    }
}
