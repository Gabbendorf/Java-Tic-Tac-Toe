package ticTacToe.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static ticTacToe.grid.Mark.CROSS;
import static ticTacToe.grid.Mark.NOUGHT;

public class Grid {

    private final ArrayList<String> cells;

    public Grid(int gridSize) {
        this.cells = allCells(gridSize);
    }

    public Grid(ArrayList cells) {
        this.cells = cells;
    }

    public void addMark(String mark, String cellNumber) {
        cells.set(positionFor(cellNumber), mark);
    }

    public boolean isEmptyCell(String cellNumber) {
        return cellDifferentFromMark(cells.get(positionFor(cellNumber)));
    }

    public boolean allOccupiedCells() {
        for (String cell: cells) {
            if (cellDifferentFromMark(cell)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getCells() {
        return cells;
    }

    private ArrayList<String> allCells(int gridSize) {
        return new ArrayList<>(asList(stringCellsNumbers(IntStream.range(1, cellsNumber(gridSize)).toArray())));
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
        return !cell.equals(CROSS.mark) && !cell.equals(NOUGHT.mark);
    }

}
