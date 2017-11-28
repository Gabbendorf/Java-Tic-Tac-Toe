package ticTacToe.game;

import java.util.ArrayList;
import java.util.List;

public enum Mark {

    NOUGHT("O"),
    CROSS("X");

    Mark(String mark) {
        sign = mark;
   }

    public final String sign;

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
