package ticTacToe.grid;

import org.junit.Before;
import org.junit.Test;
import ticTacToe.game.Lines;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ticTacToe.game.Mark.*;

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
        grid.addMark(NOUGHT, "1");

        String addedMark = grid.getCells().get(0);

        assertEquals(NOUGHT.sign, addedMark);
    }

    @Test
    public void addsMarkCrossToCell() {
        grid.addMark(CROSS, "1");

        String addedMark = grid.getCells().get(0);

        assertEquals(CROSS.sign, addedMark);
    }

    @Test
    public void returnsTrueForEmptyCell() {
        assertTrue(grid.isEmptyCell("1"));
    }

    @Test
    public void returnsFalseForOccupiedCell() {
        Grid grid = new Grid(Arrays.asList("X", "2"));

        assertFalse(grid.isEmptyCell("1"));
    }

    @Test
    public void returnsTrueForAllCellsOccupied() {
        Grid grid = new Grid(Arrays.asList("X", "X", "O", "O"));

        assertTrue(grid.allOccupiedCells());
    }

    @Test
    public void returnsFalseForNotAllCellsOccupied() {
        Grid grid = new Grid(Arrays.asList("X", "2", "3", "O"));

        assertFalse(grid.allOccupiedCells());
    }

    @Test
    public void createsCopyOfGridWithNewCells() {
        Grid grid = new Grid(Arrays.asList("X", "2", "O", "4"));

        Grid copyOfGrid = grid.copyOfGrid();

        assertEquals(grid.getCells(), copyOfGrid.getCells());
    }

    @Test
    public void copyOfGridCellsIsNotEqualToOriginalGridCellsAfterChanged() {
        Grid grid = new Grid(Arrays.asList("X", "2", "O", "4"));

        Grid copyOfGrid = grid.copyOfGrid();
        grid.addMark(CROSS, "2");

        assertFalse(grid.getCells().equals(copyOfGrid.getCells()));
    }

    @Test
    public void returnsListOfEmptyPositions() {
        Grid grid = new Grid(Arrays.asList("1", "X", "3", "O", "X", "6"));

        List<String> emptyPositions = grid.emptyPositions();

        assertEquals(3, emptyPositions.size());
    }

    @Test
    public void returnsListOfGridsWithNewMarkAddedToCells() {
        Grid grid = new Grid(Arrays.asList("1", "X", "3", "O", "X", "6"));

        List<Grid> gridList = grid.makeCopiesOfGridWith(CROSS);

        assertEquals(3, gridList.size());
        assertFalse(cells(gridList, 0).equals(cells(gridList, 1)));
        assertFalse(cells(gridList, 1).equals(cells(gridList, 2)));
        assertFalse(cells(gridList, 0).equals(cells(gridList, 2)));
    }

    @Test
    public void returnsTrueIfNewGridWithAllEmptyCells() {
        assertTrue(grid.isAllEmpty());
    }

    @Test
    public void returnsFalseIfNotEmptyGrid() {
        Grid grid = new Grid(Arrays.asList("X", "O", "X", "O", "O", "X", "X", "X", "O"));

        assertFalse(grid.isAllEmpty());
    }

    @Test
    public void knowsGameIsEndedIfDraw() {
        Grid grid = new Grid(Arrays.asList("X", "O", "X", "O", "O", "X", "X", "X", "O"));
        Lines lines = new Lines();

        assertTrue(grid.isFinishedGame(lines));
    }

    @Test
    public void knowsGameIsEndedIfThereIsWinner() {
        Grid grid = new Grid(Arrays.asList("X", "X", "X", "O", "O", "X", "X", "O", "O"));
        Lines lines = new Lines();

        assertTrue(grid.isFinishedGame(lines));
    }

    @Test
    public void knowsGameIsNotEnded() {
        Lines lines = new Lines();

        assertFalse(grid.isFinishedGame(lines));
    }

    @Test
    public void setCellsToEmptyAgain() {
        grid.addMark(CROSS, "1");

        grid.setCellsToEmpty();

        assertFalse(grid.getCells().contains("X"));
    }

    private List<String> cells(List<Grid> gridList, int gridNumber) {
        return gridList.get(gridNumber).getCells();
    }
}
