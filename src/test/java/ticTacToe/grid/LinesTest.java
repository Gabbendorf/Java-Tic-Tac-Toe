package ticTacToe.grid;

import org.junit.Before;
import org.junit.Test;
import ticTacToe.game.Lines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LinesTest {

    private static Grid grid;
    private static Lines lines;

    @Before
    public void newGridState() {
        grid = new Grid(3);
        lines = new Lines();
    }

    @Test
    public void returnsTrueForWinningRow() {
        Grid grid = new Grid(new ArrayList<>(Arrays.asList("X", "2", "3", "4", "X", "6", "7", "8", "X")));
        Lines lines = new Lines();

        assertTrue(lines.isWinning(grid));
    }

    @Test
    public void returnsFalseForNotWinningRow() {
        assertFalse(lines.isWinning(grid));
    }

    @Test
    public void returnsWinningMark() {
        Grid grid = new Grid(new ArrayList<>(Arrays.asList("X", "2", "3", "4", "X", "6", "7", "8", "X")));
        Lines lines = new Lines();

        assertEquals("X", lines.winningMark(grid));
    }

    @Test
    public void createsRows() {
        String[] firstRow = new String[]{"1","2","3"};
        String[] secondRow = new String[]{"4", "5", "6"};
        String[] thirdRow = new String[]{"7", "8", "9"};

        assertEquals(rows(firstRow, secondRow, thirdRow), lines.getRows(grid));
    }

    private List<List> rows(String[] firstRow, String[] secondRow, String[] thirdRow) {
        ArrayList<List> rows = new ArrayList<>();
        rows.add(asList(firstRow));
        rows.add(asList(secondRow));
        rows.add(asList(thirdRow));
        return rows;
    }
}
