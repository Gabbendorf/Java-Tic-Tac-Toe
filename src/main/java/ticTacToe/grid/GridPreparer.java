package ticTacToe.grid;

import java.util.ArrayList;
import java.util.List;

import static ticTacToe.game.Mark.*;

public class GridPreparer {

    private static final String NEW_LINE = "\n";
    private static final String LINE = "---------";


    public String prepareGridForPrinting(List<ArrayList<String>> rows) {
        List<String> printableList = new ArrayList<>();
        printableList.add(NEW_LINE);
        printableList.add(LINE);
        printableList.add(NEW_LINE);
        for (List<String> row : rows) {
            printableList.addAll(row);
            printableList.add(NEW_LINE);
            printableList.add(LINE);
            printableList.add(NEW_LINE);
        }
        return String.join(" | ", printableList);
    }

    public List<ArrayList<String>> rowsWithColours(List<ArrayList<String>> rows) {
        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                String coloredCell = addColorTo(row.get(i));
                row.set(i, coloredCell);
            }
        }
        return rows;
    }

    private String addColorTo(String cell) {
        String colouredMark;
        if (cell.equals(CROSS.sign)) {
            colouredMark = BLUE_X.sign;
        } else if (cell.equals(NOUGHT.sign)){
            colouredMark = RED_O.sign;
        } else {
            colouredMark = cell;
        }
        return colouredMark;
    }
}
