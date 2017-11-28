package ticTacToe.grid;

import ticTacToe.game.Lines;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static ticTacToe.game.Mark.CROSS;
import static ticTacToe.game.Mark.NOUGHT;

public class Grid {

    private List<String> cells;
    private int size;

    public Grid(int size) {
        this.size = size;
        this.cells = allCells();
    }

    public Grid(List<String> cells) {
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

    public List<String> getCells() {
        return cells;
    }

    public int getSize() {
        return size;
    }

    public Grid copyOfGrid() {
        List<String> newCells = new ArrayList<>(cells);
        return new Grid(newCells);
    }

    public List<String> emptyPositions() {
        List<String> emptyPositions = new ArrayList<>();
        for (String position : cells) {
            if (cellDifferentFromMark(position)) {
               emptyPositions.add(position);
            }
        }
        return emptyPositions;
    }

    public List<Grid> makeCopiesOfGridWith(String mark) {
        List<Grid> gridCopies = new ArrayList<>();
        for (String cell : emptyPositions()) {
            Grid gridCopy = copyOfGrid();
            gridCopy.addMark(mark, cell);
            gridCopies.add(gridCopy);
        }
        return gridCopies;
    }

    public boolean isAllEmpty() {
        return emptyPositions().size() == cells.size();
    }

    public boolean isFinishedGame(Lines lines) {
        return allOccupiedCells() || lines.isWinning(this);
    }

    public void setCellsToEmpty() {
        cells = allCells();
    }

    private List<String> allCells() {
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
        return !cell.equals(CROSS.sign) && !cell.equals(NOUGHT.sign);
    }
}
