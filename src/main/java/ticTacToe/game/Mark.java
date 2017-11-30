package ticTacToe.game;

public enum Mark {

    NOUGHT("O"),
    CROSS("X");

    Mark(String mark) {
        sign = mark;
   }

    public final String sign;

    public static boolean isValid(String markChosen) {
        return markChosen.equals(NOUGHT.sign) || markChosen.equals(CROSS.sign);
    }

    public static Mark create(String markChosen) {
        if (markChosen.equals(NOUGHT.sign)) {
            return NOUGHT;
        } else {
            return CROSS;
        }
    }

    public Mark doSwitch() {
        if (this == CROSS) {
            return NOUGHT;
        } else {
            return CROSS;
        }
    }
}
