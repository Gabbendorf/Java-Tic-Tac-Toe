package ticTacToe.grid;

import ticTacToe.game.Rules;

import java.util.ArrayList;
import java.util.List;

public class GridPreparer {

    private static final String NEW_LINE = "\n";
    private static final String LINE = "_________";

    public String prepareGridForPrinting(Rules rules, Grid grid) {
        List<ArrayList<String>> rowsToPrint = rules.slicedRows(grid);
        List<String> printableList = new ArrayList<>();
        printableList.add(NEW_LINE);
        for (List<String> row : rowsToPrint) {
            printableList.addAll(row);
            printableList.add(NEW_LINE);
            printableList.add(LINE);
            printableList.add(NEW_LINE);
        }
        return String.join(" | ", printableList);
    }
}
