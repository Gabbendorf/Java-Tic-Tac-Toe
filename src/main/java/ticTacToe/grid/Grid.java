package ticTacToe.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static ticTacToe.grid.Mark.CROSS;
import static ticTacToe.grid.Mark.NOUGHT;

public class Grid {

    private final ArrayList<String> cells;
    private int size;

    public Grid(int size) {
        this.size = size;
        this.cells = allCells();
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

    public int getSize() {
        return size;
    }

    private ArrayList<String> allCells() {
        return new ArrayList<>(asList(stringCellsNumbers(IntStream.range(1, cellsNumber()).toArray())));
    }

    private String[] stringCellsNumbers(int [] cells) {
        return Arrays.toString(cells).split("[\\[\\]]")[1].split(", ");
    }

    private int cellsNumber() {
        return size * size + 1;
    }

    private int positionFor(String cellNumber) {
        return Integer.parseInt(cellNumber) - 1;
    }

    private boolean cellDifferentFromMark(String cell) {
        return !cell.equals(CROSS.mark) && !cell.equals(NOUGHT.mark);
    }
}
