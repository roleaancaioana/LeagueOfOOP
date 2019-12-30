package game;

public class FirstWizardStrategy implements Strategy {
    @Override
    public int changeHp(int hp) {
        hp -= (int) hp/10;
        return hp;
    }

    @Override
    public float changeDamage(float damageCoefficient) {
        damageCoefficient += 0.6f;
        return damageCoefficient;
    }
}
