package ticTacToe.grid;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class LineTest {

    @Test
    public void returnsTrueIfIsWinning() {
        Line line = new Line(Arrays.asList("X", "X", "X"));

        assertTrue(line.isWinning());
    }

    @Test
    public void returnsFalseIfNotWinning() {
        Line line = new Line(Arrays.asList("1", "X", "3"));

        assertFalse(line.isWinning());
    }
}
