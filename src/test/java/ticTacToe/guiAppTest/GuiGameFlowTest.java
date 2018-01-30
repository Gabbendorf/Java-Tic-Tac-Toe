package ticTacToe.guiAppTest;

import org.junit.Before;
import org.junit.Test;
import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;
import ticTacToe.guiApp.GuiGameFlow;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ticTacToe.game.Mark.CROSS;

public class GuiGameFlowTest {

    private static Grid emptyGrid;
    private Grid winningGrid;
    private Grid drawGrid;

    @Before
    public void gridInstantiations() {
        emptyGrid = new Grid(3);
        winningGrid = new Grid(Arrays.asList("X", "X", "X"));
        drawGrid = new Grid(Arrays.asList("X", "O", "X", "X", "O", "O", "O", "X", "X"));
    }

    @Test
    public void addsNextMarkToGrid() {
        GuiGameFlow gameFlow = newGuiGameFlow(emptyGrid);
        String startingMark = "X";

        gameFlow.move("1");

        assertEquals(startingMark, emptyGrid.getCells().get(0));
    }

    @Test
    public void returnsMarkToSetAsTextForButton() {
        GuiGameFlow gameFlow = newGuiGameFlow(emptyGrid);

        Mark markForButton = gameFlow.move("1");

        assertEquals(CROSS, markForButton);
    }

    @Test
    public void returnsTrueForNotFinishedGame() {
        GuiGameFlow gameFlow = newGuiGameFlow(emptyGrid);

        assertTrue(gameFlow.isNotGameOver());
    }

    @Test
    public void returnsFalseForWinningGridState() {
        GuiGameFlow gameFlow = newGuiGameFlow(winningGrid);

        assertFalse(gameFlow.isNotGameOver());
    }

    @Test
    public void returnsFalseForDrawGridState() {
        GuiGameFlow gameFlow = newGuiGameFlow(drawGrid);

        assertFalse(gameFlow.isNotGameOver());
    }

    @Test
    public void returnsMessageForOnGoingGame() {
        GuiGameFlow gameFlow = newGuiGameFlow(emptyGrid);

        String message = gameFlow.messageForGameState();

        assertEquals(message, "It's X turn");
    }

    @Test
    public void returnsMessageForWinner() {
        GuiGameFlow gameFlow = newGuiGameFlow(winningGrid);

        String message = gameFlow.messageForGameState();

        assertEquals(message, "X won!");
    }

    @Test
    public void returnsMessageForDrawGame() {
        GuiGameFlow gameFlow = newGuiGameFlow(drawGrid);

        String message = gameFlow.messageForGameState();

        assertEquals(message, "It's draw!");
    }

    private GuiGameFlow newGuiGameFlow(Grid grid) {
        Lines lines = new Lines();
        return new GuiGameFlow(grid, lines);
    }
}
