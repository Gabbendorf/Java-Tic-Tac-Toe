package ticTacToe.grid;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
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
        assertEquals(grid.cells.size(), 9);
        assertEquals(grid.cells.get(0), "1");
        assertEquals(grid.cells.get(8), "9");
    }

    @Test
    public void addsMarkNoughtToCell() {
        grid.addMark(NOUGHT, "1");

        String addedMark = grid.cells.get(0);

        assertEquals(NOUGHT.sign, addedMark);
    }

    @Test
    public void addsMarkCrossToCell() {
        grid.addMark(CROSS, "1");

        String addedMark = grid.cells.get(0);

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

        assertEquals(grid.cells, copyOfGrid.cells);
    }

    @Test
    public void copyOfGridCellsIsNotEqualToOriginalGridCellsAfterChanged() {
        Grid grid = new Grid(Arrays.asList("X", "2", "O", "4"));

        Grid copyOfGrid = grid.copyOfGrid();
        grid.addMark(CROSS, "2");

        assertFalse(grid.cells.equals(copyOfGrid.cells));
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
    public void returnsTrueForCellNumberInsideValidRange() {
        assertTrue(grid.isInsideCellsRange("9"));
    }

    @Test
    public void returnsFalseForCellNumberOutsideValidRange() {
        assertFalse(grid.isInsideCellsRange("0"));
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
    public void returnsTrueForWinningGrid() {
        Grid grid = new Grid(Arrays.asList("X", "X", "X", "4", "5", "6", "7", "8", "9"));

        assertTrue(grid.isWinner());
    }

    @Test
    public void returnsFalseForNotWinningGrid() {
        assertFalse(grid.isWinner());
    }

    @Test
    public void knowsGameIsEndedIfDraw() {
        Grid grid = new Grid(Arrays.asList("X", "O", "X", "O", "O", "X", "X", "X", "O"));

        assertTrue(grid.isFinishedGame());
    }

    @Test
    public void knowsGameIsEndedIfThereIsWinner() {
        Grid grid = new Grid(Arrays.asList("X", "X", "X", "O", "O", "X", "X", "O", "O"));

        assertTrue(grid.isFinishedGame());
    }

    @Test
    public void knowsGameIsNotEnded() {
        assertFalse(grid.isFinishedGame());
    }

    @Test
    public void returnsWinningMark() {
        Grid grid = new Grid(Arrays.asList("X", "X", "X", "4", "5", "6", "7", "8", "9"));

        assertEquals(CROSS, grid.winningMark());
    }

    @Test
    public void returnsWinningLine() {
        Grid grid = new Grid(Arrays.asList("X", "X", "X", "4", "5", "6", "7", "8", "9"));

        Line winningLine = grid.winningLine();

        assertTrue(winningLine.cells.equals(new ArrayList<>(Arrays.asList("X", "X", "X"))));
    }

    @Test
    public void createsRows() {
        String[] firstRow = new String[]{"1","2","3"};
        String[] secondRow = new String[]{"4", "5", "6"};
        String[] thirdRow = new String[]{"7", "8", "9"};

        assertEquals(rows(firstRow, secondRow, thirdRow), grid.rows());
    }

    private List<List> rows(String[] firstRow, String[] secondRow, String[] thirdRow) {
        ArrayList<List> rows = new ArrayList<>();
        rows.add(asList(firstRow));
        rows.add(asList(secondRow));
        rows.add(asList(thirdRow));
        return rows;
    }

    private List<String> cells(List<Grid> gridList, int gridNumber) {
        return gridList.get(gridNumber).cells;
    }
}
