package ticTacToe.game;

public enum Mark {

    NOUGHT("O"),
    CROSS("X"),
    RED_O("\u001B[31mO\u001b[0m"),
    BLUE_X("\u001b[34mX\u001b[0m");

    Mark(String mark) {
        sign = mark;
   }

    public String sign;

    public static String switchPlayerMark(String currentMark) {
        if (currentMark.equals(CROSS.sign)) {
            return NOUGHT.sign;
        }
        return CROSS.sign;
    }
}
