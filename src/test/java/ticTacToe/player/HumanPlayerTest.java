package ticTacToe.player;

import org.junit.Test;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Rows;
import ticTacToe.ui.DoubleUi;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {

    @Test
    public void returnsGridPosition() {
        Grid grid = new Grid(3);
        Rows rows = new Rows(grid);
        DoubleUi doubleUi = new DoubleUi(null, null);
        HumanPlayer humanPlayer = new HumanPlayer("X");

        String positionChosen = humanPlayer.makeMove(doubleUi, grid, rows);

        assertEquals("1", positionChosen);
    }
}
