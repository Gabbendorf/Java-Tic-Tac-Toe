package ticTacToe.grid;

import java.util.ArrayList;
import java.util.List;

public class Rows {

    private final Grid grid;
    private final int gridSize;

    public Rows(Grid grid) {
        this.grid = grid;
        this.gridSize = grid.getSize();
    }

    public boolean isWinning() {
        return !winningRow().isEmpty();
    }

    public String winningMark() {
        return (String) winningRow().get(0).get(0);
    }


    public List<ArrayList<String>> slicedRows() {
        return horizontalRows();
    }

    private List<List> winningRow() {
        List<List> winningRow = new ArrayList<>();
        for (List row : allRows()) {
            if ((row.stream().distinct().limit(2).count() <= 1)) {
                winningRow.add(row);
            }
        }
        return winningRow;
    }

    private List<ArrayList<String>> allRows() {
       List<ArrayList<String>> allRows = new ArrayList<>();
       addEachRow(horizontalRows(), allRows);
       addEachRow(verticalRows(), allRows);
       addEachRow(diagonalRows(), allRows);
       return allRows;
    }

    private void addEachRow(List<ArrayList<String>> rows, List allRows) {
        allRows.addAll(rows);
    }

    private List<ArrayList<String>> horizontalRows() {
        List<ArrayList<String>> horizontalRows = new ArrayList<>();
        for (int i = 0; i < gridSize; i++){
            ArrayList<String> line = sliceArray(grid.getCells(), startingCellPosition(i), finalCellPosition(i));
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

    private int startingCellPosition(int i) {
        return i * gridSize;
    }

    private int finalCellPosition(int i) {
        return  (i + 1) * gridSize - 1;
    }

    private List<ArrayList<String>> verticalRows() {
        List<ArrayList<String>> verticalRows = new ArrayList<>();
        for (int cellPosition = 0; cellPosition < gridSize; cellPosition++) {
            ArrayList<String> row = new ArrayList<>();
            for (int horizontalRowNumber = 0; horizontalRowNumber < gridSize; horizontalRowNumber++) {
                row.add(slicedRows().get(horizontalRowNumber).get(cellPosition));
            }
            verticalRows.add(row);
        }
        return verticalRows;
    }

    private List<ArrayList<String>> diagonalRows() {
       List<ArrayList<String>> diagonalRows = new ArrayList<>();
       diagonalRows.add(firstDiagonalRow());
       diagonalRows.add(secondDiagonalRow());
       return diagonalRows;
    }

    private ArrayList<String> firstDiagonalRow() {
        ArrayList<String> row = new ArrayList<>();
        int cellNumber = 0;
        for (int rowNumber = 0; rowNumber < gridSize; rowNumber++) {
            row.add(slicedRows().get(rowNumber).get(cellNumber));
            cellNumber += 1;
        }
        return row;
    }

    private ArrayList<String> secondDiagonalRow() {
        ArrayList<String> row = new ArrayList<>();
        int cellNumber = gridSize - 1;
        for (int rowNumber = 0; rowNumber < gridSize; rowNumber++) {
            row.add(slicedRows().get(rowNumber).get(cellNumber));
            cellNumber -= 1;
        }
        return row;
    }
}
