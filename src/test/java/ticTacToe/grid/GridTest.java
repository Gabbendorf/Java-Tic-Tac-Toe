package ticTacToe.grid;

import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GridTest {

    private static Grid grid;

    @BeforeClass
    public static void newGrid() {
        grid = new Grid(3);
    }

    @Test
    public void hasNineCells() {
        assertEquals(grid.getCells().length, 9);
        assertEquals(grid.getCells()[0], "1");
        assertEquals(grid.getCells()[8], "9");
    }

    @Test
    public void addsMarkNoughtToCell() {
        Grid grid = new Grid(3);

        grid.addMark(Marks.NOUGHT.sign, "1");

        assertEquals(Marks.NOUGHT.sign, grid.getCells()[0]);
    }

    @Test
    public void addsMarkCrossToCell() {
        Grid grid = new Grid(3);

        grid.addMark(Marks.CROSS.sign, "1");

        assertEquals(Marks.CROSS.sign, grid.getCells()[0]);
    }

    @Test
    public void returnsTrueForEmptyCell() {
        assertTrue(grid.isEmptyCell("1"));
    }

    @Test
    public void returnsFalseForOccupiedCell() {
        Grid grid = new Grid(new String[] {"X", "2"});

        assertFalse(grid.isEmptyCell("1"));
    }

    @Test
    public void returnsTrueForAllCellsOccupied() {
        Grid grid = new Grid(new String[] {"X", "X", "O", "O"});

        assertTrue(grid.allOccupiedCells());
    }

    @Test
    public void returnsFalseForNotAllCellsOccupied() {
        Grid grid = new Grid(new String[] {"X", "2", "3", "O"});

        assertFalse(grid.allOccupiedCells());
    }
}
