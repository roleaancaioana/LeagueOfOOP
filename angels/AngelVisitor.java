package angels;

import game.Knight;
import game.Pyromancer;
import game.Rogue;
import game.Wizard;

public interface AngelVisitor {
    void angelPower(Pyromancer pyromancer);
    void angelPower(Knight knight);
    void angelPower(Rogue rogue);
    void angelPower(Wizard wizard);
}
