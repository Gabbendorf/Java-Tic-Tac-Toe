package ticTacToe.game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ticTacToe.game.Mark.CROSS;
import static ticTacToe.game.Mark.NOUGHT;

public class MarkTest {

    @Test
    public void returnsTrueForValidMarkForCross() {
        assertTrue(Mark.isValid("X"));
    }

    @Test
    public void returnsTrueForValidMarkForNought() {
        assertTrue(Mark.isValid("O"));
    }

    @Test
    public void returnsFalseForInvalidMark() {
        assertFalse(Mark.isValid("F"));
    }

    @Test
    public void convertsStringForCrossIntoConstant() {
        assertEquals(CROSS, Mark.create("X"));
    }

    @Test
    public void convertsStringForNoughtIntoConstant() {
        assertEquals(NOUGHT, Mark.create("O"));
    }

}
