package ticTacToe.grid;

import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;

import java.util.ArrayList;
import java.util.List;

public class Lines {

    public boolean isWinning(Grid grid) {
        return !winningLine(grid).isEmpty();
    }

    public Mark winningMark(Grid grid) {
        return Mark.create((String) winningLine(grid).get(0).get(0));
    }

    public List<ArrayList<String>> getRows(Grid grid) {
        return rows(grid);
    }

    private List<List> winningLine(Grid grid) {
        List<List> winningLine = new ArrayList<>();
        for (List line : allLines(grid)) {
            if ((line.stream().distinct().limit(2).count() <= 1)) {
                winningLine.add(line);
            }
        }
        return winningLine;
    }

    private List<ArrayList<String>> allLines(Grid grid) {
       List<ArrayList<String>> allLines = new ArrayList<>();
       addEachLine(rows(grid), allLines);
       addEachLine(columns(grid), allLines);
       addEachLine(diagonalLines(grid), allLines);
       return allLines;
    }

    private void addEachLine(List<ArrayList<String>> Lines, List<ArrayList<String>> allLines) {
        allLines.addAll(Lines);
    }

    private List<ArrayList<String>> rows(Grid grid) {
        List<ArrayList<String>> rows = new ArrayList<>();
        for (int i = 0; i < grid.getSize(); i++){
            ArrayList<String> line = sliceArray(grid.getCells(), startingCellPosition(i, grid), finalCellPosition(i, grid));
            rows.add(line);
        }
        return rows;
    }

    private ArrayList<String> sliceArray(List<String> cellsList, int startingPoint, int finalPoint) {
        ArrayList<String> row = new ArrayList<>();
        for (int i = startingPoint; i <= finalPoint; i++) {
            row.add(cellsList.get(i));
        }
        return row;
    }

    private int startingCellPosition(int i, Grid grid) {
        return i * grid.getSize();
    }

    private int finalCellPosition(int i, Grid grid) {
        return  (i + 1) * grid.getSize() - 1;
    }

    private List<ArrayList<String>> columns(Grid grid) {
        List<ArrayList<String>> columns = new ArrayList<>();
        for (int cellPosition = 0; cellPosition < grid.getSize(); cellPosition++) {
            ArrayList<String> line = new ArrayList<>();
            for (int horizontalLineNumber = 0; horizontalLineNumber < grid.getSize(); horizontalLineNumber++) {
                line.add(rows(grid).get(horizontalLineNumber).get(cellPosition));
            }
            columns.add(line);
        }
        return columns;
    }

    private List<ArrayList<String>> diagonalLines(Grid grid) {
       List<ArrayList<String>> diagonalLines = new ArrayList<>();
       diagonalLines.add(firstDiagonalLine(grid));
       diagonalLines.add(secondDiagonalLine(grid));
       return diagonalLines;
    }

    private ArrayList<String> firstDiagonalLine(Grid grid) {
        ArrayList<String> diagonalLine = new ArrayList<>();
        int cellNumber = 0;
        for (int rowNumber = 0; rowNumber < grid.getSize(); rowNumber++) {
            diagonalLine.add(rows(grid).get(rowNumber).get(cellNumber));
            cellNumber += 1;
        }
        return diagonalLine;
    }

    private ArrayList<String> secondDiagonalLine(Grid grid) {
        ArrayList<String> diagonalLine = new ArrayList<>();
        int cellNumber = grid.getSize() - 1;
        for (int rowNumber = 0; rowNumber < grid.getSize(); rowNumber++) {
            diagonalLine.add(rows(grid).get(rowNumber).get(cellNumber));
            cellNumber -= 1;
        }
        return diagonalLine;
    }
}
