package ticTacToe.game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    public static Mark random() {
        List<Mark> marks = Arrays.asList(Mark.values());
        return marks.get(new Random().nextInt(marks.size()));
    }

    public Mark doSwitch() {
        if (this == CROSS) {
            return NOUGHT;
        } else {
            return CROSS;
        }
    }
}
