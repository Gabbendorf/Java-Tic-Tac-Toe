package ticTacToe.player;

import org.junit.Test;
import ticTacToe.grid.Grid;
import ticTacToe.game.Lines;
import ticTacToe.ui.UiDouble;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {

    @Test
    public void returnsGridPosition() {
        Grid grid = new Grid(3);
        Lines lines = new Lines();
        UiDouble uiDouble = new UiDouble(null, null);
        HumanPlayer humanPlayer = new HumanPlayer("X");

        String positionChosen = humanPlayer.makeMove(uiDouble, grid, lines);

        assertEquals("1", positionChosen);
    }
}
