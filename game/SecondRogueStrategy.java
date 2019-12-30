package game;

public class SecondRogueStrategy implements Strategy {
    @Override
    public int changeHp(int hp) {
        hp += (int) hp/2;
        return hp;
    }

    @Override
    public float changeDamage(float damageCoefficient) {
        damageCoefficient *= 0.9f;
        return damageCoefficient;
    }
}
