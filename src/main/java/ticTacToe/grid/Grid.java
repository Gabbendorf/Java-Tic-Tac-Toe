package ticTacToe.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static ticTacToe.game.Mark.CROSS;
import static ticTacToe.game.Mark.NOUGHT;

public class Grid {

    private ArrayList<String> cells;
    private int size;

    public Grid(int size) {
        this.size = size;
        this.cells = allCells();
    }

    public Grid(ArrayList<String> cells) {
        this.cells = cells;
        this.size = (int) Math.sqrt(cells.size());
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

    public void setCellsToEmpty() {
        cells = allCells();
    }

    private ArrayList<String> allCells() {
        return new ArrayList<>(asList(stringCellsNumbers(IntStream.range(1, cellsNumber()).toArray())));
    }

    private String[] stringCellsNumbers(int[] cells) {
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
