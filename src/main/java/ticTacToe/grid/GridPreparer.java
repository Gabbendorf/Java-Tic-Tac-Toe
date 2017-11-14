package ticTacToe.grid;

import java.util.ArrayList;
import java.util.List;

public class GridPreparer {

    static final String NEW_LINE = "\n";
    static final String LINE = "_________";

    public String prepareGridForPrinting(Rows rows, int gridSize) {
        List<ArrayList<String>> rowsToPrint = rows.horizontalRows(gridSize);
        List<String> printableList = new ArrayList<>();
        printableList.add(NEW_LINE);
        for (List row : rowsToPrint) {
            List<String> eachRow = row;
            for (String numberPosition : eachRow) {
                printableList.add(numberPosition);
            }
            printableList.add(NEW_LINE);
            printableList.add(LINE);
            printableList.add(NEW_LINE);
        }
        String joinedGrid = String.join(" | ", printableList);
        return joinedGrid;
    }
}
