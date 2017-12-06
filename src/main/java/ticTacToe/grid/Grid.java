package ticTacToe.grid;

import ticTacToe.game.Mark;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

public class Grid {

    protected List<String> cells;
    private int size;

    public Grid(int size) {
        this.size = size;
        this.cells = allCells();
    }

    public Grid(List<String> cells) {
        this.cells = cells;
        this.size = (int) Math.sqrt(cells.size());
    }

    public void addMark(Mark mark, String cellNumber) {
        cells.set(positionFor(cellNumber), mark.sign);
    }

    public boolean isEmptyCell(String cellNumber) {
        return !Mark.isValid(cells.get(positionFor(cellNumber)));
    }

    public boolean allOccupiedCells() {
        for (String cell: cells) {
            if (!Mark.isValid(cell)) {
                return false;
            }
        }
        return true;
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
            if (!Mark.isValid(position)) {
               emptyPositions.add(position);
            }
        }
        return emptyPositions;
    }

    public List<Grid> makeCopiesOfGridWith(Mark mark) {
        List<Grid> gridCopies = new ArrayList<>();
        for (String cell : emptyPositions()) {
            Grid gridCopy = copyOfGrid();
            gridCopy.addMark(mark, cell);
            gridCopies.add(gridCopy);
        }
        return gridCopies;
    }

    public boolean isInsideCellsRange(String positionChosen) {
        return positionFor(positionChosen) >= 0 && positionFor(positionChosen) < cells.size();
    }

    public boolean isAllEmpty() {
        return emptyPositions().size() == cells.size();
    }

    public boolean isWinner() {
        for (Line line : allLines()) {
            if (line.isWinning()) {
                return true;
            }
        }
        return false;
    }

    public boolean isFinishedGame() {
        return allOccupiedCells() || isWinner();
    }

    public Mark winningMark() {
        return winningLine().winningMark();
    }

    public Line winningLine() {
        Line winningLine = null;
        for (Line line : allLines()) {
            if (line.isWinning()) {
                winningLine = line;
            }
        }
        return winningLine;
    }

    public List<ArrayList<String>> rows() {
        List<ArrayList<String>> rows = new ArrayList<>();
        for (int i = 0; i < size; i++){
            ArrayList<String> line = sliceArray(startingCellPosition(i), finalCellPosition(i));
            rows.add(line);
        }
        return rows;
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

    private List<Line> allLines() {
        List<Line> allLines = new ArrayList<>();
        addEachLine(rows(), allLines);
        addEachLine(columns(), allLines);
        addEachLine(diagonalLines(), allLines);
        return allLines;
    }

    private void addEachLine(List<ArrayList<String>> lines, List<Line> allLines) {
        for (List<String> line : lines) {
            allLines.add(new Line(line));
        }
    }

    private ArrayList<String> sliceArray(int startingPoint, int finalPoint) {
        ArrayList<String> row = new ArrayList<>();
        for (int i = startingPoint; i <= finalPoint; i++) {
            row.add(cells.get(i));
        }
        return row;
    }

    private int startingCellPosition(int i) {
        return i * size;
    }

    private int finalCellPosition(int i) {
        return  (i + 1) * size - 1;
    }

    private List<ArrayList<String>> columns() {
        List<ArrayList<String>> columns = new ArrayList<>();
        for (int cellPosition = 0; cellPosition < size; cellPosition++) {
            ArrayList<String> line = new ArrayList<>();
            for (int horizontalLineNumber = 0; horizontalLineNumber < size; horizontalLineNumber++) {
                line.add(rows().get(horizontalLineNumber).get(cellPosition));
            }
            columns.add(line);
        }
        return columns;
    }

    private List<ArrayList<String>> diagonalLines() {
        List<ArrayList<String>> diagonalLines = new ArrayList<>();
        diagonalLines.add(firstDiagonalLine());
        diagonalLines.add(secondDiagonalLine());
        return diagonalLines;
    }

    private ArrayList<String> firstDiagonalLine() {
        ArrayList<String> diagonalLine = new ArrayList<>();
        int cellNumber = 0;
        for (int rowNumber = 0; rowNumber < size; rowNumber++) {
            diagonalLine.add(rows().get(rowNumber).get(cellNumber));
            cellNumber += 1;
        }
        return diagonalLine;
    }

    private ArrayList<String> secondDiagonalLine() {
        ArrayList<String> diagonalLine = new ArrayList<>();
        int cellNumber = size - 1;
        for (int rowNumber = 0; rowNumber < size; rowNumber++) {
            diagonalLine.add(rows().get(rowNumber).get(cellNumber));
            cellNumber -= 1;
        }
        return diagonalLine;
    }
}
