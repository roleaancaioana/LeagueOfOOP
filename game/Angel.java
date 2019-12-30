package game;

import angels.AngelVisitor;

public class Angel {
    private static Angel angel;
    private AngelVisitor[] angelsList;

    private Angel() { }

    public void createAngelsList(int numberOfAngels) {
        angelsList = new AngelVisitor[numberOfAngels];
    }

//    public void addAngel(String angelType, int x, int y, int index) {
//        angelsList[index] = new AngelVisitor(angelType, x, y);
//    }

//    public char landType(int i, int j) {
//        return landTypes[i][j];
//    }

    public static Angel getInstance() {
        if (angel == null) {
            angel = new Angel();
        }
        return angel;
    }
}
