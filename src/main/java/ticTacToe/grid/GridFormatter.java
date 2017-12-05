package ticTacToe.grid;

import java.util.ArrayList;
import java.util.List;

import static ticTacToe.game.Mark.*;

public class GridFormatter {

    private static final String NEW_LINE = "\n";
    public static final String red_O = "\u001B[31mO\u001b[0m";
    public static final String blue_X = "\u001b[34mX\u001b[0m";

    public String prepareGridForPrinting(List<ArrayList<String>> rows, int gridSize) {
        List<String> printableList = new ArrayList<>();
        printableList.add(NEW_LINE);
        printableList.add(line(gridSize));
        printableList.add(NEW_LINE);
        for (List<String> row : rows) {
            printableList.addAll(row);
            printableList.add(NEW_LINE);
            printableList.add(line(gridSize));
            printableList.add(NEW_LINE);
        }
        return String.join( "| ", printableList);
    }

    public List<ArrayList<String>> rowsWithColours(List<ArrayList<String>> rows) {
        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                String colouredCell = addColourTo(row.get(i));
                row.set(i, colouredCell);
            }
        }
        return rows;
    }

    private String line(int gridSize) {
        if (gridSize == 3) {
            return "--------- ";
        } else {
            return "------------- ";
        }
    }

    private String addColourTo(String cell) {
        String colouredMark;
        if (cell.equals(CROSS.sign)) {
            colouredMark = blue_X;
        } else if (cell.equals(NOUGHT.sign)){
            colouredMark = red_O;
        } else {
            colouredMark = cell;
        }
        return String.format(justifyPattern(colouredMark, cell), colouredMark);
    }

    private String justifyPattern(String colouredMark, String cell) {
        Integer justifiedCellLength = colouredMark.length() - cell.length() + 2;
        return "%-" + justifiedCellLength.toString() + "s";
    }
}
