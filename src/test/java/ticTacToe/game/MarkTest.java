package ticTacToe.game;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static ticTacToe.game.Mark.CROSS;
import static ticTacToe.game.Mark.NOUGHT;

public class MarkTest {

    @Test
    public void returnsTrueForValidMarkForCross() {
        assertTrue(Mark.isValidMark("X"));
    }

    @Test
    public void returnsTrueForValidMarkForNough() {
        assertTrue(Mark.isValidMark("O"));
    }

    @Test
    public void returnsFalseForInvalidMark() {
        assertFalse(Mark.isValidMark("F"));
    }

    @Test
    public void convertsStringForCrossIntoConstant() {
        assertEquals(CROSS, Mark.createMark("X"));
    }

    @Test
    public void convertsStringForNoughtIntoConstant() {
        assertEquals(NOUGHT, Mark.createMark("O"));
    }
}
