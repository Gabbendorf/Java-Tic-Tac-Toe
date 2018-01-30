package ticTacToe.guiAppTest;

import org.junit.Test;
import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;
import ticTacToe.guiApp.GuiGameFlow;

import static org.junit.Assert.assertEquals;
import static ticTacToe.game.Mark.CROSS;

public class GuiGameFlowTest {

    @Test
    public void addsNextMarkToGridAndReturnsMarkToSetAsTextForButton() {
        Grid grid = new Grid(3);
        GuiGameFlow gameFlow = new GuiGameFlow(grid, new Lines());
        String nextMove = "X";

        Mark markForButton = gameFlow.move("1");

        assertEquals(nextMove, grid.getCells().get(0));
        assertEquals(CROSS, markForButton);
    }
}
