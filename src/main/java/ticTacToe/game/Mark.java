package ticTacToe.game;

import java.util.ArrayList;
import java.util.List;

public enum Mark {

    NOUGHT("O"),
    CROSS("X"),
    RED_O("\u001B[31mO\u001b[0m"),
    BLUE_X("\u001b[34mX\u001b[0m");

    Mark(String mark) {
        sign = mark;
   }

    public String sign;

    public static boolean isValidMark(String markChosen) {
        List<String> validMarks = new ArrayList<>();
        validMarks.add(NOUGHT.sign);
        validMarks.add(CROSS.sign);
        return validMarks.contains(markChosen);
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
