package angels;

import game.Knight;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public abstract class AngelVisitor {
    private String angelType;
    private int x, y;

    AngelVisitor(String angelType, int x, int y) {
        this.angelType = angelType;
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getAngelType() {
        return angelType;
    }

    public void setAngelType(String angelType) {
        this.angelType = angelType;
    }

    public abstract void angelPower(Pyromancer pyromancer);
    public abstract void angelPower(Knight knight);
    public abstract void angelPower(Rogue rogue);
    public abstract void angelPower(Wizard wizard);
}
