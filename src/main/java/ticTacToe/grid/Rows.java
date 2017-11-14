package ticTacToe.grid;

import java.util.ArrayList;
import java.util.List;

public class Rows {

    private final ArrayList<String> gridCells;

    public Rows(Grid grid) {
        this.gridCells = grid.getCells();
    }

    public boolean isWinning(int gridSize) {
        if (!winningRow(gridSize).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public String winningMark(int gridSize) {
        return (String) winningRow(gridSize).get(0).get(0);
    }

    public List<ArrayList<String>> allRows(int gridSize) {
       List<ArrayList<String>> allRows = new ArrayList<>();
       addEachRow(horizontalRows(gridSize), allRows);
       addEachRow(verticalRows(gridSize), allRows);
       addEachRow(diagonalRows(gridSize), allRows);
       return allRows;
    }

    public List<ArrayList<String>> horizontalRows(int gridSize) {
        List<ArrayList<String>> horizontalRows = new ArrayList<>();
        for (int i = 0; i < gridSize; i++){
            ArrayList<String> line = sliceArray(gridCells, startingCellPosition(i, gridSize), finalCellPosition(i, gridSize));
            horizontalRows.add(line);
        }
        return horizontalRows;
    }

    private List<ArrayList<String>> verticalRows(int gridSize) {
        List<ArrayList<String>> verticalRows = new ArrayList<>();
        for (int cellPosition = 0; cellPosition < gridSize; cellPosition++) {
            ArrayList<String> row = new ArrayList<>();
            for (int horizontalRowNumber = 0; horizontalRowNumber < gridSize; horizontalRowNumber++) {
                row.add(slicedRows(gridSize).get(horizontalRowNumber).get(cellPosition));
            }
            verticalRows.add(row);
        }
        return verticalRows;
    }

    private List<ArrayList<String>> diagonalRows(int gridSize) {
       List<ArrayList<String>> diagonalRows = new ArrayList<>();
       diagonalRows.add(firstDiagonalRow(gridSize));
       diagonalRows.add(secondDiagonalRow(gridSize));
       return diagonalRows;
    }

    private List<List> winningRow(int gridSize) {
        List winningRow = new ArrayList<>();
        for (List row : allRows(gridSize)) {
            if ((row.stream().distinct().limit(2).count() <= 1) == true) {
                winningRow.add(row);
            }
        }
        return winningRow;
    }

    private void addEachRow(List<ArrayList<String>> rows, List allRows) {
       for (List row : rows) {
           allRows.add(row);
       }
    }

    private ArrayList<String> sliceArray(ArrayList<String> cellsList, int startingPoint, int finalPoint) {
        ArrayList<String> row = new ArrayList<>();
        for (int i = startingPoint; i <= finalPoint; i++) {
            row.add(cellsList.get(i));
        }
        return row;
    }

    private int startingCellPosition(int i, int gridSize) {
        return i * gridSize;
    }

    private int finalCellPosition(int i, int gridSize) {
        return  (i + 1) * gridSize - 1;
    }

    private ArrayList<String> firstDiagonalRow(int gridSize) {
        ArrayList<String> row = new ArrayList<>();
        int cellNumber = 0;
        for (int rowNumber = 0; rowNumber < gridSize; rowNumber++) {
            row.add(slicedRows(gridSize).get(rowNumber).get(cellNumber));
            cellNumber += 1;
        }
        return row;
    }

    private ArrayList<String> secondDiagonalRow(int gridSize) {
        ArrayList<String> row = new ArrayList<>();
        int cellNumber = gridSize - 1;
        for (int rowNumber = 0; rowNumber < gridSize; rowNumber++) {
            row.add(slicedRows(gridSize).get(rowNumber).get(cellNumber));
            cellNumber -= 1;
        }
        return row;
    }

    private List<ArrayList<String>> slicedRows(int gridSize) {
        return horizontalRows(gridSize);
    }
}
