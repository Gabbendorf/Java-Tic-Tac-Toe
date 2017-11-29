package ticTacToe.game;

public enum Mark {

    NOUGHT("O"), CROSS("X");

    Mark(String name) {
        mark = name;
   }

    public String mark;

    public static String switchPlayerMark(String currentMark) {
        if (currentMark.equals(CROSS.mark)) {
            return NOUGHT.mark;
        }
        return CROSS.mark;
    }
}
