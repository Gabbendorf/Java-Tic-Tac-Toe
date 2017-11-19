package ticTacToe.game;

import ticTacToe.grid.Grid;

import java.util.ArrayList;
import java.util.List;

public class Rules {

    public boolean isWinning(Grid grid) {
        return !winningRow(grid).isEmpty();
    }

    public String winningMark(Grid grid) {
        return (String) winningRow(grid).get(0).get(0);
    }


    public List<ArrayList<String>> slicedRows(Grid grid) {
        return horizontalRows(grid);
    }

    private List<List> winningRow(Grid grid) {
        List<List> winningRow = new ArrayList<>();
        for (List row : allRows(grid)) {
            if ((row.stream().distinct().limit(2).count() <= 1)) {
                winningRow.add(row);
            }
        }
        return winningRow;
    }

    private List<ArrayList<String>> allRows(Grid grid) {
       List<ArrayList<String>> allRows = new ArrayList<>();
       addEachRow(horizontalRows(grid), allRows);
       addEachRow(verticalRows(grid), allRows);
       addEachRow(diagonalRows(grid), allRows);
       return allRows;
    }

    private void addEachRow(List<ArrayList<String>> rows, List<ArrayList<String>> allRows) {
        allRows.addAll(rows);
    }

    private List<ArrayList<String>> horizontalRows(Grid grid) {
        List<ArrayList<String>> horizontalRows = new ArrayList<>();
        for (int i = 0; i < grid.getSize(); i++){
            ArrayList<String> line = sliceArray(grid.getCells(), startingCellPosition(i, grid), finalCellPosition(i, grid));
            horizontalRows.add(line);
        }
        return horizontalRows;
    }

    private ArrayList<String> sliceArray(ArrayList<String> cellsList, int startingPoint, int finalPoint) {
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

    private List<ArrayList<String>> verticalRows(Grid grid) {
        List<ArrayList<String>> verticalRows = new ArrayList<>();
        for (int cellPosition = 0; cellPosition < grid.getSize(); cellPosition++) {
            ArrayList<String> row = new ArrayList<>();
            for (int horizontalRowNumber = 0; horizontalRowNumber < grid.getSize(); horizontalRowNumber++) {
                row.add(slicedRows(grid).get(horizontalRowNumber).get(cellPosition));
            }
            verticalRows.add(row);
        }
        return verticalRows;
    }

    private List<ArrayList<String>> diagonalRows(Grid grid) {
       List<ArrayList<String>> diagonalRows = new ArrayList<>();
       diagonalRows.add(firstDiagonalRow(grid));
       diagonalRows.add(secondDiagonalRow(grid));
       return diagonalRows;
    }

    private ArrayList<String> firstDiagonalRow(Grid grid) {
        ArrayList<String> row = new ArrayList<>();
        int cellNumber = 0;
        for (int rowNumber = 0; rowNumber < grid.getSize(); rowNumber++) {
            row.add(slicedRows(grid).get(rowNumber).get(cellNumber));
            cellNumber += 1;
        }
        return row;
    }

    private ArrayList<String> secondDiagonalRow(Grid grid) {
        ArrayList<String> row = new ArrayList<>();
        int cellNumber = grid.getSize() - 1;
        for (int rowNumber = 0; rowNumber < grid.getSize(); rowNumber++) {
            row.add(slicedRows(grid).get(rowNumber).get(cellNumber));
            cellNumber -= 1;
        }
        return row;
    }
}
