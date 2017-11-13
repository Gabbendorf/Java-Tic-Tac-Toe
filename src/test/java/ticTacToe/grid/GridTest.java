package ticTacToe.grid;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static ticTacToe.grid.Marks.*;

public class GridTest {

    private static Grid grid;

    @Before
    public void newGridWithSize() {
        grid = new Grid(3);
    }

    @Test
    public void hasNineCells() {
        assertEquals(grid.getCells().size(), 9);
        assertEquals(grid.getCells().get(0), "1");
        assertEquals(grid.getCells().get(8), "9");
    }

    @Test
    public void addsMarkNoughtToCell() {
        grid.addMark(NOUGHT.sign, "1");

        String addedMark = grid.getCells().get(0);

        assertEquals(NOUGHT.sign, addedMark);
    }

    @Test
    public void addsMarkCrossToCell() {
        grid.addMark(CROSS.sign, "1");

        String addedMark = grid.getCells().get(0);

        assertEquals(CROSS.sign, addedMark);
    }

    @Test
    public void returnsTrueForEmptyCell() {
        assertTrue(grid.isEmptyCell("1"));
    }

    @Test
    public void returnsFalseForOccupiedCell() {
        Grid grid = new Grid(new ArrayList<>(Arrays.asList("X", "2")));

        assertFalse(grid.isEmptyCell("1"));
    }

    @Test
    public void returnsTrueForAllCellsOccupied() {
        Grid grid = new Grid(new ArrayList<>(Arrays.asList("X", "X", "O", "O")));

        assertTrue(grid.allOccupiedCells());
    }

    @Test
    public void returnsFalseForNotAllCellsOccupied() {
        Grid grid = new Grid(new ArrayList<>(Arrays.asList("X", "2", "3", "O")));

        assertFalse(grid.allOccupiedCells());
    }
}
