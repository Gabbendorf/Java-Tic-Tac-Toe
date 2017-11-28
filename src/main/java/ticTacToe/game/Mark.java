package ticTacToe.game;

public enum Mark {

    NOUGHT("O"),
    CROSS("X");

    Mark(String mark) {
        sign = mark;
   }

    public final String sign;

    public static boolean isValidMark(String markChosen) {
        for (Mark mark : Mark.values()) {
            if (mark.sign.equals(markChosen)) {
                return true;
            }
        }
        return false;
    }

    public static Mark createMark(String markChosen) {
        if (markChosen.equals(NOUGHT.sign)) {
            return NOUGHT;
        } else {
            return CROSS;
        }
    }

    public static Mark switchPlayerMark(Mark currentMark) {
        if (currentMark == CROSS) {
            return NOUGHT;
        }
        return CROSS;
    }
}
