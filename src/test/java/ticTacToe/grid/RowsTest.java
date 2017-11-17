package ticTacToe.grid;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RowsTest {

    private static Grid grid;
    private static Rows rows;

    @Before
    public void newGridState() {
        grid = new Grid(3);
        rows = new Rows(grid);
    }

    @Test
    public void returnsTrueForWinningRow() {
        Grid grid = new Grid(new ArrayList<>(Arrays.asList("X", "2", "3", "4", "X", "6", "7", "8", "X")));
        Rows rows = new Rows(grid);

        assertTrue(rows.isWinning());
    }

    @Test
    public void returnsFalseForNotWinningRow() {
        assertFalse(rows.isWinning());
    }

    @Test
    public void returnsWinningMark() {
        Grid grid = new Grid(new ArrayList<>(Arrays.asList("X", "2", "3", "4", "X", "6", "7", "8", "X")));
        Rows rows = new Rows(grid);

        assertEquals("X", rows.winningMark());
    }

    @Test
    public void createsHorizontalRows() {
        String[] firstRow = new String[]{"1","2","3"};
        String[] secondRow = new String[]{"4", "5", "6"};
        String[] thirdRow = new String[]{"7", "8", "9"};

        assertEquals(rows(firstRow, secondRow, thirdRow), rows.slicedRows());
    }

    private List<List> rows(String[] firstRow, String[] secondRow, String[] thirdRow) {
        ArrayList<List> rows = new ArrayList<>();
        rows.add(asList(firstRow));
        rows.add(asList(secondRow));
        rows.add(asList(thirdRow));
        return rows;
    }
}
