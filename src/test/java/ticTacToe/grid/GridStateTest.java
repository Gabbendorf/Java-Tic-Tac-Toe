package ticTacToe.grid;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class GridStateTest {

    private static Grid grid;
    private static GridState gridState;

    @BeforeClass
    public static void newGridState() {
        grid = new Grid(3);
        gridState = new GridState(grid);
    }

    @Test
    public void createsHorizontalRows() {
        String[] firstRow = new String[]{"1","2","3"};
        String[] secondRow = new String[]{"4", "5", "6"};
        String[] thirdRow = new String[]{"7", "8", "9"};

        assertEquals(rows(firstRow, secondRow, thirdRow), gridState.horizontalRows(3));
    }

    @Test
    public void createsVerticalRows() {
        String[] firstRow = new String[]{"1", "4", "7"};
        String[] secondRow = new String[]{"2", "5", "8"};
        String[] thirdRow = new String[]{"3", "6", "9"};

        assertEquals(rows(firstRow, secondRow, thirdRow), gridState.verticalRows(3));
    }

    @Test
    public void listOfDiagonalRows() {
        ArrayList<List> rows = new ArrayList<>();
        rows.add(asList(new String[]{"1", "5", "9"}));
        rows.add(asList(new String[]{"3", "5", "7"}));

        assertEquals(rows, gridState.diagonalRows(3));
    }

    @Test
    public void listOfAllRows() {
        assertEquals(8, gridState.allRows(3).size());
    }

    private List<List> rows(String[] firstRow, String[] secondRow, String[] thirdRow) {
        ArrayList<List> rows = new ArrayList<>();
        rows.add(asList(firstRow));
        rows.add(asList(secondRow));
        rows.add(asList(thirdRow));
        return rows;
    }
}
