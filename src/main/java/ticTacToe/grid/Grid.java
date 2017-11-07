package ticTacToe.grid;

import java.util.Arrays;
import java.util.stream.IntStream;

import static ticTacToe.grid.Marks.CROSS;
import static ticTacToe.grid.Marks.NOUGHT;

public class Grid {

    private final String[] cells;

    public Grid(int gridSize) {
        this.cells = allCells(gridSize);
    }

    public Grid(String[] cells) {
        this.cells = cells.clone();
    }

    public void addMark(String mark, String cellNumber) {
        cells[positionFor(cellNumber)] = mark;
    }

    public boolean isEmptyCell(String cellNumber) {
        return cellDifferentFromMark(cells[positionFor(cellNumber)]);
    }

    public boolean allOccupiedCells() {
        for (String cell: cells) {
            if (cellDifferentFromMark(cell)) {
                return false;
            }
        }
        return true;
    }

    public String[] getCells() {
        return cells;
    }

    private String[] allCells(int gridSize) {
        return stringCellsNumbers(IntStream.range(1, cellsNumber(gridSize)).toArray());
    }

    private String[] stringCellsNumbers(int [] cells) {
        return Arrays.toString(cells).split("[\\[\\]]")[1].split(", ");
    }

    private int cellsNumber(int gridSize) {
        return gridSize * gridSize + 1;
    }

    private int positionFor(String cellNumber) {
        return Integer.parseInt(cellNumber) - 1;
    }

    private boolean cellDifferentFromMark(String cell) {
        return !cell.equals(CROSS.sign) && !cell.equals(NOUGHT.sign);
    }

}
