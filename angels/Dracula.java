package angels;

import game.Knight;
import game.Magician;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public class Dracula extends AngelVisitor {
    private Magician magician = Magician.getInstance();
    Dracula(final String name, final String angelType, final int x, final int y) {
        super(name, angelType, x, y);
    }

    @Override
    public final void angelPower(final Pyromancer pyromancer) {
        final float angelDamageModifier = -0.3f;
        pyromancer.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 40;
        int newHp = pyromancer.getHp() - angelHpModifier;
        pyromancer.setHp(newHp);
        if (newHp < 0) {
            pyromancer.setDead(true);
        }
    }

    @Override
    public final void angelPower(final Knight knight) {
        final float angelDamageModifier = -0.2f;
        knight.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 60;
        int newHp = knight.getHp() - angelHpModifier;
        knight.setHp(newHp);
        if (newHp < 0) {
            knight.setDead(true);
        }
    }

    @Override
    public final void angelPower(final Rogue rogue) {
        final float angelDamageModifier = -0.1f;
        rogue.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 35;
        int newHp = rogue.getHp() - angelHpModifier;
        rogue.setHp(newHp);
        if (newHp < 0) {
            rogue.setDead(true);
        }
    }

    @Override
    public final void angelPower(final Wizard wizard) {
        final float angelDamageModifier = -0.4f;
        wizard.changeAllModifiers(angelDamageModifier);

        final int angelHpModifier = 20;
        int newHp = wizard.getHp() - angelHpModifier;
        wizard.setHp(newHp);
        if (newHp < 0) {
            wizard.setDead(true);
        }
    }

    @Override
    public final void notifyObserver() {
        magician.updateAngelPosition(this);
    }
}
